package com.weixin.api.po;

import java.util.Date;

public class YzcxQxusers {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column yzcx_qxusers.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column yzcx_qxusers.username
	 * @mbg.generated
	 */
	private String username;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column yzcx_qxusers.ksname
	 * @mbg.generated
	 */
	private String ksname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column yzcx_qxusers.userid
	 * @mbg.generated
	 */
	private String userid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column yzcx_qxusers.createtime
	 * @mbg.generated
	 */
	private Date createtime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column yzcx_qxusers.qxids
	 * @mbg.generated
	 */
	private String qxids;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column yzcx_qxusers.id
	 * @return  the value of yzcx_qxusers.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column yzcx_qxusers.id
	 * @param id  the value for yzcx_qxusers.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column yzcx_qxusers.username
	 * @return  the value of yzcx_qxusers.username
	 * @mbg.generated
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column yzcx_qxusers.username
	 * @param username  the value for yzcx_qxusers.username
	 * @mbg.generated
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column yzcx_qxusers.ksname
	 * @return  the value of yzcx_qxusers.ksname
	 * @mbg.generated
	 */
	public String getKsname() {
		return ksname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column yzcx_qxusers.ksname
	 * @param ksname  the value for yzcx_qxusers.ksname
	 * @mbg.generated
	 */
	public void setKsname(String ksname) {
		this.ksname = ksname == null ? null : ksname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column yzcx_qxusers.userid
	 * @return  the value of yzcx_qxusers.userid
	 * @mbg.generated
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column yzcx_qxusers.userid
	 * @param userid  the value for yzcx_qxusers.userid
	 * @mbg.generated
	 */
	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column yzcx_qxusers.createtime
	 * @return  the value of yzcx_qxusers.createtime
	 * @mbg.generated
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column yzcx_qxusers.createtime
	 * @param createtime  the value for yzcx_qxusers.createtime
	 * @mbg.generated
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column yzcx_qxusers.qxids
	 * @return  the value of yzcx_qxusers.qxids
	 * @mbg.generated
	 */
	public String getQxids() {
		return qxids;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column yzcx_qxusers.qxids
	 * @param qxids  the value for yzcx_qxusers.qxids
	 * @mbg.generated
	 */
	public void setQxids(String qxids) {
		this.qxids = qxids == null ? null : qxids.trim();
	}

	@Override
	public String toString() {
		return "YzcxQxusers [id=" + id + ", username=" + username + ", ksname=" + ksname + ", userid=" + userid
				+ ", createtime=" + createtime + ", qxids=" + qxids + "]";
	}
	
}