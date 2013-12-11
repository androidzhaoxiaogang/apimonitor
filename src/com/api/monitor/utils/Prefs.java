package com.api.monitor.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Prefs {
	public static void setStrValue(final Editor editor, String key,String value) {
		editor.putString(key, value).commit();
	}
	
	public static void setStrValue(Context context,String key,String value){
		final SharedPreferences shpfs = PreferenceManager.getDefaultSharedPreferences(context);
		shpfs.edit().putString(key, value).commit();  
	}
	
	public static String getStrValue(Context context, String key,String defaultValue) {
		final SharedPreferences shpfs = PreferenceManager.getDefaultSharedPreferences(context);
		return shpfs.getString(key, defaultValue);
	}
	
	public static void setIntValue(final Editor editor, String key,int value) {
		editor.putInt(key, value).commit();
	}
	
	public static void setIntValue(Context context,String key,int value) {
		final SharedPreferences shpfs = PreferenceManager.getDefaultSharedPreferences(context);
		shpfs.edit().putInt(key, value).commit();
	}
	
	public static int getIntValue(Context context, String key) {
		final SharedPreferences shpfs = PreferenceManager.getDefaultSharedPreferences(context);
		return shpfs.getInt(key, 0);
	}

	public static void setBoolValue(final Editor editor, String key,boolean value) {
		editor.putBoolean(key, value).commit();
	}
	
	public static void setBoolValue(Context context, String key,boolean value) {
		final SharedPreferences shpfs = PreferenceManager.getDefaultSharedPreferences(context);
		shpfs.edit().putBoolean(key, value).commit();
	}
	
	public static boolean getBoolValue(Context context, String key,boolean defaultValue) {
		final SharedPreferences shpfs = PreferenceManager.getDefaultSharedPreferences(context);
		return shpfs.getBoolean(key, defaultValue);
	}
	
	public static void setLongValue(final Editor editor, String key,long value) {
		editor.putLong(key, value).commit();
	}
	
	public static void setLongValue(Context context, String key,long value) {
		final SharedPreferences shpfs = PreferenceManager.getDefaultSharedPreferences(context);
		shpfs.edit().putLong(key, value).commit();
	}
	
	public static long getLongValue(Context context, String key,long defaultValue) {
		final SharedPreferences shpfs = PreferenceManager.getDefaultSharedPreferences(context);
		return shpfs.getLong(key, defaultValue);
	}
}
