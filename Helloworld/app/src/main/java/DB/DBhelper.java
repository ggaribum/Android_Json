package DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Vo.MemberVo;

/**
 * Created by Administrator on 2017-07-11.
 */

public class DBhelper extends SQLiteOpenHelper
{
    ArrayList<MemberVo> loginList = new ArrayList<MemberVo>();
    ArrayList<MemberVo> memberList = new ArrayList<MemberVo>();

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL("create table MEMBER (no INTEGER PRIMARY KEY AUTOINCREMENT, Name text, Id text, Pw text, Email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {

    }

    public void insert(String name, String id, String pw, String email)
    {
        SQLiteDatabase database = getWritableDatabase();
        String query = "insert into MEMBER values(null,'"+name+"','"+id+"','"+pw+"','"+email+"')";
        database.execSQL(query);
        database.close();
    }

    public MemberVo LoginMember(String id)
    {
        SQLiteDatabase database = getReadableDatabase();
        String query="select * from MEMBER where Id='"+id+"'";

        Cursor cursor = database.rawQuery(query,null);
        String n=cursor.getString(1);//n=name담기
        String i=cursor.getString(2);//i=id담기
        String p=cursor.getString(3);//p=pw담기
        String e=cursor.getString(4);//e=email담기
        MemberVo loginInfo = new MemberVo(n,i,p,e);
        return loginInfo;
    }

    public ArrayList<MemberVo> readAllData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MEMBER", null);
        while (cursor.moveToNext()) {
            memberList.add(new MemberVo(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
        }
        return memberList;
    }


    public String dataLoad() throws Exception{

        URL url = new URL("http://ec2-52-26-144-160.us-west-2.compute.amazonaws.com:3000/jiminzzang");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        InputStream in = conn.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try
        {
            byte[] buf = new byte[1024 * 8];
            int length = 0;
            while((length = in.read(buf)) != -1){
                out.write(buf, 0, length);
            }
            return new String(out.toByteArray(), "UTF-8");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "NULL";
    }


    //데이터베이스 모두 삭제.
   /* public void deletemem()
    {
        SQLiteDatabase database = getWritableDatabase();
        String query="delete from MEMBER";
        database.execSQL(query);
        database.close();
    }*/


    //데이터베이스에 있는 내용 모두 출력
 /*   public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MEMBER", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " . NAME :  "
                    + cursor.getString(1)
                    + " ID : "
                    + cursor.getString(2)
                    + " PW : "
                    + cursor.getString(3)
                    + "EMAIL \n"
                    + cursor.getString(4);
        }
        return result;
    }*/
}
