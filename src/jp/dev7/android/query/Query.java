package jp.dev7.android.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Query {

    String table;
    String[] columns;
    String selection;
    String[] selectionArgs;
    String groupBy;
    String orderBy;

    Query(String table) {
        super();
        this.table = table;
    }

    Query(String table, String[] columns, String selection, String[] selectionArgs) {
        this.table = table;
        this.columns = columns;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
    }

    Query(String table, String selection, String[] selectionArgs) {
        this.table = table;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
    }

    Query(String table, String[] columns) {
        this.table = table;
        this.columns = columns;
    }

    public Query orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public Query groupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public Cursor execute(SQLiteDatabase db) {
        return db.query(table, columns, selection, selectionArgs, groupBy, null, orderBy);
    }

    public Cursor execute(SQLiteOpenHelper dbHelper) {
        return dbHelper.getReadableDatabase().query(table, columns, selection, selectionArgs, groupBy, null, orderBy);
    }
}
