package com.example.bittercart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEntry extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button addButton;

    private EditText item1;
    private EditText item2;
    private EditText item3;
    private EditText item4;
    private EditText item5;

    private EditText price1;
    private EditText price2;
    private EditText price3;
    private EditText price4;
    private EditText price5;

    private Button plus1;
    private Button plus2;
    private Button plus3;
    private Button plus4;

    private Spinner spin1;
    private Spinner spin2;
    private Spinner spin3;
    private Spinner spin4;
    private Spinner spin5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        setupButtons();
        setupEditTexts();
        setupSpinners();
    }

    public void setupButtons()
    {
        addButton = findViewById(R.id.add);

        plus1 = findViewById(R.id.button1);
        plus2 = findViewById(R.id.button2);
        plus3 = findViewById(R.id.button3);
        plus4 = findViewById(R.id.button4);

        addButton.setOnClickListener(this);
        plus1.setOnClickListener(this);
        plus2.setOnClickListener(this);
        plus3.setOnClickListener(this);
        plus4.setOnClickListener(this);

    }

    private void setupEditTexts()
    {
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);

        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        price3 = findViewById(R.id.price3);
        price4 = findViewById(R.id.price4);
        price5 = findViewById(R.id.price5);
    }

    private void setupSpinners()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1 = findViewById(R.id.spin1);
        spin2 = findViewById(R.id.spin2);
        spin3 = findViewById(R.id.spin3);
        spin4 = findViewById(R.id.spin4);
        spin5 = findViewById(R.id.spin5);

        spin1.setAdapter(adapter);
        spin2.setAdapter(adapter);
        spin3.setAdapter(adapter);
        spin4.setAdapter(adapter);
        spin5.setAdapter(adapter);

        spin1.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        spin3.setOnItemSelectedListener(this);
        spin4.setOnItemSelectedListener(this);
        spin5.setOnItemSelectedListener(this);
    }

    public void showMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }

    private void setNewElement(int e)
    {
        if(e==1)
        {
            plus1.setVisibility(View.INVISIBLE);
            plus2.setVisibility(View.VISIBLE);
            item2.setVisibility(View.VISIBLE);
            price2.setVisibility(View.VISIBLE);
            spin2.setVisibility(View.VISIBLE);
        }
        else if(e==2)
        {
            plus2.setVisibility(View.INVISIBLE);
            plus3.setVisibility(View.VISIBLE);
            item3.setVisibility(View.VISIBLE);
            price3.setVisibility(View.VISIBLE);
            spin3.setVisibility(View.VISIBLE);
        }
        else if(e==3)
        {
            plus3.setVisibility(View.INVISIBLE);
            plus4.setVisibility(View.VISIBLE);
            item4.setVisibility(View.VISIBLE);
            price4.setVisibility(View.VISIBLE);
            spin4.setVisibility(View.VISIBLE);
        }
        else if(e==4)
        {
            plus4.setVisibility(View.INVISIBLE);
            item5.setVisibility(View.VISIBLE);
            price5.setVisibility(View.VISIBLE);
            spin5.setVisibility(View.VISIBLE);
        }
    }

    public void passItemOver()
    {
        MainActivity.item1 = item1;
        MainActivity.item2 = item2;
        MainActivity.item3 = item3;
        MainActivity.item4 = item4;
        MainActivity.item5 = item5;

        MainActivity.price1 = price1;
        MainActivity.price2 = price2;
        MainActivity.price3 = price3;
        MainActivity.price4 = price4;
        MainActivity.price5 = price5;

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.add:
                    passItemOver();
                    break;
                case R.id.button1:
                    setNewElement(1);
                    break;
                case R.id.button2:
                    setNewElement(2);
                    break;
                case R.id.button3:
                    setNewElement(3);
                    break;
                case R.id.button4:
                    setNewElement(4);
                    break;
            }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();

        switch(adapterView.getId())
        {
            case R.id.spin1:
                MainActivity.quantity1 = text;
                break;
            case R.id.spin2:
                MainActivity.quantity2 = text;
                break;
            case R.id.spin3:
                MainActivity.quantity3 = text;
                break;
            case R.id.spin4:
                MainActivity.quantity4 = text;
                break;
            case R.id.spin5:
                MainActivity.quantity5 = text;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}