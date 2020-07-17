package com.example.bittercart;

public class ShoppingList {
    private String mLine1;
    private String mLine2;
    private String mLine3;
    private boolean expandable;
    public ShoppingList(String line1, String line2, String line3) {
        mLine1 = line1;
        mLine2 = line2;
        mLine3 = line3;
        expandable = false;
    }
    public String getLine1() {
        return mLine1;
    }
    public String getLine2() {
        return mLine2;
    }
    public String getLine3()
    {
        return mLine3;
    }

    public void setExpandable(boolean expand)
    {
        expandable = expand;
    }

    public boolean isExpandable()
    {
        return expandable;
    }

}
