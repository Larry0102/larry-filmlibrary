package com.dyzlg.tools;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dyzlg.model.MovieBean;
import com.dyzlg.model.NewsBean;
import com.dyzlg.model.UserRegiste;
import com.dyzlg.model.UserValidate;

/**
 * JSON解析工具类 larry create on 2014-3-26
 */
public class JsonReader {

	/**
	 * 解析登陆验证返回数据
	 * 
	 * @param userInfo
	 * @param jsonString
	 * @return
	 */
	public static UserValidate parseUserValidate(String jsonString) {
		UserValidate validate = new UserValidate();
		try {
			JSONArray array = new JSONArray(jsonString);
			JSONObject jsonObject = (JSONObject)array.get(0);
			validate.setUserid(jsonObject.getLong("userid"));
			validate.setCode(jsonObject.getLong("code"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return validate;
	}

	/**
	 * 解析注册返回数据
	 * 
	 * @param jsonString
	 * @return
	 */
	public static UserRegiste parseUserRegiste(String jsonString) {
		UserRegiste registe = new UserRegiste();
		try {
			JSONArray array = new JSONArray(jsonString);
			JSONObject jsonObject = (JSONObject)array.get(0);
			registe.setCode(jsonObject.getLong("code"));
			registe.setMsg(jsonObject.getString("msg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registe;
	}

	/**
	 * 多条电影信息解析
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<MovieBean> parseMovieListJSONObject(
			String jsonString) {
		ArrayList<MovieBean> list = new ArrayList<MovieBean>();
		try {
			JSONArray array = new JSONArray(jsonString);
			JSONObject movie = (JSONObject)array.get(0);
			JSONArray jsonArray = movie.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				MovieBean bean = new MovieBean();
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				bean.setId(jsonObject.getLong("filmid"));
				bean.setName(jsonObject.getString("name"));
				bean.setShowTime(jsonObject.getString("showtime"));
				bean.setTitleImageUrl(jsonObject.getString("titleimage"));
				list.add(bean);
			}
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 单条电影信息解析
	 * 
	 * @param jsonString
	 * @return
	 */
	public static MovieBean parseMovieJSONObject(String jsonString) {
		MovieBean bean = new MovieBean();
		try {
			JSONArray array = new JSONArray(jsonString);
			JSONObject jsonObject = (JSONObject)array.get(0);
			bean.setId(0L);
			bean.setName(jsonObject.getString("name"));
			bean.setShowTime(jsonObject.getString("showtime"));
			bean.setTitleImageUrl(jsonObject.getString("titleimage"));
			bean.setLike(jsonObject.getInt("like"));
			HtmlReader.parseHtml(jsonObject.getString("content"));
			ArrayList<String> images = new ArrayList<String>();
			JSONArray jsonArray = jsonObject.getJSONArray("AppImages");
			for (int index = 0; index < jsonArray.length(); index++) {
				JSONObject imgObject = (JSONObject) jsonArray.get(index);
				images.add(imgObject.getString("appimage"));
			}
			bean.setAppImages(images);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 多条新闻信息解析
	 * 
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<NewsBean> parseNewsListJSONObject(String jsonString) {
		ArrayList<NewsBean> list = new ArrayList<NewsBean>();
		try {
			JSONArray array = new JSONArray(jsonString);
			JSONObject news = (JSONObject) array.get(0);
			HtmlReader.parseHtml(news.getString("sum"));
			JSONArray jsonArray = news.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				NewsBean bean = new NewsBean();
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				bean.setTitle(jsonObject.getString("title"));
				bean.setCreateDate(jsonObject.getString("createdate"));
				list.add(bean);
			}
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 单条新闻信息解析
	 * 
	 * @param jsonString
	 * @return
	 */
	public static NewsBean parseNewsJSONObject(String jsonString) {
		NewsBean bean = new NewsBean();
		try {
			JSONArray array = new JSONArray(jsonString);
			JSONObject jsonObject = (JSONObject)array.get(0);
			bean.setId(0L);
			bean.setTitle(jsonObject.getString("title"));
			bean.setCreateDate(jsonObject.getString("createdate"));
			HtmlReader.parseHtml(jsonObject.getString("content"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
