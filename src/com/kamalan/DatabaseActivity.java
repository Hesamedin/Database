package com.kamalan;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseActivity extends Activity {
    
	private final String TAG = "DatabaseActivity";
	
	private DatabaseHandler dbHandler;
	private Contact contact;
	private List<Contact> list;
	private Button btnAdd;
	private EditText etName;
	private EditText etPhone;
	private ListView listView;
	private ListAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Log.i(TAG, "Inside onCreate()");
        
        
        dbHandler = new DatabaseHandler(this);
        contact   = new Contact();
        adapter   = new ListAdapter(this);
        
        
        etName   = (EditText)findViewById(R.id.etName);
        etPhone  = (EditText)findViewById(R.id.etPhone);
        
        
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Log.i(TAG, "item " + position + " clicked.");
				
				TextView tv = (TextView) v.findViewById(R.id.tvId);
				Log.i(TAG, ">> " + tv.getText());
				
				/***************************************************************
				 * One of these methods should be uncommented at the same time *
				 ***************************************************************/
				dbHandler.deleteContact(Long.parseLong(tv.getText().toString()));
//				dbHandler.updateContact(Long.parseLong(tv.getText().toString()));
				refreshList();
			}
		});
        
        
        btnAdd = (Button) findViewById(R.id.btnAddToDatabase);
        btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Button clicked.");
				
				boolean validate1 = false;
				boolean validate2 = false;
				
				String name = etName.getText().toString();
				if(name.equalsIgnoreCase(""))
					Toast.makeText(DatabaseActivity.this, "Pease enter a name.", Toast.LENGTH_LONG).show();
				else {
					contact.setName(name);
					validate1 = true;
				}
				
				String phone = etPhone.getText().toString();
				if(phone.equalsIgnoreCase(""))
					Toast.makeText(DatabaseActivity.this, "Pease enter phone number.", Toast.LENGTH_LONG).show();
				else {
					contact.setPhoneNumber(phone);
					validate2 = true;
				}
				
				if(validate1 && validate2) {
					etName.setText("");
					etPhone.setText("");
					
					dbHandler.insertContact(contact);
					refreshList();
				}
			}
		});        
    }

	@Override
	protected void onResume() {
		super.onResume();
		
		dbHandler.open();
//		dbHandler.clearTable();
		refreshList();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		dbHandler.close();
	}
	
	protected void refreshList() {
    	list = dbHandler.getAllContacts();
        adapter.setData(list);
        listView.setAdapter(adapter);
	}
}