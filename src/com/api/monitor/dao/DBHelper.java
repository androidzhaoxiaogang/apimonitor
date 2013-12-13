package com.api.monitor.dao;


import com.api.monitor.dao.DataCenter.Papers;
import com.api.monitor.dao.DataCenter.QuestionInfos;
import com.api.monitor.dao.DataCenter.Questions;
import com.api.monitor.dao.DataCenter.Symbols;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "monitor.db";
	private static final int DATABASE_VERSION = 1;
	
	static interface Tables {
		String PAPERS = "papers";
        String QUESTIONS = "questions";
        String QUESTIONINFOS = "questioninfos";
        String SYMBOLS = "symbols";
    }
	 
    public DBHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		final StringBuilder paperBuilder = new StringBuilder();
		final StringBuilder questionInfoBuilder = new StringBuilder();
		final StringBuilder questionBuilder = new StringBuilder();
		final StringBuilder symbolBuilder = new StringBuilder();
		
		paperBuilder.append("CREATE TABLE ")
			.append(Tables.PAPERS)
			.append(" (")
			.append(Papers._ID)
			.append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
			.append(Papers.PAPER_ID)
			.append(" TEXT NOT NULL,")
			.append(Papers.USER_ID)
			.append(" TEXT NOT NULL,")
			.append(Papers.PAPER_NAME)
			.append(" TEXT,")
			.append(Papers.BQUESTION_ID)
			.append(" TEXT NOT NULL,")
			.append(Papers.BQUESTION_NAME)
			.append(" TEXT,")
			.append(Papers.BQUESTION_COUNT)
			.append(" INTEGER,")
			.append(Papers.BQUESTION_MARKED)
			.append(" INTEGER,")
			.append("UNIQUE (")
			.append(Papers.BQUESTION_ID)
			.append(") ON CONFLICT REPLACE)");
		
		questionInfoBuilder.append("CREATE TABLE ")
			.append(Tables.QUESTIONINFOS)
			.append(" (")
			.append(QuestionInfos._ID)
			.append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
			.append(QuestionInfos.BQUESTION_ID)
			.append(" TEXT NOT NULL,")
			.append(QuestionInfos.USER_ID)
			.append(" TEXT NOT NULL,")
			.append(QuestionInfos.SQUESTION_ID)
			.append(" TEXT,")
			.append(QuestionInfos.SQUESTION_SCORE)
			.append(" TEXT,")
			.append(QuestionInfos.SQUESTION_LEFT)
			.append(" INTEGER,")
			.append(QuestionInfos.SQUESTION_TOP)
			.append(" INTEGER,")
			.append(QuestionInfos.SQUESTION_RIGHT)
			.append(" INTEGER,")
			.append(QuestionInfos.SQUESTION_BOTTOM)
			.append(" INTEGER)");
		
		questionBuilder.append("CREATE TABLE ")
			.append(Tables.QUESTIONS)
			.append(" (")
			.append(Questions._ID)
			.append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
			.append(Questions.BQUESTION_ID)
			.append(" TEXT NOT NULL,")
			.append(Questions.SQUESTION_ID)
			.append(" TEXT NOT NULL,")
			.append(Questions.USER_ID)
			.append(" TEXT NOT NULL,")
			.append(Questions.BQUESTION_SCORE)
			.append(" TEXT,")
			.append(Questions.SQUESTION_SCORE)
			.append(" TEXT,")
			.append(Questions.SQUESTION_MAX_SCORE)
			.append(" TEXT,")
			.append(Questions.IMAGE_ID)
			.append(" TEXT,")
			.append(Questions.IMAGE_LEFT)
			.append(" INTEGER,")
			.append(Questions.IMAGE_TOP)
			.append(" INTEGER,")
			.append(Questions.IMAGE_RIGHT)
			.append(" INTEGER,")
			.append(Questions.IMAGE_BOTTOM)
			.append(" INTEGER,")
			.append(Questions.MARK_STATUS)
			.append(" INTEGER,")
			.append(Questions.SYNC_STATUS)
			.append(" INTEGER,")
			.append(Questions.INDEX)
			.append(" INTEGER,")
			.append(Questions.HAS_OPERATE)
			.append(" INTEGER,")
			.append(Questions.TIMESTMAP)
			.append(" TEXT)");
		System.out.println("======questionBuilder===kkkkk"+questionBuilder.toString());
//			.append("UNIQUE (")
//			.append(Questions.IMAGE_ID)
//			.append(") ON CONFLICT REPLACE)");
		
		symbolBuilder.append("CREATE TABLE ")
			.append(Tables.SYMBOLS)
			.append(" (")
			.append(Symbols._ID)
			.append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
			.append(Symbols.IMAGE_ID)
			.append(" TEXT NOT NULL,")
//			.append(Symbols.SQUESTION_ID)
//			.append(" TEXT NOT NULL,")
			.append(Symbols.SYMBOL_TYPE)
			.append(" INTEGER,")
			.append(Symbols.SYMBOL_X)
			.append(" INTEGER,")
			.append(Symbols.SYMBOL_Y)
			.append(" INTEGER)");
			
//			.append("UNIQUE (")
//			.append(Symbols.IMAGE_ID)
//			.append(") ON CONFLICT REPLACE)");
		
		db.execSQL(paperBuilder.toString());
		db.execSQL(questionInfoBuilder.toString());
		db.execSQL(questionBuilder.toString());
		db.execSQL(symbolBuilder.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		 db.execSQL("DROP TABLE IF EXISTS " + Tables.PAPERS);
		 db.execSQL("DROP TABLE IF EXISTS " + Tables.QUESTIONINFOS);
		 db.execSQL("DROP TABLE IF EXISTS " + Tables.QUESTIONS);
		 db.execSQL("DROP TABLE IF EXISTS " + Tables.SYMBOLS);
         onCreate(db);
	}
}
