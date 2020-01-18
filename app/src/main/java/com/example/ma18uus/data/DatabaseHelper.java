package com.example.ma18uus.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ma18uus on 18/01/2020.
 */


//STEP 3
public class DatabaseHelper extends SQLiteOpenHelper {

    //creating tag, declaring table name and coloumn name

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "Clothes_table";
    private static final String CULUM0 = "ID";
    private static final String CULUM1 = "name";




    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    //were gonna create a table here
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CULUM1 +" TEXT)";
        sqLiteDatabase.execSQL(createTable);

    }



    //were gonna execute an sql statement drop if table exist and put our table name
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }



    ///creating a method were we are adding data to database
    public boolean addData(String item) {
        //1) create sqlite databse object first
        SQLiteDatabase db = this.getWritableDatabase();
        //2) this is gonna help us write to the database
        ContentValues contentValues = new ContentValues();
        //3) add first value to content value
        contentValues.put(CULUM1, item);
        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        //were gonna create a long varibale to see if data is inserted correctly or not
        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            //when we are inserting data we are returning a boolean to MainActivity STEP 5 method
            return true;
        }

    }





        //STEP 13
    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        //need sqlite database object and cursor object
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }




    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + CULUM1 + " FROM " + TABLE_NAME +
                " WHERE " + CULUM1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + CULUM1 +
                " = '" + newName + "' WHERE " + CULUM1 + " = '" + id + "'" +
                " AND " + CULUM1 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }



    /**
     * Delete from database
     * @param id
     * @param name
     */

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + CULUM1 + " = '" + id + "'" +
                " AND " + CULUM1 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}

