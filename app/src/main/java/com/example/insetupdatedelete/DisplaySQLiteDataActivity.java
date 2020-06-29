package com.example.insetupdatedelete;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DisplaySQLiteDataActivity extends AppCompatActivity {
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListView listView;
    ListAdapter listAdapter;
    RecyclerView recyclerView;
    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> Sirname_Array;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    String TempHolder ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_main);
       listView=(ListView)findViewById(R.id.list);

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        Sirname_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(getApplicationContext(),ShowSingleRecordActivity.class);

               intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(i).toString());

               startActivity(intent);
           }
       });
    }
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }
    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        NAME_Array.clear();
        Sirname_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Column_1)));

                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Column_1)));

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Column_2)));

                Sirname_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Column_3)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(DisplaySQLiteDataActivity.this,ID_Array,NAME_Array,Sirname_Array);

        listView.setAdapter(listAdapter);

        cursor.close();
    }
}
