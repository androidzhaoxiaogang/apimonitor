package com.api.monitor.dao;

import com.api.monitor.dao.DBHelper.Tables;
import com.api.monitor.dao.DataCenter.Papers;
import com.api.monitor.dao.DataCenter.QuestionInfos;
import com.api.monitor.dao.DataCenter.Questions;
import com.api.monitor.dao.DataCenter.Symbols;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MonitorProvider extends ContentProvider {
	private static UriMatcher sUriMatcher = buildUriMatcher();
	
	private static final int PAPERS = 0;
	private static final int QUESTIONINFOS = 1;
	private static final int QUESTIONS = 2;
	private static final int SYMBOLS = 3;
	
	private DBHelper mDBHelper;
	
	private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DataCenter.CONTENT_AUTHORITY;
        matcher.addURI(authority, Papers.PATH, PAPERS);
        matcher.addURI(authority, QuestionInfos.PATH, QUESTIONINFOS);
        matcher.addURI(authority, Questions.PATH, QUESTIONS);
        matcher.addURI(authority, Symbols.PATH, SYMBOLS);
        return matcher;
    }
	
	
	@Override
	public int delete(Uri uri, String arg1, String[] arg2) {
		final SQLiteDatabase db = mDBHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        switch(match){
        case PAPERS:
        	return db.delete(Tables.PAPERS, arg1, arg2);
        case QUESTIONINFOS:
        	return db.delete(Tables.QUESTIONINFOS, arg1, arg2);
        case QUESTIONS:
        	return db.delete(Tables.QUESTIONS, arg1, arg2);
        case SYMBOLS:
        	return db.delete(Tables.SYMBOLS, arg1, arg2);
        default:
			throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
	}

	@Override
	public String getType(Uri uri) {
		final int match = sUriMatcher.match(uri);
		switch(match){
		case PAPERS:
			return Papers.CONTENT_TYPE;
		case QUESTIONINFOS:
			return QuestionInfos.CONTENT_TYPE;
		case QUESTIONS:
			return Questions.CONTENT_TYPE;
		case SYMBOLS:
			return Symbols.CONTENT_TYPE;
		default:
			throw new UnsupportedOperationException("Unknown uri: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch(match){
        case PAPERS:
        	db.insertOrThrow(Tables.PAPERS, null, values);
        	getContext().getContentResolver().notifyChange(uri, null, false);
        	return uri.buildUpon().appendPath(values.getAsString(Papers.BQUESTION_ID)).build();
        case QUESTIONINFOS:
        	db.insertOrThrow(Tables.QUESTIONINFOS, null, values);
        	getContext().getContentResolver().notifyChange(uri, null, false);
        	return uri.buildUpon().appendPath(values.getAsString(QuestionInfos.SQUESTION_ID)).build();
        case QUESTIONS:
        	db.insertOrThrow(Tables.QUESTIONS, null, values);
        	getContext().getContentResolver().notifyChange(uri, null, false);
        	return uri.buildUpon().appendPath(values.getAsString(Questions.BQUESTION_ID)).build();
        case SYMBOLS:
        	db.insertOrThrow(Tables.SYMBOLS, null, values);
        	getContext().getContentResolver().notifyChange(uri, null, false);
        	return uri.buildUpon().appendPath(values.getAsString(Symbols.IMAGE_ID)).build();
        default:
			throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
	}

	@Override
	public boolean onCreate() {
		mDBHelper = new DBHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, 
			String[] selectionArgs, String sortOrder) {
		final SQLiteDatabase db = mDBHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        switch(match){
        case PAPERS:
        	return db.query(Tables.PAPERS, projection, selection, 
        			selectionArgs, null, null, sortOrder);
        case QUESTIONINFOS:
        	return db.query(Tables.QUESTIONINFOS, projection, selection, 
        			selectionArgs, null, null, sortOrder);
        case QUESTIONS:
        	return db.query(Tables.QUESTIONS, projection, selection, 
        			selectionArgs, null, null, sortOrder);
        case SYMBOLS:
        	return db.query(Tables.SYMBOLS, projection, selection, 
        			selectionArgs, null, null, sortOrder);
        default:
			throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
	}

	@Override
	public int update(Uri uri, ContentValues values, String arg2, String[] arg3) {
		final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch(match){
        case PAPERS:
        	return db.update(Tables.PAPERS, values, arg2, arg3);
        case QUESTIONINFOS:
        	return db.update(Tables.QUESTIONINFOS, values, arg2, arg3);
        case QUESTIONS:
        	return db.update(Tables.QUESTIONS, values, arg2, arg3);
        case SYMBOLS:
        	return db.update(Tables.SYMBOLS, values, arg2, arg3);
        default:
			throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
	}
}
