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

import java.util.Date;

import android.database.Cursor;

public class Cursors {
    private Cursors() {
    }

    public static String string(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    public static String string(Cursor cursor, String columnName, String defaultValue) {
        final String result = cursor.getString(cursor.getColumnIndexOrThrow(columnName));
        if (result == null) {
            return defaultValue;
        } else {
            return result;
        }
    }

    public static int intValue(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    public static int intValue(Cursor cursor, String columnName, int defaultValue) {
        if (cursor.isNull(cursor.getColumnIndexOrThrow(columnName))) {
            return defaultValue;
        }
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    public static long longValue(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }

    public static long longValue(Cursor cursor, String columnName, long defaultValue) {
        if (cursor.isNull(cursor.getColumnIndexOrThrow(columnName))) {
            return defaultValue;
        }
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }

    public static short shortValue(Cursor cursor, String columnName) {
        return cursor.getShort(cursor.getColumnIndexOrThrow(columnName));
    }

    public static short shortValue(Cursor cursor, String columnName, short defaultValue) {
        if (cursor.isNull(cursor.getColumnIndexOrThrow(columnName))) {
            return defaultValue;
        }
        return cursor.getShort(cursor.getColumnIndexOrThrow(columnName));
    }

    public static double doubleValue(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName));
    }

    public static double doubleValue(Cursor cursor, String columnName, double defaultValue) {
        if (cursor.isNull(cursor.getColumnIndexOrThrow(columnName))) {
            return defaultValue;
        }
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName));
    }

    public static float floatValue(Cursor cursor, String columnName) {
        return cursor.getFloat(cursor.getColumnIndexOrThrow(columnName));
    }

    public static float floatValue(Cursor cursor, String columnName, float defaultValue) {
        if (cursor.isNull(cursor.getColumnIndexOrThrow(columnName))) {
            return defaultValue;
        }
        return cursor.getFloat(cursor.getColumnIndexOrThrow(columnName));
    }

    public static Date date(Cursor cursor, String columnName) {
        if (cursor.isNull(cursor.getColumnIndexOrThrow(columnName))) {
            return null;
        }
        return new Date(longValue(cursor, columnName));
    }

    public static boolean isTrue(Cursor cursor, String columnName) {
        return intValue(cursor, columnName) != 0;
    }

    public static boolean isFalse(Cursor cursor, String columnName) {
        return !isTrue(cursor, columnName);
    }

}
