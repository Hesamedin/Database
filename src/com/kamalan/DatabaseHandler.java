package com.kamalan;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseHandler {

	private final String TAG 	 = "DatabaseHandler";
	static final  String NAME    = "name";
	static final  String VALUE   = "value";
	
	private DatabaseHelper dbHelper;
	private SQLiteDatabase database;
	
	public DatabaseHandler(Context context) {
		dbHelper = new DatabaseHelper(context);
		Log.i(TAG, "Object created.");
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public void insertContact(Contact contact) {
		ContentValues cv = new ContentValues();
		
		cv.put(NAME,  contact.getName());
		cv.put(VALUE, contact.getPhoneNumber());
		database.insert(dbHelper.getTableName(), NAME, cv);
		
		Log.i(TAG, "Contact added successfully.");
	}
	
	public void deleteContact(long id) {
		database.delete(dbHelper.getTableName(), dbHelper.getRowIdName() + "=" + id, null);
	}
	
	public void updateContact(long id) {
		ContentValues cv = new ContentValues();
		
		cv.put(NAME,  "Kamalan.com");
		cv.put(VALUE, "123456");
		database.update(dbHelper.getTableName(), cv, dbHelper.getRowIdName() + "=" + id, null);
	}
	
	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		
		Cursor cursor = database.query(dbHelper.getTableName(),	null, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Contact contact = cursorToContact(cursor);
			contacts.add(contact);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		
		return contacts;
	}
	
	public void clearTable() {
		database.delete(dbHelper.getTableName(), null, null);
	}
	
	private Contact cursorToContact(Cursor cursor) {
		Contact contact = new Contact();
		
		contact.setId(cursor.getLong(0));
		contact.setName(cursor.getString(1));
		contact.setPhoneNumber(cursor.getString(2));
		
		return contact;
	}
}
