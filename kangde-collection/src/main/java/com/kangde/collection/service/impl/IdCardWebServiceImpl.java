package com.kangde.collection.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.service.IdCardWebService;
import com.kangde.collection.util.IdcardException;
import com.kangde.collection.util.IdcardModel;
import com.kangde.collection.util.IdcardUtils;

@Service
public class IdCardWebServiceImpl implements IdCardWebService {

	@Override
	public String parseIdcard(String idno) {
		JSONObject json = new JSONObject();
		try {
			IdcardModel m = IdcardUtils.parseIdCard(idno);
			JSONObject data = new JSONObject();
			data.put("place", m.getPlace());
			data.put("birthday", m.getBirthday());
			data.put("gender", m.getGender());
			data.put("idcardNum", m.getIdcardNum());
			data.put("tip", "以下信息根据国际编码规则解读并非认证结果， 仅供参考");
			json.put("data", data);
			json.put("status", 1);
			json.put("msg", "ok");
		} catch (IdcardException e) {
			json.put("status", 0);
			json.put("msg", e.getMessage());
		}
		return json.toJSONString();
	}

}
