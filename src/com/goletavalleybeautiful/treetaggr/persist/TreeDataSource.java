package com.goletavalleybeautiful.treetaggr.persist;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.goletavalleybeautiful.treetaggr.data.Tree;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class TreeDataSource {
	
	// JSON Node names
	private static final String TAG_TREES = "trees";
	private static final String TAG_TREE_TYPE_ID = "tree_type_id";
	private static final String TAG_AGENCY_ID = "agency_id";
	
	private static final String TAG_DBH = "dbh";
	private static final String TAG_HEIGHT = "height";
	private static final String TAG_SPREAD = "spread";
	private static final String TAG_STATUS = "status";
	
	private static final String TAG_LONGITUDE = "longitude";
	private static final String TAG_LATITUDE = "latitude";

	//JSON Array of trees
	
	JSONArray trees = null;

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
		MySQLiteHelper.COLUMN_TREE_TYPE_ID,
		MySQLiteHelper.COLUMN_AGENCY_ID,
		MySQLiteHelper.COLUMN_DBH,
		MySQLiteHelper.COLUMN_HEIGHT,
		MySQLiteHelper.COLUMN_SPREAD,
		MySQLiteHelper.COLUMN_STATUS,
		MySQLiteHelper.COLUMN_LONGITUDE,
		MySQLiteHelper.COLUMN_LATITUDE };

  public TreeDataSource(Context context) {
	 dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
	 database = dbHelper.getWritableDatabase();
  }

  public void close() {
	 dbHelper.close();
  }

  public void enqueueTree(Tree tr) {
	 System.out.println("Queuing Tree");
	 ContentValues values = new ContentValues();
	 values.put(MySQLiteHelper.COLUMN_TREE_TYPE_ID, tr.getTree_type_id());
	 values.put(MySQLiteHelper.COLUMN_AGENCY_ID, tr.getAgency_id());
	 values.put(MySQLiteHelper.COLUMN_DBH, tr.getDiameter_at_height());
	 values.put(MySQLiteHelper.COLUMN_HEIGHT, tr.getHeight());
	 values.put(MySQLiteHelper.COLUMN_SPREAD, tr.getSpread());
	 values.put(MySQLiteHelper.COLUMN_STATUS, tr.getStatus());
	 values.put(MySQLiteHelper.COLUMN_LONGITUDE, tr.getLongitude());
	 values.put(MySQLiteHelper.COLUMN_LATITUDE, tr.getLatitude());
	 
	 long insertId = database.insert(MySQLiteHelper.TABLE_TREES, null,
		  values);
	 Cursor cursor = database.query(MySQLiteHelper.TABLE_TREES,
		  allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		  null, null, null);
	 cursor.moveToFirst();
	 Tree newTree = cursorToTree(cursor);
	 cursor.close();
  }

  public int dequeueTree(Tree tree) {
	 long id = tree.showId();
	 System.out.println("Tree deleted with id: " + id);
	 return database.delete(MySQLiteHelper.TABLE_TREES, MySQLiteHelper.COLUMN_ID
		  + " = " + id, null);
  }

  public ArrayList<Tree> getQueue() {
	 ArrayList<Tree> trees = new ArrayList<Tree>();

	 Cursor cursor = database.query(MySQLiteHelper.TABLE_TREES,
		  allColumns, null, null, null, null, null);

	 cursor.moveToFirst();
	 while (!cursor.isAfterLast()) {
		Tree tree = cursorToTree(cursor);
		trees.add(tree);
		cursor.moveToNext();
	 }
	 // Make sure to close the cursor
	 cursor.close();
	 return trees;
  }


  private Tree cursorToTree(Cursor cursor) {
	 Tree tree = new Tree();
	 tree.setId(cursor.getInt(0));
	 tree.setTree_type_id(cursor.getInt(1));
	 tree.setAgency_id(cursor.getInt(2));
	 tree.setDiameter_at_height(cursor.getFloat(3));
	 tree.setHeight(cursor.getFloat(4));
	 tree.setSpread(cursor.getFloat(5));
	 tree.setStatus(cursor.getInt(6));
	 tree.setLatitude(cursor.getFloat(7));
	 tree.setLongitude(cursor.getFloat(8));
	 return tree;
  }
} 