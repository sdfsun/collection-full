package com.kangde.collection.service;

import com.kangde.collection.model.CommentModel;
import com.kangde.commons.service.BaseService;
public interface CommentService extends BaseService<CommentModel,String>{

	void saveCommentAndUpdateCaseColor(String[] ids, String content, String color, boolean isBatch);
}


