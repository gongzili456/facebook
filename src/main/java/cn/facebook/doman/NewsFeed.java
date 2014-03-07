package cn.facebook.doman;

import java.util.Date;

/**
 * 
 * @author gongzili
 * 
 *         用户的动态消息类
 * 
 */
public class NewsFeed {

	private Long id;
	private Long userId;

	private String title;
	private String content;

	private Date created;
	/**
	 * 修改时间
	 */
	private Date modified;

	private Integer commentCount = 0;
	private Integer shareCount = 0;
	private Integer favourCount = 0;

	private Integer status = 1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getFavourCount() {
		return favourCount;
	}

	public void setFavourCount(Integer favourCount) {
		this.favourCount = favourCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NewsFeed [id=" + id + ", userId=" + userId + ", title=" + title
				+ ", content=" + content + ", created=" + created
				+ ", modified=" + modified + ", commentCount=" + commentCount
				+ ", shareCount=" + shareCount + ", favourCount=" + favourCount
				+ ", status=" + status + "]";
	}

}
