package com.example.ma18uus.data;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ma18uus on 18/01/2020.
 */

//THIS CLASS LETS USE SEE THE STORED DATA, WE CAN BASICALLY VIEW IT
//STEP 8
public class ListingDataActivity extends AppCompatActivity {


    //STEP 10
    //we need tag, database helper and list view
    private static final String TAG = "ListDataActivity";
    DatabaseHelper DatabaseHelper;
    private ListView ListView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdata_layout);




        //STEP 11
        //declaring all those oncreate
        ListView = (ListView) findViewById(R.id.listView);
        DatabaseHelper = new DatabaseHelper(this);

        //STEP 12
        //this will display the data in the List view
        populateListView();

    }


    //STEP 14
    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        //get the data and append to a list
        //gonna return all the rows from SELECT * STATAEMENT IN DatabaseHelper(the cursor method)
        Cursor data = DatabaseHelper.getData();
        //we create and arraylist, loop through all the rows of the query and get the data
        ArrayList<String> listingData = new ArrayList<>();
        //gonna iterate through each one of the rows
        while(data.moveToNext()){
            //as it iterates through the rows, we add data to the list
            //get the value from the database in column 1
            //then add it to the ArrayList
            //gets string at coloumn one, in which case
            listingData.add(data.getString(1));
        }

        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listingData);
        ListView.setAdapter(adapter);


        //set an onItemClickListener to the ListView

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);
                Cursor data = DatabaseHelper.getItemID(name); //get the id associated with that name
                int items_ID = -1;
                while(data.moveToNext()){
                    items_ID = data.getInt(0);
                }

                if(items_ID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + items_ID);
                    Intent editScreenIntent = new Intent(ListingDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",items_ID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }





    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


}
