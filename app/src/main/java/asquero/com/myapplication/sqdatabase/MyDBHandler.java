package asquero.com.myapplication.sqdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NOTI = "notifyUser";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTI = "noti";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + TABLE_NOTI + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOTI + " TEXT );" ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTI);
        onCreate(db);
    }

    //Add a new row to the Databse
    public void addData(database database){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTI, database.get_noti());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NOTI, null, values);
        db.close();
    }

    //Delete row from database
    public void deleteData(String dataValue){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTI + " WHERE " + COLUMN_NOTI + "==\"" + dataValue + "\";");
    }
}
