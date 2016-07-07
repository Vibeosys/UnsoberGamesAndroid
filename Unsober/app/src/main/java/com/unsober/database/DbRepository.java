package com.unsober.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.unsober.data.responsedata.ResponseCategoryDTO;
import com.unsober.data.responsedata.ResponseItemDTO;
import com.unsober.utils.DateUtils;
import com.unsober.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshay on 07-07-2016.
 */
public class DbRepository extends SQLiteOpenHelper {

    private static final String TAG = DbRepository.class.getSimpleName();
    private final static String DATABASE_NAME = "unsober.db";

    public DbRepository(Context context, SessionManager sessionManager) {
        super(context, DATABASE_NAME, null, sessionManager.getDatabaseVersion());
    }


    private final String CREATE_CATEGORY = "CREATE TABLE IF NOT EXISTS categories(" +
            "id int(11) NOT NULL," +
            "name varchar(255) NOT NULL," +
            "icon varchar(255) NOT NULL," +
            "parent_id int(11) NOT NULL," +
            "status int(1),PRIMARY KEY(id));";

    private final String CREATE_ITEMS = "CREATE TABLE IF NOT EXISTS items(" +
            "id int(11) NOT NULL," +
            "title varchar(255) NOT NULL," +
            "description varchar(1024) NOT NULL," +
            "image_link varchar(255)," +
            "youtube_link varchar(255)," +
            "number_of_players varchar(255)," +
            "tag1 varchar(255),tag2 varchar(255),tag3 varchar(255)," +
            "category_id int(11),status int(1)," +
            "date_time datetime, views int(11)," +
            "PRIMARY KEY(id));";

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_CATEGORY);
            Log.d(TAG, "## Category table create Successfully" + CREATE_CATEGORY);
        } catch (SQLiteException e) {
            Log.e(TAG, "## Category table error" + e.toString());
        }

        try {
            db.execSQL(CREATE_ITEMS);
            Log.d(TAG, "## items table create Successfully" + CREATE_ITEMS);
        } catch (SQLiteException e) {
            Log.e(TAG, "## items table error" + e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void getDatabaseStructure() {
        final ArrayList<String> dirArray = new ArrayList<String>();

        SQLiteDatabase DB = getReadableDatabase();
        //SQLiteDatabase DB = sqlHelper.getWritableDatabase();
        Cursor c = DB.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        c.moveToFirst();
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                dirArray.add(c.getString(c.getColumnIndex("name")));

                c.moveToNext();
            }
        }
        Log.i(TAG, "##" + dirArray);
        c.close();

    }


    public boolean insertCategory(List<ResponseCategoryDTO> responseCategoryDTOs) {
        boolean flagError = false;
        String errorMessage = "";
        SQLiteDatabase sqLiteDatabase = null;
        ContentValues contentValues = null;
        long count = -1;
        try {
            sqLiteDatabase = getWritableDatabase();
            synchronized (sqLiteDatabase) {
                contentValues = new ContentValues();
                for (ResponseCategoryDTO responseCategoryDTO : responseCategoryDTOs) {
                    contentValues.put(SqlContract.SqlCategories.ID, responseCategoryDTO.getId());
                    contentValues.put(SqlContract.SqlCategories.NAME, responseCategoryDTO.getName());
                    contentValues.put(SqlContract.SqlCategories.ICON, responseCategoryDTO.getIcon());
                    contentValues.put(SqlContract.SqlCategories.PARENT_ID, responseCategoryDTO.getParent_id());
                    contentValues.put(SqlContract.SqlCategories.STATUS, responseCategoryDTO.getStatus());
                    if (!sqLiteDatabase.isOpen()) sqLiteDatabase = getWritableDatabase();
                    count = sqLiteDatabase.insert(SqlContract.SqlCategories.TABLE_NAME, null, contentValues);
                    contentValues.clear();
                    Log.d(TAG, "## Category is Added Successfully");
                    flagError = true;
                }
            }
        } catch (Exception e) {
            flagError = false;
            errorMessage = e.getMessage();
            Log.e(TAG, "##Error while insert category " + e.toString());
        } finally {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
                sqLiteDatabase.close();
            if (!flagError)
                Log.e(TAG, "##Insert Category" + errorMessage);
        }
        return flagError;
    }


    public boolean inserItems(List<ResponseItemDTO> responseItemDTOs) {
        boolean flagError = false;
        String errorMessage = "";
        SQLiteDatabase sqLiteDatabase = null;
        ContentValues contentValues = null;
        DateUtils dateUtils = new DateUtils();
        long count = -1;
        try {
            sqLiteDatabase = getWritableDatabase();
            synchronized (sqLiteDatabase) {
                contentValues = new ContentValues();
                for (ResponseItemDTO responseItemDTO : responseItemDTOs) {
                    contentValues.put(SqlContract.SqlItems.ID, responseItemDTO.getId());
                    contentValues.put(SqlContract.SqlItems.TITLE, responseItemDTO.getTitle());
                    contentValues.put(SqlContract.SqlItems.DESC, responseItemDTO.getDescription());
                    contentValues.put(SqlContract.SqlItems.IMG_LINK, responseItemDTO.getImage_link());
                    contentValues.put(SqlContract.SqlItems.YOU_LINK, responseItemDTO.getYoutube_link());
                    contentValues.put(SqlContract.SqlItems.NO_OF_PLAYERS, responseItemDTO.getNumber_of_players());
                    contentValues.put(SqlContract.SqlItems.TAG1, responseItemDTO.getTag1());
                    contentValues.put(SqlContract.SqlItems.TAG2, responseItemDTO.getTag2());
                    contentValues.put(SqlContract.SqlItems.TAG3, responseItemDTO.getTag3());
                    contentValues.put(SqlContract.SqlItems.CATEGORY_ID, responseItemDTO.getCategory_id());
                    contentValues.put(SqlContract.SqlItems.STATUS, responseItemDTO.getStatus());
                    contentValues.put(SqlContract.SqlItems.VIEWS, responseItemDTO.getViews());
                    /*contentValues.put(SqlContract.SqlItems.DATE_TIME,
                            dateUtils.getDateAndTimeFromLong(responseItemDTO.getDatetime()));*/ // date time problem
                    if (!sqLiteDatabase.isOpen())
                        sqLiteDatabase = getWritableDatabase();
                    count = sqLiteDatabase.insert(SqlContract.SqlItems.TABLE_NAME, null, contentValues);
                    contentValues.clear();
                    Log.d(TAG, "## Item is Added Successfully");
                    flagError = true;
                }
            }
        } catch (Exception e) {
            flagError = false;
            errorMessage = e.getMessage();
            Log.e(TAG, "##Error while insert Item " + e.toString());
        } finally {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
                sqLiteDatabase.close();
            if (!flagError)
                Log.e(TAG, "##Insert Item" + errorMessage);
        }
        return flagError;
    }
}
