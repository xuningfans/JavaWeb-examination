package com.netease.course.meta;

import java.io.Serializable;

/**
 * 商品内容实体类
 * 
 * @author 公猴脖子男
 */
public class Content implements Serializable {
	private static final long serialVersionUID = 6751853197091578593L;

	/** id  */
	private Integer id;

	/** 价格  */
	private Double price;

	/** 标题 */
	private String title;

	/** 摘要 */
	private String summary;

	/** 图片位置 */
	private String image;

	/** 详情 */
	private String detail;

	public Content() {
		super();
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", price=" + price + ", title=" + title + ", summary=" + summary + ", image="
				+ image + ", detail=" + detail + "]";
	}

	
}