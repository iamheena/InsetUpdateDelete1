package com.example.insetupdatedelete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    EditText name,sirname;
    Button add,display;
    Boolean EdittextEmptyHold;
    String nameHolder,sirnameHolder,SqldbHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=(Button)findViewById(R.id.btnadd);
        display=(Button)findViewById(R.id.btndisplay);
        name=(EditText)findViewById(R.id.edtname);
        sirname=(EditText)findViewById(R.id.edtsirname);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqldbBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                InsertDataIntoSQLiteDatabase();

                EmptyEditTextAfterDataInsert();

            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplaySQLiteDataActivity.class);
                startActivity(intent);
            }
        });
    }
    public  void SqldbBuild()
    {
        sqLiteDatabase=openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE,null);
    }
    public void SQLiteTableBuild(){

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+"("+SQLiteHelper.Column_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SQLiteHelper.Column_2+" VARCHAR, "+SQLiteHelper.Column_3+" VARCHAR);");

    }
        public void CheckEditTextStatus(){

        nameHolder = name.getText().toString() ;
        sirnameHolder = sirname.getText().toString();

        if(TextUtils.isEmpty(nameHolder) || TextUtils.isEmpty(sirnameHolder)){

            EdittextEmptyHold = false ;

        }
        else {

            EdittextEmptyHold = true ;
        }
    }
    public void InsertDataIntoSQLiteDatabase(){

        if(EdittextEmptyHold == true)
        {

           SqldbHolder  = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,sirname) VALUES('"+nameHolder+"', '"+sirnameHolder+"');";

            sqLiteDatabase.execSQL(SqldbHolder);

            sqLiteDatabase.close();

            Toast.makeText(MainActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }
    public void EmptyEditTextAfterDataInsert(){

        name.getText().clear();

        sirname.getText().clear();

    }
}