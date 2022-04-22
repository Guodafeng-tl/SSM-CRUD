package com.atguigu.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.DepartmentService;

/** 
* @author ���� Dafeng
* @version ����ʱ�䣺2019��2��14�� ����1:16:48 
* ��˵�� 
* �����Ͳ����йص�����,��ȡ������Ϣ
*/

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	/*
	 * �������в�����Ϣ
	 */
	
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts() {
		List<Department> list=departmentService.getDepts();
		return Msg.success().add("depts", list); 
	}
	
}