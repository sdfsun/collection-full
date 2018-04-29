package com.kangde.collection.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kangde.DictionaryConst;
import com.kangde.collection.model.AddressModel;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.sys.model.DictionaryModel;
import com.kangde.sys.service.DictionaryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class AddressServiceTest {
	@Autowired
	private AddressService addressService;
	@Autowired
	private DictionaryService dictionaryService;

	@Test
	public void save() {
		AddressModel model = new AddressModel();
		model.setId(UUIDUtil.UUID32());
		 
		model.setAge(20);
		model.setAreaId1("甘肃省");
		model.setAreaId2("兰州市");
		model.setAreaId3("静宁县");
		model.setAddress("不告诉你");
		model.setCaseId("XXXXXXXXXXXX");
		model.setCreateTime(new Date());
		model.setCreateEmpId("admin");
		addressService.save(model);
	}

	@Test
	public void query() {
	 List<AddressModel> list = addressService.queryByCaseId("5115255f7f034c49a3b17263243bc3b5");
	 System.out.println(list);
	}
	
	
	@Test
	public void testdic() {
		List<DictionaryModel> list = dictionaryService.findSubsByPath(DictionaryConst.ADDRESS_TYPE);
		
		System.out.println(list);
	}

}
