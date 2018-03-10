package com.wifirelay.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Time {
	
	private static final String 
	JSON_1 = "1", JSON_2 = "2", JSON_3 = "3", JSON_4 = "4", JSON_5 = "5", JSON_6 = "6", JSON_7 = "7", JSON_8 = "8", JSON_9 = "9", JSON_10 = "10",
	JSON_11 = "11", JSON_12 = "12", JSON_13 = "13", JSON_14 = "14", JSON_15 = "15", JSON_16 = "16", JSON_17 = "17", JSON_18 = "18", JSON_19 = "19", JSON_20 = "20",
	JSON_21 = "21", JSON_22 = "22", JSON_23 = "23", JSON_24 = "24", JSON_25 = "25", JSON_26 = "26", JSON_27 = "27", JSON_28 = "28", JSON_29 = "29", JSON_30 = "30",
	JSON_31 = "31", JSON_32 = "32", JSON_33 = "33";

	private int 
	t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,
	t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,
	t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,
	t31,t32,t33;
	
	public Time() {}

	public int getT1() { return t1; } public void setT1(int t1) { this.t1 = t1; }
	public int getT2() { return t2; } public void setT2(int t2) { this.t2 = t2; }
	public int getT3() { return t3; } public void setT3(int t3) { this.t3 = t3; }
	public int getT4() { return t4; } public void setT4(int t4) { this.t4 = t4; }
	public int getT5() { return t5; } public void setT5(int t5) { this.t5 = t5; }
	public int getT6() { return t6; } public void setT6(int t6) { this.t6 = t6; }
	public int getT7() { return t7; } public void setT7(int t7) { this.t7 = t7; }
	public int getT8() { return t8; } public void setT8(int t8) { this.t8 = t8; }
	public int getT9() { return t9; } public void setT9(int t9) { this.t9 = t9; }
	public int getT10() { return t10; } public void setT10(int t10) { this.t10 = t10; }
	public int getT11() { return t11; } public void setT11(int t11) { this.t11 = t11; }
	public int getT12() { return t12; } public void setT12(int t12) { this.t12 = t12; }
	public int getT13() { return t13; } public void setT13(int t13) { this.t13 = t13; }
	public int getT14() { return t14; } public void setT14(int t14) { this.t14 = t14; }
	public int getT15() { return t15; } public void setT15(int t15) { this.t15 = t15; }
	public int getT16() { return t16; } public void setT16(int t16) { this.t16 = t16; }
	public int getT17() { return t17; } public void setT17(int t17) { this.t17 = t17; }
	public int getT18() { return t18; } public void setT18(int t18) { this.t18 = t18; }
	public int getT19() { return t19; } public void setT19(int t19) { this.t19 = t19; }
	public int getT20() { return t20; } public void setT20(int t20) { this.t20 = t20; }
	public int getT21() { return t21; } public void setT21(int t21) { this.t21 = t21; }
	public int getT22() { return t22; } public void setT22(int t22) { this.t22 = t22; }
	public int getT23() { return t23; } public void setT23(int t23) { this.t23 = t23; }
	public int getT24() { return t24; } public void setT24(int t24) { this.t24 = t24; }
	public int getT25() { return t25; } public void setT25(int t25) { this.t25 = t25; }
	public int getT26() { return t26; } public void setT26(int t26) { this.t26 = t26; }
	public int getT27() { return t27; } public void setT27(int t27) { this.t27 = t27; }
	public int getT28() { return t28; } public void setT28(int t28) { this.t28 = t28; }
	public int getT29() { return t29; } public void setT29(int t29) { this.t29 = t29; }
	public int getT30() { return t30; } public void setT30(int t30) { this.t30 = t30; }
	public int getT31() { return t31; } public void setT31(int t31) { this.t31 = t31; }
	public int getT32() { return t32; } public void setT32(int t32) { this.t32 = t32; }
	public int getT33() { return t33; } public void setT33(int t33) { this.t33 = t33; }
	
	/** ¥”JSON∂¡»° */
	public Time(JSONObject json) throws JSONException{
		t1 = json.getInt(JSON_1);
		t2 = json.getInt(JSON_2);
		t3 = json.getInt(JSON_3);
		t4 = json.getInt(JSON_4);
		t5 = json.getInt(JSON_5);
		t6 = json.getInt(JSON_6);
		t7 = json.getInt(JSON_7);
		t8 = json.getInt(JSON_8);
		t9 = json.getInt(JSON_9);
		t10 = json.getInt(JSON_10);
		t11 = json.getInt(JSON_11);
		t12 = json.getInt(JSON_12);
		t13 = json.getInt(JSON_13);
		t14 = json.getInt(JSON_14);
		t15 = json.getInt(JSON_15);
		t16 = json.getInt(JSON_16);
		t17 = json.getInt(JSON_17);
		t18 = json.getInt(JSON_18);
		t19 = json.getInt(JSON_19);
		t20 = json.getInt(JSON_20);
		t21 = json.getInt(JSON_21);
		t22 = json.getInt(JSON_22);
		t23 = json.getInt(JSON_23);
		t24 = json.getInt(JSON_24);
		t25 = json.getInt(JSON_25);
		t26 = json.getInt(JSON_26);
		t27 = json.getInt(JSON_27);
		t28 = json.getInt(JSON_28);
		t29 = json.getInt(JSON_29);
		t30 = json.getInt(JSON_30);
		t31 = json.getInt(JSON_31);
		t32 = json.getInt(JSON_32);
		t33 = json.getInt(JSON_33);
	}
	
	/** –¥»ÎJSON */
	public JSONObject toJSON() throws JSONException{
		JSONObject json = new JSONObject();
		json.put(JSON_1, t1);
		json.put(JSON_2, t2);
		json.put(JSON_3, t3);
		json.put(JSON_4, t4);
		json.put(JSON_5, t5);
		json.put(JSON_6, t6);
		json.put(JSON_7, t7);
		json.put(JSON_8, t8);
		json.put(JSON_9, t9);
		json.put(JSON_10, t10);
		json.put(JSON_11, t11);
		json.put(JSON_12, t12);
		json.put(JSON_13, t13);
		json.put(JSON_14, t14);
		json.put(JSON_15, t15);
		json.put(JSON_16, t16);
		json.put(JSON_17, t17);
		json.put(JSON_18, t18);
		json.put(JSON_19, t19);
		json.put(JSON_20, t20);
		json.put(JSON_21, t21);
		json.put(JSON_22, t22);
		json.put(JSON_23, t23);
		json.put(JSON_24, t24);
		json.put(JSON_25, t25);
		json.put(JSON_26, t26);
		json.put(JSON_27, t27);
		json.put(JSON_28, t28);
		json.put(JSON_29, t29);
		json.put(JSON_30, t30);
		json.put(JSON_31, t31);
		json.put(JSON_32, t32);
		json.put(JSON_33, t33);
		return json;
	}
	
}
