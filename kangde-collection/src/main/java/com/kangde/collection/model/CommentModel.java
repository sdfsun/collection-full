package com.kangde.collection.model;

import java.util.Date;

import com.kangde.commons.model.BizModel;

/**
 * 
 * @Description: 案件评论
 * @author lidengwen
 * @date 2016年8月6日 上午10:22:28
 *
 */
public class CommentModel extends BizModel<String> {

	private static final long serialVersionUID = -1482032318920983067L;
	/** 评语 */
	private String content;
	/** 案件ID */
	private String caseId;
	/** 评语人姓名 */
	private String userName;
	/** 评论时间 */
	private Date commentTime;
	/** 评论状态 */
	private Integer status;
	/** 评语人ID */
	private String createEmpId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId == null ? null : caseId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateEmpId() {
		return createEmpId;
	}

	public void setCreateEmpId(String createEmpId) {
		this.createEmpId = createEmpId == null ? null : createEmpId.trim();
	}

}