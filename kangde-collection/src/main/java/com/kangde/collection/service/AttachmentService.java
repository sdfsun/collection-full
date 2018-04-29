package com.kangde.collection.service;
import java.util.List;

import com.kangde.collection.model.AttachmentModel;
import com.kangde.commons.vo.ParamCondition;
public interface AttachmentService {
	/**
	 * 批量逻辑删除， 不删除实际文件
	 */
	public void mulDelete(String []id) throws Exception;
	public void save(AttachmentModel caseAttachment);
	public List<AttachmentModel> queryAttachmentsByCaseId(String caseId);
	int queryCount(ParamCondition condition);
	public AttachmentModel findById(String id);
}
