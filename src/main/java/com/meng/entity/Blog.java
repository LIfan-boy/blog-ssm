package com.meng.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description 博客实体
 *
 */
public class Blog {

	private Integer id;
	private String title;// 博客标题
	private String summary; // 博客摘要
	private Date releaseDate; // 发布日期
	private Integer clickHit; // 阅读次数
	private Integer replyHit; // 评论次数
	private Integer clickPraise; // 点赞次数
	private String content; // 博客内容
	private String contentNoTag; // 不带标签的博客内容，用于Lucene索引中
	private String keyWord; // 关键字，用空格隔开
	
	private BlogType blogType; //外键关联博客类别
	private Integer blogCount; //博客数量，非博客实际属性，用于根据发布日期归档查询
	private String releaseDateStr; //发布日期的字符串，只取年月
	
	private List<String> imageList = new LinkedList<String>();//博客里存的图片，主要用于展示缩略图

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getClickHit() {
		return clickHit;
	}

	public void setClickHit(Integer clickHit) {
		this.clickHit = clickHit;
	}
	
	public Integer getClickPraise() {
		return clickPraise;
	}

	public void setClickPraise(Integer clickPraise) {
		this.clickPraise = clickPraise;
	}

	public Integer getReplyHit() {
		return replyHit;
	}

	public void setReplyHit(Integer replyHit) {
		this.replyHit = replyHit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}

	public Integer getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}

	public String getReleaseDateStr() {
		return releaseDateStr;
	}

	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public String getContentNoTag() {
		return contentNoTag;
	}

	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", summary=" + summary + ", releaseDate=" + releaseDate
				+ ", clickHit=" + clickHit + ", replyHit=" + replyHit + ", clickPraise=" + clickPraise + ", content="
				+ content + ", contentNoTag=" + contentNoTag + ", keyWord=" + keyWord + ", blogType=" + blogType
				+ ", blogCount=" + blogCount + ", releaseDateStr=" + releaseDateStr + ", imageList=" + imageList + "]";
	}
	
}
