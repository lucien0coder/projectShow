package com.pm.tool.dao.impl;

import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.pm.tool.base.BaseDao;
import com.pm.tool.dao.IProjectDao;
import com.pm.tool.entity.Distribution;
import com.pm.tool.entity.Project;
import com.pm.tool.entity.Task;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: ProjectDaoImpl.java
 * @Date: 14-3-20
 * @Time: 下午7:19
 */
@SuppressWarnings("unchecked")
public class ProjectDaoImpl extends BaseDao implements IProjectDao {

	public ProjectDaoImpl(){}
	public ProjectDaoImpl(Connection conn){
		super(conn);
	}

	@Override
	public List<Project> fullProjectList(String userAccount) {
		getConn();

//		String sql = "select p.*,e.employee_name from project p INNER JOIN hr_employee e ON p.pLeaderAccount = e.useraccount order by pCreateTime desc";
		String sql = "select * from project order by pCreateTime desc";
		List<Project> projects = null;
		try {
			conn.setAutoCommit(false);
			projects = findWithSql(sql, Project.class);
			int bottom = 0;
			for (Project project : projects) {
				project.setEvents(obtainTask(project.getId(), 2, userAccount));
				project.setBottom(bottom + "");
				bottom += 10;
			}
			conn.commit();
		} catch (Exception e) {
			rollBack();
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return projects;
	}

	private List<Task> obtainTask(String projectId, int type,
			String userAccount) throws Exception {
		String sql = "";
		List<Task> tasks = null;
		if (type == 0) {
			sql = "select t.tId, pId, tName, tDesc, tCreateTime, "
					+ "tPlanStartTime, tPlanFinishTime, tFactStartTime, "
					+ "tFactFinishTime,tExpectFinishTime, tDistributeStatus, tDevelopStatus, "
					+ "tProgressRate from task t left join distribution d "
					+ "on t.tId = d.tId where d.tId is not null and "
					+ "dStatus = 'accept' and t.pId=? order by "
					+ "tCreateTime desc";
		} else if (type == 1) {
			sql = "select t.tId, pId, tName, tDesc, tCreateTime, "
					+ "tPlanStartTime, tPlanFinishTime,tExpectFinishTime, tFactStartTime, "
					+ "tFactFinishTime, tDistributeStatus, tDevelopStatus, "
					+ "tProgressRate from task t left join distribution d "
					+ "on t.tId = d.tId where dStatus <> 'accept' or dStatus"
					+ " is null and t.pId=? order by tCreateTime desc";
		} else if (type == 2 || type == 3) {
			sql = "select * from task where pId=? order by tCreateTime desc";
		}
		tasks = findWithSql(sql, Task.class, new Object[] { projectId },
				new int[] { Types.INTEGER });
		sql = "select * from distribution where tId=?";
		for (Task task : tasks) {
			task.setIsAll(true);
			List<Distribution> distributions = findWithSql(sql,
					Distribution.class, new Object[] { task.gettId() },
					new int[] { Types.INTEGER });
			List<String> distribute = new ArrayList<String>();

			/** 0 已领取； 1 未领取； 2 其它 */
			for (Distribution distribution : distributions) {
				String account = distribution.getUserAccount() == null ? ""
						: distribution.getUserAccount();
				String name = distribution.getUserName() == null ? ""
						: distribution.getUserName();
				String user = account + "-" + name;
				distribute.add(user);
			}
			task.setDistribute(distribute);
		} 
		closeAll();
		return tasks;
	}
	
	
	@Override
	public List fullTaskList(String userAccount, String timeType,
			 String pName,String userName, String tName) {
		String sql = "";
		List tasks = null;
		try {
			if (timeType.equals("today")){
				sql = "SELECT d.userAccount,d.userName,p.pName,t.* from task t LEFT JOIN distribution d ON t.tId = d.tId " +
						"LEFT JOIN project p ON t.pId = p.pId WHERE DATEDIFF(tExpectFinishTime,NOW()) = 0 " +
						"and p.pName like ? " +
						"and d.userName like ? " +
						"and t.tName like ? "+
						"order by tExpectFinishTime";
				tasks = findWithSql(sql, new Object[] {"%"+pName+"%","%"+userName+"%","%"+tName+"%" },new int[] { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR });
			}else if (timeType.equals("week")){
				sql = "SELECT d.userAccount,d.userName,p.pName,t.* from task t LEFT JOIN distribution d ON t.tId = d.tId " +
						"LEFT JOIN project p ON t.pId = p.pId WHERE YEARWEEK( date_format(  tExpectFinishTime,'%Y-%m-%d' ),1 ) = YEARWEEK( now( ),1 ) " +
						"and p.pName like ? " +
						"and d.userName like ? " +
						"and t.tName like ? "+
						"order by tExpectFinishTime";
				tasks = findWithSql(sql, new Object[] {"%"+pName+"%","%"+userName+"%","%"+tName+"%" },new int[] { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR });
			}else{
				sql =  "SELECT d.userAccount,d.userName,p.pName,t.* from task t LEFT JOIN distribution d ON t.tId = d.tId " +
						"LEFT JOIN project p ON t.pId = p.pId"+
						"order by tExpectFinishTime";
				tasks = findWithSql(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
		closeAll();
		return tasks;
	}

}
