package com.atguigu.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

/** 
* @author 作者 Dafeng
* @version 创建时间：2019年1月31日 上午11:18:23 
* 类说明 
* 使用Spirng测试模块提供的测试请求功能,测试crud的正确性
*/

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	//传入springmvc的IOC
	@Autowired
	WebApplicationContext context;
	//模拟mvc请求,获取到处理结果
	MockMvc mockMvc;
	
	@Before
	public void initMockMvc() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到返回值
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
		
		//请求成功以后,请求域中会有pageInfo,我们可以取出pageInfo进行验证
		
		MockHttpServletRequest request = result.getRequest();
		PageInfo attribute = (PageInfo)request.getAttribute("pageInfo");
		System.out.println("当前页码:"+attribute.getPageNum());
		System.out.println("总页码:"+attribute.getPages());
		System.out.println("总记录数:"+attribute.getTotal());
		System.out.println("在页面需要连续显示的页码");
		int[] nums = attribute.getNavigatepageNums();
		for (int i : nums) {
			System.out.println(" "+i);
		}
		
		//测试获取员工信息
		List<Employee> list = attribute.getList();
		for (Employee employee : list) {
			System.out.println("ID:"+employee.getEmpId()+"==>Name:"+employee.getEmpName());
		}
	}
	
}
