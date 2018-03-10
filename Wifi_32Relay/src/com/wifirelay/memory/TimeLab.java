package com.wifirelay.memory;

import com.wifirelay.entity.Time;

import android.content.Context;

public class TimeLab {
	private static final String FILENAME = "time.json";
	public static Time Time;
	private JSONSerializer mSerializer;
	private static TimeLab sTimeLab;
	private Context mAppContext;
	
	private TimeLab(Context appContext){
		mAppContext = appContext;
		mSerializer = new JSONSerializer(mAppContext, FILENAME);
		try{
			Time = (Time)mSerializer.loadJSON(Time.class);
		}catch(Exception e){
			e.printStackTrace();
			Time = new Time();
		}
	}
	
	public boolean saveTime() {
		try{
			mSerializer.saveJSON((Object)Time, Time.class);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static TimeLab get(Context c){
		if(sTimeLab == null){
			sTimeLab = new TimeLab(c.getApplicationContext());
		}
		return sTimeLab;
	}
	
	public Time getTime() {
		return Time;
	}
	
}
