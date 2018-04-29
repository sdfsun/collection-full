package com.kangde.collection.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.dto.AddressDto;
import com.kangde.collection.exception.AddressException;
import com.kangde.collection.mapper.AddressMapper;
import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.RegionModel;
import com.kangde.collection.service.AddressService;
import com.kangde.collection.service.HurryRecordService;
import com.kangde.collection.service.RegionService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.service.AbstractService;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.sys.security.util.SecurityUtil;

@Service
public class AddressServiceImpl extends AbstractService<AddressModel, String> implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private RegionService regionService;
	@Autowired
	private HurryRecordService hurryRecordService;

	@Override
	public List<AddressModel> queryByCaseId(String caseId) {
		return addressMapper.queryByCaseId(caseId);
	}

	@Override
	public AddressModel save(AddressModel model) {
		model.setId(UUIDUtil.UUID32());
		model.setVisApp(0);
		model.setCheckApp(0);
		model.setMailApp(0);
		model.setMailCount(0);
		model.setCreateEmpId(SecurityUtil.getCurrentUser().getId());
		model.setCreateTime(new Date());
		addressMapper.save(model);
		try {
			// 保存操作记录 bianbianbian
			String address = this.findFullAddress(model.getAreaId1(), model.getAreaId2(), model.getAreaId3())
					+ model.getAddress();
			String content = model.getName() + ", " + address + "[" + model.getAdrCat() + "]";
			hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.cAdd, model.getCaseId(), "地址", content,
					model.getId(), HurryRecordConst.add));
			return model;
		} catch (AddressException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public AddressModel findById(String id) {
		return addressMapper.findById(id);
	}

	@Override
	public List<AddressModel> findFinishedVisitRecord(String caseId) {
		return addressMapper.findFinishedVisitRecord(caseId);
	}

	@Override
	public AddressDto findFullAddress(String areaId1, String areaId2, String areaId3) throws AddressException {
		AddressDto dto = new AddressDto("","","");
		if(StringUtils.isNotBlank(areaId1)){
			RegionModel province = regionService.findById(Integer.parseInt(areaId1));
			if (province == null) {
				throw new AddressException("地址：【省】信息为空, 请完善地址信息");
			}
			String provinceName = province.getName()==null?"":province.getName();
			dto.setProvinceName(provinceName);
		}
		if(StringUtils.isNotBlank(areaId2)){
			RegionModel city = regionService.findById(Integer.parseInt(areaId2));
			if (city == null) {
				throw new AddressException("地址：【市】信息为空, 请完善地址信息");
			}
			String cityName = city.getName()==null?"":city.getName();
			dto.setCityName(cityName);
		}
		if(StringUtils.isNotBlank(areaId3)){
			RegionModel area = regionService.findById(Integer.parseInt(areaId3));
			if (area == null) {
				throw new AddressException("地址：【县/区】信息为空, 请完善地址信息");
			}
			String areaName = area.getName()==null?"":area.getName();
			dto.setAreaName(areaName);
		}
		return dto;
	}

	@Override
	public void updateById(AddressModel model) {

		AddressModel queryModel = this.findById(model.getId());
		String oldAddress = "";
		String oldName = "";
		try {
			oldAddress = this.findFullAddress(queryModel.getAreaId1(), queryModel.getAreaId2(), queryModel.getAreaId3())
					+ queryModel.getAddress();
			oldName = queryModel.getName();
		} catch (AddressException e1) {
			e1.printStackTrace();
			throw new ServiceException(e1.getMessage());
		}
		queryModel.setAreaId1(model.getAreaId1());
		queryModel.setAreaId2(model.getAreaId2());
		queryModel.setAreaId3(model.getAreaId3());
		queryModel.setAddress(model.getAddress());
		queryModel.setName(model.getName());
		queryModel.setRelation(model.getRelation());
		queryModel.setAdrCat(model.getAdrCat());
		queryModel.setRemark(model.getRemark());
		queryModel.setStatus(model.getStatus());
		addressMapper.updateById(queryModel);

		// 保存操作记录 bianbianbian
		try {
			String newAddress = this.findFullAddress(model.getAreaId1(), model.getAreaId2(), model.getAreaId3())
					+ model.getAddress();
			String newName = model.getName();
			String oldContent = "姓名：" + oldName + ",地址：" + oldAddress;
			String newContent = "姓名：" + newName + ",地址：" + newAddress;
			String content = "[" + oldContent + "]修改为[" + newContent + "]";
			hurryRecordService.save(
					OperationUtil.addRecord(HurryRecordConst.cAdd, model.getCaseId(), "地址", content, model.getId(), 2));
		} catch (AddressException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteById(String id) {

		// 保存操作记录 bianbianbian
		try {
			AddressModel model = addressMapper.findById(id);
			String address = this.findFullAddress(model.getAreaId1(), model.getAreaId2(), model.getAreaId3())
					+ model.getAddress();
			String content = model.getName() + ", " + address + "[" + model.getAdrCat() + "]";
			hurryRecordService
					.save(OperationUtil.addRecord(HurryRecordConst.cAdd, model.getCaseId(), "地址", content, id, 3));
			addressMapper.deleteById(id);
		} catch (AddressException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public SearchResult<AddressModel> queryDetail(ParamCondition condition) {
		List<AddressModel> list = addressMapper.queryDetail(condition);
		SearchResult<AddressModel> result = new SearchResult<>();
		result.setTotal(condition.getTotal());
		result.setRows(list);
		return result;
	}
	
	@Override
	public List<AddressModel> AddressAll(String caseId){
		return addressMapper.AddressAll(caseId);
	}
	
	@Override
	public List<AddressModel> query(ParamCondition condition) {
		return addressMapper.query(condition);
	}
}
