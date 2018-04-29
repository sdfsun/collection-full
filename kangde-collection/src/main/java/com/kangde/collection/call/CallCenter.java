package com.kangde.collection.call;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kangde.commons.CoreConst;
import com.kangde.sys.security.util.MD5Util;

public class CallCenter extends CallModel{
	
	private final static Map map = new HashMap();  
	private final static String callUrl;
	private final static String callRUrl;
	private final static String enterpriseId;
	private final static String hotline;
	private final static String sign;
	private static volatile CallCenter instance = null;  
	
	public static CallCenter getInstance() {   
	    if(instance==null){  
             synchronized (CallCenter.class) {  
                 if(instance==null)  
                     instance = new CallCenter();  
            }  
         }  
         return instance;  
     }  

	static {  
		callUrl = CoreConst.getString("call.url");
		callRUrl = CoreConst.getString("callR.url");
		enterpriseId = CoreConst.getString("enterpriseId");
		hotline =  CoreConst.getString("hotline");
		sign = "&";
	    map.put("0", "呼叫座席成功， 请等待...");  
		map.put("1", "呼叫座席失败");  
		map.put("2", "参数不正确");  
		map.put("3", "用户验证没有通过");  
		map.put("4", "账号被停用");  
		map.put("5", "资费不足");  
		map.put("6", "指定的业务尚未开通");  
		map.put("7", "电话号码不正确");  
		map.put("8", "座席工号（cno）不存在");  
		map.put("9", "座席状态不为空闲，可能未登录或忙");  
		map.put("10", "其他错误");  
		map.put("11", "电话号码为黑名单");  
		map.put("12", "座席不在线");  
		map.put("13", "座席正在通话/呼叫中");  
		map.put("14", "外显号码不正确");  
	}  
	
	/**
	 * 外呼电话
	 * @return
	 */
	public String call(){
        StringBuilder sb = new StringBuilder();
        sb.append("enterpriseId=").append(enterpriseId).append(sign);
        sb.append("hotline=").append(hotline).append(sign);
        sb.append("cno=").append(this.getCno()).append(sign);
        sb.append("pwd=").append(this.getPwd()).append(sign);
        sb.append("customerNumber=").append(this.getCustomerNumber());
        
	    String result = CallCenter.sendGet(callUrl, sb.toString());
	    JSONObject json = JSONObject.parseObject(result);
	    String res = json.getString("res");
	    String uniqueId = json.getString("uniqueId");
	    //System.out.println(res+","+uniqueId+","+map.get(res));
	    System.out.println("调用结果:"+json);
	    System.out.println("参数:"+sb.toString());
	    return map.get(res).toString(); 
	}
	
	/**
	 * 外呼电话记录
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<CallRecordModel> callR() throws UnsupportedEncodingException{
		List<CallRecordModel> model = null;
		String sr=CallCenter.sendPost(callRUrl, this.sb().toString());
		System.out.println("sr----------"+sr);   
	    JSONObject array = JSONObject.parseObject(sr);
	    if(array.getString("result").equals("success")){
	    	//System.out.println(array.getJSONObject("msg").getString("data"));
	    	model = CallCenter.getDTOList(array.getJSONObject("msg").getString("data"), CallRecordModel.class);
	    }
	    return model;
		
	}
	
	/**
	 * 外呼电话记录查询条件
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private StringBuilder sb() throws UnsupportedEncodingException{
		StringBuilder sb = new StringBuilder();
	    sb.append("enterpriseId=").append(enterpriseId).append(sign);
        sb.append("hotline=").append(hotline).append(sign);
        sb.append("userName=").append(this.getUserName()).append(sign);
        sb.append("pwd=").append(MD5Util.md5(this.getPwd()+this.getSeed())).append(sign);
        sb.append("seed=").append(this.getSeed()).append(sign);
        /**
         * 电话唯一标识
         */
        if(StringUtils.isNotBlank(this.getUniqueId())){
        	sb.append("uniqueId=").append(this.getUniqueId()).append(sign);
	    }
        /**
         * 呼叫类型 为空表示全部，3表示点击外呼,4表示预览外呼,5表示预测外呼,6表示主叫外呼,7表示自助录音,8表示传真发送10预约回呼
         */
        if(StringUtils.isNotBlank(this.getCallType())){
        	sb.append("callType=").append(this.getCallType()).append(sign);
	    }
        /**
         * 任务Id callType为预览或预测外呼时才有此条件
         */
        if(StringUtils.isNotBlank(this.getTaskId())){
        	sb.append("taskId=").append(this.getTaskId()).append(sign);
	    }
        /**
         * 为空表示全部，1表示客户未接听, 2表示座席未接听，3表示双方接听
         */
        if(StringUtils.isNotBlank(this.getStatus())){
        	sb.append("status=").append(this.getStatus()).append(sign);
        }
    	/**
    	 * 注：title为动态列,匹配以下字段
    	 * qno表示队列号(多个之间用英文逗号隔开)，
    	 * customer_number表示客户电话，
    	 * cno表示座席工号(多个之间用英文逗号隔开)，
    	 * number_trunk表示中继号码(多个之间用英文逗号隔开)，
    	 * client_number表示座席电话
    	 */
        if(StringUtils.isNotBlank(this.getTitle())){
        	sb.append("title=").append(this.getTitle()).append(sign);
        }
        /**
         * 查询条件,对应title的值
         */
        if(StringUtils.isNotBlank(this.getValue())){
        	sb.append("value=").append(java.net.URLEncoder.encode(this.getValue(), "GBK")).append(sign);
        }
        /**
         * 客户电话归属省份
         */
        if(StringUtils.isNotBlank(this.getProvince())){
        	sb.append("province=").append(java.net.URLEncoder.encode(this.getProvince(), "GBK")).append(sign);
	    }
        /**
         * 客户电话归属城市
         */
        if(StringUtils.isNotBlank(this.getCity())){
        	sb.append("city=").append(java.net.URLEncoder.encode(this.getCity(), "GBK")).append(sign);
	    }
        /**
         * 开始时间 格式“yyyy-mm-dd hh:mm:ss”
         */
        if(StringUtils.isNotBlank(this.getStartTime())){
        	sb.append("startTime=").append(java.net.URLEncoder.encode(this.getStartTime(), "GBK")).append(sign);
	    }
        /**
         * 结束时间 格式“yyyy-mm-dd hh:mm:ss”
         */
        if(StringUtils.isNotBlank(this.getEndTime())){
        	sb.append("endTime=").append(java.net.URLEncoder.encode(this.getEndTime(), "GBK")).append(sign);
        }
        /**
         * 查询的时间类型 默认取值为 1 。1电话进入系统时间，2 记录创建时间
         */
        if(StringUtils.isNotBlank(this.getTimeType())){
        	sb.append("timeType=").append(this.getTimeType()).append(sign);
        }
        /**
         * 从第几条开始取 正整数，大于等于0，默认0
         */
        if(StringUtils.isNotBlank(this.getStart())){
        	sb.append("start=").append(this.getStart()).append(sign);
        }
        /**
         * 需要取出的数据条数 正整数，大于0，最大不能超过1000，默认10
         */
        if(StringUtils.isNotBlank(this.getLimit())){
        	sb.append("limit=").append(this.getLimit()).append(sign);
        }
        /**
         * 用户自定义参数
         */
        if(StringUtils.isNotBlank(this.getUserField())){
        	sb.append("userField=").append(this.getUserField()).append(sign);
        }
        
        return sb;
	}
	
	
    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
        	
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    /** 
    * 从一个JSON数组得到一个java对象数组
    * @param object 
    * @param clazz 
    * @param map 
    * @return 
    */  
    public static Object[] getDTOArray(String jsonString, Class clazz){     
        JSONArray array = JSONArray.parseArray(jsonString);     
        Object[] obj = new Object[array.size()];     
        for(int i = 0; i < array.size(); i++){     
        	JSONObject jsonObject = array.getJSONObject(i);     
            obj[i] = JSON.toJavaObject(jsonObject, clazz);     
        }     
        return obj;     
    }    
    
    /** 
     * 从一个JSON数组得到一个java对象集合 
     * @param object 
     * @param clazz 
     * @return 
     */  
    public static List getDTOList(String jsonString, Class clazz){   
        JSONArray array = JSONArray.parseArray(jsonString);  
        List list = new ArrayList();  
        for(Iterator iter = array.iterator(); iter.hasNext();){  
        	JSONObject jsonObject = (JSONObject)iter.next();  
            list.add(JSONObject.toJavaObject(jsonObject, clazz));  
        }  
        return list;  
    }  
    
    public static void main(String[] args) throws UnsupportedEncodingException {
    	CallCenter call = CallCenter.getInstance();
    	call.setCno("2627");
    	call.setPwd("ef73781effc5774100f87fe2f437a435");
    	
    	call.setCustomerNumber("13661328957");
    	String result = call.call();
  	    System.out.println("result===="+result);
    	
    	//call.setPwd(MD5Util.md5("123456QWE"));
    	//call.setSeed("test");
    	//call.setUserName("admin");
    	//call.setStartTime("2016-08-01 00:00:00");
    	//call.setEndTime("2016-09-01 00:00:00");
    	//call.setStatus("3");
    	//call.setTitle("customer_number");
    	//call.setValue("15711121933");
    	//List<CallRecordModel> result = call.callR();
  	    //System.out.println("result===="+result);
  	    //for(CallRecordModel model:result){
  	    //	System.out.println("model===="+model.getCustomerCity());
  	    //}
  	    
  	  //http://bj-out-2.ccic2.com/voices/record/20160629/3002679-20160629182521-15711121933-01042932918-record-waihu2CTI3-1467195921.103329.mp3?hotline=4006777966&enterpriseId=3002679&userName=admin&pwd=9a08155745d3d7cf84af9e4d5457dd9d&seed=test
      //String sr=CallCenter.sendPost("http://bj-out-2.ccic2.com/interfaceAction/cdrObInterface!listCdrOb.action", "enterpriseId=3002679&hotline=4006777966&userName=admin&pwd="+password+"&seed="+seed+"&startTime=2016-06-28 00:00:00&endTime=2016-06-29 00:00:00&status=3&title=customer_number&value="+java.net.URLEncoder.encode("07174207633", "GBK")+"");	

    	//System.out.println(MD5Util.md5("1234abcd", "ef73781effc5774100f87fe2f437a435"));
    }
    
}
