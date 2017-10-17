package com.meng.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 评论实体
 * @author xiaoli
 *
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int commentId;	// 评论id
	private Visitor visitorId;	// 访客id
	private Date commentDate;	// 评论时间
	private String commentContent; //评论内容
	private int auditStatus; // 审核状态 1：审核通过 | 0：审核未通过（未通过的将被管理员从前台删除，但库里会留备份）
	private Blog blogId;	// 关联外键博客id
	
	public Comment() {
		
	}

	public Comment(Visitor visitorId, Date commentDate, String commentContent, int auditStatus, Blog blogId) {
		this.visitorId = visitorId;
		this.commentDate = commentDate;
		this.commentContent = commentContent;
		this.auditStatus = auditStatus;
		this.blogId = blogId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Visitor getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Visitor visitorId) {
		this.visitorId = visitorId;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public Blog getBlogId() {
		return blogId;
	}

	public void setBlogId(Blog blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId 
				+ ", visitorId=" + visitorId
				+ ", commentDate=" + commentDate
				+ ", commentContent=" + commentContent 
				+ ", auditStatus=" + auditStatus 
				+ ", blogId=" + blogId + "]";
	}

}
