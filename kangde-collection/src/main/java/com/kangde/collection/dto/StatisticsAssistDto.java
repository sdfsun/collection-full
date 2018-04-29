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
public class StatisticsAssistDto extends BizModel<String>{
	/*tj.sur_time,#协催时间
	tj.apply_count,#协催总次数
	tj.hj,#
	tj.shb,#
	tj.dx#*/

	private String sur_time;
	private Integer apply_count=0;
	private Integer hj=0;
	private Integer shb=0;
	private Integer dx=0;
	private Double average_case_apply_count=0.00;
	
	
	public Integer getApply_count() {
		return apply_count;
	}
	public void setApply_count(Integer apply_count) {
		this.apply_count = apply_count;
	}
	public Integer getHj() {
		return hj;
	}
	public void setHj(Integer hj) {
		this.hj = hj;
	}
	public Integer getShb() {
		return shb;
	}
	public void setShb(Integer shb) {
		this.shb = shb;
	}
	public Integer getDx() {
		return dx;
	}
	public void setDx(Integer dx) {
		this.dx = dx;
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
