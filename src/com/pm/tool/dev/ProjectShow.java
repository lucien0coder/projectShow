package com.pm.tool.dev;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fy.wetoband.bean.RunToolParam;
import com.fy.wetoband.tool.manager.Tool;
import com.pm.tool.dao.IProjectDao;
import com.pm.tool.dao.impl.ProjectDaoImpl;
import com.pm.tool.entity.Project;
import com.pm.tool.entity.Task;
import com.pm.tool.entity.UserConfig;

public class ProjectShow extends Tool {

	@Override
	public void act(HttpServletRequest request, HttpServletResponse response) {
		Object result = this.procedure(request);
		flushdata(response, result);
	}
	
	public Object procedure(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String action = (String) request.getParameter("toolAction");
		Connection conn = this.getConnectionByBViewID();  //数据库连接  关闭在其他地方处理
		if ("all".equals(action)) {
			List<Project> projects = listProjects(conn);
			Map<String,Object> allProjects = new HashMap<String,Object>();
			Integer size = 0;
			if(!projects.isEmpty()){
				size = projects.size();
			}
			allProjects.put("total", size);
			allProjects.put("projectList", projects);
			return JSONObject.fromObject(allProjects);
			
		}else if("configAndProjects".equals(action)){
			Map<String,Object> configAndPro = new HashMap<String,Object>();
			List<Project> projects = listProjects(conn);
			
			UserConfig uc = new UserConfig();
			uc.setMoniter(4);
			uc.setType("0");
			uc.setTypeExtend("80;40;100;90");
			
			Integer size = 0;
			if(!projects.isEmpty()){
				size = projects.size();
			}
			configAndPro.put("total", size);
			configAndPro.put("projectList", projects);
			configAndPro.put("config", uc);
			return JSONObject.fromObject(configAndPro);
		}else if("listTasks".equals(action)){
			String timeType = request.getParameter("timeType");// 当天“today”或者本周“week”
			String pName = request.getParameter("pName");// 项目
			String userName = request.getParameter("userName");// 任务承担者
			String tName = request.getParameter("tName");// 任务名称
			List tasks = listTasks(conn,timeType,pName,userName,tName);
			Map<String,Object> allTasks = new HashMap<String,Object>();
			Integer size = 0;
			if(!tasks.isEmpty()){
				size = tasks.size();
			}
			allTasks.put("total", size);
			allTasks.put("TaskList", tasks);
			return JSONObject.fromObject(allTasks);
		}
		return "no action";
	}


	/** 获取任务列表的方法
	 * @param pId 
	 * @param dayTime
	 * @param weekTime
	 * @param tName 
	 * @param userName 
	 */
	private List listTasks(Connection conn, String timeType,
			String pName, String userName, String tName) {
		IProjectDao projectDao = new ProjectDaoImpl(conn);
		List fullTasks = projectDao.fullTaskList(this.getUserAccount(),timeType,pName,userName,tName);
		return fullTasks;
	}

	/** 获取项目的方法
	 *  @param 
	 *  @param 
	 */
	private List<Project> listProjects(Connection conn){
		IProjectDao projectDao = new ProjectDaoImpl(conn);
		List<Project> fullProjects = projectDao.fullProjectList(this.getUserAccount());
		return fullProjects;
	}
	
	/**
	 * 将数据冲到前台
	 * @param response
	 * @param obj
	 */
	private void flushdata(HttpServletResponse response, Object obj){
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.print(obj);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer != null){
				writer.close();
			}
		}
	}

	@Override
	public Map<Object, Object> toolMain(RunToolParam arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> toolMain(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
