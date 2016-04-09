package com.netease.course.meta;

import java.io.Serializable;
/**
 * 用户实体类
 * 
 * @author 公猴脖子男
 */
public class Person implements Serializable {
	private static final long serialVersionUID = -4752407050384590609L;

	/** id */
	private Integer id;

	/** 用户名 */
	private String userName;

	/** 密码 */
	private String password;

	/** 用户名 */
	private String nickName;

	/** 用户类型 */
	private Byte userType;

	public Person() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickname) {
		this.nickName = nickname;
	}

	public Byte getUsertype() {
		return userType;
	}

	public void setUsertype(Byte usertype) {
		this.userType = usertype;
	}
}