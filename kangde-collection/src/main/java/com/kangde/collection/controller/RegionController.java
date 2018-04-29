package com.kangde.collection.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangde.collection.model.RegionModel;
import com.kangde.collection.service.RegionService;

@Controller
@RequestMapping(value = "/region")
public class RegionController {
	@Autowired
	private RegionService regionService;
	@RequestMapping(value = "/getProvince")
	@ResponseBody
	public List<RegionModel> getProvince(HttpServletRequest request, String q) throws Exception {
		List<RegionModel> list = regionService.findProvince();
		return list;
	}

	@RequestMapping(value = "/getCity")
	@ResponseBody
	public List<RegionModel> getCity(HttpServletRequest request, String provinceId) throws Exception {
		List<RegionModel> list = regionService.findCityByProvinceId(provinceId);
		return list;
	}

	@RequestMapping(value = "/getArea")
	@ResponseBody
	public List<RegionModel> getArea(HttpServletRequest request, String cityId) throws Exception {
		List<RegionModel> list = regionService.findAreaByCityId(cityId);
		return list;
	}
}
