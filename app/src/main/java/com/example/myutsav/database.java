package com.example.myutsav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

public class database extends SQLiteOpenHelper {
     private static  final String dbname="user.dp";
    String auth=FirebaseAuth.getInstance().getCurrentUser().getUid();

    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String q="create table userdata( userid text, eventname text,eventdate text,eventnote text,eventstatus text,eventcategar text)";
        db.execSQL(q);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists userdata");
        onCreate(db);

    }
    public  boolean insert_data(String userid,String eventname,String eventdate,String eventcat,String eventstatus,String eventnote)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c= new ContentValues();
        c.put("userid",userid);
        c.put("eventname",eventname);
        c.put("eventdate",eventdate);
        c.put("eventnote",eventnote);
        c.put("eventstatus",eventstatus);
        c.put("eventcategar",eventcat);
        long  r= db.insert("userdata",null,c);
        if(r==-1)
            return false;
        else
            return true;

    }
    public Cursor getInfo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.query("userdata", new String[]{"userid,eventname,eventdate,eventnote,eventstatus,eventcategar"},"userid=?", new String[]{auth},null,null,null);
        return  c;
    }

    public Boolean update_task(String updatenote, String updatestatus, String strname, String strcat,String updatedate) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("eventnote",updatenote);
        c.put("eventstatus",updatestatus);
        c.put("eventdate",updatedate);
        Cursor cursor=db.rawQuery("select * from userdata where userid=? and eventname=? and eventcategar=?",new String[]{auth,strname,strcat});
        if(cursor.getCount()>0)
        {
            long r=db.update("userdata",c,"userid=? and eventname=? and eventcategar=?",new String[]{auth,strname,strcat});
            if (r==-1)
                return false;
            else
                return  true;
        }
        return false;
    }

    public Boolean deletetask(String strname, String strcat) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from userdata where userid=? and eventname=? and eventcategar=?",new String[]{auth,strname,strcat});
        if(cursor.getCount()>0)
        {
            long r=db.delete("userdata","userid=? and eventname=? and eventcategar=?",new String[]{auth,strname,strcat});
            if (r==-1)
                return false;
            else
                return  true;
        }
        return false;
    }
    //For guest data insert
}
