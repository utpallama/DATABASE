package com.example.ma18uus.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //STEP 1, define all the varibales
    private static final String TAG = "MainActivity";
    DatabaseHelper DatabaseHelper;
    private Button Addbtn, Listbtn;
    private EditText Clothesbtn, Weatherbtn, Prefrencebtn, Tagbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //STEP 2, do on create for all the variables you have defined
        //this links your code to the activity_main_xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Clothesbtn = (EditText) findViewById(R.id.Clothesbtn);
        Weatherbtn = (EditText) findViewById(R.id.Weatherbtn);
        Prefrencebtn = (EditText) findViewById(R.id.Prefrencebtn);
        Tagbtn = (EditText) findViewById(R.id.Tagbtn);
        Addbtn = (Button) findViewById(R.id.Addbtn);
        Listbtn = (Button) findViewById(R.id.Listbtn);
        DatabaseHelper = new DatabaseHelper(this);


        //STEP 6
        //declaring some buttons, so when you click on Addbtn, these editText would be added to sql
        //This is our Addbtn
        Addbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String newValue = Clothesbtn.getText().toString();
                String newValue2 = Weatherbtn.getText().toString();
                String newValue3 = Prefrencebtn.getText().toString();
                String newValue4 = Tagbtn.getText().toString();

                //make sure the name isn't null before someone tries to add it to the database
                //if EditText is not equal to 0, then add it to the database
                if ((Clothesbtn.length() != 0) || (Weatherbtn.length() != 0) || (Prefrencebtn.length() != 0) || (Tagbtn.length() != 0)) {
                    AddData(newValue);
                    AddData(newValue2);
                    AddData(newValue3);
                    AddData(newValue4);
                    Clothesbtn.setText("");             //resets the text
                    Weatherbtn.setText("");
                    Prefrencebtn.setText("");
                    Tagbtn.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });


        //STEP 7
        //button which navigates us to the List view
        //this will let us go to another Class, which is called ListingDataActivity
        //so when you click on Listbtn, it will show you the list of all the fields you have created
        Listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListingDataActivity.class);
                startActivity(intent);
            }
        });




    }



    //STEP 5
    //create adddata method for adding data to database
    //Adddata method is going to take string, and add it to database
    public void AddData(String newEntry) {
        //we know that if insetData is false its not going to be inserted correctly
        //also calling the addData method from DatabaseHelper
        boolean insertDataInDB = DatabaseHelper.addData(newEntry);

        if (insertDataInDB) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }

    }





    //STEP 4
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){

        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

    }





}
