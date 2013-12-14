android-query
=============

simple database access library for Android.

license
-------

The MIT License (MIT)

Copyright (c) 2013 daishi_hmr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.


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
