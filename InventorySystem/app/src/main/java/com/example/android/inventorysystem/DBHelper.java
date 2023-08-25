package com.example.android.inventorysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Inventorydetails(itemName TEXT, itemDesc TEXT, price TEXT, itemId TEXT primary key )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Inventorydetails");
    }

    public Boolean addData(String itemName, String itemDesc, String price, String itemId){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName", itemName);
        contentValues.put("itemDesc", itemDesc);
        contentValues.put("price", price);
        contentValues.put("itemId", itemId);
        long result = DB.insert("Inventorydetails", null, contentValues);
        if (result==-1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateData(String itemName, String itemDesc, String price, String itemId){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName", itemName);
        contentValues.put("itemDesc", itemDesc);
        contentValues.put("price", price);
        contentValues.put("itemId", itemId);
        Cursor cursor = DB.rawQuery("Select * from Inventorydetails where itemId = ?", new String[]{itemId});
        if (cursor.getCount()>0) {
            long result = DB.update("Inventorydetails", contentValues, "itemId=?", new String[]{itemId});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteData(String itemId){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Inventorydetails where itemId = ?", new String[] {itemId});
        if (cursor.getCount()>0) {
            long result = DB.delete("Inventorydetails", "itemId=?", new String[]{itemId});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Inventorydetails", null);
        return cursor;
    }

}

