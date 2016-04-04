package com.akaver.sqllitecomplex01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by akaver on 03/04/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = MySQLiteHelper.class.getName();
    private final Context context;

    public static final String TABLE_PERSONS = "persons";
    public static final String COLUMN_PERSONS_ID = "_id";
    public static final String COLUMN_PERSONS_FIRSTNAME = "firstName";
    public static final String COLUMN_PERSONS_LASTNAME = "lastName";
    // ID has to be first in list!!!!
    public static final String[] ALLCOLUMNS_PERSONS = {COLUMN_PERSONS_ID, COLUMN_PERSONS_FIRSTNAME, COLUMN_PERSONS_LASTNAME};

    public static final String TABLE_CONTACTTYPES = "contactTypes";
    public static final String COLUMN_CONTACTTYPES_ID = "_id";
    public static final String COLUMN_CONTACTTYPES_VALUE = "value";
    public static final String[] ALLCOLUMNS_CONTACTTYPES = {COLUMN_CONTACTTYPES_ID, COLUMN_CONTACTTYPES_VALUE};

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_CONTACTS_ID = "_id";
    public static final String COLUMN_CONTACTS_VALUE = "value";
    public static final String COLUMN_CONTACTS_PERSONID = "personId";
    public static final String COLUMN_CONTACTS_CONTACTTYPEID = "contactTypeId";
    public static final String[] ALLCOLUMNS_CONTACTS = {COLUMN_CONTACTS_ID, COLUMN_CONTACTS_VALUE, COLUMN_CONTACTS_PERSONID, COLUMN_CONTACTS_CONTACTTYPEID};


    private static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_PERSONS = "create table "
            + TABLE_PERSONS + "("
            + COLUMN_PERSONS_ID + " integer primary key autoincrement, "
            + COLUMN_PERSONS_FIRSTNAME + " text not null, "
            + COLUMN_PERSONS_LASTNAME + " text not null);";

    private static final String DATABASE_CREATE_CONTACTTYPES = "create table "
            + TABLE_CONTACTTYPES + "("
            + COLUMN_CONTACTTYPES_ID + " integer primary key autoincrement, "
            + COLUMN_CONTACTTYPES_VALUE + " text not null);";

    private static final String DATABASE_CREATE_CONTACTS = "create table "
            + TABLE_CONTACTS + "("
            + COLUMN_CONTACTS_ID + " integer primary key autoincrement, "
            + COLUMN_CONTACTS_VALUE + " text not null, "
            + COLUMN_CONTACTS_PERSONID + " integer, "
            + COLUMN_CONTACTS_CONTACTTYPEID + " integer);";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void dropCreateDatabase(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTTYPES);

        db.execSQL(DATABASE_CREATE_PERSONS);
        db.execSQL(DATABASE_CREATE_CONTACTTYPES);
        db.execSQL(DATABASE_CREATE_CONTACTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PERSONS);
        db.execSQL(DATABASE_CREATE_CONTACTTYPES);
        db.execSQL(DATABASE_CREATE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTTYPES);
        onCreate(db);
    }
}
