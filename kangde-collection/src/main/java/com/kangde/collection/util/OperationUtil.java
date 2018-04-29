package com.kangde.collection.util;
import java.util.Date;

import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;

/**为了添加操作记录提供简洁方法
 * @author wangcy
 */
public class OperationUtil{
	
	/** 添加操作记录
	 * @param hurCat（业务类型）、caseId（案件id）、content（协催操作内容）、hurryRecordId（记录id 对应各业务表）
	 * @author wangcy
	 */
	public static HurryRecordModel Operation(String hurCat,String caseId,String content,String hurryRecordId){
		HurryRecordModel model=new HurryRecordModel();
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		model.setId(UUIDUtil.UUID32());
		model.setHurCat(hurCat);
		model.setCaseId(caseId);
		model.setContent(content);
		model.setHurryRecordId(hurryRecordId);
		model.setCreateEmpId(currentUser.getId());
		model.setOperatorName(currentUser.getUserName());
		model.setCreateTime(new Date());
		return model;
	}
	
	/** 新增、修改、删除、添加操作记录
	 * @param hurCat（业务类型）、caseId（案件id）、type（类型）、content（内容）、id（对应业务表的id）、bs(1:新增、2：修改、3删除)
	 * @date 2016年9月22日17:08:56
	 * @author wangcy
	 */
	public static HurryRecordModel addRecord(String hurCat,String caseId,String type,String content,String id,int bs){
		HurryRecordModel model=new HurryRecordModel();
		model.setHurCat(hurCat);
		model.setCaseId(caseId);
		model.setHurryRecordId(id);
		if(HurryRecordConst.add==bs){
			model.setContent("[新增"+type+"]"+content);
		}else if(HurryRecordConst.edit==bs){
			model.setContent("[修改"+type+"]"+content);
		}else if(HurryRecordConst.delete==bs){
			model.setContent("[删除"+type+"]"+content);
		}else if(HurryRecordConst.suggest==bs){
			model.setContent("[建议"+type+"]"+content);
		}else if(HurryRecordConst.noaction==bs){
			model.setContent("["+type+"]"+content);
		}
		gongyong(model);
		return model;
	}
	
	
	/** 操作记录表公用的属性设置。
	 * @date 2016年9月22日17:08:56
	 * @author wangcy
	 */
	public static void gongyong(HurryRecordModel model){
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();//获取登录人信息
		model.setId(UUIDUtil.UUID32());//32位id
		model.setCreateEmpId(currentUser.getId());//登录人id
		model.setOperatorName(currentUser.getUserName());//登陆人姓名
		model.setCreateTime(new Date());//创建时间
	}
}
