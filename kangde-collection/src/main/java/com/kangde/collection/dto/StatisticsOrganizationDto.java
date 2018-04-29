package com.kangde.collection.dto;

import java.util.Date;

import com.kangde.sys.model.OrganizationModel;

/**
 * 统计Dto
 * 
 * @author zhangyj
 *
 */
@SuppressWarnings("serial")
public class StatisticsOrganizationDto  extends OrganizationModel{
	/*tj. NAME,#委托方
	tj.case_count,#案件数量
	tj.total_case_money,#委案金额	
	tj.ptp_money,#PTP
	tj.cp_money,#CP
	tj.paid_num,#已确认还款		
	tj.achieve,#业绩
	tj.target,#回款率
	tj.new_case_count,#新案数量
	tj.lose_case_count,#失联数量
	tj.involve_case_count,#涉案数量
	tj.negotiation_case_count,#谈判数量
	tj.family_case_count,#中家人数量
	tj.someone_case_count,#中第三人数量
	tj.oneself_case_count#中本人数量*/
	private String orgId;
	private String enId;
	private Integer case_count=0;
	private Double total_case_money=0.00;
	private Double cp_money=0.00;
	private Double paid_num=0.00;
	private Double target=0.00;
	private Integer new_case_count=0;
	private Integer lose_case_count=0;
	private Integer involve_case_count=0;
	private Integer negotiation_case_count=0;
	private Integer family_case_count=0;
	private Integer someone_case_count=0;
	private Integer oneself_case_count=0;
	private Date begin_date;
	private Date end_date;
	private Date paid_time;
	private Date normalDate;
	private Date normalDate1;
	
	//基础还款统计
	private String average_count;
	private Integer la_count=0;
	private Double la_case_money=0.00;
	private Integer cp_case_count=0;
	private Integer paid_case_count=0;
	private String paid_rate;
	
	private Double ptp_money=0.00;
	private Integer ptp_case_count=0;
	private Double zlz_paid_num=0.00;
	private Integer zlz_paid_case_count=0;
	private String paid_count_rate;
	private String paid_money_rate;
	
	//业绩统计
	private Double back_paid=0.00;
	private Double cp_back_paid=0.00;
	private Double achieve=0.00;
	private String average_rate;
	private String hun_average_out;
	private String part_average_out;
	
	//风控投入
	private Integer phone_count=0;
	private Integer visit_count=0;
	private Integer apply_count=0;
	private Integer assist_count=0;
	
	private Integer stop_case_count=0;
	private Integer back_case_count=0;
	private Integer promise_case_count=0;
    private Integer cheat_case_count=0;

    public String getAverage_rate() {
		return average_rate;
	}
	public void setAverage_rate(String average_rate) {
		this.average_rate = average_rate;
	}
	public String getHun_average_out() {
		return hun_average_out;
	}
	public void setHun_average_out(String hun_average_out) {
		this.hun_average_out = hun_average_out;
	}
	public String getPart_average_out() {
		return part_average_out;
	}
	public void setPart_average_out(String part_average_out) {
		this.part_average_out = part_average_out;
	}
	public String getAverage_count() {
		return average_count;
	}
	public void setAverage_count(String average_count) {
		this.average_count = average_count;
	}
	public String getPaid_rate() {
		return paid_rate;
	}
	public void setPaid_rate(String paid_rate) {
		this.paid_rate = paid_rate;
	}
	public String getPaid_count_rate() {
		return paid_count_rate;
	}
	public void setPaid_count_rate(String paid_count_rate) {
		this.paid_count_rate = paid_count_rate;
	}
	public String getPaid_money_rate() {
		return paid_money_rate;
	}
	public void setPaid_money_rate(String paid_money_rate) {
		this.paid_money_rate = paid_money_rate;
	}
	public Integer getPtp_case_count() {
		return ptp_case_count;
	}
	public void setPtp_case_count(Integer ptp_case_count) {
		this.ptp_case_count = ptp_case_count;
	}
	public Double getZlz_paid_num() {
		return zlz_paid_num;
	}
	public void setZlz_paid_num(Double zlz_paid_num) {
		this.zlz_paid_num = zlz_paid_num;
	}
	public Integer getZlz_paid_case_count() {
		return zlz_paid_case_count;
	}
	public void setZlz_paid_case_count(Integer zlz_paid_case_count) {
		this.zlz_paid_case_count = zlz_paid_case_count;
	}

	public Integer getStop_case_count() {
		return stop_case_count;
	}
	public void setStop_case_count(Integer stop_case_count) {
		this.stop_case_count = stop_case_count;
	}
	public Integer getBack_case_count() {
		return back_case_count;
	}
	public void setBack_case_count(Integer back_case_count) {
		this.back_case_count = back_case_count;
	}
	public Integer getPromise_case_count() {
		return promise_case_count;
	}
	public void setPromise_case_count(Integer promise_case_count) {
		this.promise_case_count = promise_case_count;
	}
	public Integer getCheat_case_count() {
		return cheat_case_count;
	}
	public void setCheat_case_count(Integer cheat_case_count) {
		this.cheat_case_count = cheat_case_count;
	}
	public Date getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getPaid_time() {
		return paid_time;
	}
	public void setPaid_time(Date paid_time) {
		this.paid_time = paid_time;
	}
	public Date getNormalDate() {
		return normalDate;
	}
	public void setNormalDate(Date normalDate) {
		this.normalDate = normalDate;
	}
	public Date getNormalDate1() {
		return normalDate1;
	}
	public void setNormalDate1(Date normalDate1) {
		this.normalDate1 = normalDate1;
	}
	public Integer getCase_count() {
		return case_count;
	}
	public void setCase_count(Integer case_count) {
		this.case_count = case_count;
	}
	public Double getTotal_case_money() {
		return total_case_money;
	}
	public void setTotal_case_money(Double total_case_money) {
		this.total_case_money = total_case_money;
	}
	public Double getPtp_money() {
		return ptp_money;
	}
	public void setPtp_money(Double ptp_money) {
		this.ptp_money = ptp_money;
	}
	public Double getCp_money() {
		return cp_money;
	}
	public void setCp_money(Double cp_money) {
		this.cp_money = cp_money;
	}
	public Double getPaid_num() {
		return paid_num;
	}
	public void setPaid_num(Double paid_num) {
		this.paid_num = paid_num;
	}
	public Double getAchieve() {
		return achieve;
	}
	public void setAchieve(Double achieve) {
		this.achieve = achieve;
	}
	public Double getTarget() {
		return target;
	}
	public void setTarget(Double target) {
		this.target = target;
	}
	public Integer getNew_case_count() {
		return new_case_count;
	}
	public void setNew_case_count(Integer new_case_count) {
		this.new_case_count = new_case_count;
	}
	public Integer getLose_case_count() {
		return lose_case_count;
	}
	public void setLose_case_count(Integer lose_case_count) {
		this.lose_case_count = lose_case_count;
	}
	public Integer getInvolve_case_count() {
		return involve_case_count;
	}
	public void setInvolve_case_count(Integer involve_case_count) {
		this.involve_case_count = involve_case_count;
	}
	public Integer getNegotiation_case_count() {
		return negotiation_case_count;
	}
	public void setNegotiation_case_count(Integer negotiation_case_count) {
		this.negotiation_case_count = negotiation_case_count;
	}
	public Integer getFamily_case_count() {
		return family_case_count;
	}
	public void setFamily_case_count(Integer family_case_count) {
		this.family_case_count = family_case_count;
	}
	public Integer getSomeone_case_count() {
		return someone_case_count;
	}
	public void setSomeone_case_count(Integer someone_case_count) {
		this.someone_case_count = someone_case_count;
	}
	public Integer getOneself_case_count() {
		return oneself_case_count;
	}
	public void setOneself_case_count(Integer oneself_case_count) {
		this.oneself_case_count = oneself_case_count;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEnId() {
		return enId;
	}
	public void setEnId(String enId) {
		this.enId = enId;
	}
	
	public Integer getLa_count() {
		return la_count;
	}
	public void setLa_count(Integer la_count) {
		this.la_count = la_count;
	}
	public Double getLa_case_money() {
		return la_case_money;
	}
	public void setLa_case_money(Double la_case_money) {
		this.la_case_money = la_case_money;
	}
	public Integer getCp_case_count() {
		return cp_case_count;
	}
	public void setCp_case_count(Integer cp_case_count) {
		this.cp_case_count = cp_case_count;
	}
	public Integer getPaid_case_count() {
		return paid_case_count;
	}
	public void setPaid_case_count(Integer paid_case_count) {
		this.paid_case_count = paid_case_count;
	}
	
	public Double getBack_paid() {
		return back_paid;
	}
	public void setBack_paid(Double back_paid) {
		this.back_paid = back_paid;
	}
	
	public Integer getPhone_count() {
		return phone_count;
	}
	public void setPhone_count(Integer phone_count) {
		this.phone_count = phone_count;
	}
	public Integer getVisit_count() {
		return visit_count;
	}
	public void setVisit_count(Integer visit_count) {
		this.visit_count = visit_count;
	}
	public Integer getApply_count() {
		return apply_count;
	}
	public void setApply_count(Integer apply_count) {
		this.apply_count = apply_count;
	}
	public Integer getAssist_count() {
		return assist_count;
	}
	public void setAssist_count(Integer assist_count) {
		this.assist_count = assist_count;
	}
	public Double getCp_back_paid() {
		return cp_back_paid;
	}
	public void setCp_back_paid(Double cp_back_paid) {
		this.cp_back_paid = cp_back_paid;
	}
}
