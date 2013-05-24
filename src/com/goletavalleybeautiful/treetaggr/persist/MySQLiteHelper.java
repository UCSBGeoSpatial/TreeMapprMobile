package com.goletavalleybeautiful.treetaggr.persist;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_TREES = "trees";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_TREE_TYPE_ID = "tree_type_id";
  public static final String COLUMN_AGENCY_ID = "agency_id";
  public static final String COLUMN_DBH = "dbh";
  public static final String COLUMN_HEIGHT = "height";
  public static final String COLUMN_SPREAD = "spread";
  public static final String COLUMN_STATUS = "status";
  public static final String COLUMN_LONGITUDE = "longitude";
  public static final String COLUMN_LATITUDE = "latitude";

  private static final String DATABASE_NAME = "treequeue.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_TREES
      + "("+ COLUMN_ID + " integer primary key autoincrement, "
      + COLUMN_TREE_TYPE_ID + " integer, " 
      + COLUMN_AGENCY_ID + " integer, "
      + COLUMN_DBH + " float, "
      + COLUMN_HEIGHT + " float, "
      + COLUMN_SPREAD + " float, "
      + COLUMN_STATUS + " int, " 
      + COLUMN_LONGITUDE + " float, "
      + COLUMN_LATITUDE + " float); ";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TREES);
    onCreate(db);
  }

} 