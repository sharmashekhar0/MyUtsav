package com.example.myutsav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

public class budgetdb extends SQLiteOpenHelper {
    private static  final String dbname="userbudget.db";
    String auth= FirebaseAuth.getInstance().getCurrentUser().getUid();
    public budgetdb(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q="create table userbudget( userid text,Eventname text, totalbudget text,giftbudget text,decorationbudget text,cateringbudget text,carbusbudget text,expenditurebudget text,campingbudget text)";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userbudget");
        onCreate(db);
    }

    public Boolean insert_data(String eventname,String strtotal, String strgift, String strdecoration, String strcatering, String strcarbus, String strother, String strcamping) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c= new ContentValues();
        c.put("userid",auth);
        c.put("Eventname",eventname);
        c.put("totalbudget",strtotal);
        c.put("giftbudget",strgift);
        c.put("decorationbudget",strdecoration);
        c.put("cateringbudget",strcatering);
        c.put("carbusbudget",strcarbus);
        c.put("expenditurebudget",strother);
        c.put("campingbudget",strcamping);
        long  r= db.insert("userbudget",null,c);
        if(r==-1)
            return false;
        else
            return true;
    }
    public Cursor getInfo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.query("userbudget", new String[]{"userid,Eventname,totalbudget,giftbudget,decorationbudget,cateringbudget,carbusbudget,expenditurebudget,campingbudget"},"userid=?", new String[]{auth},null,null,null);
        return  c;
    }

    public boolean update_task(String eventname,String strtotal, String strgift, String strdecoration, String strcatering, String strcarbus, String strother, String strcamping)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("totalbudget",strtotal);
        c.put("giftbudget",strgift);
        c.put("decorationbudget",strdecoration);
        c.put("cateringbudget",strcatering);
        c.put("carbusbudget",strcarbus);
        c.put("expenditurebudget",strother);
        c.put("campingbudget",strcamping);
        Cursor cursor=db.rawQuery("select * from userbudget where userid=? and Eventname=?",new String[]{auth,eventname});
        if(cursor.getCount()>0)
        {
            long r=db.update("userbudget",c,"userid=? and Eventname=?",new String[]{auth,eventname});
            if (r==-1)
                return false;
            else
                return  true;
        }
        return false;
    }

    public Boolean deletetask(String eventname,String strtotal, String strgift, String strdecoration, String strcatering, String strcarbus, String strother, String strcamping) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from userbudget where userid=? and Eventname=?",new String[]{auth,eventname});
        if(cursor.getCount()>0)
        {
            long r=db.delete("userbudget","userid=? and Eventname=? ",new String[]{auth,eventname});
            if (r==-1)
                return false;
            else
                return  true;
        }
        return false;
    }
}
