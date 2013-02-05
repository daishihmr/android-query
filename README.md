android-query
=============

simple database access library for Android.

example
-------

select

~~~~~ java
import static jp.dev7.android.query.Cursors.*;
import static jp.dev7.android.query.Queries.*;

...

SQLiteDatabase db = dbHelper.getReadableDatabase();
Cursor cursor = query("BOOK", selection("TITLE = ? OR AUTHOR = ?", title, author)).orderBy("ISBN").execute(db);

String isbn = string(cursor, "ISBN"); // String
int price = intValue(cursor, "PRICE", 0); // int, with default value
boolean outOfPrint = isTrue(cursor, "OUT_OF_PRINT"); // boolean
Date createdAt = date(cursor, "CREATED_AT"); // java.util.Date
~~~~~

insert

~~~~~ java
import static jp.dev7.android.query.Queries.*;

...

SQLiteDatabase db = dbHelper.getWritableDatabase();
insert(db, "BOOK",
    keyValue("ISBN", isbn), // String
    keyValue("TITLE", title), // String
    keyValue("AUTHOR", author), // String
    keyValue("CREATED_AT", new Date()), // java.util.Date
    keyValue("PRICE", 1200), // int
    keyValue("OUT_OF_PRINT", false) // boolean
);
~~~~~

update

~~~~~ java
import static jp.dev7.android.query.Queries.*;

...

SQLiteDatabase db = dbHelper.getWritableDatabase();
update(db, "BOOK",
    selection("ISBN = ?", isbn),
    keyValue("TITLE", title), // String
    keyValue("AUTHOR", author), // String
    keyValue("CREATED_AT", new Date()), // java.util.Date
    keyValue("PRICE", 1200), // int
    keyValue("OUT_OF_PRINT", false) // boolean
);
~~~~~

delete

~~~~~ java
import static jp.dev7.android.query.Queries.*;

...

SQLiteDatabase db = dbHelper.getWritableDatabase();
delete(db, "BOOK", selection("TITLE = ?", title));
~~~~~
