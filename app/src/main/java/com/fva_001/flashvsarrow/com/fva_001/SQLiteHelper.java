package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ShamimH on 25-Mar-16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private String DB_PATH = null;
    private static final String DB_NAME = "db__core";
    private SQLiteDatabase appDatabase;
    private final Context context;



    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.context = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }

    public void createDatabase() throws IOException{
        boolean dbExist = checkDataBase();
        if(dbExist){

        }else{
            this.getReadableDatabase();
            try{
                copyDataBase();
            }catch (IOException e){
                throw new Error("Error copying database");
            }
        }
    }
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){

        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{
        InputStream inputStream = context.getAssets().open(DB_NAME);
        String fileName = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(fileName);
        byte[] buffer = new byte[10];
        int length;
        while((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    private void openDataBase() throws SQLiteException{
        String path = DB_PATH + DB_NAME;
        appDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close(){
        if(appDatabase != null){
            appDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            try{
                copyDataBase();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return appDatabase.query("EMP_TABLE", null, null, null ,null, null, null);
    }
}
