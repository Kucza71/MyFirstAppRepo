package com.example.my.first.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {

	public static final String KEY_ROWID =  "_id";
	public static final String KEY_NAME =  "name";
	public static final String KEY_HOTNES =  "hotness";
	
	private DbHelper dbHelper;
	private final Context context;
	private SQLiteDatabase db;
	
	public HotOrNot(Context context) {
		super();
		this.context = context;
	}
	
	public HotOrNot open() {
		dbHelper = new DbHelper(this.context);
		db = dbHelper.getWritableDatabase();		
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public void createEntry(String name, String hotness) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNES, hotness);
		
		db.insert("people", null, cv);
	}
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, "SampleDb", null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE people " +" (" +
					KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME+" TEXT NOT NULL, "+
					KEY_HOTNES+" TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public CharSequence getdata() {
		String[] columns = {KEY_ROWID, KEY_NAME, KEY_HOTNES};
		Cursor c = db.query("people", columns, null, null, null, null, null);
		
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNES);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			
			result = result + c.getString(iRow) +" | "+ c.getString(iName) +" | "+ c.getString(iHotness) + "\n";
			
		}
		
		return result;
	}
	
	
}
