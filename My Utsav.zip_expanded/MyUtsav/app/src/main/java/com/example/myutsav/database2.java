package com.example.myutsav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

public class database2 extends SQLiteOpenHelper {
    private static  final String dbname="usertask.dp";
    String auth=FirebaseAuth.getInstance().getCurrentUser().getUid();

    public database2(@Nullable Context context) {
        super(context, dbname, null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String q="create table usertask( userid text, taskname text,tasknote text,taskstatus text,taskcategar text)";
        db.execSQL(q);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists usertask");
        onCreate(db);

    }
    public  boolean insert_data(String userid,String taskname,String taskcat,String taskstatus,String tasknote)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c= new ContentValues();
        c.put("userid",userid);
        c.put("taskname",taskname);
        c.put("tasknote",tasknote);
        c.put("taskstatus",taskstatus);
        c.put("taskcategar",taskcat);
        long  r= db.insert("usertask",null,c);
        if(r==-1)
            return false;
        else
            return true;

}
    public Cursor getInfo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.query("usertask", new String[]{"userid,taskname,tasknote,taskstatus,taskcategar"},"userid=?", new String[]{auth},null,null,null);
        return  c;
    }
    public boolean deletetask(String strnote,String strstatus,String strname,String strcat)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from usertask where userid=? and taskname=? and taskcategar=?",new String[]{auth,strname,strcat});
        if(cursor.getCount()>0)
        {
            long r=db.delete("usertask","userid=? and taskname=? and taskcategar=?",new String[]{auth,strname,strcat});
            if (r==-1)
                return false;
            else
                return  true;
        }
        return false;
    }
    public boolean update_task(String updatenote,String updatestatus,String strname,String strcat)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("tasknote",updatenote);
        c.put("taskstatus",updatestatus);
        Cursor cursor=db.rawQuery("select * from usertask where userid=? and taskname=? and taskcategar=?",new String[]{auth,strname,strcat});
        if(cursor.getCount()>0)
        {
            long r=db.update("usertask",c,"userid=? and taskname=? and taskcategar=?",new String[]{auth,strname,strcat});
            if (r==-1)
                return false;
            else
                return  true;
        }
        return false;
    }
}
