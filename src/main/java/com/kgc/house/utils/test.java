package com.kgc.house.utils;

/**
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class test {
	
	//用户名
	private static String Uid = "k9502";
	
	//接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	//private static String smsMob = "13800000000";
	
	//短信内容
//	private static String smsText = "验证码：8888";

	/**
	 *短信平台
	 * @param smsMob 手机号码
	 * @param smsText 验证码
	 * @return 大于0成功
	 */
	public static int verifyCode(String smsMob,String smsText) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//UTF发送
	     return	client.sendMsgUtf8(Uid, Key, smsText, smsMob);

	}

	public static void main(String[] args) {
		verifyCode("13207270301","验证码：1234,请保管好");
	}
}
