package com.kangde.collection.model;

import com.kangde.commons.model.BizModel;
/**
 * 
  * @Description: 消息提醒类
  * @author lidengwen
  * @date 2017年1月11日 下午6:23:57
  *
 */
public class MessageReminderModel  extends BizModel<String> {

	private static final long serialVersionUID = -2533678580293694766L;

	private int remindType;

    private Integer isRead;

    private String remindTarget;
    
    private String content;
    private String url;
    private String title;

  
    public int getRemindType() {
        return remindType;
    }

    public void setRemindType(int remindType) {
        this.remindType = remindType;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getRemindTarget() {
		return remindTarget;
	}

	public void setRemindTarget(String remindTarget) {
		this.remindTarget = remindTarget;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
    
}