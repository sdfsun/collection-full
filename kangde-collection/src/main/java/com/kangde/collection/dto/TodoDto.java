package com.kangde.collection.dto;

/**
 *代办任务
 */
public class TodoDto {
/**消息*/
private String message;
/**数量*/
private Integer num;
/**跳转路径*/
private String url;
/**页面标题*/
private String pagetitle;

public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getPagetitle() {
	return pagetitle;
}
public void setPagetitle(String pagetitle) {
	this.pagetitle = pagetitle;
}
public TodoDto(String message, int num, String url, String pagetitle) {
	this.message=message;
	this.num=num;
	this.url=url;
	this.pagetitle=pagetitle;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
	

}
