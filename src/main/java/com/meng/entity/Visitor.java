package com.meng.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 访客实体
 *	
 */
public class Visitor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int visitorId;  // 访客id
	private String email;	
	private String nikeName;	// 昵称
	private String signContent;	// 签名
	private Date regTime;	// 注册时间，只有注册之后才可以评论
	private String photoImg;
	
	public Visitor() {
		
	}

	public Visitor(int visitorId, String email, String nikeName, String signContent, Date regTime) {
		super();
		this.visitorId = visitorId;
		this.email = email;
		this.nikeName = nikeName;
		this.signContent = signContent;
		this.regTime = regTime;
	}

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getPhotoImg() {
		return photoImg;
	}

	public void setPhotoImg(String photoImg) {
		this.photoImg = photoImg;
	}

	@Override
	public String toString() {
		return "Visitor [visitorId=" + visitorId 
				+ ", email=" + email 
				+ ", nikeName=" + nikeName 
				+ ", signContent="+ signContent 
				+ ", regTime=" + regTime 
				+ ", photoImg=" + photoImg + "]";
	}

	
	
}
