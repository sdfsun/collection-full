package com.kangde.collection.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangde.collection.dto.StatisticsEmployeeDto;
import com.kangde.collection.model.CallRecord;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.TimelenOutStatistics;
import com.kangde.collection.service.CallRecordService;
import com.kangde.collection.service.CaseBatchService;
import com.kangde.commons.vo.AjaxResult;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * 电催统计 Controller
 * @author zhangyj
 * @date 2016年12月29日
 */
@Controller
@RequestMapping("collection/callRecord")
public class CallRecordController extends RestfulUrlController<CallRecord,String> {
	@Autowired
	private CallRecordService callRecordService;
	
/*	// 前端排序字段 
	public CallRecordController() {
		columnNames.put("totalNumber", "case_batch.total_number");
	}*/
	
	@Override
	public SearchResult<CallRecord> queryForPage() {
		ParamCondition condition = parseCondition("*");
		
		String emId=condition.get("emId");
		if (StringUtils.isNotBlank(emId)) {
			String[] emIds = emId.split(",");
			condition.put("emIds", emIds);
		}
		
		SearchResult<CallRecord> queryForPage = super.queryForPage(condition);
		List<CallRecord> list = queryForPage.getRows();
		List<CallRecord> footer = new ArrayList<CallRecord>();
		CallRecord dto = new CallRecord();
		for (CallRecord callRecord : list) {
			
			
			
			dto.setCrCount(callRecord.getCrCount()+dto.getCrCount());
			dto.setStatus(callRecord.getStatus()+dto.getStatus());
		}
		dto.setName("合计");
		footer.add(dto);
		queryForPage.setFooter(footer);
		return queryForPage;
	}
	@Override
	protected String getBaseName() {
		return "collection/callRecord/";
	}
	
	public static String timeAdd(String time1, String time2){
		String time = "00:00:00";
		
		if (time1!=null&&time2!=null){
			String c[] = time1.split(":");
			String substring = c[0];
			String substring1 = c[1];
			String substring2 = c[2];
			int i = Integer.parseInt(substring);
			int i1 = Integer.parseInt(substring1);
			int i2 = Integer.parseInt(substring2);
			
			String f[] = time2.split(":");
			String sub = f[0];
			String sub1 = f[1];
			String sub2 = f[2];
			int in = Integer.parseInt(sub);
			int in1 = Integer.parseInt(sub1);
			int in2 = Integer.parseInt(sub2);
			
			i2=i2+in2;
			int ss = i2/60;
			int dd = i2%60;
			
			i1=i1+in1+ss;
			int ss1 = i1/60;
			int dd1 = i1%60;
			i=i+in+ss1;
			
			String dd1v= String.valueOf(dd1);
			String ddv= String.valueOf(dd);
			String iv= String.valueOf(i);
			if(iv.length()==1){
				if(dd1v.length()==1&&ddv.length()==1){
					dd1v="0"+dd1v;
					ddv="0"+ddv;
					iv="0"+iv;
					time = iv+":"+dd1v+":"+ddv;
					return time;
				}else if(ddv.length()==1&&dd1v.length()!=1){
					ddv="0"+ddv;
					iv="0"+iv;
					time = iv+":"+dd1v+":"+ddv;
					return time;
				}else if(dd1v.length()==1&&ddv.length()!=1){
					dd1v="0"+dd1v;
					iv="0"+iv;
					time = iv+":"+dd1v+":"+ddv;
					return time;
				}else{
					iv="0"+iv;
					time = iv+":"+dd1v+":"+ddv;
					return time;
				}
			}else{
				if(dd1v.length()==1&&ddv.length()==1){
					dd1v="0"+dd1v;
					ddv="0"+ddv;
					time = i+":"+dd1v+":"+ddv;
					return time;
				}else if(ddv.length()==1&&dd1v.length()!=1){
					ddv="0"+ddv;
					time = i+":"+dd1+":"+ddv;
					System.out.println(time);
					return time;
				}else if(dd1v.length()==1&&ddv.length()!=1){
					dd1v="0"+dd1v;
					time = i+":"+dd1v+":"+dd;
					System.out.println(time);
					return time;
				}else{
					time = i+":"+dd1+":"+dd;
					return time;
				}
			}
		}else if (time1!=null&&time2==null){
			time=time1;
			return time;
			
		}else if (time1==null&&time2!=null){
			time=time2;
			return time;
			
		}else{
			return time;
		}
	}
	
	public static void main(String[] args) throws ParseException { 
		/*String d = "2017-02-10";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
		Date date = format.parse(d);*/
String date = "02/10/2017";
		
		String dateAdd = dateAdd(date);
		System.out.println(dateAdd);
		/*String a = "9,10,11,12,14,15,16,17,19";
		String b = "74,76,73,110,107,92,84,76,58";
		String c = "18,11,10,3,22,7,12,4,9";
		String d = "00:,00:,00:,00:,00:,00:,00:,00:,00:";
		String e = "00:00:28,00:00:19,00:00:12,00:00:03,00:00:15,00:00:32,00:00:40,00:00:10,00:00:13";
		String f = "24.32,14.47,13.70,2.73,20.56,7.61,14.29,5.26,15.52";
		String g = "00:05:03,00:03:29,00:02:05,00:00:56,00:03:54,00:07:10,00:08:00,00:01:50,00:01:54";
		String l = "2345";
		String m = "张三";
		 String[] aaa = bbb(a, b, c,d,e,f,g,l,m);
		for (int i = 0; i < aaa.length; i++) {
			System.out.println(i+"--"+aaa[i]);
		}*/
		
	}
	
	public static String[] bbb(String time, 
							   String count, 
							   String connect,
							   String a,
							   String b,
							   String c,
							   String d,
							   String cno, 
							   String emp) {

		String a1[] = time.split(",");
		String b1[] = count.split(",");
		String c1[] = connect.split(",");
		String d1[] = a.split(",");
		String e1[] = b.split(",");
		String f1[] = c.split(",");
		String g1[] = d.split(",");
		String t[] = new String[146];
		
		for(int j=0;j<t.length;j++){
		    t[j]="0";	
		    for (int i = 0; i < 24; i++) {
				t[6*i+2]="00:00:00";
				t[6*i+3]="00:00:00";
				t[6*i+5]="00:00:00";
			}
			for(int i=0;i<a1.length;i++){
				int ind = 0;
				String index = a1[i];
				ind = Integer.parseInt(index);
				t[ind*6] = b1[i];
				t[ind*6+1]= c1[i];
				t[ind*6+2]= d1[i];
				t[ind*6+3]= e1[i];
				t[ind*6+4]= f1[i];
				t[ind*6+5]= g1[i];
				t[144]=cno;
				t[145]=emp;
				
			}
		}
		return t;
		
	}
	
	
	
	public static String[] aaa(String time, String count, String connect,String cno, String emp) {

		String a1[] = time.split(",");
		String b1[] = count.split(",");
		String c1[] = connect.split(",");
		String t[] = new String[122];
		
		for(int j=0;j<t.length;j++){
		    t[j]="0";	
			for(int i=0;i<a1.length;i++){
				int ind = 0;
				String index = a1[i];
				ind = Integer.parseInt(index);
				t[ind*2] = b1[i];
				t[ind*2+1]= c1[i];
				t[120]=cno;
				t[121]=emp;
				
			}
		}
		return t;
		
	}
	@Test
	public void test(){
		String a = "0,1,2,5,6,7,8,9,24";
		String b = "21,10,5,1,6,1,5,1,3";
		String c = "4,8,7,2,1,4,3,8,2";
//		String d = "1,3,9,4,2,4,5,6,4";
		
		List l = new ArrayList();
		for(int i=0;i<60;i++){
			l.add(i);
		}

		String a1[] = a.split(",");
		String b1[] = b.split(",");
		String c1[] = c.split(",");
//		String d1[] = d.split(",");
		String[][] e = new String[24][4];
		
		
		List<TimelenOutStatistics> ll = new ArrayList<TimelenOutStatistics>();
		
		for(int i=0;i<l.size();i++){
			TimelenOutStatistics statistics = new TimelenOutStatistics();
			for(int j=0;j<a1.length;j++){
				if(String.valueOf(i).equals(a1[j])){
					e[i][0] = a1[j];
					e[i][1] = b1[j];
					e[i][2] = c1[j];
//					e[i][3] = d1[j];
//					statistics.setCallOutCount(Integer.valueOf(b1[j]));
//					statistics.setCallOutConnectCount(Integer.valueOf(c1[j]));
					
					break;
				}
				else{
					e[i][0] = String.valueOf(i);
					e[i][1] = "0";
					e[i][2] = "0";
//					e[i][3] = "0";
//					statistics.setCallOutCount(0);
//					statistics.setCallOutConnectCount(0);
				}
				
			}
			ll.add(statistics);
			
		}
		for (TimelenOutStatistics stics : ll) {
			System.out.println(stics.getCallOutConnectCount());
			System.out.println(stics.getCallOutCount());
		}
		
		/*for(int i=0;i<e.length;i++){
			System.out.println("结果:"+e[i][0]+","+e[i][1]+","+e[i][2]+","+e[i][3]);
		}*/

	}
	
	public static String dateAdd(String date){
		String da = null;
		if (date!=null){
			if(date.contains("/")){
				String c[] = date.split("/");
				String substring = c[0];
				String substring1 = c[1];
				String substring2 = c[2];
				da = substring2+substring+substring1;
			}else if(date.contains("-")){
				String c[] = date.split("-");
				String substring = c[0];
				String substring1 = c[1];
				String substring2 = c[2];
				da = substring2+substring+substring1;
			}
		}
		return da;	
	}
	

}
