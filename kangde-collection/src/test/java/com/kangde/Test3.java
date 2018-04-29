package com.kangde;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.reflection.Reflector;

import com.kangde.collection.model.CaseModel;
import com.kangde.commons.model.BaseModel;
import com.kangde.commons.util.ReflectUtil;

public class Test3 {
	private static String sql = "insert into `case_info` (id, case_code, state, collec_state_id, batch_id, case_date, case_backdate, version, cus_no, userId, account_no, case_app_no, case_file_no, case_name, case_sex, case_is_marriage, case_birthday, case_age, area_id, bank_account, case_house_id, case_card, case_num, case_num_id, case_position, case_position_level, account_name, lend_institution, principal, surplus_principal, breach, case_money, account_money, loan_contract, loan_money, loan_rate, loan_startdate, loan_enddate, contract_money, overdue_money, overdue_principal, overdue_expense, overdue_age, overdue_penalty, overdue_interest, overdue_days, overdue_date, overdue_periods, once_overdue_periods, overdue_loan, overdue_principal_balance, overdue_compound, social_security_no, agent_rate, entrust_rate, credit_id, stay_periods, month_repayment, last_repayment, stay_principal, stay_expense, stay_interest, bill, repayment_periods, commodity, company_name, company_phone, company_address, company_zipcode, mobile1, mobile2, family_phone, family_address, family_zipcode, domicile, mark_id, user_name, is_verify, verify_date, repayment_type, protocol_no, loan_area, actual_loan_money, interest_money, penalty_reference, compound_interest_reference, quota_no, quota_manager1, quota_manager2, manager_phone, collateral_no, collateral_id, collateral_name, collateral_address, collateral_evalua, repay_date, total_construc, regione, sub_center, sales_dep, consult_fee, audit_fee, service_fee, adviser_fee, fee_total, initial_repay, due_periods, penalty_days, remain_history, debit_bank_code, debit_bank_name, debit_account, relief_policy, case_assigned_id, case_assign_id, case_assign_name, case_assign_time, create_emp_id, modify_emp_id, modify_time, create_time, status, old_collec_record, remark1, province_id,province_name,city_id,city_name, remark2,org_id,org_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?,?) ";
	
	private static Map<Class<?>,Reflector> reflectors = new HashMap<>();
	
	public static void main(String[] args) throws SQLException {
		executeBatch(sql, getList(), Type.INSERT,null);
	}
	
	@SuppressWarnings("rawtypes")
	public static void executeBatch(String sql,List<? extends Object> list,Type type,PreparedStatement prst) throws SQLException{
		if(CollectionUtils.isNotEmpty(list)){
			Class clazz = list.get(0).getClass();
			Reflector reflector = reflectors.get(clazz);
			if(reflector == null){
				reflector = new Reflector(clazz);
				reflectors.put(clazz, reflector);
			}
			if(Type.INSERT==type){
				int idx = sql.indexOf("values");
				String prefix = sql.substring(0, idx);
				prefix = prefix.substring(prefix.indexOf("(")+1, prefix.indexOf(")"));
				List<String> names = new ArrayList<>();
				String[] columns = prefix.split(",");
				for(String column:columns){
					column = column.trim().replace("_", "");
					String name = reflector.findPropertyName(column);
					if(column == null){
						throw new RuntimeException("没有找到["+column+"]的属性");
					}
					names.add(name);
				}
				int index = 1;
				//便利数据
				for(Object obj:list){
					//便利数据字段属性
					for(int i=0;i<names.size();i++){
						String name = names.get(i);
						Object val = ReflectUtil.getProperty(obj, name);
						prst.setObject((i+1), val);
					}
					prst.addBatch();
					if(index%5==0){
						prst.executeBatch();
						prst.clearBatch();
					}
					index++;
				}
				prst.executeBatch();
			}else if(Type.UPDATE == type){
				
			}
		}
	}
	
	enum Type{
		INSERT,UPDATE
	}
	
	public static List<CaseModel> getList(){
		List<CaseModel> list = new ArrayList<>();
		for(int i=0;i< 3;i++){
			CaseModel c1 = new CaseModel();
			c1.setId("ID:"+i+1);
			c1.setAccountName("acc"+(i+1));
			list.add(c1);
		}
		return list;
	}
	
}
