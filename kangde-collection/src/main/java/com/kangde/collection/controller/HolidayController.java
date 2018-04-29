package com.kangde.collection.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.kangde.collection.exception.HolidayException;
import com.kangde.collection.model.HolidayModel;
import com.kangde.collection.service.HolidayService;
import com.kangde.commons.web.controller.SimpleController;
@Controller
@RequestMapping("collection/holiday")
public class HolidayController extends SimpleController {
	@Autowired
	private  HolidayService holidayService;
	
	@RequestMapping(value = PAGE_INDEX, method = RequestMethod.GET)
	public ModelAndView pageIndex() {
		return createBaseView(PAGE_INDEX);
	}
	@ResponseBody
	@RequestMapping(value = "holidays", method = RequestMethod.GET)
	public String holidays() {
		JSONObject json=new JSONObject();
			List<HolidayModel> data = holidayService.queryAll();
			json.put("state", 1);
			json.put("msg", "ok");
			json.put("data", data);
		return json.toJSONString();
		
	}
	@ResponseBody
	@RequestMapping(value = "addDays", method = RequestMethod.GET)
	public String holidays(int day) {
		Calendar canlendar = holidayService.addDateByWorkDay(new Date(), day);
		return new SimpleDateFormat("yyyy-MM-dd").format(canlendar.getTime());
	}
	@ResponseBody
	@RequestMapping(value = "delDay", method = RequestMethod.GET)
	public  String delDay(int id) {
		holidayService.delDay(id);
		return this.holidays();
	}
	@ResponseBody
	@RequestMapping(value = "clearholiday", method = RequestMethod.GET)
	public String clearholiday() {
		holidayService.clearholiday();
		return this.holidays();
	}
	@ResponseBody
	@RequestMapping(value = "saveholidays", method = RequestMethod.POST)
	public String saveholidays(String content) {
		String[] records = content.split("\n");
		try {
			holidayService.saveholidays(records);
		} catch (HolidayException e) {
			JSONObject json=new JSONObject();
			json.put("state", 0);
			json.put("msg", e.getMessage());
			return json.toJSONString();
		}
		return this.holidays();
	}
	@Override
	protected String getBaseName() {
		return "collection/holiday/";
	}

}
