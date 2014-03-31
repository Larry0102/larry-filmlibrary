package com.dyzlg.model;

import java.util.ArrayList;

/**
 * 新闻Bean类 larry create on 2014-3-28
 */
public class NewsBean extends BaseBean {

	private static final long serialVersionUID = 7441132993418762420L;

	private long id;
	/** 新闻标题 */
	private String title;
	/** 创建时间 */
	private String createDate;
	/** 新闻详情 */
	private String content;
	/** 摘要图片 */
	private ArrayList<String> imageUrls;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(ArrayList<String> imageUrls) {
		this.imageUrls = imageUrls;
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
		NewsBean bean = (NewsBean) obj;
		if (id != bean.id) {
			return false;
		}
		return true;
	}
}
