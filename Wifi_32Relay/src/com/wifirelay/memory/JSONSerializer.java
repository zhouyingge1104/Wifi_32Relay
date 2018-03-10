package com.wifirelay.memory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.json.JSONException;
import org.json.JSONObject;

import com.wifirelay.entity.Time;

import android.content.Context;

/** �����JSONSerializer������һ����ͨ�õķ��������ߣ��࣬��Ӧ���еĶ���ʵ�嶼�����������д�ļ� */
public class JSONSerializer {
	private Context mContext;
	private String mFilename;
	
	public JSONSerializer(Context c, String f){
		mContext = c;
		mFilename = f;
	}
	
	@SuppressWarnings("rawtypes")
	public Object loadJSON(Class classType) throws IOException, JSONException{
		Object obj = null;
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null){
				jsonString.append(line);
			}
			
			JSONObject json = (JSONObject) new JSONObject(jsonString.toString());
			
			if(classType == Time.class) {
				obj = (Object)new Time(json);
			}
			
		
		}catch(FileNotFoundException e){
		}finally{
			if(reader != null){
				reader.close();
			}
		}
		return obj;
	}
	
	@SuppressWarnings("rawtypes")
	public void saveJSON(Object obj, Class classType) throws JSONException, IOException{
		JSONObject json = new JSONObject();
		
		if(classType == Time.class) {
			json = ((Time)obj).toJSON();
		}
		
		Writer writer = null;
		try{
			OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(json.toString());
		}finally{
			if(writer != null){ writer.close(); }
		}
	}
	
}
