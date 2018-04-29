package com.kangde.collection.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.CaseBatchMapper;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.model.HurryRecordModel;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.util.SnUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.mapper.EntrustMapper;
import com.kangde.sys.model.EmployeeInfoModel;
import com.kangde.sys.security.util.SecurityUtil;

/**
 * 职位 Service实现类
 * 
 * @author wcy
 * @date 2016年5月9日11:17:07
 */
@Service("casebatchService")
public class CaseBatchServiceImpl extends AbstractService<CaseBatchModel, String> implements CaseBatchService {

	@Autowired
	private CaseBatchMapper caseBatchmapper;
	@Autowired
	private HurryRecordMapper hurryRecordMapper;
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private CaseService caseService;
	@Autowired
	private EntrustMapper entrustMapper;

	/**
	 * 单独创建批次号
	 */
	@Override
	public String createBatchCode(CaseBatchModel model) {
		String entrustCode = entrustMapper.findCodeById(model.getEntrustId());
		String batchSn = SnUtil.getCaseBatchSn(entrustCode, model.getTypeId());
		// model.setBatchCode(batchSn);
		return batchSn;
	}

	/**
	 * 添加批次方法
	 */
	@Override
	public CaseBatchModel save(CaseBatchModel model) {
		model.setState(1);
		String batchCode = model.getBatchCode().trim();
		if(StringUtils.isBlank(batchCode)){
			throw new ServiceException("批次号不能为空");
		}
		CaseBatchModel batchModel = caseBatchmapper.findByBatchCode(batchCode);
		if(batchModel == null){
			return super.save(model);
		}else{
			throw new ServiceException("批次号重复");
		}
		/*String batchSn = createBatchCode(model); 
		model.setBatchCode(batchSn);*/
		

	}

	@Override
	public CaseBatchModel update(CaseBatchModel model) {
		String batchCode = model.getBatchCode();
		CaseBatchModel batchModel = caseBatchmapper.findByBatchCode(batchCode);
		if(batchModel == null){
			String batchId = model.getId();
			Date caseDate = model.getBeginDate();
			Date endDate = model.getEndDate();
			caseMapper.updateCaseInfo(batchId,caseDate,endDate);
			return super.update(model);
		}else{
			//更新的批次与数据库的批次不是同一个
			if(!model.getId().equals(batchModel.getId())){
				throw new ServiceException("批次号重复");
			}else if(model.getEndDate().equals(batchModel.getEndDate())){
				return super.update(model);
				
			}else{
				String batchId = model.getId();
				Date caseDate = model.getBeginDate();
				Date endDate = model.getEndDate();
				caseMapper.updateCaseInfo(batchId,caseDate,endDate);
				return super.update(model);
			}
		}
		
		
		
		/*if(batchId !=null){
			List<CaseModel> list = caseMapper.findBatchId(batchId);
			List<CaseModel> caseList = new ArrayList<>();
			for (CaseModel caseModel : list) {
				caseModel.setCaseDate(model.getBeginDate());
				caseModel.setEndDate(model.getEndDate());
				caseList.add(caseModel);
			}
			if (CollectionUtils.isNotEmpty(caseList)) {
				caseService.batchUpdate(caseList);
			}
		}*/
		
	}

	/**
	 * 撤案 恢复案件 并 添加操作动作
	 */
	@Override
	public int updateForStatus(CaseBatchModel model) {
		int t = caseBatchmapper.updateForStatus(model);
		Integer state = model.getState();
		EmployeeInfoModel currentUser = SecurityUtil.getCurrentUser();
		String content;
		if (t > 0) {
			HurryRecordModel hr = new HurryRecordModel();
			hr.setId(UUIDUtil.UUID32());
			Date date = new Date();
			String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

			if (state == 1) {
				caseMapper.updateStatusBack(model.getId());
				content = "批次状态：撤回案件" + ", 撤案说明：" + model.getWdcDesc() + ", 撤案时间：" + dateStr;
			} else {
				caseMapper.updateStatusNomal(model.getId());
				content = "批次状态：恢复案件" + ", 操作人：" + currentUser.getUserName() + ", 恢复案件时间：" + dateStr;
			}
			hr.setCaseId(model.getId());
			hr.setHurCat("casM");
			hr.setContent(content);
			hr.setHurryRecordId(model.getId());
			hr.setCreateEmpId(currentUser.getId());
			hr.setOperatorName(currentUser.getUserName());
			hr.setCreateTime(date);
			hurryRecordMapper.save(hr);
		}
		return t;
	}

	@Override
	public void delete(CaseBatchModel model) {
		super.delete(model);
	}

	/**
	 * 批次软删除 id 是批次id
	 */
	public void deleteByBatchId(String id) {

		// 通过批次ID查询所有案件
		Map<String, Object> map = new HashMap<>(3);
		map.put("fieldName", "batch_id");
		map.put("fieldValue", id);
		map.put("expression", "=");
		List<CaseModel> cases = caseMapper.findByField(map);
		if (CollectionUtils.isNotEmpty(cases)) {
			List<String> caseIds = new ArrayList<>(cases.size());
			boolean hard = true;
			for (CaseModel cs : cases) {
				caseIds.add(cs.getId());
				}
			for (CaseModel cs : cases) {
				if (cs.getCaseAssignedId() != null) {
					hard = false;
					break;
			}
			}
			if (hard) {
				// 物理删除
				caseMapper.deleteByIds(caseIds);
				caseBatchmapper.deleteById(id);

			} else {

				// 逻辑删除
				caseMapper.softDelete(caseIds);
				int softDelete = caseBatchmapper.softDelete(id,-1);
				System.out.println(softDelete);
			}
		}

	}
	
	/**
	 * 批次号下拉
	 */
	public List<CaseBatchModel> findBatchCode(){
		return caseBatchmapper.findBatchCode();
	}
	
	/** 分解批次号方法*/
	public String BatchCode(String Code){
		String batchCode=null;
		if(StringUtils.isNotBlank(Code)){
				   String[] ary = Code.split(",");
				   StringBuffer testStrBuff=new StringBuffer("(");
					  for (int i = 0; i < ary.length; i++) {
						  testStrBuff.append("'"+ary[i].trim()+"',");
					}
					  batchCode=testStrBuff.substring(0,testStrBuff.length()-1);
					  batchCode=batchCode+")";
		}
		return batchCode;
	}

}
