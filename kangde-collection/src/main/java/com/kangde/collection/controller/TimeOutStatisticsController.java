package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kangde.collection.model.TimeOutStatistics;
import com.kangde.collection.model.TimelenOutStatistics;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * 电催统计 Controller
 * @author zhangyj
 * @date 2016年12月29日
 */
@Controller
@RequestMapping("collection/timeOutStatistics")
public class TimeOutStatisticsController extends RestfulUrlController<TimeOutStatistics,String> {
//	@Autowired
//	private TimeOutStatisticsService tTimeOutStatisticsService;

	@Override
	public SearchResult<TimeOutStatistics> queryForPage() {
		ParamCondition condition = parseCondition("*");
		
		String emId=condition.get("emId");
		if (StringUtils.isNotBlank(emId)) {
			String[] emIds = emId.split(",");
			condition.put("emIds", emIds);
		}
		
		SearchResult<TimeOutStatistics> queryForPage = super.queryForPage(condition);
		List<TimeOutStatistics> list = queryForPage.getRows();
		List<TimeOutStatistics> footer = new ArrayList<TimeOutStatistics>();
		TimeOutStatistics dto = new TimeOutStatistics();
		List<TimeOutStatistics> list2 = new ArrayList<TimeOutStatistics>();
		for (TimeOutStatistics callRecord : list) {
			String time = callRecord.getTime();
			String callOutCount = callRecord.getCallOutCount();
			String callOutConnectCount = callRecord.getCallOutConnectCount();
			String callOutConnectDuration = callRecord.getCallOutConnectDuration();
			String callOutConnectDurationAvg = callRecord.getCallOutConnectDurationAvg();
			String callOutConnectRate = callRecord.getCallOutConnectRate();
			String bridgeDurationDayAvg = callRecord.getBridgeDurationDayAvg();
			String cno = callRecord.getCno();
			String empName = callRecord.getEmpName();
			
			String[] model = bbb(time,callOutCount, 
								callOutConnectCount,
								callOutConnectDuration, 
								callOutConnectDurationAvg, 
								callOutConnectRate, 
								bridgeDurationDayAvg, 
								cno, empName);
			
			for (int i = 0; i < model.length; i++) {
				//0-8点
				int count = Integer.parseInt(model[0])
						+Integer.parseInt(model[6])
						+Integer.parseInt(model[12])
						+Integer.parseInt(model[18])
						+Integer.parseInt(model[24])
						+Integer.parseInt(model[30])
						+Integer.parseInt(model[36])
						+Integer.parseInt(model[42]);
				
				int connectCount = Integer.parseInt(model[1])
						+Integer.parseInt(model[7])
						+Integer.parseInt(model[13])
						+Integer.parseInt(model[19])
						+Integer.parseInt(model[25])
						+Integer.parseInt(model[31])
						+Integer.parseInt(model[37])
						+Integer.parseInt(model[43]);
				
				String add0 = DateUtil.timeAdd(model[2],model[8]);
				String add1 = DateUtil.timeAdd(add0,model[14]);
				String add2 = DateUtil.timeAdd(add1,model[20]);
				String add3 = DateUtil.timeAdd(add2,model[26]);
				String add4 = DateUtil.timeAdd(add3,model[32]);
				String add5 = DateUtil.timeAdd(add4,model[38]);
				String duration = DateUtil.timeAdd(add5,model[44]);
				
				int rate=0;
				try {
					rate = connectCount*100/count;
				} catch (Exception e1) {
					// Auto-generated catch block
					//e1.printStackTrace();
				}
				
				callRecord.setCallOutCount(String.valueOf(count));
				callRecord.setCallOutConnectCount(String.valueOf(connectCount));
				callRecord.setCallOutConnectDuration(duration);
				//callRecord.setCallOutConnectDurationAvg(model[3]);
				callRecord.setCallOutConnectRate(String.valueOf(rate));
				//callRecord.setBridgeDurationDayAvg(model[5]);
				
				/*callRecord.setCallOutCount(model[0]);
				callRecord.setCallOutConnectCount(model[1]);
				callRecord.setCallOutConnectDuration(model[2]);
				callRecord.setCallOutConnectDurationAvg(model[3]);
				callRecord.setCallOutConnectRate(model[4]);
				callRecord.setBridgeDurationDayAvg(model[5]);
				
				callRecord.setCallOutCount1(model[6]);
				callRecord.setCallOutConnectCount1(model[7]);
				callRecord.setCallOutConnectDuration1(model[8]);
				callRecord.setCallOutConnectDurationAvg1(model[9]);
				callRecord.setCallOutConnectRate1(model[10]);
				callRecord.setBridgeDurationDayAvg1(model[11]);
				
				callRecord.setCallOutCount2(model[12]);
				callRecord.setCallOutConnectCount2(model[13]);
				callRecord.setCallOutConnectDuration2(model[14]);
				callRecord.setCallOutConnectDurationAvg2(model[15]);
				callRecord.setCallOutConnectRate2(model[16]);
				callRecord.setBridgeDurationDayAvg2(model[17]);
				callRecord.setCallOutCount3(model[18]);
				callRecord.setCallOutConnectCount3(model[19]);
				callRecord.setCallOutConnectDuration3(model[20]);
				callRecord.setCallOutConnectDurationAvg3(model[21]);
				callRecord.setCallOutConnectRate3(model[22]);
				callRecord.setBridgeDurationDayAvg3(model[23]);
				callRecord.setCallOutCount4(model[24]);
				callRecord.setCallOutConnectCount4(model[25]);
				callRecord.setCallOutConnectDuration4(model[26]);
				callRecord.setCallOutConnectDurationAvg4(model[27]);
				callRecord.setCallOutConnectRate4(model[28]);
				callRecord.setBridgeDurationDayAvg4(model[29]);
				callRecord.setCallOutCount5(model[30]);
				callRecord.setCallOutConnectCount5(model[31]);
				callRecord.setCallOutConnectDuration5(model[32]);
				callRecord.setCallOutConnectDurationAvg5(model[33]);
				callRecord.setCallOutConnectRate5(model[34]);
				callRecord.setBridgeDurationDayAvg5(model[35]);
				callRecord.setCallOutCount6(model[36]);
				callRecord.setCallOutConnectCount6(model[37]);
				callRecord.setCallOutConnectDuration6(model[38]);
				callRecord.setCallOutConnectDurationAvg6(model[39]);
				callRecord.setCallOutConnectRate6(model[40]);
				callRecord.setBridgeDurationDayAvg6(model[41]);
				callRecord.setCallOutCount7(model[42]);
				callRecord.setCallOutConnectCount7(model[43]);
				callRecord.setCallOutConnectDuration7(model[44]);
				callRecord.setCallOutConnectDurationAvg7(model[45]);
				callRecord.setCallOutConnectRate7(model[46]);
				callRecord.setBridgeDurationDayAvg7(model[47]);*/
				//
				callRecord.setCallOutCount8(model[48]);
				callRecord.setCallOutConnectCount8(model[49]);
				callRecord.setCallOutConnectDuration8(model[50]);
				callRecord.setCallOutConnectDurationAvg8(model[51]);
				callRecord.setCallOutConnectRate8(model[52]);
				callRecord.setBridgeDurationDayAvg8(model[53]);
				callRecord.setCallOutCount9(model[54]);
				callRecord.setCallOutConnectCount9(model[55]);
				callRecord.setCallOutConnectDuration9(model[56]);
				callRecord.setCallOutConnectDurationAvg9(model[57]);
				callRecord.setCallOutConnectRate9(model[58]);
				callRecord.setBridgeDurationDayAvg9(model[59]);
				callRecord.setCallOutCount10(model[60]);
				callRecord.setCallOutConnectCount10(model[61]);
				callRecord.setCallOutConnectDuration10(model[62]);
				callRecord.setCallOutConnectDurationAvg10(model[63]);
				callRecord.setCallOutConnectRate10(model[64]);
				callRecord.setBridgeDurationDayAvg10(model[65]);
				callRecord.setCallOutCount11(model[66]);
				callRecord.setCallOutConnectCount11(model[67]);
				callRecord.setCallOutConnectDuration11(model[68]);
				callRecord.setCallOutConnectDurationAvg11(model[69]);
				callRecord.setCallOutConnectRate11(model[70]);
				callRecord.setBridgeDurationDayAvg11(model[71]);
				callRecord.setCallOutCount12(model[72]);
				callRecord.setCallOutConnectCount12(model[73]);
				callRecord.setCallOutConnectDuration12(model[74]);
				callRecord.setCallOutConnectDurationAvg12(model[75]);
				callRecord.setCallOutConnectRate12(model[76]);
				callRecord.setBridgeDurationDayAvg12(model[77]);
				callRecord.setCallOutCount13(model[78]);
				callRecord.setCallOutConnectCount13(model[79]);
				callRecord.setCallOutConnectDuration13(model[80]);
				callRecord.setCallOutConnectDurationAvg13(model[81]);
				callRecord.setCallOutConnectRate13(model[82]);
				callRecord.setBridgeDurationDayAvg13(model[83]);
				callRecord.setCallOutCount14(model[84]);
				callRecord.setCallOutConnectCount14(model[85]);
				callRecord.setCallOutConnectDuration14(model[86]);
				callRecord.setCallOutConnectDurationAvg14(model[87]);
				callRecord.setCallOutConnectRate14(model[88]);
				callRecord.setBridgeDurationDayAvg14(model[89]);
				callRecord.setCallOutCount15(model[90]);
				callRecord.setCallOutConnectCount15(model[91]);
				callRecord.setCallOutConnectDuration15(model[92]);
				callRecord.setCallOutConnectDurationAvg15(model[93]);
				callRecord.setCallOutConnectRate15(model[94]);
				callRecord.setBridgeDurationDayAvg15(model[95]);
				callRecord.setCallOutCount16(model[96]);
				callRecord.setCallOutConnectCount16(model[97]);
				callRecord.setCallOutConnectDuration16(model[98]);
				callRecord.setCallOutConnectDurationAvg16(model[99]);
				callRecord.setCallOutConnectRate16(model[100]);
				callRecord.setBridgeDurationDayAvg16(model[101]);
				callRecord.setCallOutCount17(model[102]);
				callRecord.setCallOutConnectCount17(model[103]);
				callRecord.setCallOutConnectDuration17(model[104]);
				callRecord.setCallOutConnectDurationAvg17(model[105]);
				callRecord.setCallOutConnectRate17(model[106]);
				callRecord.setBridgeDurationDayAvg17(model[107]);
				callRecord.setCallOutCount18(model[108]);
				callRecord.setCallOutConnectCount18(model[109]);
				callRecord.setCallOutConnectDuration18(model[110]);
				callRecord.setCallOutConnectDurationAvg18(model[111]);
				callRecord.setCallOutConnectRate18(model[112]);
				callRecord.setBridgeDurationDayAvg18(model[113]);
				callRecord.setCallOutCount19(model[114]);
				callRecord.setCallOutConnectCount19(model[115]);
				callRecord.setCallOutConnectDuration19(model[116]);
				callRecord.setCallOutConnectDurationAvg19(model[117]);
				callRecord.setCallOutConnectRate19(model[118]);
				callRecord.setBridgeDurationDayAvg19(model[119]);
				callRecord.setCallOutCount20(model[120]);
				callRecord.setCallOutConnectCount20(model[121]);
				callRecord.setCallOutConnectDuration20(model[122]);
				callRecord.setCallOutConnectDurationAvg20(model[123]);
				callRecord.setCallOutConnectRate20(model[124]);
				callRecord.setBridgeDurationDayAvg20(model[125]);
				//21-23点
				int count21 = Integer.parseInt(model[126])
						+Integer.parseInt(model[132])
						+Integer.parseInt(model[138]);
				
				int connectCount21 = Integer.parseInt(model[127])
						+Integer.parseInt(model[133])
						+Integer.parseInt(model[139]);
				
				String add = DateUtil.timeAdd(model[128],model[134]);
				String duration21 = DateUtil.timeAdd(add,model[140]);
				
				int rate21=0;
				try {
					rate21 = connectCount21*100/count21;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
				callRecord.setCallOutCount21(String.valueOf(count21));
				callRecord.setCallOutConnectCount21(String.valueOf(connectCount21));
				callRecord.setCallOutConnectDuration21(duration21);
				//callRecord.setCallOutConnectDurationAvg21(model[129]);
				callRecord.setCallOutConnectRate21(String.valueOf(rate21));
				//callRecord.setBridgeDurationDayAvg21(model[131]);
				/*callRecord.setCallOutCount21(model[126]);
				callRecord.setCallOutConnectCount21(model[127]);
				callRecord.setCallOutConnectDuration21(model[128]);
				callRecord.setCallOutConnectDurationAvg21(model[129]);
				callRecord.setCallOutConnectRate21(model[130]);
				callRecord.setBridgeDurationDayAvg21(model[131]);
				
				callRecord.setCallOutCount22(model[132]);
				callRecord.setCallOutConnectCount22(model[133]);
				callRecord.setCallOutConnectDuration22(model[134]);
				callRecord.setCallOutConnectDurationAvg22(model[135]);
				callRecord.setCallOutConnectRate22(model[136]);
				callRecord.setBridgeDurationDayAvg22(model[137]);
				
				callRecord.setCallOutCount23(model[138]);
				callRecord.setCallOutConnectCount23(model[139]);
				callRecord.setCallOutConnectDuration23(model[140]);
				callRecord.setCallOutConnectDurationAvg23(model[141]);
				callRecord.setCallOutConnectRate23(model[142]);
				callRecord.setBridgeDurationDayAvg23(model[143]);*/
			}
			list2.add(callRecord);
		}
		for (TimeOutStatistics timelen : list2) {
			int a =Integer.parseInt(timelen.getCallOutCount())+ Integer.parseInt(dto.getCallOutCount()==null?"0":dto.getCallOutCount());
			int b = Integer.parseInt(timelen.getCallOutConnectCount())+Integer.parseInt(dto.getCallOutConnectCount()==null?"0":dto.getCallOutConnectCount());
			
//			int a1 = Integer.parseInt(timelen.getCallOutCount1())+Integer.parseInt(dto.getCallOutCount1()==null?"0":dto.getCallOutCount1());
//			int b1 = Integer.parseInt(timelen.getCallOutConnectCount1())+Integer.parseInt(dto.getCallOutConnectCount1()==null?"0":dto.getCallOutConnectCount1());
//			int a2 = Integer.parseInt(timelen.getCallOutCount2())+Integer.parseInt(dto.getCallOutCount2()==null?"0":dto.getCallOutCount2());
//			int b2 = Integer.parseInt(timelen.getCallOutConnectCount2())+Integer.parseInt(dto.getCallOutConnectCount2()==null?"0":dto.getCallOutConnectCount2());
//			int a3 = Integer.parseInt(timelen.getCallOutCount3())+Integer.parseInt(dto.getCallOutCount3()==null?"0":dto.getCallOutCount3());
//			int b3 = Integer.parseInt(timelen.getCallOutConnectCount3())+Integer.parseInt(dto.getCallOutConnectCount3()==null?"0":dto.getCallOutConnectCount3());
//			int a4 = Integer.parseInt(timelen.getCallOutCount4())+Integer.parseInt(dto.getCallOutCount4()==null?"0":dto.getCallOutCount4());
//			int b4 = Integer.parseInt(timelen.getCallOutConnectCount4())+Integer.parseInt(dto.getCallOutConnectCount4()==null?"0":dto.getCallOutConnectCount4());
//			int a5 = Integer.parseInt(timelen.getCallOutCount5())+Integer.parseInt(dto.getCallOutCount5()==null?"0":dto.getCallOutCount5());
//			int b5 = Integer.parseInt(timelen.getCallOutConnectCount5())+Integer.parseInt(dto.getCallOutConnectCount5()==null?"0":dto.getCallOutConnectCount5());
//			int a6 = Integer.parseInt(timelen.getCallOutCount6())+Integer.parseInt(dto.getCallOutCount6()==null?"0":dto.getCallOutCount6());
//			int b6 = Integer.parseInt(timelen.getCallOutConnectCount6())+Integer.parseInt(dto.getCallOutConnectCount6()==null?"0":dto.getCallOutConnectCount6());
//			int a7 = Integer.parseInt(timelen.getCallOutCount7())+Integer.parseInt(dto.getCallOutCount7()==null?"0":dto.getCallOutCount7());
//			int b7 = Integer.parseInt(timelen.getCallOutConnectCount7())+Integer.parseInt(dto.getCallOutConnectCount7()==null?"0":dto.getCallOutConnectCount7());
			int a8 = Integer.parseInt(timelen.getCallOutCount8())+Integer.parseInt(dto.getCallOutCount8()==null?"0":dto.getCallOutCount8());
			int b8 = Integer.parseInt(timelen.getCallOutConnectCount8())+Integer.parseInt(dto.getCallOutConnectCount8()==null?"0":dto.getCallOutConnectCount8());
			int a9 = Integer.parseInt(timelen.getCallOutCount9())+Integer.parseInt(dto.getCallOutCount9()==null?"0":dto.getCallOutCount9());
			int b9 = Integer.parseInt(timelen.getCallOutConnectCount9())+Integer.parseInt(dto.getCallOutConnectCount9()==null?"0":dto.getCallOutConnectCount9());
			int a10 = Integer.parseInt(timelen.getCallOutCount10())+Integer.parseInt(dto.getCallOutCount10()==null?"0":dto.getCallOutCount10());
			int b10 = Integer.parseInt(timelen.getCallOutConnectCount10())+Integer.parseInt(dto.getCallOutConnectCount10()==null?"0":dto.getCallOutConnectCount10());
			int a11 = Integer.parseInt(timelen.getCallOutCount11())+Integer.parseInt(dto.getCallOutCount11()==null?"0":dto.getCallOutCount11());
			int b11 = Integer.parseInt(timelen.getCallOutConnectCount11())+Integer.parseInt(dto.getCallOutConnectCount11()==null?"0":dto.getCallOutConnectCount11());
			int a12 = Integer.parseInt(timelen.getCallOutCount12())+Integer.parseInt(dto.getCallOutCount12()==null?"0":dto.getCallOutCount12());
			int b12 = Integer.parseInt(timelen.getCallOutConnectCount12())+Integer.parseInt(dto.getCallOutConnectCount12()==null?"0":dto.getCallOutConnectCount12());
			int a13 = Integer.parseInt(timelen.getCallOutCount13())+Integer.parseInt(dto.getCallOutCount13()==null?"0":dto.getCallOutCount13());
			int b13 = Integer.parseInt(timelen.getCallOutConnectCount13())+Integer.parseInt(dto.getCallOutConnectCount13()==null?"0":dto.getCallOutConnectCount13());
			int a14 = Integer.parseInt(timelen.getCallOutCount14())+Integer.parseInt(dto.getCallOutCount14()==null?"0":dto.getCallOutCount14());
			int b14 = Integer.parseInt(timelen.getCallOutConnectCount14())+Integer.parseInt(dto.getCallOutConnectCount14()==null?"0":dto.getCallOutConnectCount14());
			int a15 = Integer.parseInt(timelen.getCallOutCount15())+Integer.parseInt(dto.getCallOutCount15()==null?"0":dto.getCallOutCount15());
			int b15 = Integer.parseInt(timelen.getCallOutConnectCount15())+Integer.parseInt(dto.getCallOutConnectCount15()==null?"0":dto.getCallOutConnectCount15());
			int a16 = Integer.parseInt(timelen.getCallOutCount16())+Integer.parseInt(dto.getCallOutCount16()==null?"0":dto.getCallOutCount16());
			int b16 = Integer.parseInt(timelen.getCallOutConnectCount16())+Integer.parseInt(dto.getCallOutConnectCount16()==null?"0":dto.getCallOutConnectCount16());
			int a17 = Integer.parseInt(timelen.getCallOutCount17())+Integer.parseInt(dto.getCallOutCount17()==null?"0":dto.getCallOutCount17());
			int b17 = Integer.parseInt(timelen.getCallOutConnectCount17())+Integer.parseInt(dto.getCallOutConnectCount17()==null?"0":dto.getCallOutConnectCount17());
			int a18 = Integer.parseInt(timelen.getCallOutCount18())+Integer.parseInt(dto.getCallOutCount18()==null?"0":dto.getCallOutCount18());
			int b18 = Integer.parseInt(timelen.getCallOutConnectCount18())+Integer.parseInt(dto.getCallOutConnectCount18()==null?"0":dto.getCallOutConnectCount18());
			int a19 = Integer.parseInt(timelen.getCallOutCount19())+Integer.parseInt(dto.getCallOutCount19()==null?"0":dto.getCallOutCount19());
			int b19 = Integer.parseInt(timelen.getCallOutConnectCount19())+Integer.parseInt(dto.getCallOutConnectCount19()==null?"0":dto.getCallOutConnectCount19());
			int a20 = Integer.parseInt(timelen.getCallOutCount20())+Integer.parseInt(dto.getCallOutCount20()==null?"0":dto.getCallOutCount20());
			int b20 = Integer.parseInt(timelen.getCallOutConnectCount20())+Integer.parseInt(dto.getCallOutConnectCount20()==null?"0":dto.getCallOutConnectCount20());
			int a21 = Integer.parseInt(timelen.getCallOutCount21())+Integer.parseInt(dto.getCallOutCount21()==null?"0":dto.getCallOutCount21());
			int b21 = Integer.parseInt(timelen.getCallOutConnectCount21())+Integer.parseInt(dto.getCallOutConnectCount21()==null?"0":dto.getCallOutConnectCount21());
//			int a22 = Integer.parseInt(timelen.getCallOutCount22())+Integer.parseInt(dto.getCallOutCount22()==null?"0":dto.getCallOutCount22());
//			int b22 = Integer.parseInt(timelen.getCallOutConnectCount22())+Integer.parseInt(dto.getCallOutConnectCount22()==null?"0":dto.getCallOutConnectCount22());
//			int a23 = Integer.parseInt(timelen.getCallOutCount23())+Integer.parseInt(dto.getCallOutCount23()==null?"0":dto.getCallOutCount23());
//			int b23 = Integer.parseInt(timelen.getCallOutConnectCount23())+Integer.parseInt(dto.getCallOutConnectCount23()==null?"0":dto.getCallOutConnectCount23());
			
			dto.setCallOutCount(String.valueOf(a));
			dto.setCallOutConnectCount(String.valueOf(b));
//			dto.setCallOutCount1(String.valueOf(a1));
//			dto.setCallOutConnectCount1(String.valueOf(b1));
//			dto.setCallOutCount2(String.valueOf(a2));
//			dto.setCallOutConnectCount2(String.valueOf(b2));
//			dto.setCallOutCount3(String.valueOf(a3));
//			dto.setCallOutConnectCount3(String.valueOf(b3));
//			dto.setCallOutCount4(String.valueOf(a4));
//			dto.setCallOutConnectCount4(String.valueOf(b4));
//			dto.setCallOutCount5(String.valueOf(a5));
//			dto.setCallOutConnectCount5(String.valueOf(b5));
//			dto.setCallOutCount6(String.valueOf(a6));
//			dto.setCallOutConnectCount6(String.valueOf(b6));
//			dto.setCallOutCount7(String.valueOf(a7));
//			dto.setCallOutConnectCount7(String.valueOf(b7));
			dto.setCallOutCount8(String.valueOf(a8));
			dto.setCallOutConnectCount8(String.valueOf(b8));
			dto.setCallOutCount9(String.valueOf(a9));
			dto.setCallOutConnectCount9(String.valueOf(b9));
			dto.setCallOutCount10(String.valueOf(a10));
			dto.setCallOutConnectCount10(String.valueOf(b10));
			dto.setCallOutCount11(String.valueOf(a11));
			dto.setCallOutConnectCount11(String.valueOf(b11));
			dto.setCallOutCount12(String.valueOf(a12));
			dto.setCallOutConnectCount12(String.valueOf(b12));
			dto.setCallOutCount13(String.valueOf(a13));
			dto.setCallOutConnectCount13(String.valueOf(b13));
			dto.setCallOutCount14(String.valueOf(a14));
			dto.setCallOutConnectCount14(String.valueOf(b14));
			dto.setCallOutCount15(String.valueOf(a15));
			dto.setCallOutConnectCount15(String.valueOf(b15));
			dto.setCallOutCount16(String.valueOf(a16));
			dto.setCallOutConnectCount16(String.valueOf(b16));
			dto.setCallOutCount17(String.valueOf(a17));
			dto.setCallOutConnectCount17(String.valueOf(b17));
			dto.setCallOutCount18(String.valueOf(a18));
			dto.setCallOutConnectCount18(String.valueOf(b18));
			dto.setCallOutCount19(String.valueOf(a19));
			dto.setCallOutConnectCount19(String.valueOf(b19));
			dto.setCallOutCount20(String.valueOf(a20));
			dto.setCallOutConnectCount20(String.valueOf(b20));
			dto.setCallOutCount21(String.valueOf(a21));
			dto.setCallOutConnectCount21(String.valueOf(b21));
//			dto.setCallOutCount22(String.valueOf(a22));
//			dto.setCallOutConnectCount22(String.valueOf(b22));
//			dto.setCallOutCount23(String.valueOf(a23));
//			dto.setCallOutConnectCount23(String.valueOf(b23));
			
			dto.setCallOutConnectDuration(DateUtil.timeAdd(timelen.getCallOutConnectDuration(),dto.getCallOutConnectDuration()));
//			dto.setCallOutConnectDuration1(DateUtil.timeAdd(timelen.getCallOutConnectDuration1(),dto.getCallOutConnectDuration1()));
//			dto.setCallOutConnectDuration2(DateUtil.timeAdd(timelen.getCallOutConnectDuration2(),dto.getCallOutConnectDuration2()));
//			dto.setCallOutConnectDuration3(DateUtil.timeAdd(timelen.getCallOutConnectDuration3(),dto.getCallOutConnectDuration3()));
//			dto.setCallOutConnectDuration4(DateUtil.timeAdd(timelen.getCallOutConnectDuration4(),dto.getCallOutConnectDuration4()));
//			dto.setCallOutConnectDuration5(DateUtil.timeAdd(timelen.getCallOutConnectDuration5(),dto.getCallOutConnectDuration5()));
//			dto.setCallOutConnectDuration6(DateUtil.timeAdd(timelen.getCallOutConnectDuration6(),dto.getCallOutConnectDuration6()));
//			dto.setCallOutConnectDuration7(DateUtil.timeAdd(timelen.getCallOutConnectDuration7(),dto.getCallOutConnectDuration7()));
			dto.setCallOutConnectDuration8(DateUtil.timeAdd(timelen.getCallOutConnectDuration8(),dto.getCallOutConnectDuration8()));
			dto.setCallOutConnectDuration9(DateUtil.timeAdd(timelen.getCallOutConnectDuration9(),dto.getCallOutConnectDuration9()));
			dto.setCallOutConnectDuration10(DateUtil.timeAdd(timelen.getCallOutConnectDuration10(),dto.getCallOutConnectDuration10()));
			dto.setCallOutConnectDuration11(DateUtil.timeAdd(timelen.getCallOutConnectDuration11(),dto.getCallOutConnectDuration11()));
			dto.setCallOutConnectDuration12(DateUtil.timeAdd(timelen.getCallOutConnectDuration12(),dto.getCallOutConnectDuration12()));
			dto.setCallOutConnectDuration13(DateUtil.timeAdd(timelen.getCallOutConnectDuration13(),dto.getCallOutConnectDuration13()));
			dto.setCallOutConnectDuration14(DateUtil.timeAdd(timelen.getCallOutConnectDuration14(),dto.getCallOutConnectDuration14()));
			dto.setCallOutConnectDuration15(DateUtil.timeAdd(timelen.getCallOutConnectDuration15(),dto.getCallOutConnectDuration15()));
			dto.setCallOutConnectDuration16(DateUtil.timeAdd(timelen.getCallOutConnectDuration16(),dto.getCallOutConnectDuration16()));
			dto.setCallOutConnectDuration17(DateUtil.timeAdd(timelen.getCallOutConnectDuration17(),dto.getCallOutConnectDuration17()));
			dto.setCallOutConnectDuration18(DateUtil.timeAdd(timelen.getCallOutConnectDuration18(),dto.getCallOutConnectDuration18()));
			dto.setCallOutConnectDuration19(DateUtil.timeAdd(timelen.getCallOutConnectDuration19(),dto.getCallOutConnectDuration19()));
			dto.setCallOutConnectDuration20(DateUtil.timeAdd(timelen.getCallOutConnectDuration20(),dto.getCallOutConnectDuration20()));
			dto.setCallOutConnectDuration21(DateUtil.timeAdd(timelen.getCallOutConnectDuration21(),dto.getCallOutConnectDuration21()));
//			dto.setCallOutConnectDuration22(DateUtil.timeAdd(timelen.getCallOutConnectDuration22(),dto.getCallOutConnectDuration22()));
//			dto.setCallOutConnectDuration23(DateUtil.timeAdd(timelen.getCallOutConnectDuration23(),dto.getCallOutConnectDuration23()));

		}
		/*dto.setCallOutCount(callRecord.getCallOutCount()+dto.getCallOutCount());
			dto.setCallOutConnectCount(callRecord.getCallOutConnectCount()+dto.getCallOutConnectCount());
			dto.setCallOutConnectDuration(DateUtil.timeAdd(callRecord.getCallOutConnectDuration(),dto.getCallOutConnectDuration()));*/
		queryForPage.setRows(list2);
		queryForPage.setTotal(list2.size());
//		dto.setBridgeDurationDayAvg("—");
//		dto.setCallOutConnectDurationAvg("—");
		dto.setCallOutConnectRate("—");
//		dto.setBridgeDurationDayAvg1("—");
//		dto.setCallOutConnectDurationAvg1("—");
//		dto.setCallOutConnectRate1("—");
//		dto.setBridgeDurationDayAvg2("—");
//		dto.setCallOutConnectDurationAvg2("—");
//		dto.setCallOutConnectRate2("—");
//		dto.setBridgeDurationDayAvg3("—");
//		dto.setCallOutConnectDurationAvg3("—");
//		dto.setCallOutConnectRate3("—");
//		dto.setBridgeDurationDayAvg4("—");
//		dto.setCallOutConnectDurationAvg4("—");
//		dto.setCallOutConnectRate4("—");
//		dto.setBridgeDurationDayAvg5("—");
//		dto.setCallOutConnectDurationAvg5("—");
//		dto.setCallOutConnectRate5("—");
//		dto.setBridgeDurationDayAvg6("—");
//		dto.setCallOutConnectDurationAvg6("—");
//		dto.setCallOutConnectRate6("—");
//		dto.setBridgeDurationDayAvg7("—");
//		dto.setCallOutConnectDurationAvg7("—");
//		dto.setCallOutConnectRate7("—");
		dto.setBridgeDurationDayAvg8("—");
		dto.setCallOutConnectDurationAvg8("—");
		dto.setCallOutConnectRate8("—");
		dto.setBridgeDurationDayAvg9("—");
		dto.setCallOutConnectDurationAvg9("—");
		dto.setCallOutConnectRate9("—");
		dto.setBridgeDurationDayAvg10("—");
		dto.setCallOutConnectDurationAvg10("—");
		dto.setCallOutConnectRate10("—");
		dto.setBridgeDurationDayAvg11("—");
		dto.setCallOutConnectDurationAvg11("—");
		dto.setCallOutConnectRate11("—");
		dto.setBridgeDurationDayAvg12("—");
		dto.setCallOutConnectDurationAvg12("—");
		dto.setCallOutConnectRate12("—");
		dto.setBridgeDurationDayAvg13("—");
		dto.setCallOutConnectDurationAvg13("—");
		dto.setCallOutConnectRate13("—");
		dto.setBridgeDurationDayAvg14("—");
		dto.setCallOutConnectDurationAvg14("—");
		dto.setCallOutConnectRate14("—");
		dto.setBridgeDurationDayAvg15("—");
		dto.setCallOutConnectDurationAvg15("—");
		dto.setCallOutConnectRate15("—");
		dto.setBridgeDurationDayAvg16("—");
		dto.setCallOutConnectDurationAvg16("—");
		dto.setCallOutConnectRate16("—");
		dto.setBridgeDurationDayAvg17("—");
		dto.setCallOutConnectDurationAvg17("—");
		dto.setCallOutConnectRate17("—");
		dto.setBridgeDurationDayAvg18("—");
		dto.setCallOutConnectDurationAvg18("—");
		dto.setCallOutConnectRate18("—");
		dto.setBridgeDurationDayAvg19("—");
		dto.setCallOutConnectDurationAvg19("—");
		dto.setCallOutConnectRate19("—");
		dto.setBridgeDurationDayAvg20("—");
		dto.setCallOutConnectDurationAvg20("—");
		dto.setCallOutConnectRate20("—");
//		dto.setBridgeDurationDayAvg21("—");
//		dto.setCallOutConnectDurationAvg21("—");
		dto.setCallOutConnectRate21("—");
//		dto.setBridgeDurationDayAvg22("—");
//		dto.setCallOutConnectDurationAvg22("—");
//		dto.setCallOutConnectRate22("—");
//		dto.setBridgeDurationDayAvg23("—");
//		dto.setCallOutConnectDurationAvg23("—");
//		dto.setCallOutConnectRate23("—");
		dto.setEmpName("合计");
		dto.setCno("—");
		dto.setTime("—");
		footer.add(dto);
		queryForPage.setFooter(footer);
		return queryForPage;
	}
	@Override
	protected String getBaseName() {
		return "collection/timeOutStatistics/";
	}
	
	public static String[] bbb(String time, 
			   String count, 
			   String connect,
			   String callOutConnectDuration, 
			   String callOutConnectDurationAvg, 
			   String callOutConnectRate, 
			   String bridgeDurationDayAvg, 
			   String cno, 
			   String emp) {

		String a1[] = time.split(",");
		String b1[] = count.split(",");
		String c1[] = connect.split(",");
		String d1[] = callOutConnectDuration.split(",");
		String e1[] = callOutConnectDurationAvg.split(",");
		String f1[] = callOutConnectRate.split(",");
		String g1[] = bridgeDurationDayAvg.split(",");
		String t[] = new String[146];

		for (int j = 0; j < t.length; j++) {
			t[j] = "0";
			for (int i = 0; i < 24; i++) {
				t[6*i+2]="00:00:00";
				t[6*i+3]="00:00:00";
				t[6*i+5]="00:00:00";
			}
			for (int i = 0; i < a1.length; i++) {
				int ind = 0;
				String index = a1[i];
				ind = Integer.parseInt(index);
				t[ind * 6] = b1[i];
				t[ind * 6 + 1] = c1[i];
				t[ind * 6 + 2] = d1[i];
				t[ind * 6 + 3] = e1[i];
				t[ind * 6 + 4] = f1[i];
				t[ind * 6 + 5] = g1[i];
				t[144] = cno;
				t[145] = emp;

			}
		}
		return t;
	
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 24; i++) {
//			System.out.println("private String callOutCount"+i+";");
//			System.out.println("private String callOutConnectCount"+i+";");
//			System.out.println("private String callOutConnectDuration"+i+";");
//			System.out.println("private String callOutConnectDurationAvg"+i+";");
//			System.out.println("private String callOutConnectRate"+i+";");
//			System.out.println("private String bridgeDurationDayAvg"+i+";");
//			
//			System.out.println("callRecord.setCallOutCount"+i+"(model["+(i*6)+"]);");
//			System.out.println("callRecord.setCallOutConnectCount"+i+"(model["+(i*6+1)+"]);");
//			System.out.println("callRecord.setCallOutConnectDuration"+i+"(model["+(i*6+2)+"]);");
//			System.out.println("callRecord.setCallOutConnectDurationAvg"+i+"(model["+(i*6+3)+"]);");
//			System.out.println("callRecord.setCallOutConnectRate"+i+"(model["+(i*6+4)+"]);");
//			System.out.println("callRecord.setBridgeDurationDayAvg"+i+"(model["+(i*6+5)+"]);");
//			
//			System.out.println("dto.setBridgeDurationDayAvg"+i+"(\"—\");");
//			System.out.println("dto.setCallOutConnectDurationAvg"+i+"(\"—\");");
//			System.out.println("dto.setCallOutConnectRate"+i+"(\"—\");");
			
		//System.out.println("dto.setCallOutConnectDuration(DateUtil.timeAdd(timelen.getCallOutConnectDuration"+i+"(),dto.getCallOutConnectDuration"+i+"()));");	
			
			
		}
	}
}
