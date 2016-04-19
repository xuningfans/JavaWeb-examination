package com.netease.course.entity;

import java.io.Serializable;

import com.netease.course.meta.Content;
import com.netease.course.meta.Person;

/**
 * 商品页面回显类
 * 
 * @author 公猴脖子男
 */
public class Product extends Content implements Serializable {
	private static final long serialVersionUID = 7898211696442567868L;

	/** 购买时价格 */
	private Double buyPrice;

	/** 购买者 */
	private Person person;

	/** 是否已购买，根据是否有用户购买生成 */
	private Boolean isBuy;

	/** 是否已出售，根据是否有用户购买生成 */
	private Boolean isSell;

	public Product() {
		super();
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

	public Boolean getIsBuy() {
		// 有用户Id，已购买
		if (person != null && person.getId() != null) {
			isBuy = true;
		} else {
			isBuy = false;
		}
		return isBuy;
	}

	public Boolean getIsSell() {
		// 有用户id，已出售
		if (person != null && person.getId() != null) {
			isSell = true;
		} else {
			isSell = false;
		}
		return isSell;
	}

	@Override
	public String toString() {
		return "Product [buyPrice=" + buyPrice + ", person=" + person + ", isBuy=" + isBuy + ", isSell=" + isSell
				+ ", toString()=" + super.toString() + "]";
	}

}
