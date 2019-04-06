package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyUserExec {
    private MyHelper myHelper;
    private SQLiteDatabase db;

    public MyUserExec(Context context){
        myHelper = new MyHelper(context);
        db =  myHelper.getWritableDatabase();
    }
    //增加用户方法
    public long addUser(User user){
        //第一种方式
        //db.execSQL("insert into myuser values(null,?,?)",new Object[]{user.getUname(), user.getUpwd()});

        //第二种方式
        ContentValues values = new ContentValues();
        values.put("uname", user.getUname());
        values.put("upwd", user.getUpwd());
        long row = db.insert("myuser",null ,values );
        return row;
    }

    //删除用户方法
    public long deleteUser(int id){
        //db.execSQL("delete from myuser where _id = " + id);
        long row = db.delete("myuser", "_id=?", new String[]{id + ""});
        return row;
    }
    //更新用户方法
    public long updateUser(User user){
        db.execSQL("update myuser set uname = ?, upwd = ? where _id = ?",new Object[]{user.getUid() + ""});
        ContentValues contentValues = new ContentValues();
        contentValues.put("uname", user.getUname());
        contentValues.put("upwd", user.getUpwd());
        long row = db.update("myuser",contentValues, "_id", new String[]{user.getUid()+""} );
        return row;
    }

    //单条查询
    public User showUserById(int uid){
        User user = null;
        Cursor cursor = db.rawQuery("select * from myuser where _id = ?" ,new String[]{uid+""});
        while(cursor.moveToFirst()){
            user = new User();
            String name = cursor.getString( cursor.getColumnIndex("uname"));
           user.setUname(name);
        }
        //db.query("myuser", , , , , , )
        return user;
    }
    //多条查询
    public List<User> showUsersById(){
        List<User> list = new ArrayList<User>();
        Cursor cursor = db.rawQuery("select * from myuser" , null);
        while(cursor.moveToNext()){
            User user = new User();
            String name = cursor.getString( cursor.getColumnIndex("uname"));
            user.setUname(name);
            list.add(user);
        }
        return list;
    }
    //返回Cursor对象，是SimpleCursorAdapter的数据源
    public Cursor getCursor(){
        return db.rawQuery("select * from myuser", null);
    }
}
