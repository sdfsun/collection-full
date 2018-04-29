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
public class StatisticsApplyDto extends BizModel<String>{
	/*tj.sur_time,#协催时间
	tj.apply_count,#协催总次数
	tj.customer_count,#客服咨询次数
	tj.police_count,#公安协助次数
	tj.court_count,#法院协助次数
	tj.charge_count, #主管协催次数
	tj.average_case_apply_count #平均每案件协催次数（协催总次数/协催过的案件数量）*/

	private String sur_time;
	private Integer apply_count =0;
	private Integer customer_count=0;
	private Integer police_count=0;
	private Integer court_count=0;
	private Integer charge_count=0;
	private Double average_case_apply_count=0.00;
	
	

	public Integer getCustomer_count() {
		return customer_count;
	}
	public void setCustomer_count(Integer customer_count) {
		this.customer_count = customer_count;
	}
	public Integer getPolice_count() {
		return police_count;
	}
	public void setPolice_count(Integer police_count) {
		this.police_count = police_count;
	}
	public Integer getCourt_count() {
		return court_count;
	}
	public void setCourt_count(Integer court_count) {
		this.court_count = court_count;
	}
	public Integer getCharge_count() {
		return charge_count;
	}
	public void setCharge_count(Integer charge_count) {
		this.charge_count = charge_count;
	}
	public Integer getApply_count() {
		return apply_count;
	}
	public void setApply_count(Integer apply_count) {
		this.apply_count = apply_count;
	}
	public String getSur_time() {
		return sur_time;
	}
	public void setSur_time(String sur_time) {
		this.sur_time = sur_time;
	}
	public Double getAverage_case_apply_count() {
		return average_case_apply_count;
	}
	public void setAverage_case_apply_count(Double average_case_apply_count) {
		this.average_case_apply_count = average_case_apply_count;
	}
	
	
}
