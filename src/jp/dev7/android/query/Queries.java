package jp.dev7.android.query;

import java.util.Date;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

public class Queries {
    private Queries() {
    }

    /**
     * Pairコンストラクタのラッパ.
     */
    public static Pair<String, Object> keyValue(String key, Object value) {
        if (value == Boolean.FALSE) {
            value = 0;
        } else if (value == Boolean.TRUE) {
            value = 1;
        }
        return new Pair<String, Object>(key, value);
    }

    /**
     * ContentValuesオブジェクトを簡単に作る.
     */
    public static ContentValues values(Pair<String, Object>... pairs) {
        final ContentValues result = new ContentValues();
        for (final Pair<String, Object> pair : pairs) {
            if (pair.first != null) {
                if (pair.second == null) {
                    result.put(pair.first, "");
                } else if (pair.second instanceof Boolean) {
                    if (pair.second == Boolean.TRUE) {
                        result.put(pair.first, 1);
                    } else {
                        result.put(pair.first, 0);
                    }
                } else if (pair.second instanceof Date) {
                    result.put(pair.first, "" + ((Date) pair.second).getTime());
                } else {
                    result.put(pair.first, "" + pair.second);
                }
            }
        }
        return result;
    }

    /**
     * 条件式を簡単に作る.
     * 
     * @param expression
     *            置き換え文字(?)入りの条件式
     * @param params
     *            パラメータ
     */
    public static Selection selection(String expression, Object... params) {
        return new Selection(expression, strings(params));
    }

    /**
     * Stringの配列を簡単に作る.
     */
    public static String[] strings(Object... values) {
        final String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                result[i] = "";
            } else if (values[i] == Boolean.TRUE) {
                result[i] = "1";
            } else if (values[i] == Boolean.FALSE) {
                result[i] = "0";
            } else {
                result[i] = "" + values[i];
            }
        }
        return result;
    }

    public static String[] columns(String... values) {
        return values;
    }

    public static Query query(String table) {
        return new Query(table);
    }

    public static Query query(String table, Selection selection) {
        return new Query(table, selection.exp, selection.args);
    }

    public static Query query(String table, String[] columns) {
        return new Query(table, columns);
    }

    public static Query query(String table, String[] columns, Selection selection) {
        return new Query(table, columns, selection.exp, selection.args);
    }

    public static long insert(SQLiteDatabase db, String table, Pair<String, Object>... pairs) {
        return db.insert(table, null, values(pairs));
    }

    public static int update(SQLiteDatabase db, String table, Selection where, Pair<String, Object>... pairs) {
        if (where != null) {
            return db.update(table, values(pairs), where.exp, where.args);
        } else {
            return db.update(table, values(pairs), null, null);
        }
    }

    public static int update(SQLiteDatabase db, String table, String whereClause, String[] whereArgs,
            Pair<String, Object>... pairs) {
        return db.update(table, values(pairs), whereClause, whereArgs);
    }

    public static int delete(SQLiteDatabase db, String table) {
        return db.delete(table, null, null);
    }

    public static int delete(SQLiteDatabase db, String table, Selection selection) {
        return db.delete(table, selection.exp, selection.args);
    }

}
