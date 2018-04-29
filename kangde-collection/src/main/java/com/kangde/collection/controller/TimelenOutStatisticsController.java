package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kangde.collection.model.TimelenOutStatistics;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * 电催统计 Controller
 * @author zhangyj
 * @date 2016年12月29日
 */
@Controller
@RequestMapping("collection/timelenOutStatistics")
public class TimelenOutStatisticsController extends RestfulUrlController<TimelenOutStatistics,String> {
//	@Autowired
//	private TimelenOutStatisticsService timelenOutStatisticsService;
	
/*	// 前端排序字段 
	public CallRecordController() {
		columnNames.put("totalNumber", "case_batch.total_number");
	}*/
	
	@Override
	public SearchResult<TimelenOutStatistics> queryForPage() {
		ParamCondition condition = parseCondition("*");
		
		String emId=condition.get("emId");
		if (StringUtils.isNotBlank(emId)) {
			String[] emIds = emId.split(",");
			condition.put("emIds", emIds);
		}
		
		SearchResult<TimelenOutStatistics> queryForPage = super.queryForPage(condition);
		List<TimelenOutStatistics> list = queryForPage.getRows();
		List<TimelenOutStatistics> footer = new ArrayList<TimelenOutStatistics>();
		TimelenOutStatistics dto = new TimelenOutStatistics();
		List<TimelenOutStatistics> list2 = new ArrayList<TimelenOutStatistics>();
		for (TimelenOutStatistics callRecord : list) {
			String callOutCount = callRecord.getCallOutCount();
			String callOutConnectCount = callRecord.getCallOutConnectCount();
			String timeLen = callRecord.getTimeLen();
			String cno = callRecord.getCno();
			String empName = callRecord.getEmpName();
			
			String[] model = aaa(timeLen, callOutCount, callOutConnectCount,cno,empName);
			for (int i = 0; i < model.length; i++) {
				
				int a = Integer.parseInt(model[0])-Integer.parseInt(model[1]);
				//0分钟
				callRecord.setCallOutCount(String.valueOf(a));
				//1分钟以内
				callRecord.setCallOutConnectCount(model[1]);
				//1-2分钟
				callRecord.setCallOutConnectCount1(model[3]);
				//2-3分钟
				callRecord.setCallOutConnectCount2(model[5]);
				//3-5分钟
				int a3 = Integer.parseInt(model[7])+Integer.parseInt(model[9]);
				callRecord.setCallOutConnectCount3(String.valueOf(a3));
				//5-10分钟
				int a5 = Integer.parseInt(model[11])
						+Integer.parseInt(model[13])
						+Integer.parseInt(model[15])
						+Integer.parseInt(model[17])
						+Integer.parseInt(model[19]);
				callRecord.setCallOutConnectCount5(String.valueOf(a5));
//				callRecord.setCallOutConnectCount5(model[11]);
//				callRecord.setCallOutConnectCount6(model[13]);
//				callRecord.setCallOutConnectCount7(model[15]);
//				callRecord.setCallOutConnectCount8(model[17]);
//				callRecord.setCallOutConnectCount9(model[19]);
				//10-15分钟
				int a10 = Integer.parseInt(model[21])
						+Integer.parseInt(model[23])
						+Integer.parseInt(model[25])
						+Integer.parseInt(model[27])
						+Integer.parseInt(model[29]);
				callRecord.setCallOutConnectCount10(String.valueOf(a10));
//				callRecord.setCallOutConnectCount10(model[21]);
//				callRecord.setCallOutConnectCount11(model[23]);
//				callRecord.setCallOutConnectCount12(model[25]);
//				callRecord.setCallOutConnectCount13(model[27]);
//				callRecord.setCallOutConnectCount14(model[29]);
				//15-20分钟
				int a15 = Integer.parseInt(model[31])
						+Integer.parseInt(model[33])
						+Integer.parseInt(model[35])
						+Integer.parseInt(model[37])
						+Integer.parseInt(model[39]);
				callRecord.setCallOutConnectCount15(String.valueOf(a15));
//				callRecord.setCallOutConnectCount15(model[31]);
//				callRecord.setCallOutConnectCount16(model[33]);
//				callRecord.setCallOutConnectCount17(model[35]);
//				callRecord.setCallOutConnectCount18(model[37]);
//				callRecord.setCallOutConnectCount19(model[39]);
				//20-30分钟
				int a20 = Integer.parseInt(model[41])
						+Integer.parseInt(model[43])
						+Integer.parseInt(model[45])
						+Integer.parseInt(model[47])
						+Integer.parseInt(model[49])
						+Integer.parseInt(model[51])
						+Integer.parseInt(model[53])
						+Integer.parseInt(model[55])
						+Integer.parseInt(model[57])
						+Integer.parseInt(model[59]);
				callRecord.setCallOutConnectCount20(String.valueOf(a20));
//				callRecord.setCallOutConnectCount20(model[41]);
//				callRecord.setCallOutConnectCount21(model[43]);
//				callRecord.setCallOutConnectCount22(model[45]);
//				callRecord.setCallOutConnectCount23(model[47]);
//				callRecord.setCallOutConnectCount24(model[49]);
//				callRecord.setCallOutConnectCount25(model[51]);
//				callRecord.setCallOutConnectCount26(model[53]);
//				callRecord.setCallOutConnectCount27(model[55]);
//				callRecord.setCallOutConnectCount28(model[57]);
//				callRecord.setCallOutConnectCount29(model[59]);
				//30分钟以上
				int a30 = Integer.parseInt(model[61])
						+Integer.parseInt(model[63])
						+Integer.parseInt(model[65])
						+Integer.parseInt(model[67])
						+Integer.parseInt(model[69])
						+Integer.parseInt(model[71])
						+Integer.parseInt(model[73])
						+Integer.parseInt(model[75])
						+Integer.parseInt(model[77])
						+Integer.parseInt(model[79])
						+Integer.parseInt(model[81])
						+Integer.parseInt(model[83])
						+Integer.parseInt(model[85])
						+Integer.parseInt(model[87])
						+Integer.parseInt(model[89])
						+Integer.parseInt(model[91])
						+Integer.parseInt(model[93])
						+Integer.parseInt(model[95])
						+Integer.parseInt(model[97])
						+Integer.parseInt(model[99])
						+Integer.parseInt(model[101])
						+Integer.parseInt(model[103])
						+Integer.parseInt(model[105])
						+Integer.parseInt(model[107])
						+Integer.parseInt(model[109])
						+Integer.parseInt(model[111])
						+Integer.parseInt(model[113])
						+Integer.parseInt(model[115])
						+Integer.parseInt(model[117])
						+Integer.parseInt(model[119]);
				callRecord.setCallOutConnectCount30(String.valueOf(a30));
//				callRecord.setCallOutConnectCount30(model[61]);
//				callRecord.setCallOutConnectCount31(model[63]);
//				callRecord.setCallOutConnectCount32(model[65]);
//				callRecord.setCallOutConnectCount33(model[67]);
//				callRecord.setCallOutConnectCount34(model[69]);
//				callRecord.setCallOutConnectCount35(model[71]);
//				callRecord.setCallOutConnectCount36(model[73]);
//				callRecord.setCallOutConnectCount37(model[75]);
//				callRecord.setCallOutConnectCount38(model[77]);
//				callRecord.setCallOutConnectCount39(model[79]);
//				callRecord.setCallOutConnectCount40(model[81]);
//				callRecord.setCallOutConnectCount41(model[83]);
//				callRecord.setCallOutConnectCount42(model[85]);
//				callRecord.setCallOutConnectCount43(model[87]);
//				callRecord.setCallOutConnectCount44(model[89]);
//				callRecord.setCallOutConnectCount45(model[91]);
//				callRecord.setCallOutConnectCount46(model[93]);
//				callRecord.setCallOutConnectCount47(model[95]);
//				callRecord.setCallOutConnectCount48(model[97]);
//				callRecord.setCallOutConnectCount49(model[99]);
//				callRecord.setCallOutConnectCount50(model[101]);
//				callRecord.setCallOutConnectCount51(model[103]);
//				callRecord.setCallOutConnectCount52(model[105]);
//				callRecord.setCallOutConnectCount53(model[107]);
//				callRecord.setCallOutConnectCount54(model[109]);
//				callRecord.setCallOutConnectCount55(model[111]);
//				callRecord.setCallOutConnectCount56(model[113]);
//				callRecord.setCallOutConnectCount57(model[115]);
//				callRecord.setCallOutConnectCount58(model[117]);
//				callRecord.setCallOutConnectCount59(model[119]);
				
			}
			list2.add(callRecord);
		}
		for (TimelenOutStatistics timelen : list2) {
			int a =Integer.parseInt(timelen.getCallOutCount())+ Integer.parseInt(dto.getCallOutCount()==null?"0":dto.getCallOutCount());
			int b = Integer.parseInt(timelen.getCallOutConnectCount())+Integer.parseInt(dto.getCallOutConnectCount()==null?"0":dto.getCallOutConnectCount());
			
			int b1 = Integer.parseInt(timelen.getCallOutConnectCount1())+Integer.parseInt(dto.getCallOutConnectCount1()==null?"0":dto.getCallOutConnectCount1());
			int b2 = Integer.parseInt(timelen.getCallOutConnectCount2())+Integer.parseInt(dto.getCallOutConnectCount2()==null?"0":dto.getCallOutConnectCount2());
			
			int b3 = Integer.parseInt(timelen.getCallOutConnectCount3())+Integer.parseInt(dto.getCallOutConnectCount3()==null?"0":dto.getCallOutConnectCount3());
			int b5 = Integer.parseInt(timelen.getCallOutConnectCount5())+Integer.parseInt(dto.getCallOutConnectCount5()==null?"0":dto.getCallOutConnectCount5());
			
			int b10 = Integer.parseInt(timelen.getCallOutConnectCount10())+Integer.parseInt(dto.getCallOutConnectCount10()==null?"0":dto.getCallOutConnectCount10());
			int b15 = Integer.parseInt(timelen.getCallOutConnectCount15())+Integer.parseInt(dto.getCallOutConnectCount15()==null?"0":dto.getCallOutConnectCount15());
			
			int b20 = Integer.parseInt(timelen.getCallOutConnectCount20())+Integer.parseInt(dto.getCallOutConnectCount20()==null?"0":dto.getCallOutConnectCount20());
			int b30 = Integer.parseInt(timelen.getCallOutConnectCount30())+Integer.parseInt(dto.getCallOutConnectCount30()==null?"0":dto.getCallOutConnectCount30());

			
			dto.setCallOutCount(String.valueOf(a));
			
			dto.setCallOutConnectCount(String.valueOf(b));
			
			dto.setCallOutConnectCount1(String.valueOf(b1));
			
			dto.setCallOutConnectCount2(String.valueOf(b2));
			
			dto.setCallOutConnectCount3(String.valueOf(b3));
			
			dto.setCallOutConnectCount5(String.valueOf(b5));
			
			dto.setCallOutConnectCount10(String.valueOf(b10));
			
			dto.setCallOutConnectCount15(String.valueOf(b15));
		
			dto.setCallOutConnectCount20(String.valueOf(b20));
			
			dto.setCallOutConnectCount30(String.valueOf(b30));
			
		}
		
		queryForPage.setRows(list2);
		queryForPage.setTotal(list2.size());
		dto.setEmpName("合计");
		dto.setCno("—");
		dto.setTimeLen("—");
		footer.add(dto);
		queryForPage.setFooter(footer);
		return queryForPage;
	}
	@Override
	protected String getBaseName() {
		return "collection/timelenOutStatistics/";
	}
	public static void main(String[] args) {
		for (int i = 0; i < 60; i++) {
//			System.out.println("callRecord.setCallOutCount"+i+"(model["+(i*2)+"]);");
//			System.out.println("callRecord.setCallOutConnectCount"+i+"(model["+(i*2+1)+"]);");
//			System.out.println("private String callOutCount"+i+";");
//			System.out.println("private String callOutConnectCount"+i+";");
//			System.out.println("int a"+i+" = Integer.parseInt(timelen.getCallOutCount"+i+"())+Integer.parseInt(dto.getCallOutCount"+i+"()==null?\"0\":dto.getCallOutCount"+i+"());");
//			System.out.println("int b"+i+" = Integer.parseInt(timelen.getCallOutConnectCount"+i+"())+Integer.parseInt(dto.getCallOutConnectCount"+i+"()==null?\"0\":dto.getCallOutConnectCount"+i+"());");
//			System.out.println("dto.setCallOutCount"+i+"(String.valueOf(a"+i+"));");
			
//			System.out.println("dto.setCallOutConnectCount"+i+"(String.valueOf(b"+i+"));");
//			System.out.println("<th colspan=\"2\" data-options=\"field:'timeLen',align:'center',title:'"+i+"-"+(i+1)+"分钟',width:95\"></th>");
//			System.out.println("<th data-options=\"field:'callOutCount"+i+"',title:'电催总次数',width:95\"></th>");
//			System.out.println("<th data-options=\"field:'callOutConnectCount"+i+"',title:'总接通次数',width:95\"></th>");
		}
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
}
