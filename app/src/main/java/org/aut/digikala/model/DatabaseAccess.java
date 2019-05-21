package org.aut.digikala.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseAccess extends SQLiteAssetHelper {
    private static String DATABASE_NAME = "digikala.db";
    private static int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public DatabaseAccess(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }


    public SQLiteDatabase getDb() {
        return db;
    }
}
