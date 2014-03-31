package com.dyzlg.model;

import java.util.ArrayList;

/**
 * 电影Bean类 larry create on 2014-3-28
 */
public class MovieBean extends BaseBean {

	private static final long serialVersionUID = -132565996044267839L;

	private long id;
	/** 片名 */
	private String name;
	/** 放映时间 */
	private String showTime;
	/** 电影封面 */
	private String titleImageUrl;
	/** 导演 */
	private String director;
	/** 主演 */
	private String starring;
	/** 类型 */
	private String type;
	/** 制片国家或地区 */
	private String country;
	/** 语言 */
	private String language;
	/** 片长 */
	private String length;
	/** 场地 */
	private String cinema;
	/** 剧情简介 */
	private String intro;
	/** 喜欢的人数 */
	private int like;
	/** 片花截图 */
	private ArrayList<String> appImages;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getTitleImageUrl() {
		return titleImageUrl;
	}

	public void setTitleImageUrl(String titleImageUrl) {
		this.titleImageUrl = titleImageUrl;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCinema() {
		return cinema;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public ArrayList<String> getAppImages() {
		return appImages;
	}

	public void setAppImages(ArrayList<String> appImages) {
		this.appImages = appImages;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MovieBean bean = (MovieBean) obj;
		if (id != bean.id) {
			return false;
		}
		return true;
	}

}
