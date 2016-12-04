package com.example.evertocastro.quizappgo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.evertocastro.quizappgo.models.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Everto Castro on 27/11/2016.
 */

public class DBQuestion extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "quizManager";

    private static final String TABLE_QUESTION = "question";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ITEM_1 = "item1";
    private static final String KEY_ITEM_2 = "item2";
    private static final String KEY_ITEM_3 = "item3";
    private static final String KEY_ITEM_4 = "item4";
    private static final String KEY_ITEM_CORRECT = "itemCorrect";

    public DBQuestion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_QUESTION_TABLE = "CREATE TABLE "+TABLE_QUESTION+" ("
                +KEY_ID+" INTEGER PRIMARY KEY, "+ KEY_IMAGE +" TEXT, "+KEY_QUESTION+" TEXT, "+ KEY_ITEM_1 +" TEXT, "+ KEY_ITEM_2 +" TEXT, "
                + KEY_ITEM_3 +" TEXT, "+ KEY_ITEM_4 +" TEXT, "+KEY_ITEM_CORRECT+" INTEGER)";
        sqLiteDatabase.execSQL(CREATE_QUESTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,  int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, question.getImage());
        values.put(KEY_QUESTION, question.getQuestion());
        values.put(KEY_ITEM_1, question.getItem1());
        values.put(KEY_ITEM_2, question.getItem2());
        values.put(KEY_ITEM_3, question.getItem3());
        values.put(KEY_ITEM_4, question.getItem4());
        values.put(KEY_ITEM_CORRECT, question.getItemCorrect());

        // Inserting Row
        db.insert(TABLE_QUESTION, null, values);
        db.close(); // Closing database connection
    }

    // Getting single question
    public Question getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUESTION, new String[] { KEY_ID,
                        KEY_QUESTION}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Question question = new Question( Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getString(6), Integer.parseInt(cursor.getString(7)));
        // return question
        cursor.close();
        return question;
    }

    // Getting All Questions
    public List<Question> getAllQuestion() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Question> questionList = new ArrayList<Question>();
        // Select All Query
        Cursor cursor = db.query(TABLE_QUESTION, new String[] {KEY_ID, KEY_IMAGE,
                KEY_QUESTION, KEY_ITEM_1, KEY_ITEM_2, KEY_ITEM_3, KEY_ITEM_4,  KEY_ITEM_CORRECT}, null, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(0)));
                question.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
                question.setItem1(cursor.getString(cursor.getColumnIndex(KEY_ITEM_1)));
                question.setItem2(cursor.getString(cursor.getColumnIndex(KEY_ITEM_2)));
                question.setItem3(cursor.getString(cursor.getColumnIndex(KEY_ITEM_3)));
                question.setItem4(cursor.getString(cursor.getColumnIndex(KEY_ITEM_4)));
                question.setItemCorrect(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ITEM_CORRECT))));

                // Adding question to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return questionList;
    }

    // Updating single question
    public int updateQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, question.getImage());
        values.put(KEY_QUESTION, question.getQuestion());
        values.put(KEY_ITEM_1, question.getItem1());
        values.put(KEY_ITEM_2, question.getItem2());
        values.put(KEY_ITEM_3, question.getItem3());
        values.put(KEY_ITEM_4, question.getItem4());
        values.put(KEY_ITEM_CORRECT, question.getItemCorrect());

        // updating row
        return db.update(TABLE_QUESTION, values, KEY_ID + " = ?",
                new String[] { String.valueOf(question.getId()) });
    }

    // Deleting single question
    public void deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTION, KEY_ID + " = ?",
                new String[] { String.valueOf(question.getId()) });
        db.close();
    }

    //query with random
    //select question with qtde choose
    public List<Question> selectQuestion(int qtde){
        List<Question> questionList = new ArrayList<Question>();
        String selectQuery = "SELECT * FROM "+TABLE_QUESTION+" ORDER BY RANDOM() LIMIT "+qtde;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(0)));
                question.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
                question.setItem1(cursor.getString(cursor.getColumnIndex(KEY_ITEM_1)));
                question.setItem2(cursor.getString(cursor.getColumnIndex(KEY_ITEM_2)));
                question.setItem3(cursor.getString(cursor.getColumnIndex(KEY_ITEM_3)));
                question.setItem4(cursor.getString(cursor.getColumnIndex(KEY_ITEM_4)));
                question.setItemCorrect(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ITEM_CORRECT))));

                // Adding question to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return questionList;

    }



}
