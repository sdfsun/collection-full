package com.kangde.collection.mapper;

import com.kangde.collection.model.CommentModel;
import com.kangde.commons.mapper.BaseMapper;

public interface CommentModelMapper extends BaseMapper<CommentModel, String> {
    int deleteByPrimaryKey(String id);

    int insert(CommentModel record);

    int insertSelective(CommentModel record);

    CommentModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommentModel record);

    int updateByPrimaryKey(CommentModel record);
}