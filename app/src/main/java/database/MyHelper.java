package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context, "mysql.db", null, 1);
    }

    /*
    * 第一次操作数据库时调用
    */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table myUser(_id integer primary key autoIncrement, uname varchar(50), password varchar(50))");
    }

    /*版本号发生变化时调用*/
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
