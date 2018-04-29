package com.kangde.collection.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.dto.VisitDto;
import com.kangde.collection.mapper.AddressMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.mapper.VisitRecordToApproveMapper;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.collection.service.VisitRecordMessageReminderService;
import com.kangde.collection.service.VisitRecordToApproveService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.service.AbstractService;

/**
 * 
 * @Description: 待审批外访
 * @author lidengwen
 * @date 2016年8月13日 下午4:33:11
 *
 */
@Service
public class VisitRecordToApproveServiceImpl extends AbstractService<VisitDto, String>
		implements VisitRecordToApproveService {

	@Autowired
	private VisitRecordToApproveMapper visitRecordToApproveMapper;
	@Autowired
	private HurryRecordMapper hurryRecordMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private VisitRecordMessageReminderService visitRecordMessageReminderService;

	/** 批量修改外访数据 */
	@Override
	public int approveYes(VisitRecordModel model) {
		Map<String, Object> params = new HashMap<>(2);
		String[] ids = model.getId().split(",");
		params.put("visitRecord", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		int upstate = visitRecordToApproveMapper.updatefoVi(params);// 执行批量修改
		saveHurryRecord(model.getApproveOpinion(), ids, "审批通过");
		visitRecordMessageReminderService.saveMessageMinders(ids,"已通过外访审批");
		return upstate;
	}

	private void saveHurryRecord(String approveOpinion, String[] ids, String type) {
		// 保存操作记录 bianbianbian
		/** 根据外访Id查询案件id */
		if (CollectionUtils.isNotEmpty(visitRecordToApproveMapper.findIds(Arrays.asList(ids)))) {
			for (VisitRecordModel visitRecordModel : visitRecordToApproveMapper.findIds(Arrays.asList(ids))) {
				String content = type+":" + approveOpinion;
				hurryRecordMapper.save(OperationUtil.addRecord(HurryRecordConst.oVis, visitRecordModel.getCaseId(),
						"外访审批", content, visitRecordModel.getId(), HurryRecordConst.noaction));
			}
		}
	}


	/** 批量修改外访数据 */
	@Override
	public int approveNo(VisitRecordModel model) {
		Map<String, Object> params = new HashMap<>(2);
		String[] ids = model.getId().split(",");
		params.put("visitRecord", model);
		params.put("list", ids);// model存放审批内容，list存放选中id
		int upstate = visitRecordToApproveMapper.updatefoVi(params);// 执行批量修改
		List<VisitRecordModel> visitRecords = visitRecordToApproveMapper.findIds(Arrays.asList(ids));
		String[] addressIds = new String[visitRecords.size()];
		for (int i = 0; i < addressIds.length; i++) {
			addressIds[i] = visitRecords.get(i).getAddressId();
		}
		addressMapper.resetAddressVisApp(Arrays.asList(addressIds));
		saveHurryRecord(model.getApproveOpinion(), ids, "审批不通过");
		visitRecordMessageReminderService.saveMessageMinders(ids," 未通过外访审批");
		return upstate;
	}
}
