package com.example.insetupdatedelete;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditSingleRecord extends AppCompatActivity {
    EditText name, sirname;
    Button update;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String IDholder;
    String SQLiteDataBaseQueryHolder ;
    SQLiteDatabase sqLiteDatabaseObj;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_single_item);

        name = (EditText) findViewById(R.id.EditTextName);
        sirname = (EditText) findViewById(R.id.editText3);

        update = (Button) findViewById(R.id.buttonUpdate);

        sqLiteHelper = new SQLiteHelper(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String GetName = name.getText().toString();
                String GetSirname = sirname.getText().toString();

                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "UPDATE " + SQLiteHelper.TABLE_NAME + " SET "+SQLiteHelper.Column_2+" = '"+GetName+"' , "+SQLiteHelper.Column_3+" = '"+GetSirname+"' WHERE id = " + IDholder + "";

                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();

                Toast.makeText(EditSingleRecord.this,"Data Edit Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
    protected void onResume() {

        ShowSRecordInEditText();

        super.onResume();
    }

    public void ShowSRecordInEditText() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("EditID");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                name.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Column_2)));

                sirname.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Column_3)));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

}
