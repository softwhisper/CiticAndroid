package curso.and10;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author pablo
 *
 */
public class DataBaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "PersonalContacts";
	private static final String TABLE_CONTACTS = "contacts";

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PH_NO = "phone_number";	
	
	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PH_NO + " TEXT" + ")";
		
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	/**
	 * Hacemos un drop y un create de nuevo.
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("DB", "Old Version: " + oldVersion + " New Version: " + newVersion);
		db.execSQL("DELETE TABLE IF EXIST " + TABLE_CONTACTS);
		onCreate(db);
	}


	/*
	 * CRUD sobre Contact
	 */
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getPhoneNumber());
		
		long i = db.insert(TABLE_CONTACTS, null, values);
		Log.i("DB", "Creado contacto con id: " + i);
		db.close();
	}

	Contact getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_CONTACTS, 
								 new String[] {KEY_ID,  KEY_NAME, KEY_PH_NO}, 
								 KEY_ID + "=?",
								 new String[] { String.valueOf(id) },
								 null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		
		Contact contact = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
		db.close();
		return contact;
	}
	
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		
		String query = "SELECT * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		if (cursor.moveToFirst()) {
			do {
				Contact c = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
				contactList.add(c);				
			} while (cursor.moveToNext());
		}
		db.close();
		return contactList;
	}

	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getPhoneNumber());
		
		int contact_id = db.update(TABLE_CONTACTS, 
								   values, 
								   KEY_ID + "=?", 
								   new String[] { String.valueOf(contact.getID()) });
		db.close();
		
		return contact_id;
	}

	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, 		   
				  KEY_ID + "=?", 
				  new String[] { String.valueOf(contact.getID()) });
		db.close();
	}

	public int getContactsCount() {		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
		cursor.close();
		
		return cursor.getCount();
	}	
}
