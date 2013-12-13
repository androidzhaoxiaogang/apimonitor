package com.api.monitor.dao;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataCenter {

	public static final String CONTENT_AUTHORITY = "com.api.monitor";
	private static final Uri DATA_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
	
	interface PaperColumns {
		String USER_ID = "user_id";
		String PAPER_ID = "paper_id";
		String PAPER_NAME = "paper_name";
		String BQUESTION_ID = "big_question_id";
		String BQUESTION_NAME = "big_question_name";
		String BQUESTION_COUNT = "big_question_count";
		String BQUESTION_MARKED = "big_question_marked";
	}
	
	interface QuestionInfoColumns {
		String USER_ID = "user_id";
		String BQUESTION_ID = "big_question_id";
		String SQUESTION_ID = "small_question_id";
		String SQUESTION_SCORE = "small_question_score";
		String SQUESTION_LEFT = "small_question_left";
		String SQUESTION_TOP = "small_question_top";
		String SQUESTION_RIGHT = "small_question_right";
		String SQUESTION_BOTTOM = "small_question_bottom";
	}
	
	interface QuestionColumns {
		String USER_ID = "user_id";
		String BQUESTION_ID = "big_question_id";
		String SQUESTION_ID = "small_question_id";
		String SQUESTION_SCORE = "small_question_score";
		String BQUESTION_SCORE = "big_question_score";
		String SQUESTION_MAX_SCORE = "small_question_max_score";
		
		String IMAGE_ID = "image_id";
		String IMAGE_LEFT = "image_left";
		String IMAGE_TOP = "image_top";
		String IMAGE_RIGHT = "image_right";
		String IMAGE_BOTTOM = "image_bittom";
		
		String MARK_STATUS = "mark_status";
		String SYNC_STATUS = "sync_status";
		String INDEX = "image_index";
		String HAS_OPERATE = "has_operate";//是否本地操作过，判断是否是一键下载的试题
		String TIMESTMAP = "timestamp";
	}
	
	interface SymbolColumns {
		String IMAGE_ID = "image_id";
//		String SQUESTION_ID = "small_question_id";
		String SYMBOL_TYPE = "symbol_type";
		String SYMBOL_X = "symbol_x";
		String SYMBOL_Y = "symbol_y";
	}

	public static class Papers implements BaseColumns, PaperColumns {
		public static final String PATH = "paper";
		public static final Uri CONTENT_URI = DATA_URI.buildUpon().appendPath(PATH).build();
		public static final String CONTENT_TYPE ="vnd.android.cursor.dir/vnd.edu.marker";
        public static final String CONTENT_MESSAGE_TYPE ="vnd.android.cursor.item/vnd.edu.marker";
	}
	
	public static class QuestionInfos implements BaseColumns, QuestionInfoColumns {
		public static final String PATH = "questioninfo";
		public static final Uri CONTENT_URI = DATA_URI.buildUpon().appendPath(PATH).build();
		public static final String CONTENT_TYPE ="vnd.android.cursor.dir/vnd.edu.marker";
        public static final String CONTENT_MESSAGE_TYPE ="vnd.android.cursor.item/vnd.edu.marker";
	}
	
	public static class Questions implements BaseColumns, QuestionColumns {
		public static final String PATH = "question";
		public static final Uri CONTENT_URI = DATA_URI.buildUpon().appendPath(PATH).build();
		public static final String CONTENT_TYPE ="vnd.android.cursor.dir/vnd.edu.marker";
        public static final String CONTENT_MESSAGE_TYPE ="vnd.android.cursor.item/vnd.edu.marker";
	}
	
	public static class Symbols implements BaseColumns, SymbolColumns {
		public static final String PATH = "symbol";
		public static final Uri CONTENT_URI = DATA_URI.buildUpon().appendPath(PATH).build();
		public static final String CONTENT_TYPE ="vnd.android.cursor.dir/vnd.edu.marker";
        public static final String CONTENT_MESSAGE_TYPE ="vnd.android.cursor.item/vnd.edu.marker";
	}
}
