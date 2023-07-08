package com.example.uasmobileno2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.uasmobileno2.objects.Orders;
import com.example.uasmobileno2.objects.Products;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "OrderDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "myOrders";
    private static final String ID_COL = "id";
    private static final String ORDERITEM_COL = "name";
    private static final String ORDERPRICE_COL = "totalprice";
    private static final String ORDERAMOUNT_COL = "amount";
    private static final String ORDERTIME_COL = "date";


    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ORDERITEM_COL + " TEXT,"
                + ORDERPRICE_COL + " TEXT,"
                + ORDERAMOUNT_COL + " TEXT,"
                + ORDERTIME_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewOrder(ArrayList<Products> products){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String orderName = "";
        String orderAmount = "", orderPrice = "";

        for(int i = 0; i < products.size(); i++) {
            if(i != products.size() - 1) {
                orderName = orderName + products.get(i).getProductName() + ", ";
                orderPrice = orderPrice + String.valueOf(products.get(i).getProductPrice()) + ", ";
                orderAmount = orderAmount + String.valueOf(products.get(i).getAmount()) + ", ";
            }
            else {
                orderName = orderName + products.get(i).getProductName();
                orderPrice = orderPrice + String.valueOf(products.get(i).getProductPrice());
                orderAmount = orderAmount + String.valueOf(products.get(i).getAmount());
            }
        }

        values.put(ORDERITEM_COL, orderName);
        values.put(ORDERPRICE_COL, orderPrice);
        values.put(ORDERAMOUNT_COL, orderAmount);
        values.put(ORDERTIME_COL, getDate());

        db.insert(TABLE_NAME, null, values);

//        Log.d("orderName", "addNewOrder: " + orderName);
//        Log.d("orderPrice", "addNewOrder: " + orderPrice);
//        Log.d("orderAmount", "addNewOrder: " + orderAmount);
//        Log.d("orderDate", "addNewOrder: " + getDate());

        db.close();
    }

    public ArrayList<Orders> readOrders() {
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Orders> ordersList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.

//                Log.d("cursor.getString", "readOrders: index: 1: " + cursor.getString(1));
//                Log.d("cursor.getString", "readOrders: index: 2: " + cursor.getString(2));
//                Log.d("cursor.getString", "readOrders: index: 3: " + cursor.getString(3));
//                Log.d("cursor.getString", "readOrders: index: 4: " + cursor.getString(4));

                ordersList.add(new Orders(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();

        return ordersList;
    }

    public String getDate() {
        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String formattedDate = df.format(d);

        return formattedDate;
    }

    public void deleteOrder() {
        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.execSQL("delete from "+ TABLE_NAME);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
