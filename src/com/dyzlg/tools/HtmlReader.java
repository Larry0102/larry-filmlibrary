package com.dyzlg.tools;

import android.text.Html;

/**
 * Html标签解析类
 * larry create on 2014-3-28
 */
public class HtmlReader {
	
	public static void parseHtml(String html){
		System.out.println("********parseHtml*********" + Html.fromHtml(html));
	}

}
