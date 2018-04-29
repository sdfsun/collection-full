package com.kangde.demo.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChartController {
	
	
	@RequestMapping("/chart")
	public String testview(Map<String,Object> map,HttpServletResponse response) throws IOException{
		
		map.put("Jan.", 1200L);
		map.put("Feb.", 2100L);
		map.put("Mar.", 3200L);
		
		return "chartView";
	}
	
	

}
