package com.example.mshehab.db_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mshehab on 12/4/17.
 */

public class DBDataManager {
    private Context mContext;
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NoteDAO noteDAO;

    public DBDataManager(Context mContext) {
        this.mContext = mContext;
        dbOpenHelper = new DBOpenHelper(mContext);
        db = dbOpenHelper.getWritableDatabase();
        noteDAO = new NoteDAO(db);
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public NoteDAO getNoteDAO() {
        return noteDAO;
    }
}
