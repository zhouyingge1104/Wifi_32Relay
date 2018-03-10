package com.wifirelay;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.wifi_relay.R;
import com.wifirelay.entity.Time;
import com.wifirelay.memory.TimeLab;
import com.wifirelay.util.StringUtil;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private int af1,af2,af3,af4,af5,af6,af7,af8,af9,af10,af11,af12,af13,af14,af15,af16,af17,af18,af19,af20,af21,af22,af23,af24,af25,af26,af27,af28,
	            af29,af30,af31,af32,af33;
	private final int KEY1=1,KEY2=2,KEY3=3,KEY4=4,KEY5=5,KEY6=6,KEY8=8,KEY9=9,KEY10=10,KEY11=11,KEY12=12,KEY13=13,KEY14=14,KEY15=15,KEY16=16,KEY17=17,
			          KEY18=18,KEY19=19,KEY20=20,KEY21=21,KEY22=22,KEY23=23,KEY24=24,KEY25=25,KEY26=26,KEY27=27,KEY28=28,KEY29=29,KEY30=30,KEY31=31,
			          KEY32=32,KEY33=33,KEY7=7;
	private final int UPDATE_UI = 34;
	private final int LG_OK = 35, SF_OK = 36;
	private ImageButton but1=null,but2=null,but3=null,but4=null,but5=null,but6=null,but7=null,but8=null,but9=null,but10=null,but11=null,but12=null,
			            but13=null,but14=null,but15=null,but16=null,but17=null,but18=null,but19=null,but20=null,but21=null,but22=null,but23=null,
			            but24=null,but25=null,but26=null,but27=null,but28=null,but29=null,but30=null,but31=null,but32=null,but33=null,but34=null,
			            butLG=null,butSF=null;
	private LinearLayout layoutTime = null;
	private EditText ServerIp = null;
	private EditText ServerPort = null;
	//private EditText fhz = null;
	private TextView tvTotalTime, tvSF;
	
	private CountDownTimer countDownTimer = null;
	
	private EditText delaytime1,delaytime2,delaytime3,delaytime4,delaytime5,delaytime6,delaytime7,delaytime8,delaytime9,delaytime10,delaytime11,delaytime12,
	                 delaytime13,delaytime14,delaytime15,delaytime16,delaytime17,delaytime18,delaytime19,delaytime20,delaytime21,delaytime22,delaytime23,
	                 delaytime24,delaytime25,delaytime26,delaytime27,delaytime28,delaytime29,delaytime30,delaytime31,delaytime32,delaytime33;
	private InputStream ips=null;
    private OutputStream ops=null;
	private Socket mSocket=null;
	private byte[] back = new byte[12], backSF = new byte[10]; //backSF针对后来新加的土壤水分协议
	//private String s1="";
	private String value1 = "",value2 = "",value3 = "",value4 = "",value5 = "",value6 = "",value7 = "",value8 = "",value9 = "",value10 = "",
			       value11 = "",value12 = "",value13 = "",value14 = "",value15 = "",value16 = "",value17 = "",value18 = "",value19 = "",value20 = "",
			       value21 = "",value22 = "",value23 = "",value24 = "",value25 = "",value26 = "",value27 = "",value28 = "",value29 = "",value30 = "",
			       value31 = "",value32 = "",value33 = "";
	//private int i;
	private int int_r1,int_r2,int_r3,int_r4,int_r5,int_r6,int_r7,int_r8,int_r9,int_r10,int_r11,int_r12,int_r13,int_r14,int_r15,int_r16,int_r17,int_r18,
	            int_r19,int_r20,int_r21,int_r22,int_r23,int_r24,int_r25,int_r26,int_r27,int_r28,int_r29,int_r30,int_r31,int_r32,int_r33;
	
	//轮灌部分命令（33个时间）
	private Time time;
	
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) { 
		switch (msg.what) {
		case UPDATE_UI: {
			layoutTime.setVisibility(View.VISIBLE);
			//轮灌按钮变为可用
			butLG.setVisibility(View.VISIBLE);
			butLG.setClickable(true);
			
			//s1=""; for(i=0;i<back.length;i++){s1=s1+byteToString(back[i])+" "; }//fhz.setText(s1);
			if((back[5]&1)!=0){af1=0;but1.setImageDrawable(getResources().getDrawable(R.drawable.key1_2));}  //1					
						  else{af1=1;but1.setImageDrawable(getResources().getDrawable(R.drawable.key1_3));}
			if((back[5]&2)!=0){af2=0;but2.setImageDrawable(getResources().getDrawable(R.drawable.key2_2));}  //2
						  else{af2=1;but2.setImageDrawable(getResources().getDrawable(R.drawable.key2_3));}
			if((back[5]&4)!=0){af3=0;but3.setImageDrawable(getResources().getDrawable(R.drawable.key3_2));}  //3
						  else{af3=1;but3.setImageDrawable(getResources().getDrawable(R.drawable.key3_3));} 
			if((back[5]&8)!=0){af4=0;but4.setImageDrawable(getResources().getDrawable(R.drawable.key4_2));}  //4
						  else{af4=1;but4.setImageDrawable(getResources().getDrawable(R.drawable.key4_3));}
			if((back[5]&16)!=0){af5=0;but5.setImageDrawable(getResources().getDrawable(R.drawable.key5_2));}  //5
						   else{af5=1;but5.setImageDrawable(getResources().getDrawable(R.drawable.key5_3));}
			if((back[5]&32)!=0){af6=0;but6.setImageDrawable(getResources().getDrawable(R.drawable.key6_2));}  //6
						   else{af6=1;but6.setImageDrawable(getResources().getDrawable(R.drawable.key6_3));} 
			
			if((back[6]&1)!=0){af7=0;but7.setImageDrawable(getResources().getDrawable(R.drawable.key7_2));}   //7					
						  else{af7=1;but7.setImageDrawable(getResources().getDrawable(R.drawable.key7_3));}
			if((back[6]&2)!=0){af8=0;but8.setImageDrawable(getResources().getDrawable(R.drawable.key8_2));}   //8
						  else{af8=1;but8.setImageDrawable(getResources().getDrawable(R.drawable.key8_3));}
			if((back[6]&4)!=0){af9=0;but9.setImageDrawable(getResources().getDrawable(R.drawable.key9_2));}   //9
						  else{af9=1;but9.setImageDrawable(getResources().getDrawable(R.drawable.key9_3));} 
			if((back[6]&8)!=0){af10=0;but10.setImageDrawable(getResources().getDrawable(R.drawable.key10_2));}//10
						  else{af10=1;but10.setImageDrawable(getResources().getDrawable(R.drawable.key10_3));}
			if((back[6]&16)!=0){af11=0;but11.setImageDrawable(getResources().getDrawable(R.drawable.key11_2));}//11
						   else{af11=1;but11.setImageDrawable(getResources().getDrawable(R.drawable.key11_3));}
			if((back[6]&32)!=0){af12=0;but12.setImageDrawable(getResources().getDrawable(R.drawable.key12_2));}//12
						  else{af12=1;but12.setImageDrawable(getResources().getDrawable(R.drawable.key12_3));} 
			if((back[6]&64)!=0){af13=0;but13.setImageDrawable(getResources().getDrawable(R.drawable.key13_2));}//13					
			              else{af13=1;but13.setImageDrawable(getResources().getDrawable(R.drawable.key13_3));}
			if((back[6]&128)!=0){af14=0;but14.setImageDrawable(getResources().getDrawable(R.drawable.key14_2));}//14
						  else{af14=1;but14.setImageDrawable(getResources().getDrawable(R.drawable.key14_3));}
			
			if((back[7]&1)!=0){af15=0;but15.setImageDrawable(getResources().getDrawable(R.drawable.key15_2));}//15
						  else{af15=1;but15.setImageDrawable(getResources().getDrawable(R.drawable.key15_3));} 
			if((back[7]&2)!=0){af16=0;but16.setImageDrawable(getResources().getDrawable(R.drawable.key16_2));}//16
						  else{af16=1;but16.setImageDrawable(getResources().getDrawable(R.drawable.key16_3));}
			if((back[7]&4)!=0){af17=0;but17.setImageDrawable(getResources().getDrawable(R.drawable.key17_2));}//17
						   else{af17=1;but17.setImageDrawable(getResources().getDrawable(R.drawable.key17_3));}
			if((back[7]&8)!=0){af18=0;but18.setImageDrawable(getResources().getDrawable(R.drawable.key18_2));}//18
						  else{af18=1;but18.setImageDrawable(getResources().getDrawable(R.drawable.key18_3));} 
			if((back[7]&16)!=0){af19=0;but19.setImageDrawable(getResources().getDrawable(R.drawable.key19_2));}//19					
						  else{af19=1;but19.setImageDrawable(getResources().getDrawable(R.drawable.key19_3));}
			if((back[7]&32)!=0){af20=0;but20.setImageDrawable(getResources().getDrawable(R.drawable.key20_2));}//20
						  else{af20=1;but20.setImageDrawable(getResources().getDrawable(R.drawable.key20_3));}
			
			if((back[8]&1)!=0){af21=0;but21.setImageDrawable(getResources().getDrawable(R.drawable.key21_2));}//21
						  else{af21=1;but21.setImageDrawable(getResources().getDrawable(R.drawable.key21_3));} 
			if((back[8]&2)!=0){af22=0;but22.setImageDrawable(getResources().getDrawable(R.drawable.key22_2));}//22
						  else{af22=1;but22.setImageDrawable(getResources().getDrawable(R.drawable.key22_3));}
			if((back[8]&4)!=0){af23=0;but23.setImageDrawable(getResources().getDrawable(R.drawable.key23_2));}//23
						   else{af23=1;but23.setImageDrawable(getResources().getDrawable(R.drawable.key23_3));}
			if((back[8]&8)!=0){af24=0;but24.setImageDrawable(getResources().getDrawable(R.drawable.key24_2));}//24
						  else{af24=1;but24.setImageDrawable(getResources().getDrawable(R.drawable.key24_3));} 
   		    if((back[8]&16)!=0){af25=0;but25.setImageDrawable(getResources().getDrawable(R.drawable.key25_2));}  //25
			              else{af25=1;but25.setImageDrawable(getResources().getDrawable(R.drawable.key25_3));}
			if((back[8]&32)!=0){af26=0;but26.setImageDrawable(getResources().getDrawable(R.drawable.key26_2));}  //26
			              else{af26=1;but26.setImageDrawable(getResources().getDrawable(R.drawable.key26_3));}		
			if((back[8]&64)!=0){af27=0;but27.setImageDrawable(getResources().getDrawable(R.drawable.key27_2));}  //27
			              else{af27=1;but27.setImageDrawable(getResources().getDrawable(R.drawable.key27_3));}	
			if((back[8]&128)!=0){af28=0;but28.setImageDrawable(getResources().getDrawable(R.drawable.key28_2));}  //28
                          else{af28=1;but28.setImageDrawable(getResources().getDrawable(R.drawable.key28_3));}
			
			if((back[9]&1)!=0){af29=0;but29.setImageDrawable(getResources().getDrawable(R.drawable.key29_2));}  //29
                         else{af29=1;but29.setImageDrawable(getResources().getDrawable(R.drawable.key29_3));}	
			if((back[9]&2)!=0){af30=0;but30.setImageDrawable(getResources().getDrawable(R.drawable.key30_2));}  //30
			             else{af30=1;but30.setImageDrawable(getResources().getDrawable(R.drawable.key30_3));} 
			if((back[9]&4)!=0){af31=0;but31.setImageDrawable(getResources().getDrawable(R.drawable.key31_2));}  //31
			             else{af31=1;but31.setImageDrawable(getResources().getDrawable(R.drawable.key31_3));} 
			if((back[9]&8)!=0){af32=0;but32.setImageDrawable(getResources().getDrawable(R.drawable.key32_2));}  //32
			             else{af32=1;but32.setImageDrawable(getResources().getDrawable(R.drawable.key32_3));} 
			if((back[9]&16)!=0){af33=0;but33.setImageDrawable(getResources().getDrawable(R.drawable.key33_2));}  //33
			             else{af33=1;but33.setImageDrawable(getResources().getDrawable(R.drawable.key33_3));} 
				break;
			}
		case LG_OK:{
			setTimer(LG_OK);
			break;
		}
		case SF_OK:{
			//显示出水分数据
			String resp = "";
			for(byte b : backSF){
				resp += b + " ";
			}
			System.out.println("水分返回：" + resp);
			
			//先校验：
			byte[] expectBackSF = { -1, 0x5B, -2, 0x0A, 0x22};
            for (int i = 0; i < 5; i++) //比较前5位
            {
                if (backSF[i] != expectBackSF[i])
                {
                    Toast.makeText(MainActivity.this, "返回内容不正确，第" + (i + 1) + "位，预期 " + expectBackSF[i] + "，实际 " + backSF[i], Toast.LENGTH_LONG).show();	
                    updateEarthInfo(null);
                    return;
                }
            }
			
			byte[] info = new byte[] { backSF[5], backSF[6], backSF[7] };
            updateEarthInfo(info);
			break;
		}
		case KEY1:{			
			
			//s1=""; for(i=0;i<back.length;i++){s1=s1+byteToString(back[i])+" "; }//fhz.setText(s1);
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x15&&back[6]==0x01&& back[7]==-29 && back[8]==0x5a)
				{but1.setImageDrawable(getResources().getDrawable(R.drawable.key1_2));af1=0; 
				setTimer(KEY1); }
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x15&&back[6]==0x01&& back[7]==0x66 && back[8]==-54)
				{but1.setImageDrawable(getResources().getDrawable(R.drawable.key1_3));af1=1;
				stopTimer(); }break;}
		case KEY2:{
			
			//s1=""; for(i=0;i<back.length;i++){s1=s1+byteToString(back[i])+" "; }//fhz.setText(s1);
			if(back[0]==-1 && back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x16&&back[6]==0x01&& back[7]==-74 && back[8]==0x09)
				{but2.setImageDrawable(getResources().getDrawable(R.drawable.key2_2));af2=0;
				setTimer(KEY2); }
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x16&&back[6]==0x01&& back[7]==0x33 && back[8]==-103)
				{but2.setImageDrawable(getResources().getDrawable(R.drawable.key2_3));af2=1;
				stopTimer(); }break;}
		case KEY3:{
			
			//s1=""; for(i=0;i<back.length;i++){s1=s1+byteToString(back[i])+" "; }//fhz.setText(s1);
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x17&&back[6]==0x01&& back[7]==-123 && back[8]==0x38)
				{but3.setImageDrawable(getResources().getDrawable(R.drawable.key3_2));af3=0;
				setTimer(KEY3); }
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x17&&back[6]==0x01&& back[7]==0x00 && back[8]==-88)
				{but3.setImageDrawable(getResources().getDrawable(R.drawable.key3_3));af3=1;
				stopTimer(); }break;}
		case KEY4:{
			
			//s1=""; for(i=0;i<back.length;i++){s1=s1+byteToString(back[i])+" "; }//fhz.setText(s1);
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x18&&back[6]==0x01&& back[7]==-107 && back[8]==0x06)
				{but4.setImageDrawable(getResources().getDrawable(R.drawable.key4_2));af4=0;
				setTimer(KEY4); }
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x18&&back[6]==0x01&& back[7]==0x10 && back[8]==-106)
				{but4.setImageDrawable(getResources().getDrawable(R.drawable.key4_3));af4=1;
				stopTimer(); }break;}
		case KEY5:{
			
			//s1=""; for(i=0;i<back.length;i++){s1=s1+byteToString(back[i])+" "; }//fhz.setText(s1);
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x19&&back[6]==0x01&& back[7]==-90 && back[8]==0x37)
				{but5.setImageDrawable(getResources().getDrawable(R.drawable.key5_2));af5=0;
				setTimer(KEY5); }
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x19&&back[6]==0x01&& back[7]==0x23 && back[8]==-89)
				{but5.setImageDrawable(getResources().getDrawable(R.drawable.key5_3));af5=1;
				stopTimer(); }break;}
		case KEY6:{
			
			//s1=""; for(i=0;i<back.length;i++){s1=s1+byteToString(back[i])+" "; }//fhz.setText(s1);
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1A&&back[6]==0x01&& back[7]==-13 && back[8]==0x64)
				{but6.setImageDrawable(getResources().getDrawable(R.drawable.key6_2));af6=0;
				setTimer(KEY6); }
			if(back[0]==-1&& back[1]==0x5b&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1a&&back[6]==0x01&& back[7]==0x76 && back[8]==-12)
				{but6.setImageDrawable(getResources().getDrawable(R.drawable.key6_3));af6=1;
				stopTimer(); }break;}

		case KEY7:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x15&&back[6]==0x01&& back[7]==-31 && back[8]==-95)
				{but7.setImageDrawable(getResources().getDrawable(R.drawable.key7_2));af7=0;
				setTimer(KEY7); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x15&&back[6]==0x01&& back[7]==0x64 && back[8]==0x31)
				{but7.setImageDrawable(getResources().getDrawable(R.drawable.key7_3));af7=1;
				stopTimer(); }break;}		
		case KEY8:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x16&&back[6]==0x01&& back[7]==-76 && back[8]==-14)
				{but8.setImageDrawable(getResources().getDrawable(R.drawable.key8_2));af8=0;
				setTimer(KEY8); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x16&&back[6]==0x01&& back[7]==0x31 && back[8]==0x62)
				{but8.setImageDrawable(getResources().getDrawable(R.drawable.key8_3));af8=1;
				stopTimer(); }break;}
		case KEY9:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x17&&back[6]==0x01&& back[7]==-121 && back[8]==-61)
				{but9.setImageDrawable(getResources().getDrawable(R.drawable.key9_2));af9=0;
				setTimer(KEY9); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x17&&back[6]==0x01&& back[7]==0x02 && back[8]==0x53)
				{but9.setImageDrawable(getResources().getDrawable(R.drawable.key9_3));af9=1;
				stopTimer(); }break;}	
		case KEY10:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x18&&back[6]==0x01&& back[7]==-105 && back[8]==-3)
				{but10.setImageDrawable(getResources().getDrawable(R.drawable.key10_2));af10=0;
				setTimer(KEY10); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x18&&back[6]==0x01&& back[7]==0x12 && back[8]==0x6d)
				{but10.setImageDrawable(getResources().getDrawable(R.drawable.key10_3));af10=1;
				stopTimer(); }break;}	
		case KEY11:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x19&&back[6]==0x01&& back[7]==-92 && back[8]==-52)
				{but11.setImageDrawable(getResources().getDrawable(R.drawable.key11_2));af11=0;
				setTimer(KEY11); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x19&&back[6]==0x01&& back[7]==0x21 && back[8]==0x5c)
				{but11.setImageDrawable(getResources().getDrawable(R.drawable.key11_3));af11=1;
				stopTimer(); }break;}	
		case KEY12:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1a&&back[6]==0x01&& back[7]==-15 && back[8]==-97)
				{but12.setImageDrawable(getResources().getDrawable(R.drawable.key12_2));af12=0;
				setTimer(KEY12); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1a&&back[6]==0x01&& back[7]==0x74 && back[8]==0x0f)
				{but12.setImageDrawable(getResources().getDrawable(R.drawable.key12_3));af12=1;
				stopTimer(); }break;}
		case KEY13:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1b&&back[6]==0x01&& back[7]==-62 && back[8]==-82)
				{but13.setImageDrawable(getResources().getDrawable(R.drawable.key13_2));af13=0;
				setTimer(KEY13); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1b&&back[6]==0x01&& back[7]==0x47 && back[8]==0x3e)
				{but13.setImageDrawable(getResources().getDrawable(R.drawable.key13_3));af13=1;
				stopTimer(); }break;}
		case KEY14:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1c&&back[6]==0x01&& back[7]==0x5b && back[8]==0x39)
				{but14.setImageDrawable(getResources().getDrawable(R.drawable.key14_2));af14=0;
				setTimer(KEY14); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1c&&back[6]==0x01&& back[7]==-34 && back[8]==-87)
				{but14.setImageDrawable(getResources().getDrawable(R.drawable.key14_3));af14=1;
				stopTimer(); }break;}	
		case KEY15:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1d&&back[6]==0x01&& back[7]==0x68 && back[8]==0x08)
				{but15.setImageDrawable(getResources().getDrawable(R.drawable.key15_2));af15=0;
				setTimer(KEY15); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1d&&back[6]==0x01&& back[7]==-19 && back[8]==-104)
				{but15.setImageDrawable(getResources().getDrawable(R.drawable.key15_3));af15=1;
				stopTimer(); }break;}
		case KEY16:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1e&&back[6]==0x01&& back[7]==0x3d && back[8]==0x5b)
				{but16.setImageDrawable(getResources().getDrawable(R.drawable.key16_2));af16=0;
				setTimer(KEY16); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1e&&back[6]==0x01&& back[7]==-72 && back[8]==-53)
				{but16.setImageDrawable(getResources().getDrawable(R.drawable.key16_3));af16=1;
				stopTimer(); }break;}	
		case KEY17:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1f&&back[6]==0x01&& back[7]==0x0e && back[8]==0x6a)
				{but17.setImageDrawable(getResources().getDrawable(R.drawable.key17_2));af17=0;
				setTimer(KEY17); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1f&&back[6]==0x01&& back[7]==-117 && back[8]==-6)
				{but17.setImageDrawable(getResources().getDrawable(R.drawable.key17_3));af17=1;
				stopTimer(); }break;}
		case KEY18:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x20&&back[6]==0x01&& back[7]==0x1b && back[8]==-63)
				{but18.setImageDrawable(getResources().getDrawable(R.drawable.key18_2));af18=0;
				setTimer(KEY18); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x20&&back[6]==0x01&& back[7]==-98 && back[8]==0x51)
				{but18.setImageDrawable(getResources().getDrawable(R.drawable.key18_3));af18=1;
				stopTimer(); }break;}
		case KEY19:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x21&&back[6]==0x01&& back[7]==0x28 && back[8]==-16)
				{but19.setImageDrawable(getResources().getDrawable(R.drawable.key19_2));af19=0;
				setTimer(KEY19); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x21&&back[6]==0x01&& back[7]==-83 && back[8]==0x60)
				{but19.setImageDrawable(getResources().getDrawable(R.drawable.key19_3));af19=1;
				stopTimer(); }break;}	
		case KEY20:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x13&&back[5]==0x22&&back[6]==0x01&& back[7]==0x7d && back[8]==-93)
				{but20.setImageDrawable(getResources().getDrawable(R.drawable.key20_2));af20=0;
				setTimer(KEY20); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-2&&back[3]==0x09&&back[4]==0x14&&back[5]==0x22&&back[6]==0x01&& back[7]==-8 && back[8]==0x33)
				{but20.setImageDrawable(getResources().getDrawable(R.drawable.key20_3));af20=1;
				stopTimer(); }break;}

		case KEY21:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x15&&back[6]==0x01&& back[7]==0x0f && back[8]==0x73)
				{but21.setImageDrawable(getResources().getDrawable(R.drawable.key21_2));af21=0;
				setTimer(KEY21); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x15&&back[6]==0x01&& back[7]==-118 && back[8]==-29)
				{but21.setImageDrawable(getResources().getDrawable(R.drawable.key21_3));af21=1;
				stopTimer(); }break;}
		case KEY22:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x16&&back[6]==0x01&& back[7]==0x5a && back[8]==0x20)
				{but22.setImageDrawable(getResources().getDrawable(R.drawable.key22_2));af22=0;
				setTimer(KEY22); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x16&&back[6]==0x01&& back[7]==-33 && back[8]==-80)
				{but22.setImageDrawable(getResources().getDrawable(R.drawable.key22_3));af22=1;
				stopTimer(); }break;}
		case KEY23:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x17&&back[6]==0x01&& back[7]==0x69 && back[8]==0x11)
				{but23.setImageDrawable(getResources().getDrawable(R.drawable.key23_2));af23=0;
				setTimer(KEY23); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x17&&back[6]==0x01&& back[7]==-20 && back[8]==-127)
				{but23.setImageDrawable(getResources().getDrawable(R.drawable.key23_3));af23=1;
				stopTimer(); }break;}	
		case KEY24:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x18&&back[6]==0x01&& back[7]==0x79 && back[8]==0x2f)
				{but24.setImageDrawable(getResources().getDrawable(R.drawable.key24_2));af24=0;
				setTimer(KEY24); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x18&&back[6]==0x01&& back[7]==-4 && back[8]==-65)
				{but24.setImageDrawable(getResources().getDrawable(R.drawable.key24_3));af24=1;
				stopTimer(); }break;}	
		case KEY25:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x19&&back[6]==0x01&& back[7]==0x4a && back[8]==0x1e)
				{but25.setImageDrawable(getResources().getDrawable(R.drawable.key25_2));af25=0;
				setTimer(KEY25); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x19&&back[6]==0x01&& back[7]==-49 && back[8]==-114)
				{but25.setImageDrawable(getResources().getDrawable(R.drawable.key25_3));af25=1;
				stopTimer(); }break;}
		case KEY26:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1a&&back[6]==0x01&& back[7]==0x1f && back[8]==0x4d)
				{but26.setImageDrawable(getResources().getDrawable(R.drawable.key26_2));af26=0;
				setTimer(KEY26); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1a&&back[6]==0x01&& back[7]==-102 && back[8]==-35)
				{but26.setImageDrawable(getResources().getDrawable(R.drawable.key26_3));af26=1;
				stopTimer(); }break;}
		case KEY27:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1b&&back[6]==0x01&& back[7]==0x2c && back[8]==0x7c)
				{but27.setImageDrawable(getResources().getDrawable(R.drawable.key27_2));af27=0;
				setTimer(KEY27); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1b&&back[6]==0x01&& back[7]==-87 && back[8]==-20)
				{but27.setImageDrawable(getResources().getDrawable(R.drawable.key27_3));af27=1;
				stopTimer(); }break;}
		case KEY28:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1c&&back[6]==0x01&& back[7]==-75 && back[8]==-21)
				{but28.setImageDrawable(getResources().getDrawable(R.drawable.key28_2));af28=0;
				setTimer(KEY28); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1c&&back[6]==0x01&& back[7]==0x30 && back[8]==0x7b)
				{but28.setImageDrawable(getResources().getDrawable(R.drawable.key28_3));af28=1;
				stopTimer(); }break;}
		case KEY29:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1d&&back[6]==0x01&& back[7]==-122 && back[8]==-38)
				{but29.setImageDrawable(getResources().getDrawable(R.drawable.key29_2));af29=0;
				setTimer(KEY29); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1d&&back[6]==0x01&& back[7]==0x03 && back[8]==0x4a)
				{but29.setImageDrawable(getResources().getDrawable(R.drawable.key29_3));af29=1;
				stopTimer(); }break;}
		case KEY30:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1e&&back[6]==0x01&& back[7]==-45 && back[8]==-119)
				{but30.setImageDrawable(getResources().getDrawable(R.drawable.key30_2));af30=0;
				setTimer(KEY30); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1e&&back[6]==0x01&& back[7]==0x56 && back[8]==0x19)
				{but30.setImageDrawable(getResources().getDrawable(R.drawable.key30_3));af30=1;
				stopTimer(); }break;}
		case KEY31:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x1f&&back[6]==0x01&& back[7]==-32 && back[8]==-72)
				{but31.setImageDrawable(getResources().getDrawable(R.drawable.key31_2));af31=0;
				setTimer(KEY31); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x1f&&back[6]==0x01&& back[7]==0x65 && back[8]==0x28)
				{but31.setImageDrawable(getResources().getDrawable(R.drawable.key31_3));af31=1;
				stopTimer(); }break;}
		case KEY32:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x20&&back[6]==0x01&& back[7]==-11 && back[8]==0x13)
				{but32.setImageDrawable(getResources().getDrawable(R.drawable.key32_2));af32=0;
				setTimer(KEY32); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x20&&back[6]==0x01&& back[7]==0x70 && back[8]==-125)
				{but32.setImageDrawable(getResources().getDrawable(R.drawable.key32_3));af32=1;
				stopTimer(); }break;}
		case KEY33:{
			
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x13&&back[5]==0x21&&back[6]==0x01&& back[7]==-58 && back[8]==0x22)
				{but33.setImageDrawable(getResources().getDrawable(R.drawable.key33_2));af33=0;
				setTimer(KEY33); }
			if(back[0]==-18&& back[1]==0x5d&&back[2]==-3&&back[3]==0x09&&back[4]==0x14&&back[5]==0x21&&back[6]==0x01&& back[7]==0x43 && back[8]==-78)
				{but33.setImageDrawable(getResources().getDrawable(R.drawable.key33_3));af33=1;
				stopTimer(); }break;}   
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_main);
		
		 tvTotalTime = (TextView)findViewById(R.id.tvTotalTime);
		 tvTotalTime.setBackgroundColor(Color.argb(0, 0, 0, 0)); //背景透明度
		 
		 tvSF = (TextView)findViewById(R.id.tvSF);
		
		 but1 = (ImageButton)findViewById(R.id.imageButton1); but11 = (ImageButton)findViewById(R.id.imageButton11); but21 = (ImageButton)findViewById(R.id.imageButton21); 
		 but2 = (ImageButton)findViewById(R.id.imageButton2); but12 = (ImageButton)findViewById(R.id.imageButton12); but22 = (ImageButton)findViewById(R.id.imageButton22);
		 but3 = (ImageButton)findViewById(R.id.imageButton3); but13 = (ImageButton)findViewById(R.id.imageButton13); but23 = (ImageButton)findViewById(R.id.imageButton23);
		 but4 = (ImageButton)findViewById(R.id.imageButton4); but14 = (ImageButton)findViewById(R.id.imageButton14); but24 = (ImageButton)findViewById(R.id.imageButton24);
		 but5 = (ImageButton)findViewById(R.id.imageButton5); but15 = (ImageButton)findViewById(R.id.imageButton15); but25 = (ImageButton)findViewById(R.id.imageButton25);
		 but6 = (ImageButton)findViewById(R.id.imageButton6); but16 = (ImageButton)findViewById(R.id.imageButton16); but26 = (ImageButton)findViewById(R.id.imageButton26);
		 but7 = (ImageButton)findViewById(R.id.imageButton7); but17 = (ImageButton)findViewById(R.id.imageButton17); but27 = (ImageButton)findViewById(R.id.imageButton27);
		 but8 = (ImageButton)findViewById(R.id.imageButton8); but18 = (ImageButton)findViewById(R.id.imageButton18); but28 = (ImageButton)findViewById(R.id.imageButton28);
		 but9 = (ImageButton)findViewById(R.id.imageButton9); but19 = (ImageButton)findViewById(R.id.imageButton19); but29 = (ImageButton)findViewById(R.id.imageButton29);
		 but10 = (ImageButton)findViewById(R.id.imageButton10);but20 = (ImageButton)findViewById(R.id.imageButton20);but30 = (ImageButton)findViewById(R.id.imageButton30);
		 but31 = (ImageButton)findViewById(R.id.imageButton31);but32 = (ImageButton)findViewById(R.id.imageButton32);but33 = (ImageButton)findViewById(R.id.imageButton33);
       	 but34 = (ImageButton)findViewById(R.id.imageButton34);
       	 butLG = (ImageButton)findViewById(R.id.imageButtonLG);
       	 butSF = (ImageButton)findViewById(R.id.imageButtonSF);
		 
       	 layoutTime = (LinearLayout)findViewById(R.id.layoutTime);
       	 
       	 //初始化33个输入框
       	 delaytime1 = (EditText)findViewById(R.id.ET1); delaytime11 = (EditText)findViewById(R.id.ET11); delaytime21 = (EditText)findViewById(R.id.ET21); delaytime31 = (EditText)findViewById(R.id.ET31);
       	 delaytime2 = (EditText)findViewById(R.id.ET2); delaytime12 = (EditText)findViewById(R.id.ET12); delaytime22 = (EditText)findViewById(R.id.ET22); delaytime32 = (EditText)findViewById(R.id.ET32);
       	 delaytime3 = (EditText)findViewById(R.id.ET3); delaytime13 = (EditText)findViewById(R.id.ET13); delaytime23 = (EditText)findViewById(R.id.ET23); delaytime33 = (EditText)findViewById(R.id.ET33);
       	 delaytime4 = (EditText)findViewById(R.id.ET4); delaytime14 = (EditText)findViewById(R.id.ET14); delaytime24 = (EditText)findViewById(R.id.ET24);
       	 delaytime5 = (EditText)findViewById(R.id.ET5); delaytime15 = (EditText)findViewById(R.id.ET15); delaytime25 = (EditText)findViewById(R.id.ET25);
       	 delaytime6 = (EditText)findViewById(R.id.ET6); delaytime16 = (EditText)findViewById(R.id.ET16); delaytime26 = (EditText)findViewById(R.id.ET26);
       	 delaytime7 = (EditText)findViewById(R.id.ET7); delaytime17 = (EditText)findViewById(R.id.ET17); delaytime27 = (EditText)findViewById(R.id.ET27);
       	 delaytime8 = (EditText)findViewById(R.id.ET8); delaytime18 = (EditText)findViewById(R.id.ET18); delaytime28 = (EditText)findViewById(R.id.ET28);
       	 delaytime9 = (EditText)findViewById(R.id.ET9); delaytime19 = (EditText)findViewById(R.id.ET19); delaytime29 = (EditText)findViewById(R.id.ET29);
       	 delaytime10 = (EditText)findViewById(R.id.ET10); delaytime20 = (EditText)findViewById(R.id.ET20); delaytime30 = (EditText)findViewById(R.id.ET30);
       	 
       	 //给输入框赋初值
       	 time = TimeLab.get(this).getTime();
       	 if(time != null){
          	delaytime1.setText(time.getT1() + ""); delaytime11.setText(time.getT11() + ""); delaytime21.setText(time.getT21() + ""); delaytime31.setText(time.getT31() + "");
          	delaytime2.setText(time.getT2() + ""); delaytime12.setText(time.getT12() + ""); delaytime22.setText(time.getT22() + ""); delaytime32.setText(time.getT32() + "");
          	delaytime3.setText(time.getT3() + ""); delaytime13.setText(time.getT13() + ""); delaytime23.setText(time.getT23() + ""); delaytime33.setText(time.getT33() + "");
          	delaytime4.setText(time.getT4() + ""); delaytime14.setText(time.getT14() + ""); delaytime24.setText(time.getT24() + "");
          	delaytime5.setText(time.getT5() + ""); delaytime15.setText(time.getT15() + ""); delaytime25.setText(time.getT25() + "");
          	delaytime6.setText(time.getT6() + ""); delaytime16.setText(time.getT16() + ""); delaytime26.setText(time.getT26() + "");
          	delaytime7.setText(time.getT7() + ""); delaytime17.setText(time.getT17() + ""); delaytime27.setText(time.getT27() + "");
          	delaytime8.setText(time.getT8() + ""); delaytime18.setText(time.getT18() + ""); delaytime28.setText(time.getT28() + "");
          	delaytime9.setText(time.getT9() + ""); delaytime19.setText(time.getT19() + ""); delaytime29.setText(time.getT29() + "");
          	delaytime10.setText(time.getT10() + ""); delaytime20.setText(time.getT20() + ""); delaytime30.setText(time.getT30() + "");	
          	
       	 }
       	 
       //增加输入框的限制：0~255
       	final TextWatcher wtr = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
			
			@Override
			public void afterTextChanged(Editable editable) {
				if(editable.toString().equals("")){
					editable.append("0");
				}
				
				int t = Integer.parseInt(editable.toString());
				if(t < 0 || t > 255){
					int pos = editable.length() - 1;
					editable.delete(pos, pos + 1);
				}
			}
		};
       	 
       	delaytime1.addTextChangedListener(wtr); delaytime11.addTextChangedListener(wtr); delaytime21.addTextChangedListener(wtr); delaytime31.addTextChangedListener(wtr);
       	delaytime2.addTextChangedListener(wtr); delaytime12.addTextChangedListener(wtr); delaytime22.addTextChangedListener(wtr); delaytime32.addTextChangedListener(wtr);
       	delaytime3.addTextChangedListener(wtr); delaytime13.addTextChangedListener(wtr); delaytime23.addTextChangedListener(wtr); delaytime33.addTextChangedListener(wtr);
       	delaytime4.addTextChangedListener(wtr); delaytime14.addTextChangedListener(wtr); delaytime24.addTextChangedListener(wtr);
       	delaytime5.addTextChangedListener(wtr); delaytime15.addTextChangedListener(wtr); delaytime25.addTextChangedListener(wtr);
       	delaytime6.addTextChangedListener(wtr); delaytime16.addTextChangedListener(wtr); delaytime26.addTextChangedListener(wtr);
       	delaytime7.addTextChangedListener(wtr); delaytime17.addTextChangedListener(wtr); delaytime27.addTextChangedListener(wtr);
       	delaytime8.addTextChangedListener(wtr); delaytime18.addTextChangedListener(wtr); delaytime28.addTextChangedListener(wtr);
       	delaytime9.addTextChangedListener(wtr); delaytime19.addTextChangedListener(wtr); delaytime29.addTextChangedListener(wtr);
       	delaytime10.addTextChangedListener(wtr); delaytime20.addTextChangedListener(wtr); delaytime30.addTextChangedListener(wtr);       	 
       	 
		 DisplayMetrics outMetrics = new DisplayMetrics();
		 this.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		 but1.getLayoutParams().height=outMetrics.heightPixels/6;but11.getLayoutParams().height=outMetrics.heightPixels/6;but21.getLayoutParams().height=outMetrics.heightPixels/6;
		 but1.getLayoutParams().width=outMetrics.heightPixels/6; but11.getLayoutParams().width=outMetrics.heightPixels/6; but21.getLayoutParams().width=outMetrics.heightPixels/6;
		 but2.getLayoutParams().height=outMetrics.heightPixels/6;but12.getLayoutParams().height=outMetrics.heightPixels/6;but22.getLayoutParams().height=outMetrics.heightPixels/6;
		 but2.getLayoutParams().width=outMetrics.heightPixels/6; but12.getLayoutParams().width=outMetrics.heightPixels/6; but22.getLayoutParams().width=outMetrics.heightPixels/6;
		 but3.getLayoutParams().height=outMetrics.heightPixels/6;but13.getLayoutParams().height=outMetrics.heightPixels/6;but23.getLayoutParams().height=outMetrics.heightPixels/6;
		 but3.getLayoutParams().width=outMetrics.heightPixels/6; but13.getLayoutParams().width=outMetrics.heightPixels/6; but23.getLayoutParams().width=outMetrics.heightPixels/6;
		 but4.getLayoutParams().height=outMetrics.heightPixels/6;but14.getLayoutParams().height=outMetrics.heightPixels/6;but24.getLayoutParams().height=outMetrics.heightPixels/6;
		 but4.getLayoutParams().width=outMetrics.heightPixels/6; but14.getLayoutParams().width=outMetrics.heightPixels/6; but24.getLayoutParams().width=outMetrics.heightPixels/6;
		 but5.getLayoutParams().height=outMetrics.heightPixels/6;but15.getLayoutParams().height=outMetrics.heightPixels/6;but25.getLayoutParams().height=outMetrics.heightPixels/6;
		 but5.getLayoutParams().width=outMetrics.heightPixels/6; but15.getLayoutParams().width=outMetrics.heightPixels/6; but25.getLayoutParams().width=outMetrics.heightPixels/6;
		 but6.getLayoutParams().height=outMetrics.heightPixels/6;but16.getLayoutParams().height=outMetrics.heightPixels/6;but26.getLayoutParams().height=outMetrics.heightPixels/6;
		 but6.getLayoutParams().width=outMetrics.heightPixels/6; but16.getLayoutParams().width=outMetrics.heightPixels/6; but26.getLayoutParams().width=outMetrics.heightPixels/6;
		 but7.getLayoutParams().height=outMetrics.heightPixels/6;but17.getLayoutParams().height=outMetrics.heightPixels/6;but27.getLayoutParams().height=outMetrics.heightPixels/6;
		 but7.getLayoutParams().width=outMetrics.heightPixels/6;  but17.getLayoutParams().width=outMetrics.heightPixels/6; but27.getLayoutParams().width=outMetrics.heightPixels/6;
		 but8.getLayoutParams().height=outMetrics.heightPixels/6;but18.getLayoutParams().height=outMetrics.heightPixels/6;but28.getLayoutParams().height=outMetrics.heightPixels/6;
		 but8.getLayoutParams().width=outMetrics.heightPixels/6;  but18.getLayoutParams().width=outMetrics.heightPixels/6; but28.getLayoutParams().width=outMetrics.heightPixels/6;
		 but9.getLayoutParams().height=outMetrics.heightPixels/6;but19.getLayoutParams().height=outMetrics.heightPixels/6;but29.getLayoutParams().height=outMetrics.heightPixels/6;
		 but9.getLayoutParams().width=outMetrics.heightPixels/6;  but19.getLayoutParams().width=outMetrics.heightPixels/6; but29.getLayoutParams().width=outMetrics.heightPixels/6;
		 but10.getLayoutParams().height=outMetrics.heightPixels/6;but20.getLayoutParams().height=outMetrics.heightPixels/6;but30.getLayoutParams().height=outMetrics.heightPixels/6;
		 but10.getLayoutParams().width=outMetrics.heightPixels/6;  but20.getLayoutParams().width=outMetrics.heightPixels/6; but30.getLayoutParams().width=outMetrics.heightPixels/6;
		 but31.getLayoutParams().height=outMetrics.heightPixels/6;but32.getLayoutParams().height=outMetrics.heightPixels/6;but33.getLayoutParams().height=outMetrics.heightPixels/6;
		 but31.getLayoutParams().width=outMetrics.heightPixels/6; but32.getLayoutParams().width=outMetrics.heightPixels/6; but33.getLayoutParams().width=outMetrics.heightPixels/6;
		 but34.getLayoutParams().height=outMetrics.heightPixels/6;
		 but34.getLayoutParams().width=outMetrics.widthPixels/6;
		 butLG.getLayoutParams().height=outMetrics.heightPixels/6;
		 butLG.getLayoutParams().width=outMetrics.widthPixels/6;
		 butSF.getLayoutParams().height=outMetrics.heightPixels/6;
		 butSF.getLayoutParams().width=outMetrics.widthPixels/6;
		 
		this.ServerIp = (EditText) super.findViewById(R.id.editText1);
		this.ServerPort = (EditText) super.findViewById(R.id.editText2);
	//	this.fhz = (EditText) super.findViewById(R.id.editText3);
		af1=2;af2=2;af3=2;af4=2;af5=2;af6=2;af7=2;af8=2;af9=2;af10=2;af11=2;af12=2;af13=2;af14=2;af15=2;af16=2;af17=2;af18=2;af19=2;af20=2;
		af21=2;af22=2;af23=2;af24=2;af25=2;af26=2;af27=2;af28=2;af29=2;af30=2;af31=2;af32=2;af33=2;
		loadIpPort(); 
		//读文件=======

   //but1==============================================================================
		but1.setOnClickListener(new Button.OnClickListener(){

			Runnable runnable = new Runnable(){
				 @Override
				public void run() {
					Message message = new Message();
					message.what = KEY1;
					 try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};//ff 5a fe 08 13 15 87 21
							Open[0]= (byte) 0xff;
							Open[1]= (byte) 0x5a;
							Open[2]= (byte) 0xfe;
							Open[3]= (byte) 0x08;
							Open[4]= (byte) 0x13;
							Open[5]= (byte) 0x15;
							Open[6]= (byte) 0x87;
							Open[7]= (byte) 0x21;
							delaytime1 = (EditText)findViewById(R.id.ET1);
							value1 = delaytime1.getText().toString();
							int_r1 = Integer.parseInt(value1)%256;
							Open[8]= (byte) int_r1;
							
						byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00}; //FF 5A FE 08 14 15 1E B6
							close[0]= (byte) 0xff;
							close[1]= (byte) 0x5a;
							close[2]= (byte) 0xfe;
							close[3]= (byte) 0x08;
							close[4]= (byte) 0x14;
							close[5]= (byte) 0x15;
							close[6]= (byte) 0x1e;
							close[7]= (byte) 0xb6;
		    		    if(af1==1||af1==0){
		    	          mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
		    		      // mSocket =new Socket("121.224.133.97",8899);
		    	          ops=mSocket.getOutputStream();
		    	          ips =mSocket.getInputStream();
	               	      if(af1==1)ops.write(Open);
	               	      if(af1==0)ops.write(close);
	               	      ips.read(back);
	               	      ips.close();
	               	      ops.close();
	               	      mSocket.close();	                  
	                 mHandler.sendMessage(message); }} 
					 catch (Exception e) {
						 e.printStackTrace(); }}};
						 public void onClick(View v) {
							 new Thread(runnable).start();}});
		//but2==============================================================================
		but2.setOnClickListener(new Button.OnClickListener(){
			Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY2;try{
				byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
					Open[0]= (byte) 0xff;
					Open[1]= (byte) 0x5a;
					Open[2]= (byte) 0xfe;
					Open[3]= (byte) 0x08;
					Open[4]= (byte) 0x13;
					Open[5]= (byte) 0x16;
					Open[6]= (byte) 0xb7;
					Open[7]= (byte) 0x42;
					delaytime2 = (EditText)findViewById(R.id.ET2);
					value2 = delaytime2.getText().toString();
					int_r2 = Integer.parseInt(value2)%256;
					Open[8]= (byte) int_r2;
					
				byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
					close[0]= (byte) 0xff;
					close[1]= (byte) 0x5a;
					close[2]= (byte) 0xfe;
					close[3]= (byte) 0x08;
					close[4]= (byte) 0x14;
					close[5]= (byte) 0x16;
					close[6]= (byte) 0x2e;
					close[7]= (byte) 0xd5;
		    		if(af2==1||af2==0){
		    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
		    		// mSocket =new Socket("121.224.133.97",8899);
					//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r2)); 
		    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
	               	 if(af2==1)ops.write(Open);
	               	 if(af2==0)ops.write(close);
	               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
	                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});
		//but3==============================================================================
			but3.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY3;try{
					byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xff;
						Open[1]= (byte) 0x5a;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x17;
						Open[6]= (byte) 0xa7;
						Open[7]= (byte) 0x63;
						delaytime3 = (EditText)findViewById(R.id.ET3);
						value3 = delaytime3.getText().toString();
						int_r3 = Integer.parseInt(value3)%256;
						Open[8]= (byte) int_r3;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xff;
						close[1]= (byte) 0x5a;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x17;
						close[6]= (byte) 0x3e;
						close[7]= (byte) 0xf4;
			    		if(af3==1||af3==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		 //mSocket =new Socket("121.224.133.97",8899);
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af3==1)ops.write(Open);
		               	 if(af3==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});
	  //but4==============================================================================
				but4.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY4;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xff;
						Open[1]= (byte) 0x5a;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x18;
						Open[6]= (byte) 0x56;
						Open[7]= (byte) 0x8c;
						delaytime4 = (EditText)findViewById(R.id.ET4);
						value4 = delaytime4.getText().toString();
						int_r4 = Integer.parseInt(value4)%256;
						Open[8]= (byte) int_r4;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xff;
						close[1]= (byte) 0x5a;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x18;
						close[6]= (byte) 0xcf;
						close[7]= (byte) 0x1b;
			    		if(af4==1||af4==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af4==1)ops.write(Open);
		               	 if(af4==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});
		//but5==============================================================================
				but5.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY5;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xff;
						Open[1]= (byte) 0x5a;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x19;
						Open[6]= (byte) 0x46;
						Open[7]= (byte) 0xad;
						delaytime5 = (EditText)findViewById(R.id.ET5);
						value5 = delaytime5.getText().toString();
						int_r5 = Integer.parseInt(value5)%256;
						Open[8]= (byte) int_r5;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xff;
						close[1]= (byte) 0x5a;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x19;
						close[6]= (byte) 0xdf;
						close[7]= (byte) 0x3a;
				    		if(af5==1||af5==0){
				    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
				    		// mSocket =new Socket("121.224.133.97",8899);
							//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r5)); 
				    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
			               	 if(af5==1)ops.write(Open);
			               	 if(af5==0)ops.write(close);
			               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
			                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});
		//but6==============================================================================
				but6.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY6;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xff;
						Open[1]= (byte) 0x5a;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1a;
						Open[6]= (byte) 0x76;
						Open[7]= (byte) 0xce;
						delaytime6 = (EditText)findViewById(R.id.ET6);
						value6 = delaytime6.getText().toString();
						int_r6 = Integer.parseInt(value6)%256;
						Open[8]= (byte) int_r6;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xff;
						close[1]= (byte) 0x5a;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1a;
						close[6]= (byte) 0xef;
						close[7]= (byte) 0x59;
			    		if(af6==1||af6==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af6==1)ops.write(Open);
		               	 if(af6==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});
		//but7==============================================================================
				but7.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY7;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x15;
						Open[6]= (byte) 0x15;
						Open[7]= (byte) 0x80;
						delaytime7 = (EditText)findViewById(R.id.ET7);
						value7 = delaytime7.getText().toString();
						int_r7 = Integer.parseInt(value7)%256;
						Open[8]= (byte) int_r7;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x15;
						close[6]= (byte) 0x8c;
						close[7]= (byte) 0x17;
			    		if(af7==1||af7==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af7==1)ops.write(Open);
		               	 if(af7==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});
		//but8==============================================================================
				but8.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY8;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x16;
						Open[6]= (byte) 0x25;
						Open[7]= (byte) 0xe3;
						delaytime8 = (EditText)findViewById(R.id.ET8);
						value8 = delaytime8.getText().toString();
						int_r8 = Integer.parseInt(value8)%256;
						Open[8]= (byte) int_r8;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x16;
						close[6]= (byte) 0xbc;
						close[7]= (byte) 0x74;
			    		if(af8==1||af8==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af8==1)ops.write(Open);
		               	 if(af8==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});
		//but9==============================================================================
				but9.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY9;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x17;
						Open[6]= (byte) 0x35;
						Open[7]= (byte) 0xc2;
						delaytime9 = (EditText)findViewById(R.id.ET9);
						value9 = delaytime9.getText().toString();
						int_r9 = Integer.parseInt(value9)%256;
						Open[8]= (byte) int_r9;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x17;
						close[6]= (byte) 0xac;
						close[7]= (byte) 0x55;
			    		if(af9==1||af9==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af9==1)ops.write(Open);
		               	 if(af9==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});				
	 //but10==============================================================================
				but10.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY10;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x18;
						Open[6]= (byte) 0xc4;
						Open[7]= (byte) 0x2d;
						delaytime10 = (EditText)findViewById(R.id.ET10);
						value10 = delaytime10.getText().toString();
						int_r10 = Integer.parseInt(value10)%256;
						Open[8]= (byte) int_r10;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x18;
						close[6]= (byte) 0x5d;
						close[7]= (byte) 0xba;
			    		if(af10==1||af10==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af10==1)ops.write(Open);
		               	 if(af10==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});				
	 //but11==============================================================================
				but11.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY11;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x19;
						Open[6]= (byte) 0xd4;
						Open[7]= (byte) 0x0c;
						delaytime11 = (EditText)findViewById(R.id.ET11);
						value11 = delaytime10.getText().toString();
						int_r11 = Integer.parseInt(value11)%256;
						Open[8]= (byte) int_r11;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x19;
						close[6]= (byte) 0x4d;
						close[7]= (byte) 0x9b;
			    		if(af11==1||af11==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af11==1)ops.write(Open);
		               	 if(af11==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but12==============================================================================
				but12.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY12;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1a;
						Open[6]= (byte) 0xe4;
						Open[7]= (byte) 0x6f;
						delaytime12 = (EditText)findViewById(R.id.ET12);
						value12 = delaytime12.getText().toString();
						int_r12 = Integer.parseInt(value12)%256;
						Open[8]= (byte) int_r12;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1a;
						close[6]= (byte) 0x7d;
						close[7]= (byte) 0xf8;
			    		if(af12==1||af12==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af12==1)ops.write(Open);
		               	 if(af12==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but13==============================================================================
				but13.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY13;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1b;
						Open[6]= (byte) 0xf4;
						Open[7]= (byte) 0x4e;
						delaytime13 = (EditText)findViewById(R.id.ET13);
						value13 = delaytime13.getText().toString();
						int_r13 = Integer.parseInt(value13)%256;
						Open[8]= (byte) int_r13;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1b;
						close[6]= (byte) 0x6d;
						close[7]= (byte) 0xd9;
			    		if(af13==1||af13==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af13==1)ops.write(Open);
		               	 if(af13==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but14==============================================================================
				but14.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY14;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1c;
						Open[6]= (byte) 0x84;
						Open[7]= (byte) 0xa9;
						delaytime14 = (EditText)findViewById(R.id.ET14);
						value14 = delaytime14.getText().toString();
						int_r14 = Integer.parseInt(value14)%256;
						Open[8]= (byte) int_r14;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1c;
						close[6]= (byte) 0x1d;
						close[7]= (byte) 0x3e;
			    		if(af14==1||af14==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af14==1)ops.write(Open);
		               	 if(af14==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but15==============================================================================
				but15.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY15;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1d;
						Open[6]= (byte) 0x94;
						Open[7]= (byte) 0x88;
						delaytime15 = (EditText)findViewById(R.id.ET15);
						value15 = delaytime15.getText().toString();
						int_r15 = Integer.parseInt(value15)%256;
						Open[8]= (byte) int_r15;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1d;
						close[6]= (byte) 0x0d;
						close[7]= (byte) 0x1f;
			    		if(af15==1||af15==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af15==1)ops.write(Open);
		               	 if(af15==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but16==============================================================================
				but16.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY16;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1e;
						Open[6]= (byte) 0xa4;
						Open[7]= (byte) 0xeb;
						delaytime16 = (EditText)findViewById(R.id.ET16);
						value16 = delaytime16.getText().toString();
						int_r16 = Integer.parseInt(value16)%256;
						Open[8]= (byte) int_r16;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1e;
						close[6]= (byte) 0x3d;
						close[7]= (byte) 0x7c;
			    		if(af16==1||af16==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af16==1)ops.write(Open);
		               	 if(af16==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but17==============================================================================
				but17.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY17;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1f;
						Open[6]= (byte) 0xb4;
						Open[7]= (byte) 0xca;
						delaytime17 = (EditText)findViewById(R.id.ET17);
						value17 = delaytime17.getText().toString();
						int_r17 = Integer.parseInt(value17)%256;
						Open[8]= (byte) int_r17;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1f;
						close[6]= (byte) 0x2d;
						close[7]= (byte) 0x5d;
			    		if(af17==1||af17==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af17==1)ops.write(Open);
		               	 if(af17==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but18==============================================================================
				but18.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY18;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x20;
						Open[6]= (byte) 0x73;
						Open[7]= (byte) 0x76;
						delaytime18 = (EditText)findViewById(R.id.ET18);
						value18 = delaytime18.getText().toString();
						int_r18 = Integer.parseInt(value18)%256;
						Open[8]= (byte) int_r18;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x20;
						close[6]= (byte) 0xea;
						close[7]= (byte) 0xe1;
			    		if(af18==1||af18==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af18==1)ops.write(Open);
		               	 if(af18==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but19==============================================================================
				but19.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY19;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x21;
						Open[6]= (byte) 0x63;
						Open[7]= (byte) 0x57;
						delaytime19 = (EditText)findViewById(R.id.ET19);
						value19 = delaytime19.getText().toString();
						int_r19 = Integer.parseInt(value19)%256;
						Open[8]= (byte) int_r19;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x21;
						close[6]= (byte) 0xfa;
						close[7]= (byte) 0xc0;
			    		if(af19==1||af19==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af19==1)ops.write(Open);
		               	 if(af19==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but20==============================================================================
				but20.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY20;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfe;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x22;
						Open[6]= (byte) 0x53;
						Open[7]= (byte) 0x34;
						delaytime20 = (EditText)findViewById(R.id.ET20);
						value20 = delaytime20.getText().toString();
						int_r20 = Integer.parseInt(value20)%256;
						Open[8]= (byte) int_r20;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfe;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x22;
						close[6]= (byte) 0xca;
						close[7]= (byte) 0xa3;
			    		if(af20==1||af20==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af20==1)ops.write(Open);
		               	 if(af20==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but21==============================================================================
				but21.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY21;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x15;
						Open[6]= (byte) 0x8e;
						Open[7]= (byte) 0x5c;
						delaytime21 = (EditText)findViewById(R.id.ET21);
						value21 = delaytime21.getText().toString();
						int_r21 = Integer.parseInt(value21)%256;
						Open[8]= (byte) int_r21;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x15;
						close[6]= (byte) 0x17;
						close[7]= (byte) 0xcb;
			    		if(af21==1||af21==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af21==1)ops.write(Open);
		               	 if(af21==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but22==============================================================================
				but22.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY22;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x16;
						Open[6]= (byte) 0xbe;
						Open[7]= (byte) 0x3f;
						delaytime22 = (EditText)findViewById(R.id.ET22);
						value22 = delaytime22.getText().toString();
						int_r22 = Integer.parseInt(value22)%256;
						Open[8]= (byte) int_r22;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x16;
						close[6]= (byte) 0x27;
						close[7]= (byte) 0xa8;
			    		if(af22==1||af22==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af22==1)ops.write(Open);
		               	 if(af22==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but23==============================================================================
				but23.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY23;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x17;
						Open[6]= (byte) 0xae;
						Open[7]= (byte) 0x1e;
						delaytime23 = (EditText)findViewById(R.id.ET23);
						value23 = delaytime23.getText().toString();
						int_r23 = Integer.parseInt(value23)%256;
						Open[8]= (byte) int_r23;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x17;
						close[6]= (byte) 0x37;
						close[7]= (byte) 0x89;
			    		if(af23==1||af23==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af23==1)ops.write(Open);
		               	 if(af23==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but24==============================================================================
				but24.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY24;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x18;
						Open[6]= (byte) 0x5f;
						Open[7]= (byte) 0xf1;
						delaytime24 = (EditText)findViewById(R.id.ET24);
						value24 = delaytime24.getText().toString();
						int_r24 = Integer.parseInt(value24)%256;
						Open[8]= (byte) int_r24;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x18;
						close[6]= (byte) 0xc6;
						close[7]= (byte) 0x66;
			    		if(af24==1||af24==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af24==1)ops.write(Open);
		               	 if(af24==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but25==============================================================================
				but25.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY25;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x19;
						Open[6]= (byte) 0x4f;
						Open[7]= (byte) 0xd0;
						delaytime25 = (EditText)findViewById(R.id.ET25);
						value25 = delaytime25.getText().toString();
						int_r25 = Integer.parseInt(value25)%256;
						Open[8]= (byte) int_r25;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x19;
						close[6]= (byte) 0xd6;
						close[7]= (byte) 0x47;
			    		if(af25==1||af25==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af25==1)ops.write(Open);
		               	 if(af25==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but26==============================================================================
				but26.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY26;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1a;
						Open[6]= (byte) 0x7f;
						Open[7]= (byte) 0xb3;
						delaytime26 = (EditText)findViewById(R.id.ET26);
						value26 = delaytime26.getText().toString();
						int_r26 = Integer.parseInt(value26)%256;
						Open[8]= (byte) int_r26;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1a;
						close[6]= (byte) 0xe6;
						close[7]= (byte) 0x24;
			    		if(af26==1||af26==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af26==1)ops.write(Open);
		               	 if(af26==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but27==============================================================================
				but27.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY27;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1b;
						Open[6]= (byte) 0x6f;
						Open[7]= (byte) 0x92;
						delaytime27 = (EditText)findViewById(R.id.ET27);
						value27 = delaytime27.getText().toString();
						int_r27 = Integer.parseInt(value27)%256;
						Open[8]= (byte) int_r27;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1b;
						close[6]= (byte) 0xf6;
						close[7]= (byte) 0x05;
			    		if(af27==1||af27==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af27==1)ops.write(Open);
		               	 if(af27==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but28==============================================================================
				but28.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY28;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1c;
						Open[6]= (byte) 0x1f;
						Open[7]= (byte) 0x75;
						delaytime28 = (EditText)findViewById(R.id.ET28);
						value28 = delaytime28.getText().toString();
						int_r28 = Integer.parseInt(value28)%256;
						Open[8]= (byte) int_r28;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1c;
						close[6]= (byte) 0x86;
						close[7]= (byte) 0xe2;
			    		if(af28==1||af28==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af28==1)ops.write(Open);
		               	 if(af28==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but29==============================================================================
				but29.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY29;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1d;
						Open[6]= (byte) 0x0f;
						Open[7]= (byte) 0x54;
						delaytime29 = (EditText)findViewById(R.id.ET29);
						value29 = delaytime29.getText().toString();
						int_r29 = Integer.parseInt(value29)%256;
						Open[8]= (byte) int_r29;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1d;
						close[6]= (byte) 0x96;
						close[7]= (byte) 0xc3;
			    		if(af29==1||af29==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af29==1)ops.write(Open);
		               	 if(af29==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but30==============================================================================
				but30.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY30;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1e;
						Open[6]= (byte) 0x3f;
						Open[7]= (byte) 0x37;
						delaytime30 = (EditText)findViewById(R.id.ET30);
						value30 = delaytime30.getText().toString();
						int_r30 = Integer.parseInt(value30)%256;
						Open[8]= (byte) int_r30;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1e;
						close[6]= (byte) 0xa6;
						close[7]= (byte) 0xa0;
			    		if(af30==1||af30==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af30==1)ops.write(Open);
		               	 if(af30==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but31==============================================================================
				but31.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY31;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x1f;
						Open[6]= (byte) 0x2f;
						Open[7]= (byte) 0x16;
						delaytime31 = (EditText)findViewById(R.id.ET31);
						value31 = delaytime31.getText().toString();
						int_r31 = Integer.parseInt(value31)%256;
						Open[8]= (byte) int_r31;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x1f;
						close[6]= (byte) 0xb6;
						close[7]= (byte) 0x81;
			    		if(af31==1||af31==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af31==1)ops.write(Open);
		               	 if(af31==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but32==============================================================================
				but32.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY32;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x20;
						Open[6]= (byte) 0xe8;
						Open[7]= (byte) 0xaa;
						delaytime32 = (EditText)findViewById(R.id.ET32);
						value32 = delaytime32.getText().toString();
						int_r32 = Integer.parseInt(value32)%256;
						Open[8]= (byte) int_r32;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x20;
						close[6]= (byte) 0x71;
						close[7]= (byte) 0x3d;
			    		if(af32==1||af32==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af32==1)ops.write(Open);
		               	 if(af32==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					
	//but33==============================================================================
				but33.setOnClickListener(new Button.OnClickListener(){
					Runnable runnable = new Runnable(){public void run() {Message message = new Message();message.what = KEY33;try{
						byte[] Open = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						Open[0]= (byte) 0xee;
						Open[1]= (byte) 0x5c;
						Open[2]= (byte) 0xfd;
						Open[3]= (byte) 0x08;
						Open[4]= (byte) 0x13;
						Open[5]= (byte) 0x21;
						Open[6]= (byte) 0xf8;
						Open[7]= (byte) 0x8b;
						delaytime33 = (EditText)findViewById(R.id.ET33);
						value33 = delaytime33.getText().toString();
						int_r33 = Integer.parseInt(value33)%256;
						Open[8]= (byte) int_r33;
						
					byte[] close = new byte[]{0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
						close[0]= (byte) 0xee;
						close[1]= (byte) 0x5c;
						close[2]= (byte) 0xfd;
						close[3]= (byte) 0x08;
						close[4]= (byte) 0x14;
						close[5]= (byte) 0x21;
						close[6]= (byte) 0x61;
						close[7]= (byte) 0x1c;
			    		if(af33==1||af33==0){
			    	   mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
			    		// mSocket =new Socket("121.224.133.97",8899);
						//Open[8]= Byte.decode("0x" + Integer.toHexString(int_r6)); 
			    	     ops=mSocket.getOutputStream();ips =mSocket.getInputStream();
		               	 if(af33==1)ops.write(Open);
		               	 if(af33==0)ops.write(close);
		               	 ips.read(back);ips.close();ops.close();mSocket.close();	                  
		                 mHandler.sendMessage(message); }} catch (Exception e) {e.printStackTrace(); }}};public void onClick(View v) {new Thread(runnable).start();}});					

     //but34==============================================================================
		but34.setOnClickListener(new Button.OnClickListener(){
			byte[] cmd=new byte[]{ -1, 0x5a, -2, 0x08, 0x0b, 0x00,0x4f,0x6f};
			Runnable runnable = new Runnable(){public void run() {Message message = new Message();                       
		               message.what = UPDATE_UI; 
		    	try{	           
				     mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
		    		//mSocket =new Socket("121.224.133.97",8899);
	               	 ops=mSocket.getOutputStream();
	               	 ips =mSocket.getInputStream();
	               	 ops.write(cmd); ips.read(back);ips.close();ops.close();mSocket.close();	                  
	                 mHandler.sendMessage(message); } catch (Exception e) {e.printStackTrace(); }}};	
	                 public void onClick(View v) {
	                	new Thread(runnable).start();
	     				String ip = ServerIp.getText().toString().trim();
	    				String port = ServerPort.getText().toString().trim();
	    				saveIpPort();
	                 }});
	  //butLG==============================================================================
		butLG.setOnClickListener(new Button.OnClickListener(){
			
	                 public void onClick(View v) {
	                	 		
	         			Runnable runnable = new Runnable(){public void run() {Message message = new Message();                       
	         		               message.what = LG_OK; 
	         		               
	         		            //组织命令
	       	         			//0xff 0x5a 0xfe 0x28 0x21 xx xx ... xx xx 00 00  (xx xx...xx xx 为33个按钮的时间，每个时间一个字节)
	       	         			//（十六进制0xff = 无符号255 = 有符号-127）
	         		            //16.02.20最新包头：FF 5A FE 28 21   
	       	         			//byte[] cmd=new byte[]{ -1, 0x5a, -2, 0x08, 0x0b, 0x00,0x4f,0x6f};
	         		              byte[] cmd=new byte[]{ -1, 0x5a, -2, 0x28, 0x21,
	      	         					
	      	         				(byte)Integer.parseInt(delaytime1.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime2.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime3.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime4.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime5.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime6.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime7.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime8.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime9.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime10.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime11.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime12.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime13.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime14.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime15.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime16.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime17.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime18.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime19.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime20.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime21.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime22.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime23.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime24.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime25.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime26.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime27.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime28.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime29.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime30.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime31.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime32.getText().toString()),
	      	         				(byte)Integer.parseInt(delaytime33.getText().toString()),
	      	         					
	      	         				0x00, 0x00};       
	         		               
	         		    	try{	           
	         				     mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
	         		    		//mSocket =new Socket("121.224.133.97",8899);
	         	               	 ops=mSocket.getOutputStream();
	         	               	 ips =mSocket.getInputStream();
	         	               	 ops.write(cmd); ips.read(back);ips.close();ops.close();mSocket.close();	                  
	         	                 mHandler.sendMessage(message); } catch (Exception e) {e.printStackTrace(); }}};	
	                	 
	                	new Thread(runnable).start();
	     				String ip = ServerIp.getText().toString().trim();
	    				String port = ServerPort.getText().toString().trim();
	    				saveIpPort();
	                 }});
		
		//butSF==============================================================================
		butSF.setOnClickListener(new Button.OnClickListener(){
			    
			byte[] cmd=new byte[]{ -1, 0x5A, -2, 0x08, 0x22, 0x00, -13, 0x11 };
			Runnable runnable = new Runnable(){public void run() {Message message = new Message();                       
				       message.what = SF_OK; 
			try{	           
					mSocket =new Socket(InetAddress.getByName(ServerIp.getText().toString().trim()), Integer.parseInt(ServerPort.getText().toString().trim()));
					ops=mSocket.getOutputStream();
			        ips =mSocket.getInputStream();
			        ops.write(cmd); ips.read(backSF);ips.close();ops.close();mSocket.close();	                  
			        mHandler.sendMessage(message); } catch (Exception e) {e.printStackTrace(); }}};	
			        public void onClick(View v) {
			                new Thread(runnable).start();
			     			String ip = ServerIp.getText().toString().trim();
			    			String port = ServerPort.getText().toString().trim();
			    			saveIpPort();
			        }});

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public void saveIpPort()             //保存IP地址及端口
    {
 	    SharedPreferences uiState   = getSharedPreferences("wifi relay", MODE_PRIVATE);
 		Editor et=uiState.edit();
 		et.putString("ip",ServerIp.getText().toString());
 		et.putString("port",ServerPort.getText().toString()); 
 		et.commit();
    } 

    public void loadIpPort()              //载入IP地址及端口
    {
 	    SharedPreferences uiState   = getSharedPreferences("wifi relay", MODE_PRIVATE);
 	   ServerIp.setText(uiState.getString("ip","192.168.1.180" ));
 	   ServerPort.setText(uiState.getString("port", "8899")); 
    }

	public static String byteToString(byte b) {
		byte high, low;
		byte maskHigh = (byte) 0xf0;
		byte maskLow = 0x0f;
		high = (byte) ((b & maskHigh) >> 4);
		low = (byte) (b & maskLow);
		StringBuffer buf = new StringBuffer();
		if (b!=0){ 
			buf.append(findHex(high));
			buf.append(findHex(low));
		}else{
			buf.append("00");		
		}
		return buf.toString();
	}

	private static char findHex(byte b) {
		int t = new Byte(b).intValue();
		t = t < 0 ? t + 16 : t;
		if ((0 <= t) && (t <= 9)) {
			return (char) (t + '0');
		}
		return (char) (t - 10 + 'A');
	}

	/**
	 * 启动倒计时
	 * @param key， 要启动哪个区的倒计时
	 */
	private void setTimer(int key){
		if(countDownTimer != null){
			countDownTimer.cancel();
		}
		int time = 0;
		switch(key){
			case KEY1: time = Integer.parseInt(delaytime1.getText().toString()); break;
			case KEY2: time = Integer.parseInt(delaytime2.getText().toString()); break;
			case KEY3: time = Integer.parseInt(delaytime3.getText().toString()); break;
			case KEY4: time = Integer.parseInt(delaytime4.getText().toString()); break;
			case KEY5: time = Integer.parseInt(delaytime5.getText().toString()); break;
			case KEY6: time = Integer.parseInt(delaytime6.getText().toString()); break;
			case KEY7: time = Integer.parseInt(delaytime7.getText().toString()); break;
			case KEY8: time = Integer.parseInt(delaytime8.getText().toString()); break;
			case KEY9: time = Integer.parseInt(delaytime9.getText().toString()); break;
			case KEY10: time = Integer.parseInt(delaytime10.getText().toString()); break;
			case KEY11: time = Integer.parseInt(delaytime11.getText().toString()); break;
			case KEY12: time = Integer.parseInt(delaytime12.getText().toString()); break;
			case KEY13: time = Integer.parseInt(delaytime13.getText().toString()); break;
			case KEY14: time = Integer.parseInt(delaytime14.getText().toString()); break;
			case KEY15: time = Integer.parseInt(delaytime15.getText().toString()); break;
			case KEY16: time = Integer.parseInt(delaytime16.getText().toString()); break;
			case KEY17: time = Integer.parseInt(delaytime17.getText().toString()); break;
			case KEY18: time = Integer.parseInt(delaytime18.getText().toString()); break;
			case KEY19: time = Integer.parseInt(delaytime19.getText().toString()); break;
			case KEY20: time = Integer.parseInt(delaytime20.getText().toString()); break;
			case KEY21: time = Integer.parseInt(delaytime21.getText().toString()); break;
			case KEY22: time = Integer.parseInt(delaytime22.getText().toString()); break;
			case KEY23: time = Integer.parseInt(delaytime23.getText().toString()); break;
			case KEY24: time = Integer.parseInt(delaytime24.getText().toString()); break;
			case KEY25: time = Integer.parseInt(delaytime25.getText().toString()); break;
			case KEY26: time = Integer.parseInt(delaytime26.getText().toString()); break;
			case KEY27: time = Integer.parseInt(delaytime27.getText().toString()); break;
			case KEY28: time = Integer.parseInt(delaytime28.getText().toString()); break;
			case KEY29: time = Integer.parseInt(delaytime29.getText().toString()); break;
			case KEY30: time = Integer.parseInt(delaytime30.getText().toString()); break;
			case KEY31: time = Integer.parseInt(delaytime31.getText().toString()); break;
			case KEY32: time = Integer.parseInt(delaytime32.getText().toString()); break;
			case KEY33: time = Integer.parseInt(delaytime33.getText().toString()); break;
			
			case LG_OK: time = 
				Integer.parseInt(delaytime1.getText().toString()) + 
				Integer.parseInt(delaytime2.getText().toString()) + 
				Integer.parseInt(delaytime3.getText().toString()) + 
				Integer.parseInt(delaytime4.getText().toString()) + 
				Integer.parseInt(delaytime5.getText().toString()) + 
				Integer.parseInt(delaytime6.getText().toString()) + 
				Integer.parseInt(delaytime7.getText().toString()) + 
				Integer.parseInt(delaytime8.getText().toString()) + 
				Integer.parseInt(delaytime9.getText().toString()) + 
				Integer.parseInt(delaytime10.getText().toString()) + 
				Integer.parseInt(delaytime11.getText().toString()) + 
				Integer.parseInt(delaytime12.getText().toString()) + 
				Integer.parseInt(delaytime13.getText().toString()) + 
				Integer.parseInt(delaytime14.getText().toString()) + 
				Integer.parseInt(delaytime15.getText().toString()) + 
				Integer.parseInt(delaytime16.getText().toString()) + 
				Integer.parseInt(delaytime17.getText().toString()) + 
				Integer.parseInt(delaytime18.getText().toString()) + 
				Integer.parseInt(delaytime19.getText().toString()) + 
				Integer.parseInt(delaytime20.getText().toString()) + 
				Integer.parseInt(delaytime21.getText().toString()) + 
				Integer.parseInt(delaytime22.getText().toString()) + 
				Integer.parseInt(delaytime23.getText().toString()) + 
				Integer.parseInt(delaytime24.getText().toString()) + 
				Integer.parseInt(delaytime25.getText().toString()) + 
				Integer.parseInt(delaytime26.getText().toString()) + 
				Integer.parseInt(delaytime27.getText().toString()) + 
				Integer.parseInt(delaytime28.getText().toString()) + 
				Integer.parseInt(delaytime29.getText().toString()) + 
				Integer.parseInt(delaytime30.getText().toString()) + 
				Integer.parseInt(delaytime31.getText().toString()) + 
				Integer.parseInt(delaytime32.getText().toString()) + 
				Integer.parseInt(delaytime33.getText().toString()); 
				System.out.println("轮灌时间：" + time); break;
			default:
				break;	
		}
		
		Toast.makeText(this, "开始倒计时", Toast.LENGTH_SHORT).show();
		
		countDownTimer = new CountDownTimer(time * 60000, 1000) {  
      	     public void onTick(long millisUntilFinished) { 	
      	    	 
      	    //tvTotalTime.setText(millisUntilFinished / 1000 + "");
      	      tvTotalTime.setText(
      	    		  			  millisUntilFinished / 1000 / 3600 + ":" 
      	   + StringUtil.fillChar((millisUntilFinished / 1000 / 60 % 60) + "", 2, "0") + ":" 
      	   + StringUtil.fillChar((millisUntilFinished / 1000 % 60)+"", 2, "0")
      	    		  			);
      	     }  
      	     public void onFinish() {  
      	    	 //do something...
      	    	tvTotalTime.setText("--");
      	     }  
      	 };
			countDownTimer.start();
	}
	
	/**
	 * 停止倒计时，时钟清空
	 */
	private void stopTimer(){
		countDownTimer.cancel();
		Toast.makeText(this, "停止倒计时", Toast.LENGTH_SHORT).show();
		tvTotalTime.setText("--");
	}
	
	/** 更新土壤水分信息 */
	private void updateEarthInfo(byte[] info){
		if(info != null){
			String msg = "土壤湿度1：" + info[0] + "%\r\n" +
                    "土壤湿度2：" + info[1] + "%\r\n" +
                    "土壤湿度3：" + info[2] + "%\r\n";
			System.out.println("SF msg:" + msg);
			tvSF.setText(msg);
		}else{
			tvSF.setText(null);
		}
         
	}
	
	//在onStop方法中记录一下33路的倒计时数值
	@Override
	public void onStop() {
		System.out.println("onStop 方法");
		Time time = new Time();
		time.setT1(Integer.parseInt(delaytime1.getText().toString()));
		time.setT2(Integer.parseInt(delaytime2.getText().toString()));
		time.setT3(Integer.parseInt(delaytime3.getText().toString()));
		time.setT4(Integer.parseInt(delaytime4.getText().toString()));
		time.setT5(Integer.parseInt(delaytime5.getText().toString()));
		time.setT6(Integer.parseInt(delaytime6.getText().toString()));
		time.setT7(Integer.parseInt(delaytime7.getText().toString()));
		time.setT8(Integer.parseInt(delaytime8.getText().toString()));
		time.setT9(Integer.parseInt(delaytime9.getText().toString()));
		time.setT10(Integer.parseInt(delaytime10.getText().toString()));
		time.setT11(Integer.parseInt(delaytime11.getText().toString()));
		time.setT12(Integer.parseInt(delaytime12.getText().toString()));
		time.setT13(Integer.parseInt(delaytime13.getText().toString()));
		time.setT14(Integer.parseInt(delaytime14.getText().toString()));
		time.setT15(Integer.parseInt(delaytime15.getText().toString()));
		time.setT16(Integer.parseInt(delaytime16.getText().toString()));
		time.setT17(Integer.parseInt(delaytime17.getText().toString()));
		time.setT18(Integer.parseInt(delaytime18.getText().toString()));
		time.setT19(Integer.parseInt(delaytime19.getText().toString()));
		time.setT20(Integer.parseInt(delaytime20.getText().toString()));
		time.setT21(Integer.parseInt(delaytime21.getText().toString()));
		time.setT22(Integer.parseInt(delaytime22.getText().toString()));
		time.setT23(Integer.parseInt(delaytime23.getText().toString()));
		time.setT24(Integer.parseInt(delaytime24.getText().toString()));
		time.setT25(Integer.parseInt(delaytime25.getText().toString()));
		time.setT26(Integer.parseInt(delaytime26.getText().toString()));
		time.setT27(Integer.parseInt(delaytime27.getText().toString()));
		time.setT28(Integer.parseInt(delaytime28.getText().toString()));
		time.setT29(Integer.parseInt(delaytime29.getText().toString()));
		time.setT30(Integer.parseInt(delaytime30.getText().toString()));
		time.setT31(Integer.parseInt(delaytime31.getText().toString()));
		time.setT32(Integer.parseInt(delaytime32.getText().toString()));
		time.setT33(Integer.parseInt(delaytime33.getText().toString()));
		
		TimeLab.Time = time;
		TimeLab.get(this).saveTime();
		Toast.makeText(this, "时间已记录", Toast.LENGTH_SHORT);
		System.out.println("onStop 写入成功");
		super.onStop();
	}
	
}