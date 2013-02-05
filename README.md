android-query
=============

simple database access library for Android.

example
-------

    String title = getTitle();
    String author = getAuthor();
    
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cursor = query("book", selection("title = ? or author = ?", title, author)).orderBy("isbn").execute(db);

