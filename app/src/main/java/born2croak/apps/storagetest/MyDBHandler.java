package born2croak.apps.storagetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.zip.CheckedOutputStream;

/**
 * Created by Tim on 3/1/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Counting.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + MyDBContract.TABLE_NAME + " ( " + MyDBContract.COLUMN_NAME_COUNT
            + " INTEGER )";

    private static final String SQL_INITIALIZE = "INSERT INTO " + MyDBContract.TABLE_NAME
            + "(" + MyDBContract.COLUMN_NAME_COUNT +") VALUES (0)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + MyDBContract.TABLE_NAME;

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_INITIALIZE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void saveValue(int value) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + MyDBContract.TABLE_NAME
                + " SET " + MyDBContract.COLUMN_NAME_COUNT
                + " = " + value;

        db.execSQL(query);

        db.close();
    }

    public int getValue() {
        SQLiteDatabase db = this.getWritableDatabase();
        int value = 0;

        String query = "SELECT * FROM " + MyDBContract.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            value = cursor.getInt(0);
        }

        db.close();
        return value;
    }

    public void reset() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_INITIALIZE);
        db.close();
    }
}
