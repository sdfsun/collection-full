package com.kangde.collection.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangde.collection.exception.HolidayException;
import com.kangde.collection.mapper.HolidayModelMapper;
import com.kangde.collection.model.HolidayModel;
import com.kangde.collection.service.HolidayService;


@Service
@Transactional
public class HolidayServiceImpl implements HolidayService {
	
	@Autowired
	private  HolidayModelMapper holidayModelMapper;
	private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/*
	  * <p>Title: queryAll</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.kangde.collection.service.impl.HolidayService#queryAll()
	  */
	
	
	@Override
	public  List<HolidayModel> queryAll(){
		 List<HolidayModel> list = holidayModelMapper.queryAll();
		return list;
	}
	
	 /*
	  * <p>Title: addDateByWorkDay</p>
	  * <p>Description: </p>
	  * @param date
	  * @param day
	  * @return
	  * @see com.kangde.collection.service.impl.HolidayService#addDateByWorkDay(java.util.Date, int)
	  */
	
	
	@Override
	public  Calendar addDateByWorkDay(Date date,int day){
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);//设置当前时间
			 while(true){
				 calendar.add(Calendar.DAY_OF_MONTH, 1);
				 //获取下一天
				 String nextday=sdf.format(calendar.getTime());
				
				 if(isHoliday(nextday)){
					 //System.out.println("nextday:holiday>>>"+nextday);
				 }else{
					// System.out.println("nextday:workday>>>"+nextday);
					 day--;
				 }
				 if(day<=0){
					return calendar;
				 }
			 }
	 }

	 private  boolean isHoliday(String  day){
		 return this.queryAll().contains(day);
	 }

	/*
	  * <p>Title: delDay</p>
	  * <p>Description: </p>
	  * @param id
	  * @see com.kangde.collection.service.impl.HolidayService#delDay(int)
	  */
	
	
	@Override
	public void delDay(int id) {
		holidayModelMapper.deleteByPrimaryKey(id);
	}

	/*
	  * <p>Title: saveholidays</p>
	  * <p>Description: </p>
	  * @param records
	  * @throws HolidayException
	  * @see com.kangde.collection.service.impl.HolidayService#saveholidays(java.lang.String[])
	  */
	
	
	@Override
	@Transactional(rollbackFor=java.lang.Exception.class)
	public void saveholidays(String[] records) throws HolidayException {
		for(String record: records){
			String day=record.trim();
			if(StringUtils.isNotBlank(day)){
				
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				df.setLenient(false);//这个的功能是不把1996-13-3 转换为1997-1-3
				try
				{ 
					df.parse(day); 
					HolidayModel selectByDay = holidayModelMapper.selectByDay(day);
					if(selectByDay==null){
						HolidayModel model=new HolidayModel();
						model.setHoliday(day);
						holidayModelMapper.insert(model);
					}
				}
				catch(Exception e)
				{ 
					throw new HolidayException("非法格式合法【"+day+"】");
				} 
				
				
				
				
			}
			
		}
		
	}
	/*
	  * <p>Title: clearholiday</p>
	  * <p>Description: </p>
	  * @see com.kangde.collection.service.impl.HolidayService#clearholiday()
	  */
	
	
	@Override
	public void clearholiday() {
		holidayModelMapper.deleteAll();
	}

}
