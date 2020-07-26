package com.example.bittercart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String FILE_NAME = "Test.csv";
    ArrayList<ShoppingList> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static EditText item1 = null;
    public static EditText item2 = null;
    public static EditText item3 = null;
    public static EditText item4 = null;
    public static EditText item5 = null;

    public static EditText price1;
    public static EditText price2;
    public static EditText price3;
    public static EditText price4;
    public static EditText price5;

    public static String quantity1 = "0";
    public static String quantity2 = "0";
    public static String quantity3 = "0";
    public static String quantity4 = "0";
    public static String quantity5 = "0";

    private String currentDateTimeString;
    private String Shoppinglist;
    private int subTotal;

    public static int newPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Shoppinglist = "Shopping List";
        loadData();
        buildRecyclerView();
        checkEmpty();
        setInsertButton();
        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ShoppingList>>() {}.getType();
        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }
    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                showMessage("Delete" + newPosition);
                removeItem(newPosition);
                saveData();
            }
        }
        ).attachToRecyclerView(mRecyclerView);

    }
    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(this);
    }
    private void insertItem(String line2) {
        generateTime();
        mExampleList.add(new ShoppingList(Shoppinglist,"time",line2));
        mAdapter.notifyItemInserted(mExampleList.size());
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeActivity() {
        Intent intent = new Intent(getApplicationContext(),AddEntry.class);
        startActivity(intent);
    }

    public void checkEmpty() {
            String temp = quantity1 + item1.getText().toString()+price1.getText().toString();
            String temp2 = quantity2 + item2.getText().toString()+price2.getText().toString();
            String temp3 = quantity3 + item3.getText().toString()+price3.getText().toString();
            String temp4 = quantity4 + item4.getText().toString()+price4.getText().toString();
            String temp5 = quantity5 + item5.getText().toString()+price5.getText().toString();

            if(item2.getText().toString().isEmpty() && item3.getText().toString().isEmpty() && item4.getText().toString().isEmpty() && item5.getText().toString().isEmpty())
            {
                showMessage("condition 1");
            }
            //insertItem(temp + "\n" + temp2 + "\n" + temp3 + "\n" +temp4 + "\n"+temp5);

            saveData();

    }

    public void calculateSubTotal() {
       /** int p1 = Integer.parseInt(price1.getText().toString());
        int q1 = Integer.parseInt(quantity1.getBytes().toString());
        int p2 =Integer.parseInt(price1.getText().toString());
        int p3 =Integer.parseInt(price1.getText().toString());
        int p4=Integer.parseInt(price1.getText().toString());;
        int p5=Integer.parseInt(price1.getText().toString());;

        showMessage((p1*q1)+"Element 1");
        subTotal = 0;
        */
    }

    public void showMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }

    public void generateTime() {
        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
    }

    public void generateFile() throws IOException {
        FileOutputStream fos = null;

        String Beginning = "Title,Date/Time,Description"+"\n";
        String storagelocation = "";
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(Beginning.getBytes());
            for(int o=0;o<mExampleList.size();o++)
            {
                String lin1 = mExampleList.get(o).getLine1();
                String lin2 = mExampleList.get(o).getLine2();
                String lin3 = mExampleList.get(o).getLine3();
                String combined = lin1 + "," + lin2 +","+ lin3+"\n";
                fos.write(combined.getBytes());
            }

            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save:
                changeActivity();
                break;
            case R.id.button_insert:
                showMessage("Button Insert Pressed");
                try {
                    generateFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}