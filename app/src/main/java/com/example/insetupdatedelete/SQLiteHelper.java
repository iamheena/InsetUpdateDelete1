package com.example.insetupdatedelete;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="AndroidJSonDataBase";

    public static final String TABLE_NAME="AndroidJSonTable";

    public static final String Column_1="id";

    public static final String Column_2="name";

    public static final String Column_3="sirname";
    public SQLiteHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+Column_1+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+Column_2+" VARCHAR,"+Column_3+" VARCHAR)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
