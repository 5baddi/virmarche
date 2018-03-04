package info.baddi.virmarche.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class Db extends SQLiteOpenHelper
{
    public static final int VERSION = 1;
    public static final String DATABASE = "virmarche.db";
    public static final String SETTING_TABLE = "appSettings";
    public static final String DEVICE_TABLE = "devices";

    private Context context;
    private SQLiteDatabase readable;
    private SQLiteDatabase writable;

    public Db(Context context)
    {
        super(context, DATABASE, null, VERSION);

        if(this.context == null) this.context = context;
        if(readable == null) readable = this.getReadableDatabase();
        if(writable == null) writable = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, value TEXT); CREATE TABLE IF NOT EXISTS %s (id INTEGER PRIMARY KEY AUTOINCREMENT, imei TEXT, phoneNumber TEXT)", SETTING_TABLE, DEVICE_TABLE));
    }

    public int checkData(String field,  String value, String table)
    {
        Cursor data = readable.rawQuery(String.format("select * from %s where %s = ?", table, field), new String[]{value});
        if(data != null && data.moveToFirst()) return data.getInt(data.getColumnIndex("id"));

        return -1;
    }

    public boolean putData(ContentValues data, String table)
    {
        if(data.size() > 0 && writable.insert(table, null, data) != -1) return true;

        return false;
    }

    public boolean putBulkData(ArrayList<ContentValues> bulkData, String table)
    {
        boolean result = false;
        if(bulkData.size() > 0)
        {
            for (ContentValues data : bulkData)
                result = (data.size() > 0 && writable.insert(table, null, data) != -1) ? true : false;
        }

        return result;
    }


    public boolean updateData(ContentValues data, String field, String table)
    {
        int id = checkData(field, data.getAsString(field), table);
        if(data.size() > 0 && id != -1)
            return (writable.update(table, data, "id = ?", new String[]{data.getAsString(field)}) == 1) ? true : false;

        return false;
    }

    public boolean updateBulkData(ArrayList<ContentValues> bulkData, String field, String table)
    {
        boolean result = false;
        if(bulkData.size() > 0)
        {
            for(ContentValues data : bulkData)
            {
                int id = checkData(field, data.getAsString(field), table);
                result = (data.size() > 0 && id != -1 && writable.update(table, data, "id = ?", new String[]{data.getAsString(field)}) == 1) ? true : false;
            }
        }

        return result;
    }

    public Cursor getData(String field, String value, String table)
    {
        Cursor data = null;
        int id = checkData(field, value, table);
        if(id != -1)
        {
            SQLiteDatabase db = this.getReadableDatabase();
            data = db.rawQuery(String.format("select * from %s where id = ?", table), new String[]{value});
            if(data.getCount() == 1) return data;
        }

        return data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(String.format("DROP TABLE IF NOT EXISTS %s, %s", SETTING_TABLE, DEVICE_TABLE));
        onCreate(db);
    }
}
