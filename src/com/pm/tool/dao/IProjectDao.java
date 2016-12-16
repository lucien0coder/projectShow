package com.pm.tool.dao;

import java.util.List;

import com.pm.tool.entity.Project;
import com.pm.tool.entity.Task;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: IProjectDao.java
 * @Date: 14-3-20
 * @Time: 下午7:17
 */
public interface IProjectDao {

	List<Project> fullProjectList(String userAccount);

	List fullTaskList(String userAccount, String timeType,
		 String pName, String userName, String tName);

}
