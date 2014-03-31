package com.dyzlg.config;

/**
 * 全局常量类 larry create on 2014-3-16
 */
public class Config {

	public static class Key {
		/** 用户验证 */
		public static String UserValidate = "UserValidate";
		/** 用户注册 */
		public static String UserRegiste = "UserRegiste";
		/** 用户名 */
		public static String username = "username";
		/** 用户密码 */
		public static String password = "password";
		/** 邮箱 */
		public static String email = "email";

		/** 电影详情 */
		public static String FilmShowDetail = "FilmShowDetail";
		/** 近期热映 */
		public static String RecentfilmAll = "RecentfilmAll";
		/** 排片表 */
		public static String FilmShowList = "FilmShowList";
		/** 新闻列表 */
		public static String NewsList = "NewsList";
		/** 新闻详情 */
		public static String NewsDetail = "NewsDetail";
		/** 更新喜欢次数 */
		public static String UpdateFilmShowLike = "UpdateFilmShowLike";

		/** 电影ID */
		public static String filmId = "filmid";
		/** 一页加载的数量 */
		public static String pageSize = "pagesize";
		/** 页码 */
		public static String pageIndex = "pageindex";
		/** 新闻ID */
		public static String infoId = "infoid";
		
		/** SharedPreferences key */
		public static final String SHARED_PREFERENCES_TABLE 	= "dyzlg_info";
		public static final String SHARED_USER_NAME_KEY 		= "username";
		public static final String SHARED_USER_PASSWORD_KEY 	= "password";
		public static final String SHARED_COOKIE				= "cookie";
		public static final String SHARED_FIRT_LAUNCH_KEY		= "firtLaunch";
		public static final String SHARED_IS_LOGIN_KEY			= "isLogin";
	}

	/**
	 * 网络常量
	 * 
	 * @author larry
	 */
	public static class Network {
		public static String home = "http://www.cfa.gov.cn/api/appservices.asmx/";
	}

	/**
	 * 配置常量
	 * 
	 * @author larry
	 */
	public static class Setting {
		/** 近期热映的预览方式，false为poster方式，true为list方式 */
		public static boolean moviePreviewType = false;
		/** 是否开启闹钟提醒 */
		public static boolean isOpenAlarm;
	}

	/**
	 * 设备常量
	 * 
	 * @author larry
	 */
	public static class DeviceInfo {

		public static int screenHeight = 0;

		public static int screenWidth = 0;

		public static int screenActionHeight = 0;

		public static float density = 0;

		public static float densityDpi = 0;

		public static float scaledDensity = 0.0f;

		public static final String sDefaultKeyHeight = "keyheight";
	}
}
