package com.kangde.collection.mapper;

import java.util.List;

import com.kangde.collection.model.AttachmentModel;
import com.kangde.commons.vo.ParamCondition;

public interface AttachmentMapper {
    int deleteById(String id);
    int insert(AttachmentModel record);
    AttachmentModel findById(String id);
    int updateById(AttachmentModel record);
	List<AttachmentModel> findByCaseId(String caseId);
	void mulUpdate(String[] id);
	int queryCount(ParamCondition condition);
}