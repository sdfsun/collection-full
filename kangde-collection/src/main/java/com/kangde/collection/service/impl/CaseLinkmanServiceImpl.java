package com.kangde.collection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.mapper.CaseLinkmanMapper;
import com.kangde.collection.model.CaseLinkmanModel;
import com.kangde.collection.service.CaseLinkmanService;
import com.kangde.collection.service.HurryRecordService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;


@Service
public class CaseLinkmanServiceImpl extends AbstractService<CaseLinkmanModel,String> implements CaseLinkmanService {

	@Autowired
	private CaseLinkmanMapper linkmanMapper;
	@Autowired
	private HurryRecordService hurryRecordService;
	@Override
	public List<CaseLinkmanModel> findCaseIds(String caseId){
		return linkmanMapper.findCaseIds(caseId);
	}

	@Override
	public int updateStatus(CaseLinkmanModel model) {
		model.setStatus(1);
		return linkmanMapper.updateStatus(model);
	}
	
	@Override
	public int updateForStatusinvalid(CaseLinkmanModel model) {
		model.setStatus(2);
		
		return linkmanMapper.updateStatus(model);
	}

	
	@Override
	public CaseLinkmanModel update(CaseLinkmanModel model) {
		CaseLinkmanModel caseLinkmanModel = this.findById(model.getId());
		String oldName=caseLinkmanModel.getName();
		String oldPhone=caseLinkmanModel.getPhone();
		String newName=model.getName();
		String newPhone=model.getPhone();
		CaseLinkmanModel update = super.update(model);
		//保存操作记录  bianbianbian
		String oldContent="姓名:"+oldName+", 电话:"+oldPhone;
		String newContent="姓名:"+newName+", 电话:"+newPhone;
		String content="["+oldContent+"]修改为["+newContent+"]";
		hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.cPho, model.getCaseId(), "联系人", content,model.getId(), HurryRecordConst.edit));
		return update ;
	}

	@Override
	public void deleteById(String id) {
		CaseLinkmanModel caseLinkmanModel = linkmanMapper.findById(id);
		//保存操作记录  bianbianbian
		String content=caseLinkmanModel.getName()+",电话:"+caseLinkmanModel.getPhone();
		hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.cPho, caseLinkmanModel.getCaseId(), "联系人", content,id, HurryRecordConst.delete));
		super.deleteById(id);
	}

	@Override
	public CaseLinkmanModel save(CaseLinkmanModel model) {
		CaseLinkmanModel linkmanModel = super.save(model);
		//保存操作记录  bianbianbian
		String content=model.getName()+",电话："+model.getPhone();
		hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.cPho, model.getCaseId(), "联系人", content,linkmanModel.getId(), HurryRecordConst.add));
		return linkmanModel;
	}
	
	/** 根据电话号查询
	 *  如果在电话簿里查到了该电话，提示让用户从电话簿里操作
	 * */
	@Override
	public List<CaseLinkmanModel> findPhone(String phone,String caseId){
		List<CaseLinkmanModel> model=linkmanMapper.findPhone(phone,caseId);
		return model;
	}
	
	@Override
	public List<CaseLinkmanModel> findByCaseIds(String caseId){
		return linkmanMapper.findByCaseIds(caseId);
	}
	
	@Override
	public List<CaseLinkmanModel> iPhone(String caseId){
		return linkmanMapper.iPhone(caseId);
	}
	@Override
	public CaseLinkmanModel iPhone1(String caseId){
		return linkmanMapper.iPhone1(caseId);
	}
	
	@Override
	public void updateForPhone(String id , String phone){
		 linkmanMapper.updateForPhone(id,phone);
	}
	
	
}
