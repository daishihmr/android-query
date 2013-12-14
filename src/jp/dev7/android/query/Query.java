/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 daishi_hmr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jp.dev7.android.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    Query(String table, String[] columns, String selection,
            String[] selectionArgs) {
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
        writeSql();
        return db.query(table, columns, selection, selectionArgs, groupBy,
                null, orderBy);
    }

    public Cursor execute(SQLiteOpenHelper dbHelper) {
        return execute(dbHelper.getReadableDatabase());
    }

    private void writeSql() {
        final StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        if (columns != null && columns.length > 1) {
            for (String column : columns) {
                sql.append(column);
                sql.append(", ");
            }
            if (sql.length() >= 2) {
                sql.delete(sql.length() - 2, sql.length());
            }
        } else {
            sql.append("*");
        }

        sql.append(" FROM ");
        sql.append(table);

        if (selection != null && !selection.equals("")) {
            sql.append(" WHERE ");
            String s = selection;
            if (selectionArgs != null) {
                for (String arg : selectionArgs) {
                    s = s.replaceFirst("\\?", "'" + arg + "'");
                }
            }
            sql.append(s);
        }

        if (groupBy != null) {
            sql.append(" GROUP BY ");
            sql.append(groupBy);
        }

        if (orderBy != null) {
            sql.append(" ORDER BY ");
            sql.append(orderBy);
        }

        Log.println(Queries.LOG_PRIORITY, Queries.LOG_TAG, sql.toString());
    }
}
