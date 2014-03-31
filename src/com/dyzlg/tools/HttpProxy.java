package com.dyzlg.tools;

import com.dyzlg.config.Config;
import com.dyzlg.network.APIQueue;
import com.dyzlg.network.HttpRequest;
import com.dyzlg.network.HttpResponseListener;

/**
 * 网络请求代理类 larry create on 2014-3-24
 */
public class HttpProxy {

	/**
	 * 登陆验证
	 * 
	 * @param username
	 * @param password
	 * @param listener
	 */
	public static void requestUserValidate(String username, String password,
			HttpResponseListener listener) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Config.Key.UserValidate).append("?")
					.append(Config.Key.username).append("=").append(username)
					.append("&").append(Config.Key.password).append("=")
					.append(password);
			APIQueue.getInstance().execute(
					new HttpRequest(sb.toString(), listener));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param listener
	 */
	public static void requestUserRegiste(String username, String password,
			String email, HttpResponseListener listener) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Config.Key.UserRegiste).append("?")
					.append(Config.Key.username).append("=").append(username)
					.append("&").append(Config.Key.password).append("=")
					.append(password).append("&").append(Config.Key.email).append("=")
					.append(email);
			APIQueue.getInstance().execute(
					new HttpRequest(sb.toString(), listener));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载近期热映列表
	 * 
	 * @param page
	 * @param limit
	 * @param listener
	 */
	public static void requestMovieRecentList(int page, int limit,
			HttpResponseListener listener) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Config.Key.RecentfilmAll).append("?")
					.append(Config.Key.pageSize).append("=").append(limit)
					.append("&").append(Config.Key.pageIndex).append("=")
					.append(page);
			APIQueue.getInstance().execute(
					new HttpRequest(sb.toString(), listener));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载拍片表
	 * 
	 * @param page
	 * @param limit
	 * @param listener
	 */
	public static void requestMovieList(int page, int limit,
			HttpResponseListener listener) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Config.Key.FilmShowList).append("?")
					.append(Config.Key.pageSize).append("=").append(limit)
					.append("&").append(Config.Key.pageIndex).append("=")
					.append(page);
			APIQueue.getInstance().execute(
					new HttpRequest(sb.toString(), listener));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载电影详情
	 * 
	 * @param filmId
	 * @param listener
	 */
	public static void requestMovieDetails(long filmId,
			HttpResponseListener listener) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Config.Key.FilmShowDetail).append("?")
					.append(Config.Key.filmId).append("=").append(filmId);
			APIQueue.getInstance().execute(
					new HttpRequest(sb.toString(), listener));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载新闻列表
	 * 
	 * @param page
	 * @param limit
	 * @param listener
	 */
	public static void requestNewsList(int page, int limit,
			HttpResponseListener listener) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Config.Key.NewsList).append("?")
					.append(Config.Key.pageSize).append("=").append(limit)
					.append("&").append(Config.Key.pageIndex).append("=")
					.append(page);
			APIQueue.getInstance().execute(
					new HttpRequest(sb.toString(), listener));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载新闻详情
	 * 
	 * @param infoId
	 * @param listener
	 */
	public static void requestNewsDetail(long infoId,
			HttpResponseListener listener) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Config.Key.NewsDetail).append("?")
					.append(Config.Key.infoId).append("=").append(infoId);
			APIQueue.getInstance().execute(
					new HttpRequest(sb.toString(), listener));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void requestFilmShowLike(HttpResponseListener listener) {
//		try {
//			StringBuilder sb = new StringBuilder();
//			sb.append(Config.Key.NewsDetail).append("?")
//					.append(Config.Key.infoId).append("=").append(infoId);
//			APIQueue.getInstance().execute(
//					new HttpRequest(sb.toString(), listener));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
