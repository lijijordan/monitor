/**
 * 用户信息实体
 */
package com.monitor.server.entity;

import java.io.Serializable;

/**
 * @author yinhong
 *
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -2277018491304480258L;
	private int id;
	private String name;
	private int sex;
	private String email;
	private String telNum;
	private int age;
	private String pwd;
	private String webChat;
	private int aquariumsLength;
	private int aquariumsWidth;
	private int aquariumsHeigth;
	private int aquariumsType;
	private int aquariumsAge;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getWebChat() {
		return webChat;
	}

	public void setWebChat(String webChat) {
		this.webChat = webChat;
	}

	public int getAquariumsLength() {
		return aquariumsLength;
	}

	public void setAquariumsLength(int aquariumsLength) {
		this.aquariumsLength = aquariumsLength;
	}

	public int getAquariumsWidth() {
		return aquariumsWidth;
	}

	public void setAquariumsWidth(int aquariumsWidth) {
		this.aquariumsWidth = aquariumsWidth;
	}

	public int getAquariumsHeigth() {
		return aquariumsHeigth;
	}

	public void setAquariumsHeigth(int aquariumsHeigth) {
		this.aquariumsHeigth = aquariumsHeigth;
	}

	public int getAquariumsType() {
		return aquariumsType;
	}

	public void setAquariumsType(int aquariumsType) {
		this.aquariumsType = aquariumsType;
	}

	public int getAquariumsAge() {
		return aquariumsAge;
	}

	public void setAquariumsAge(int aquariumsAge) {
		this.aquariumsAge = aquariumsAge;
	}

}
