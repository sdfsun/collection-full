package com.kangde;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.model.CaseModel;
import com.kangde.commons.util.SQLUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class Test2 {
	
	private static String sql = "insert into `case_info` (id, case_code, state, collec_state_id, batch_id, case_date, case_backdate, version, cus_no, userId, account_no, case_app_no, case_file_no, case_name, case_sex, case_is_marriage, case_birthday, case_age, area_id, bank_account, case_house_id, case_card, case_num, case_num_id, case_position, case_position_level, account_name, lend_institution, principal, surplus_principal, breach, case_money, account_money, loan_contract, loan_money, loan_rate, loan_startdate, loan_enddate, contract_money, overdue_money, overdue_principal, overdue_expense, overdue_age, overdue_penalty, overdue_interest, overdue_days, overdue_date, overdue_periods, once_overdue_periods, overdue_loan, overdue_principal_balance, overdue_compound, social_security_no, agent_rate, entrust_rate, credit_id, stay_periods, month_repayment, last_repayment, stay_principal, stay_expense, stay_interest, bill, repayment_periods, commodity, company_name, company_phone, company_address, company_zipcode, mobile1, mobile2, family_phone, family_address, family_zipcode, domicile, mark_id, user_name, is_verify, verify_date, repayment_type, protocol_no, loan_area, actual_loan_money, interest_money, penalty_reference, compound_interest_reference, quota_no, quota_manager1, quota_manager2, manager_phone, collateral_no, collateral_id, collateral_name, collateral_address, collateral_evalua, repay_date, total_construc, regione, sub_center, sales_dep, consult_fee, audit_fee, service_fee, adviser_fee, fee_total, initial_repay, due_periods, penalty_days, remain_history, debit_bank_code, debit_bank_name, debit_account, relief_policy, case_assigned_id, case_assign_id, case_assign_name, case_assign_time, create_emp_id, modify_emp_id, modify_time, create_time, status, old_collec_record, remark1, province_id,province_name,city_id,city_name, remark2,org_id,org_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?,?) ";
	
	@Test
	public void testSpring3() {
		Connection c1 = SQLUtil.getConnection();
		Connection c2 = SQLUtil.getConnection();
		System.err.println(c1==c2);
	}
	
	@Test
	public void testSpring1() {
		String s = SQLUtil.getSql(CaseMapper.class, "update",getList().get(0));
		System.out.println(s);
//		Connection connection = SQLUtil.getConnection();
//		try {
//			PreparedStatement prst = connection.prepareStatement(s);
//			SQLUtil.executeBatch(s, getList(), SQLUtil.Type.INSERT, prst, 10);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(s);
	}
	
	@Test
	public void testSpring() {
		Connection connection = SQLUtil.getConnection();
		PreparedStatement prst = null;
		List<CaseModel> list = getList();
		try{
			long start = System.currentTimeMillis();
			prst = connection.prepareStatement(sql);
			int res = SQLUtil.executeBatch(sql, list, SQLUtil.Type.INSERT, prst, 2);
			System.out.println(res);
			SQLUtil.commit(connection);
			long end = System.currentTimeMillis();
			System.err.println(end-start);
		}catch(Exception e){
			SQLUtil.rollback(connection);
			e.printStackTrace();
		}finally{
			SQLUtil.close(prst);
			SQLUtil.close(connection);
		}
	}
	
	public static List<CaseModel> getList(){
		List<CaseModel> list = new ArrayList<>();
		for(int i=0;i< 5;i++){
			CaseModel c1 = new CaseModel();
			c1.setId("ID:"+(i+1));
			c1.setAccountName("acc"+(i+1));
			c1.setCaseMoney(new BigDecimal(255));
			c1.setCaseName("我是批量导入测试数据");
			list.add(c1);
		}
		return list;
	}
	
}
