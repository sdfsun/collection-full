package com.kangde.collection.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.mapper.AttachmentMapper;
import com.kangde.collection.mapper.HurryRecordMapper;
import com.kangde.collection.model.AttachmentModel;
import com.kangde.collection.service.AttachmentService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.security.util.SecurityUtil;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	AttachmentMapper attachmentMapper;
	@Autowired
	HurryRecordMapper hurryRecordMapper;

	@Override
	public void mulDelete(String[] ids) throws Exception {

		for (String id : ids) {
			AttachmentModel attachment = attachmentMapper.findById(id);
			attachment.setModifyEmpName(SecurityUtil.getCurrentUser().getUserName());
			attachment.setModifyTime(new Date());
			attachment.setIsjunk("0");
			attachmentMapper.updateById(attachment);
			
			String content = "附件名:"+attachment.getName();
			hurryRecordMapper.save(OperationUtil.addRecord(HurryRecordConst.attach, attachment.getFkId(),
					"删除附件", content, attachment.getId(), HurryRecordConst.noaction));
		}
	}

	@Override
	public void save(AttachmentModel caseAttachment) {
		Date date = new Date();
		caseAttachment.setDate(date);
		String userName = SecurityUtil.getCurrentUser().getUserName();
		caseAttachment.setCreateEmpName(userName);
		caseAttachment.setCreateTime(date);
		caseAttachment.setModifyEmpName(userName);
		caseAttachment.setModifyTime(date);
		attachmentMapper.insert(caseAttachment);
		
		String content = "附件名:"+caseAttachment.getName();
		hurryRecordMapper.save(OperationUtil.addRecord(HurryRecordConst.attach, caseAttachment.getFkId(),
				"上传附件", content, caseAttachment.getId(), HurryRecordConst.noaction));
	}

	@Override
	public List<AttachmentModel> queryAttachmentsByCaseId(String caseId) {
		return attachmentMapper.findByCaseId(caseId);
	}

	@Override
	public int queryCount(ParamCondition condition) {
		return attachmentMapper.queryCount(condition);
	}

	@Override
	public AttachmentModel findById(String id) {
		return attachmentMapper.findById(id);
	}

}
