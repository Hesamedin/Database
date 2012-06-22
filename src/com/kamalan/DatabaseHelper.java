package com.kamalan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private final String TAG = "DatabaseHelper";
	private static final String DATABASE_NAME = "db";	
	private static final String TABLE_NAME    = "contacts";
	private static final String COLUMN_ID     = "_id";
	private static final String COLUMN_NAME   = "name";
	private static final String COLUMN_VALUE  = "value";
	private static final String CREATE_TABLE  = "CREATE TABLE " + TABLE_NAME + " (" + 
		COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
		COLUMN_NAME + " TEXT," +
		COLUMN_VALUE + " TEXT" +
		");";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		
		Log.i(TAG, "Object created.");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("Drop table if exists " + TABLE_NAME);
		onCreate(db);
	}
	
	public String getTableName() {
		return TABLE_NAME;
	}
	
	public String getRowIdName() {
		return COLUMN_ID;
	}
}
