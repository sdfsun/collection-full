package com.kangde.collection.model;

import com.kangde.commons.model.BizModel;
/**
 * 公告
 */
public class NoticeModel extends BizModel<String>{
	private static final long serialVersionUID = 1061083245929008504L;
	/**标题*/
    private String title;
    /**是否可用*/
    private Integer available;
    /**通告内容*/
    private String content;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}