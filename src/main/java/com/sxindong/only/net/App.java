package com.sxindong.only.net;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.sxindong.only.net.constant.TemplateConstant;
import com.sxindong.only.net.constant.URLConstant;
import com.sxindong.only.net.util.HttpsUtil;

public class App {

	public static void send() {

	    String accessToken = getAccessToken();
		String openId = "o1vJy6q1SeZi7RGJytnc_jHjupcc";//wode
//		String openId = "o1vJy6lv0fmRyaQn5zIuu-BwpfUE";//tade
		String templateId = TemplateConstant.XIABANLA;
	
		String wechatUrl = URLConstant.NOTICE_URL;
		String url = wechatUrl.replace("ACCESS_TOKEN", accessToken);
		String outputStr = null;
		
		JSONObject jsonParam = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject dateContent = new JSONObject();
		JSONObject meetDaysContent = new JSONObject();
		JSONObject heartWordsContent = new JSONObject();
		jsonParam.put("touser", openId);
		jsonParam.put("template_id", templateId);
		jsonParam.put("topcolor", "#FF0000");
		jsonParam.put("data", data);
		data.put("DATE", dateContent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH��mm��");
		dateContent.put("value", sdf.format(new Date()));
		dateContent.put("color", "#173177");
		data.put("MEET_DAYS", meetDaysContent);
		meetDaysContent.put("value", "���������������ĵ�398�죬�ٺٺ�");
		meetDaysContent.put("color", "#90EE90");
		data.put("HEART_WORDS", heartWordsContent);
//		heartWordsContent.put("value", "��ʫһ��~~~~~\r\n"
//				+ "        ����ǣţ��\r\n"
//				+ "����ǣţ�ǣ���Ӻ�Ů��\r\n"
//				+ "����ߪ���֣�����Ū���̡�\r\n"
//				+ "���ղ����£����������ꡣ\r\n"
//				+ "�Ӻ�����ǳ����ȥ������\r\n"
//				+ "ӯӯһˮ�䣬���������");
		heartWordsContent.put("value","���㲻�������죬ÿ������ܶ�������������");
		heartWordsContent.put("color", "#14144D");
		
		outputStr = jsonParam.toJSONString();
		JSONObject result = HttpsUtil.httpRequest(url, "POST", outputStr);
		
		System.out.println(result);
	}
	
	public static String getAccessToken() {
		String url = URLConstant.ACCESS_TOKEN_URL_TEST;
		JSONObject result = HttpsUtil.httpRequest(url , "POST", null);
		if(result == null) {
			throw new RuntimeException("δ��ȡ��ACCESS_TOKEN");
		}
		return result.getString("access_token");
	}
}
