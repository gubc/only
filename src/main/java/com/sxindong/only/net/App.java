package com.sxindong.only.net;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSONObject;
import com.sxindong.only.net.constant.TemplateConstant;
import com.sxindong.only.net.constant.URLConstant;
import com.sxindong.only.net.util.HttpsUtil;

public class App {
	
	@PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分");
		dateContent.put("value", dayAdd8H(sdf));
		dateContent.put("color", "#173177");
		data.put("MEET_DAYS", meetDaysContent);
		meetDaysContent.put("value", "今天是我们相遇的第398天，嘿嘿嘿");
		meetDaysContent.put("color", "#90EE90");
		data.put("HEART_WORDS", heartWordsContent);
//		heartWordsContent.put("value", "附诗一首~~~~~\r\n"
//				+ "        迢迢牵牛星\r\n"
//				+ "迢迢牵牛星，皎皎河汉女。\r\n"
//				+ "纤纤擢素手，札札弄机杼。\r\n"
//				+ "终日不成章，泣涕零如雨。\r\n"
//				+ "河汉清且浅，相去复几许。\r\n"
//				+ "盈盈一水间，脉脉不得语。");
		heartWordsContent.put("value","爱你不是两三天，每天想你很多遍哈哈哈哈哈哈");
		heartWordsContent.put("color", "#14144D");
		
		outputStr = jsonParam.toJSONString();
		JSONObject result = HttpsUtil.httpRequest(url, "POST", outputStr);
		
		System.out.println(result);
	}
	
	public static String getAccessToken() {
		String url = URLConstant.ACCESS_TOKEN_URL_TEST;
		JSONObject result = HttpsUtil.httpRequest(url , "POST", null);
		if(result == null) {
			throw new RuntimeException("未获取到ACCESS_TOKEN");
		}
		return result.getString("access_token");
	}

	public static String dayAdd8H(SimpleDateFormat sdf) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR,calendar.get(Calendar.HOUR) + 8);
		return sdf.format(calendar.getTime());
	}

}
