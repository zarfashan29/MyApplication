package com.app.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.app.myapplication.quizdetails.*;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class appDbHelper extends SQLiteOpenHelper {
    private static final String Database_name= "testwork.db";
    private static int Database_version = 1;
    private SQLiteDatabase db;


    public appDbHelper(@Nullable Context context) {
        super(context, Database_name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUIZ_TABLE = "CREATE TABLE " +
                quiztable.table_name + " ( "+
                quiztable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                quiztable.column_question + " TEXT, " +
                quiztable.column_option1 + " TEXT, " +
                quiztable.column_option2 + " TEXT, " +
                quiztable.column_answers + " INTEGER " +
                ")";
        db.execSQL(SQL_CREATE_QUIZ_TABLE);
        fillquiztable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + quiztable.table_name);
        onCreate(db);

    }
    private void fillquiztable(){
        quizwork q1 = new quizwork("Which language use for android development?", "Java", "php", 1);
        addquestion(q1);
        quizwork q2 = new quizwork("HTML is a programming language?", "No", "Yes", 2);
        addquestion(q2);
        quizwork q3 = new quizwork("customer feed is collected in", "agile", "waterfall", 3);
        addquestion(q3);
        quizwork q4 = new quizwork("constant mainatenece followed by?", "waterfall", "agile", 4);
        addquestion(q4);
        quizwork q5 = new quizwork("Swift is used for", "Android", "IOS", 5);
        addquestion(q5);

    }
    private void addquestion(quizwork Quizwork){
        ContentValues cv = new ContentValues();
        cv.put(quiztable.column_question, Quizwork.getQuestion());
        cv.put(quiztable.column_option1, Quizwork.getOption1());
        cv.put(quiztable.column_option2, Quizwork.getOption2());
        cv.put(quiztable.column_answers, Quizwork.getAnswers());
        db.insert(quiztable.table_name, null, cv);

    }
    public List<quizwork> getallquestions(){
        List<quizwork> questionlist = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + quiztable.table_name, null);
        if (c.moveToFirst()){
            do {
                quizwork Quizwork = new quizwork();
                Quizwork.setQuestion(c.getString(c.getColumnIndex(quiztable.column_question)));
                Quizwork.setOption1(c.getString(c.getColumnIndex(quiztable.column_option1)));
                Quizwork.setOption2(c.getString(c.getColumnIndex(quiztable.column_option2)));
                Quizwork.setAnswers(c.getInt(c.getColumnIndex(quiztable.column_answers)));
                questionlist.add(Quizwork);
            } while (c.moveToNext());
        }
        c.close();
        return questionlist;
    }
}
