package com.example.myutsav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

public class database3 extends SQLiteOpenHelper {
    private static  final String dbname="userguest.dp";
    String auth=FirebaseAuth.getInstance().getCurrentUser().getUid();

    public database3(@Nullable Context context) {
        super(context, dbname, null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String q="create table userguest( userid text,guestname text,guestnumber text,guestaddress text,gueststatus text)";
        db.execSQL(q);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists userguest");
        onCreate(db);

    }
    public  boolean insert_data(String userid,String guestname,String guestnumber,String guestaddress,String gueststatus)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c= new ContentValues();
        c.put("userid",userid);
        c.put("guestname",guestname);
        c.put("guestaddress",guestaddress);
        c.put("guestnumber",guestnumber);
        c.put("gueststatus",gueststatus);
        long  r= db.insert("userguest",null,c);
        if(r==-1)
            return false;
        else
            return true;

    }
    public Cursor getInfo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.query("userguest", new String[]{"userid,guestname,guestnumber,guestaddress,gueststatus"},"userid=?", new String[]{auth},null,null,null);
        return  c;
    }

    public Boolean update_task(String updateaddress, String strname, String strnumber,String updatestatus) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("guestaddress",updateaddress);
        c.put("gueststatus",updatestatus);
        Cursor cursor=db.rawQuery("select * from userguest where userid=? and guestname=? and guestnumber=?",new String[]{auth,strname,strnumber});
        if(cursor.getCount()>0)
        {
            long r=db.update("userguest",c,"userid=? and guestname=? and guestnumber=?",new String[]{auth,strname,strnumber});
            if (r==-1)
                return false;
            else
                return  true;
        }
        return false;
    }

    public Boolean deletetask(String strnumber,String strname) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from userguest where userid=? and guestname=? and guestnumber=?",new String[]{auth,strname,strnumber});
        if(cursor.getCount()>0)
        {
            long r=db.delete("userguest","userid=? and guestname=? and guestnumber=?",new String[]{auth,strname,strnumber});
            if (r==-1)
                return false;
            else
                return  true;
        }
        return false;
    }
    //For guest data insert
}
