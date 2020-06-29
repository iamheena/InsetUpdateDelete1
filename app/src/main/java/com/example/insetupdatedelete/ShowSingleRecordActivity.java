package com.example.insetupdatedelete;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSingleRecordActivity extends AppCompatActivity {
    String IDholder;

    TextView id, name, sirname;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    Button Delete, Edit;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_single_item);

        id = (TextView) findViewById(R.id.textViewID);
        name = (TextView) findViewById(R.id.textViewName);
        sirname = (TextView) findViewById(R.id.textViewPhoneNumber);

        Delete = (Button) findViewById(R.id.buttonDelete);
        Edit = (Button) findViewById(R.id.buttonEdit);

        sqLiteHelper = new SQLiteHelper(this);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "DELETE FROM "+SQLiteHelper.TABLE_NAME+" WHERE id = "+IDholder+"";

                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();

                finish();
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditSingleRecord.class);

                intent.putExtra("EditID", IDholder);

                startActivity(intent);
            }
        });
    }
    protected void onResume() {

        ShowSingleRecordInTextView();

        super.onResume();
    }

    public void ShowSingleRecordInTextView() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("ListViewClickedItemValue");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                id.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Column_1)));
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
