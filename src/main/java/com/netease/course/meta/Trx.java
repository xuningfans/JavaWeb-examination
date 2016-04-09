package com.netease.course.meta;

import java.io.Serializable;
/**
 * 订单实体类
 * 
 * @author 公猴脖子男
 */
public class Trx implements Serializable {
	private static final long serialVersionUID = 7512499590702840344L;
	/** ID */
	private Integer id;

	/** 商品 */
	private Content content;

	/** 购买用户 */
	private Person person;

	/** 购买时价格 */
	private Double buyPrice;

	/** 购买时间 */
	private Long time;

	public Trx() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
}