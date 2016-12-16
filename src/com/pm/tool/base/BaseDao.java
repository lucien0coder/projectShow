package com.pm.tool.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fy.wetoband.tool.manager.Tool;

@SuppressWarnings("unchecked")
public class BaseDao {

    public final static String DATABASE = "pm2";
    public final static Boolean SHOW_SQL = false;
    public static Long HERE;
    public	Tool tool;   //这个是新规范提供的tool类，里面有con连接

    public	Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BaseDao(){}
    public BaseDao(Connection conn){
    	this.conn = conn;
    }
    
    /**
    *
    * @return 获得数据库连接
    * @throws SQLException
    * @throws ClassNotFoundException
    */
    protected Connection getConn() {
//        conn = ConnectionManager.getInstance().getConnectionByBvID(HERE);
//    	conn = tool.getConnectionByBViewID();
        return conn;
    }

    /**
    * 关闭数据库连接
    */
    protected void closeAll() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                System.out.println("关闭连接");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void rollBack() {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    * 执行增、删、改SQL语句
    *
    * @param sql
    *            sql语句
    * @param param
    *            值集
    * @param type
    *            值类型集
    * @return 受影响的行数
    */
	protected int updateOrDel(String sql, Object[] param, int[] type)
			throws SQLException {
		int rows = 0;
        pstmt = null;
        pstmt = conn.prepareStatement(sql);
        /*
        if (SHOW_SQL) {
            System.out.println(sql);
        }*/
        for (int i = 1; i <= param.length; i++) {
            pstmt.setObject(i, param[i - 1], type[i - 1]);
        }
        rows = pstmt.executeUpdate();
        return rows;
    }

    /**
    * 执行查询SQL语句
    *
    * @param sql
    *            sql语句
    * @param param
    *            值集
    * @param type
    *            值类型集
    * @return 结果集
    */
	protected List findWithSql(String sql, Class<?> clazz, Object[] param,
			int[] type) throws Exception {
		rs = null;
        pstmt = null;
        pstmt = conn.prepareStatement(sql);
        if (param != null && type != null) {
            for (int i = 1; i <= param.length; i++) {
                pstmt.setObject(i, param[i - 1], type[i - 1]);
            }
        }
        rs = pstmt.executeQuery();

        ResultSetMetaData rsm = rs.getMetaData();
        if (clazz == null) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<String, Object>();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    map.put(rsm.getColumnName(i), rs.getObject(rsm.getColumnName(i)));
                }
                list.add(map);
            }
           // if (SHOW_SQL) {
                //System.out.println(sql);
            //}
            return list;
        } else {
            List<Object> list = new ArrayList<Object>();
            Method[] ms = clazz.getMethods();
            while (rs.next()) {
                Object obj = clazz.newInstance();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    /*Field field = clazz.getDeclaredField(rsm.getColumnName(i));
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(rsm.getColumnName(i)));*/
                    String colName = rsm.getColumnName(i);
                    StringBuffer methodName = new StringBuffer("set");
                    Character character = colName.charAt(1);
                    if (Character.isUpperCase(character)) {
                        methodName.append(colName);
                    } else {
                        methodName.append(colName.substring(0, 1).toUpperCase())
                                .append(colName.substring(1));
                    }
                    // 循环读取所有方法
                    for (Method m : ms) {
                        // 列名和set方法名如果相同则调用该方法
                        if (methodName.toString().equals(m.getName())) {
                            Object val = rs.getObject(colName);
							if (val != null
									&& m.getParameterTypes()[0].getName()
											.equals("java.lang.String")
                                    && val instanceof Timestamp) {
                                int index = val.toString().lastIndexOf(".");
                                val = val.toString().substring(0, index);
							}
                            /*if (colName.equals("tPlanStartTime")) {
                                System.out.println("dsaf");
                            }*/
                            m.invoke(obj, val);
                        }
                    }

                }

                list.add(obj);
            }/*
            if (SHOW_SQL) {
                System.out.println(sql);
            }*/
            return list;
        }
    }

	protected List findWithSql(String sql) throws Exception {
		return findWithSql(sql, null, null, null);
	}

	protected List findWithSql(String sql, Object[] param, int[] type)
			throws Exception {
		return findWithSql(sql, null, param, type);
	}

	protected List findWithSql(String sql, Class<?> clazz)
			throws Exception {
		return findWithSql(sql, clazz, null, null);
	}
    
	 /**
	    * 执行查询SQL语句
	    *
	    * @param sql
	    *            sql语句
	    * @param param
	    *            值集
	    * @param type
	    *            值类型集
	    * @return 结果集
	    */
		protected List findWithSqlByPageIndex(String sql, Integer pageIndex,
				Integer pageSize, Object[] param, int[] type) throws Exception {
			rs = null;
	        pstmt = null;
	        pstmt = conn.prepareStatement(sql);
	        if (param != null && type != null) {
	            for (int i = 1; i <= param.length; i++) {
	                pstmt.setObject(i, param[i - 1], type[i - 1]);
	            }
	        }
	        rs = pstmt.executeQuery();
	        ResultSetMetaData rsm = rs.getMetaData();
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = null;
	        for(int k=1;k<1+pageSize*(pageIndex-1);k++){
	            rs.next();
	        }
	        for(int j=1+pageSize*(pageIndex-1); j<pageIndex*pageSize+1; j++) {
	            if(rs.next()){
	            map = new HashMap<String, Object>();
	            for (int i = 1; i <= rsm.getColumnCount(); i++) {
	                 map.put(rsm.getColumnName(i), rs.getObject(rsm.getColumnName(i)));
	            }
	            list.add(map);
	            }else{
	            	break;
	            	}
	            }/*
	            if (SHOW_SQL) {
	                System.out.println(sql);
	            }*/
	            return list;
		}
	
	
		/**
		    * 执行查询SQL语句
		    *
		    * @param sql
		    *            sql语句
		    * @param param
		    *            值集
		    * @param type
		    *            值类型集
		    * @return 结果集
		    */
			protected List findWithSqlBySearchMore(String sql, Class<?> clazz) throws Exception {
				rs = null;
		        pstmt = null;
		        pstmt = conn.prepareStatement(sql);
		        rs = pstmt.executeQuery();
		        ResultSetMetaData rsm = rs.getMetaData();
		        if (clazz == null) {
		            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		            Map<String, Object> map = null;
		            while (rs.next()) {
		                map = new HashMap<String, Object>();
		                for (int i = 1; i <= rsm.getColumnCount(); i++) {
		                    map.put(rsm.getColumnName(i), rs.getObject(rsm.getColumnName(i)));
		                }
		                list.add(map);
		            }/*
		            if (SHOW_SQL) {
		                System.out.println(sql);
		            }*/
		            return list;
		        } else {
		            List<Object> list = new ArrayList<Object>();
		            Method[] ms = clazz.getMethods();
		            while (rs.next()) {
		                Object obj = clazz.newInstance();
		                for (int i = 1; i <= rsm.getColumnCount(); i++) {
		                    /*Field field = clazz.getDeclaredField(rsm.getColumnName(i));
		                    field.setAccessible(true);
		                    field.set(obj, rs.getObject(rsm.getColumnName(i)));*/
		                    String colName = rsm.getColumnName(i);
		                    StringBuffer methodName = new StringBuffer("set");
		                    Character character = colName.charAt(1);
		                    if (Character.isUpperCase(character)) {
		                        methodName.append(colName);
		                    } else {
		                        methodName.append(colName.substring(0, 1).toUpperCase())
		                                .append(colName.substring(1));
		                    }
		                    // 循环读取所有方法
		                    for (Method m : ms) {
		                        // 列名和set方法名如果相同则调用该方法
		                        if (methodName.toString().equals(m.getName())) {
		                            Object val = rs.getObject(colName);
									if (val != null
											&& m.getParameterTypes()[0].getName()
													.equals("java.lang.String")
		                                    && val instanceof Timestamp) {
		                                int index = val.toString().lastIndexOf(".");
		                                val = val.toString().substring(0, index);
									}
		                            /*if (colName.equals("tPlanStartTime")) {
		                                System.out.println("dsaf");
		                            }*/
		                            m.invoke(obj, val);
		                        }
		                    }

		                }

		                list.add(obj);
		            }
		            /*
		            if (SHOW_SQL) {
		                System.out.println(sql);
		            }
		            */
		            return list;
		        }
			}
		
    /**
     *
     * @param obj
     * @param operateType 0-保存 1-更新
     * @return
     * @throws Exception
     */
    private int updateOrSave(Object obj, int operateType, String where) throws Exception {
        Class cl = obj.getClass();
        Field[] fields = cl.getDeclaredFields();//获取属性
        Field.setAccessible(fields,true);
        Table table = (Table) cl.getAnnotation(Table.class);
        String tableName = table.name();
        StringBuffer sql = new StringBuffer();
        StringBuffer value = new StringBuffer();
        String primaryIdName = null;
        Integer primaryIdVal = null;
        if (operateType == 0) {
            sql.append("insert into ").append(tableName)
                    .append(" (");
            value.append(" values(");
        } else if (operateType == 1) {
            sql.append("update ").append(tableName)
                    .append(" set ");
        } else if (operateType == 2) {
            sql.append("replace into ").append(tableName)
                    .append(" (");
            value.append(" values(");
        }

        //field.setAccessible(true);
        //field.set(obj, 34);
        for (int i = 0; i < fields.length; i++) {
            Object val = fields[i].get(obj);
            String name = fields[i].getName();
            StringBuffer getMethodName = new StringBuffer("get");

            Character character = name.charAt(1);
            if (Character.isUpperCase(character)) {
                getMethodName.append(name);
            } else {
                getMethodName.append(name.substring(0, 1).toUpperCase())
                        .append(name.substring(1));
            }
            Method method = cl.getMethod(getMethodName.toString());
            Column column = (Column) method.getAnnotation(Column.class);
            Id id = (Id) method.getAnnotation(Id.class);
            if (id != null) {
                primaryIdName = column.name();
                primaryIdVal = (Integer) val;
            }
            /*if (column != null) {
                System.out.println("column: " + column.name());
            } else {
                System.out.println("null: " + fields[i].getName());
            }*/
            if (column != null && val != null) {
                String type = fields[i].getType().getName();
                if (operateType == 0 || operateType == 2) {
                    sql.append(column.name()).append(", ");
                } else if (operateType == 1) {
                    sql.append(column.name()).append("=");
                }

                if (type.equals(java.lang.String.class.getName())
                        || type.equals(java.sql.Timestamp.class.getName())
                        || type.equals(Date.class.getName())) {
                    if (operateType == 0 || operateType == 2) {
                        value.append("'" + val + "'").append(", ");
                    } else if (operateType == 1) {
                        sql.append("'" + val + "'").append(", ");
                    }
                } else {
                    if (operateType == 0 || operateType == 2) {
                        value.append(val).append(", ");
                    } else if (operateType == 1) {
                        sql.append(val).append(", ");
                    }
                }
            }
        }
        if (operateType == 0 || operateType == 2) {
            sql.deleteCharAt(sql.length() - 2).append(")");
            value.deleteCharAt(value.length() - 2).append(")");
            sql.append(value);
        } else if (operateType == 1 && where == null) {
            sql.deleteCharAt(sql.length() - 2)
                    .append( "where " + primaryIdName)
                    .append("=").append(primaryIdVal);
        } else if (operateType == 1 && where != null) {
            sql.deleteCharAt(sql.length() - 2).append("where " + where);
        }

        //System.out.println(obj.gettId());
        int rows = 0;
        pstmt = null;
        pstmt = conn.prepareStatement(sql.toString(),
                Statement.RETURN_GENERATED_KEYS);
        rows = pstmt.executeUpdate();
        rs = pstmt.getGeneratedKeys();
        int key = -1;
        if (rs.next()) {
            key = rs.getInt(rows);
        }
        /*
        if (SHOW_SQL) {
            System.out.println(sql);
        }*/
        return key;
    }

    protected int update(Object obj) throws Exception {
        return updateOrSave(obj, 1, null);
    }

    protected int update(Object obj, String where) throws Exception {
        return updateOrSave(obj, 1, where);
    }

	protected int save(Object obj) throws Exception {
        return updateOrSave(obj, 0, null);
    }

    protected int replace(Object obj) throws Exception {
        return updateOrSave(obj, 2, null);
    }
    
}
