android-query
=============

simple database access library for Android.

example
-------

select

    import static jp.dev7.android.query.Cursors.*;
    import static jp.dev7.android.query.Queries.*;
    
    ...

    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cursor = query("BOOK", selection("TITLE = ? OR AUTHOR = ?", title, author)).orderBy("ISBN").execute(db);

insert

    import static jp.dev7.android.query.Cursors.*;
    import static jp.dev7.android.query.Queries.*;
    
    ...

    SQLiteDatabase db = dbHelper.getReadableDatabase();
    insert(db, "BOOK",
        keyValue("ISBN", isbn), // String
        keyValue("TITLE", title), // String
        keyValue("AUTHOR", author), // String
        keyValue("CREATED_AT", new Date()), // java.util.Date
        keyValue("PLICE", 1200), // int
        keyValue("OUT_OF_PRINT", false) // boolean
    );

update

    import static jp.dev7.android.query.Cursors.*;
    import static jp.dev7.android.query.Queries.*;
    
    ...

    SQLiteDatabase db = dbHelper.getReadableDatabase();
    update(db, "BOOK",
        selection("ISBN = ?", isbn),
        keyValue("TITLE", title), // String
        keyValue("AUTHOR", author), // String
        keyValue("CREATED_AT", new Date()), // java.util.Date
        keyValue("PLICE", 1200), // int
        keyValue("OUT_OF_PRINT", false) // boolean
    );

delete

    import static jp.dev7.android.query.Cursors.*;
    import static jp.dev7.android.query.Queries.*;
    
    ...

    SQLiteDatabase db = dbHelper.getReadableDatabase();
    delete(db, "BOOK", selection("TITLE = ?", title));

