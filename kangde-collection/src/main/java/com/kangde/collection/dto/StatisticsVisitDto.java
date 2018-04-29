package com.kangde.collection.dto;

import java.util.Date;

import com.kangde.commons.model.BizModel;

/**
 * 统计Dto
 * 
 * @author zhangyj
 *
 */
@SuppressWarnings("serial")
public class StatisticsVisitDto extends BizModel<String>{
	/*tj.actual_visit_time,
	tj.visit_count,
	tj.visit_people_count,
	tj.average_visit_count,
	tj.average_case_visit_count,
	tj.family_visit_count,
	tj.oneself_visit_count,
	tj.noaddress_visit_count,
	tj.remove_visit_count,
	tj.noone_visit_count*/

	private String actual_visit_time;
	private Integer visit_count=0;
	private Double visit_people_count=0.00;
	private Double average_visit_count=0.00;
	private Double average_case_visit_count=0.00;
	private Double family_visit_count=0.00;
	private Integer oneself_visit_count=0;
	private Integer noaddress_visit_count=0;
	private Integer remove_visit_count=0;
	private Integer noone_visit_count=0;
	private Integer case_count=0;
	
	
	public Integer getVisit_count() {
		return visit_count;
	}
	public void setVisit_count(Integer visit_count) {
		this.visit_count = visit_count;
	}
	public Double getAverage_case_visit_count() {
		return average_case_visit_count;
	}
	public void setAverage_case_visit_count(Double average_case_visit_count) {
		this.average_case_visit_count = average_case_visit_count;
	}
	public Double getFamily_visit_count() {
		return family_visit_count;
	}
	public void setFamily_visit_count(Double family_visit_count) {
		this.family_visit_count = family_visit_count;
	}
	public Integer getOneself_visit_count() {
		return oneself_visit_count;
	}
	public void setOneself_visit_count(Integer oneself_visit_count) {
		this.oneself_visit_count = oneself_visit_count;
	}
	public Integer getNoaddress_visit_count() {
		return noaddress_visit_count;
	}
	public void setNoaddress_visit_count(Integer noaddress_visit_count) {
		this.noaddress_visit_count = noaddress_visit_count;
	}
	public Integer getRemove_visit_count() {
		return remove_visit_count;
	}
	public void setRemove_visit_count(Integer remove_visit_count) {
		this.remove_visit_count = remove_visit_count;
	}
	public Integer getNoone_visit_count() {
		return noone_visit_count;
	}
	public void setNoone_visit_count(Integer noone_visit_count) {
		this.noone_visit_count = noone_visit_count;
	}
	public String getActual_visit_time() {
		return actual_visit_time;
	}
	public void setActual_visit_time(String actual_visit_time) {
		this.actual_visit_time = actual_visit_time;
	}
	public Double getAverage_visit_count() {
		return average_visit_count;
	}
	public void setAverage_visit_count(Double average_visit_count) {
		this.average_visit_count = average_visit_count;
	}
	public Double getVisit_people_count() {
		return visit_people_count;
	}
	public void setVisit_people_count(Double visit_people_count) {
		this.visit_people_count = visit_people_count;
	}
	public Integer getCase_count() {
		return case_count;
	}
	public void setCase_count(Integer case_count) {
		this.case_count = case_count;
	}
	
}
