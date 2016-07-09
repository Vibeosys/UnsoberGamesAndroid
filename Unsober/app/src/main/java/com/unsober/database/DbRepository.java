package com.unsober.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.unsober.data.adapterdata.CategoryDataDTO;
import com.unsober.data.adapterdata.GameListDataDTO;
import com.unsober.data.adapterdata.ItemDataDTO;
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

        for (ResponseCategoryDTO responseCategoryDTO : responseCategoryDTOs) {
            int rowCount = 0;
            String[] whereClause = new String[]{String.valueOf(responseCategoryDTO.getId())};
            try {
                sqLiteDatabase = getReadableDatabase();
                synchronized (sqLiteDatabase) {
                    Cursor cursor = sqLiteDatabase.rawQuery("Select * From " + SqlContract.SqlCategories.TABLE_NAME
                            + " Where " + SqlContract.SqlCategories.ID + "=?", whereClause);
                    rowCount = cursor.getCount();
                    cursor.close();
                    sqLiteDatabase.close();
                    flagError = true;
                }
            } catch (Exception e) {
                flagError = false;
                errorMessage = e.getMessage();
                Log.e(TAG, "##Error while Get Category " + e.toString());
            } finally {
                if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
                    sqLiteDatabase.close();
                if (!flagError)
                    Log.e(TAG, "##Get Category" + errorMessage);
            }
            try {
                sqLiteDatabase = getWritableDatabase();
                synchronized (sqLiteDatabase) {
                    contentValues = new ContentValues();
                    contentValues.put(SqlContract.SqlCategories.ID, responseCategoryDTO.getId());
                    contentValues.put(SqlContract.SqlCategories.NAME, responseCategoryDTO.getName());
                    contentValues.put(SqlContract.SqlCategories.ICON, responseCategoryDTO.getIcon());
                    contentValues.put(SqlContract.SqlCategories.PARENT_ID, responseCategoryDTO.getParent_id());
                    contentValues.put(SqlContract.SqlCategories.STATUS, responseCategoryDTO.getStatus());
                    if (!sqLiteDatabase.isOpen()) sqLiteDatabase = getWritableDatabase();
                    if (rowCount > 0) {
                        count = sqLiteDatabase.update(SqlContract.SqlCategories.TABLE_NAME, contentValues,
                                SqlContract.SqlCategories.ID + "=?", whereClause);
                        Log.d(TAG, "## Category is Updated Successfully");
                    } else {
                        sqLiteDatabase = getWritableDatabase();
                        count = sqLiteDatabase.insert(SqlContract.SqlCategories.TABLE_NAME, null, contentValues);
                        Log.d(TAG, "## Category is Added Successfully");
                    }

                    contentValues.clear();
                    flagError = true;
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
        }

        return flagError;
    }


    public boolean insertItems(List<ResponseItemDTO> responseItemDTOs) {
        boolean flagError = false;
        String errorMessage = "";
        SQLiteDatabase sqLiteDatabase = null;
        DateUtils dateUtils = new DateUtils();
        long count = -1;
        for (ResponseItemDTO responseItemDTO : responseItemDTOs) {
            int rowCount = 0;
            String[] whereClause = new String[]{String.valueOf(responseItemDTO.getId())};
            try {
                sqLiteDatabase = getReadableDatabase();
                synchronized (sqLiteDatabase) {
                    Cursor cursor = sqLiteDatabase.rawQuery("Select * From " + SqlContract.SqlItems.TABLE_NAME
                            + " Where " + SqlContract.SqlItems.ID + "=?", whereClause);
                    rowCount = cursor.getCount();
                    cursor.close();
                    sqLiteDatabase.close();
                    flagError = true;
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
            try {
                sqLiteDatabase = getWritableDatabase();
                synchronized (sqLiteDatabase) {
                    ContentValues contentValues = null;
                    contentValues = new ContentValues();
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
                    contentValues.put(SqlContract.SqlItems.DATE_TIME, (responseItemDTO.getDatetime())); // date time problem
                    contentValues.put(SqlContract.SqlItems.VIEWS, responseItemDTO.getViews());
                    if (!sqLiteDatabase.isOpen())
                        sqLiteDatabase = getWritableDatabase();
                    if (rowCount > 0) {

                        count = sqLiteDatabase.update(SqlContract.SqlItems.TABLE_NAME, contentValues,
                                SqlContract.SqlItems.ID + "=?", whereClause);
                        Log.d(TAG, "## Item is Updated Successfully");
                    } else {
                        sqLiteDatabase = getWritableDatabase();
                        count = sqLiteDatabase.insert(SqlContract.SqlItems.TABLE_NAME, null, contentValues);
                        Log.d(TAG, "## Item is Added Successfully");
                    }

                    contentValues.clear();
                    flagError = true;
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
        }
        return flagError;
    }

    public ArrayList<CategoryDataDTO> getCategoryList(int parentId) {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<CategoryDataDTO> categoryDTOs = null;
        try {
            String[] whereClause = new String[]{String.valueOf(parentId)};
            sqLiteDatabase = getReadableDatabase();
            synchronized (sqLiteDatabase) {
                cursor = sqLiteDatabase.rawQuery("SELECT * From " + SqlContract.SqlCategories.TABLE_NAME + " where " +
                        SqlContract.SqlCategories.STATUS + "=1 AND " + SqlContract.SqlCategories.PARENT_ID + "=?", whereClause);
                categoryDTOs = new ArrayList<>();
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();

                        do {
                            long mCategoryId = cursor.getLong(cursor.getColumnIndex(SqlContract.SqlCategories.ID));
                            String mCategoryName = cursor.getString(cursor.getColumnIndex(SqlContract.SqlCategories.NAME));
                            String mCategoryIcon = cursor.getString(cursor.getColumnIndex(SqlContract.SqlCategories.ICON));
                            long mCategoryParentId = cursor.getLong(cursor.getColumnIndex(SqlContract.SqlCategories.PARENT_ID));
                            int mCategoryStatus = cursor.getInt(cursor.getColumnIndex(SqlContract.SqlCategories.STATUS));

                            CategoryDataDTO categoryDataDTO = new CategoryDataDTO(mCategoryId,
                                    mCategoryName, mCategoryIcon, mCategoryParentId, mCategoryStatus);
                            categoryDTOs.add(categoryDataDTO);

                        } while (cursor.moveToNext());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
                sqLiteDatabase.close();
        }
        return categoryDTOs;
    }

    public ArrayList<GameListDataDTO> getGameList(long mCategoryId) {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<GameListDataDTO> gameListDataDTO = null;
        try {
            String[] whereClause = new String[]{String.valueOf(mCategoryId)};
            sqLiteDatabase = getReadableDatabase();
            synchronized (sqLiteDatabase) {
                cursor = sqLiteDatabase.rawQuery("SELECT * from " + SqlContract.SqlItems.TABLE_NAME + " where "
                        + SqlContract.SqlItems.STATUS + "=1 AND " + SqlContract.SqlItems.CATEGORY_ID + "=?", whereClause);
                gameListDataDTO = new ArrayList<>();
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        do {
                            long mItemId = cursor.getLong(cursor.getColumnIndex(SqlContract.SqlItems.ID));
                            String mImagePath = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.IMG_LINK));
                            String mNumberOfPlayers = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.NO_OF_PLAYERS));
                            String mGameTitle = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.TITLE));
                            String mGameDescription = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.DESC));
                            GameListDataDTO gameListDataDTOs = new GameListDataDTO(mItemId, mGameTitle, mImagePath, mNumberOfPlayers, mGameDescription);
                            gameListDataDTO.add(gameListDataDTOs);

                        } while (cursor.moveToNext());
                    }
                }
            }
        } catch (Exception e) {
            Log.e("getGameList", e.toString());
        } finally {
            if (cursor != null)
                cursor.close();
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
                sqLiteDatabase.close();
        }

        return gameListDataDTO;
    }


    public ItemDataDTO getItemDetail(long mItemId) {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ItemDataDTO itemDataDTO = null;
        try {
            String[] whereClause = new String[]{String.valueOf(mItemId)};
            sqLiteDatabase = getReadableDatabase();
            synchronized (sqLiteDatabase) {
                cursor = sqLiteDatabase.rawQuery("SELECT * from " + SqlContract.SqlItems.TABLE_NAME + " where "
                        + SqlContract.SqlItems.STATUS + "=1 AND " + SqlContract.SqlItems.ID + "=?", whereClause);
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        String title = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.TITLE));
                        String description = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.DESC));
                        String imageLink = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.IMG_LINK));
                        String youtubeLink = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.YOU_LINK));
                        String tag1 = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.TAG1));
                        String tag2 = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.TAG2));
                        String tag3 = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.TAG3));
                        int categoryId = cursor.getInt(cursor.getColumnIndex(SqlContract.SqlItems.CATEGORY_ID));
                        int status = cursor.getInt(cursor.getColumnIndex(SqlContract.SqlItems.STATUS));
                        String mItemDateTime = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.DATE_TIME));
                        long view = cursor.getLong(cursor.getColumnIndex(SqlContract.SqlItems.VIEWS));
                        int noOfPlayers = cursor.getInt(cursor.getColumnIndex(SqlContract.SqlItems.NO_OF_PLAYERS));
                        itemDataDTO = new ItemDataDTO(mItemId, title, description, imageLink,
                                youtubeLink, tag1, tag2, tag3, categoryId, status, mItemDateTime, view, noOfPlayers);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("getItem", e.toString());
        } finally {
            if (cursor != null)
                cursor.close();
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
                sqLiteDatabase.close();
        }

        return itemDataDTO;
    }

    public ArrayList<String> getFirstTag(String columnName) {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            sqLiteDatabase = getReadableDatabase();
            synchronized (sqLiteDatabase) {
                cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT LOWER (" + columnName + ") AS " + columnName + " FROM " +
                        SqlContract.SqlItems.TABLE_NAME + " where " + SqlContract.SqlItems.STATUS + "=1 ", null);
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        {
                            do {
                                String tag1 = cursor.getString(cursor.getColumnIndex(columnName));
                                arrayList.add(tag1);
                            } while (cursor.moveToNext());
                        }
                    }

                }

            }

        } catch (Exception e) {
            Log.e("getTag", e.toString());
        } finally {
            if (cursor != null)
                cursor.close();
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
                sqLiteDatabase.close();
        }
        return arrayList;
    }

    public ArrayList<GameListDataDTO> getAdvancedSearch(String whereCondition) {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<GameListDataDTO> gameListDataDTO = null;
        try {
            sqLiteDatabase = getReadableDatabase();
            synchronized (sqLiteDatabase) {
                cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + SqlContract.SqlItems.TABLE_NAME + " " + whereCondition, null);
                Log.d("TAG", " SELECT * FROM " + SqlContract.SqlItems.TABLE_NAME + whereCondition);
                gameListDataDTO = new ArrayList<>();
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        do {
                            long mItemId = cursor.getLong(cursor.getColumnIndex(SqlContract.SqlItems.ID));
                            String mImagePath = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.IMG_LINK));
                            String mNumberOfPlayers = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.NO_OF_PLAYERS));
                            String mGameTitle = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.TITLE));
                            String mGameDescription = cursor.getString(cursor.getColumnIndex(SqlContract.SqlItems.DESC));
                            GameListDataDTO gameListDataDTOs = new GameListDataDTO(mItemId, mGameTitle, mImagePath, mNumberOfPlayers, mGameDescription);
                            gameListDataDTO.add(gameListDataDTOs);
                        } while (cursor.moveToNext());
                    }
                }
            }


        } catch (Exception e) {
            Log.e("AdvanceSearch", e.toString());
        } finally {
            if (cursor != null)
                cursor.close();
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
                sqLiteDatabase.close();
        }
        return gameListDataDTO;
    }
}
