package cn.facebook.doman;

import java.util.List;
import java.util.Map;

public class UserInfoTemplate {

	/**
	 * 曾经工作的公司
	 */
	private List<String> employer;
	/**
	 * 曾经工作的大学
	 */
	private List<String> university;
	/**
	 * 现在的感情状况 1：单身，2：恋爱中，3：开放式的交往关系，4：已婚，5：订婚，6：不好说，7：丧偶，
	 * 8：分居，9：离婚，10：同性伴侣，11：同性恋-同居
	 */
	private Integer relation;

	/**
	 * 家庭成员 map<姓名，关系> 102:妈妈，103：爸爸....
	 */
	private Map<String, Integer> family;
	/**
	 * 一段关于自己的描述
	 */
	private String aboutMe;
	/**
	 * 喜欢的名言
	 */
	private String saying;
	/**
	 * 家乡
	 */
	private String hometown;
	/**
	 * 居住地
	 */
	private String place;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 封面
	 */
	private String cover;

	@Override
	public String toString() {
		return "UserInfoTemplate [employer=" + employer + ", university="
				+ university + ", relation=" + relation + ", family=" + family
				+ ", aboutMe=" + aboutMe + ", saying=" + saying + ", hometown="
				+ hometown + ", place=" + place + ", nation=" + nation
				+ ", phone=" + phone + ", cover=" + cover + "]";
	}

}
