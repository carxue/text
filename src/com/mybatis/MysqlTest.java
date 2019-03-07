package com.mybatis;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.client.time.CqTimeLottery;
import com.client.time.HttpClientJsonUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class MysqlTest {

	public static void main(String[] args) throws Exception {
		initDataFrom20170101();//数据库数据初始化同步
//		addDataFromPrefix();//增量从上一次更新的地方同步数据
	}
	
	/**
	 * 从上一次更新的地方同步数据
	 * @throws Exception
	 */
	private static void addDataFromPrefix() throws Exception {
		String sql="SELECT * FROM cq_time_lottery ORDER BY id desc  limit 1"; 
		String[] parameters = null;
		MysqlDBHelper.getConnection();
		ResultSet rs = MysqlDBHelper.exeuteQuery(sql, parameters);
		int openDate = 0;
		String sortNo = null;
		while(rs.next()){
            //rs.get+数据库中对应的类型+(数据库中对应的列别名)
            openDate = rs.getInt(2);
            sortNo = rs.getString("sort_no");
        }
		if(openDate>0){
			String openDateStr = openDate+"";
			int year = Integer.parseInt(openDateStr.substring(0,4));
			int month = Integer.parseInt(openDateStr.substring(4,6))-1;
			int day = Integer.parseInt(openDateStr.substring(6,8));
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();	
			Integer today = Integer.parseInt(df.format(date));
			Calendar c = Calendar.getInstance();
			c.set(year, month, day, 0, 0, 0);
			Integer queryDay = Integer.parseInt(df.format(c.getTime()));
			while(queryDay<=today){
				queryAndSaveNeedCaipiao(queryDay,sortNo);
				sortNo = null;
				c.add(Calendar.DAY_OF_MONTH, 1);
				queryDay = Integer.parseInt(df.format(c.getTime()));
			}
		}
	}
	
	/**
	 * 查询初始化数据
	 */
	private static void initDataFrom20170101(){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();	
		Integer today = Integer.parseInt(df.format(date));
		Calendar c = Calendar.getInstance();
		c.set(2017, 0, 1, 0, 0, 0);
		Integer queryDay = Integer.parseInt(df.format(c.getTime()));
		while(queryDay<=today){
			queryAndSaveCaipiao(queryDay);
			c.add(Calendar.DAY_OF_MONTH, 1);
			queryDay = Integer.parseInt(df.format(c.getTime()));
		}
	}
	
	private static void queryAndSaveCaipiao(int day){
		List<CqTimeLottery> list = HttpClientJsonUtils.getEveryDayData(day);
		int length = list.size();
		String[] sql = new String[length];
		String[][] parameters = new String[length][14];
		for (int i = 0; i < length; i++) {
			sql[i] = "insert into cq_time_lottery(open_date,sort_no,open_num,zu_third_position123,zu_third_position124,zu_third_position125,"
					+ "zu_third_position134,zu_third_position135,zu_third_position145,zu_third_position234,zu_third_position235,zu_third_position245,zu_third_position345,zu_five) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			CqTimeLottery lottery = list.get(i);
			parameters[i][0] = lottery.getOpenDate().toString();
			parameters[i][1] = lottery.getSortNo();
			parameters[i][2] = lottery.getOpenNum();
			parameters[i][3] = lottery.getZuThirdPosition123();
			parameters[i][4] = lottery.getZuThirdPosition124();
			parameters[i][5] = lottery.getZuThirdPosition125();
			parameters[i][6] = lottery.getZuThirdPosition134();
			parameters[i][7] = lottery.getZuThirdPosition135();
			parameters[i][8] = lottery.getZuThirdPosition145();
			parameters[i][9] = lottery.getZuThirdPosition234();
			parameters[i][10] = lottery.getZuThirdPosition235();
			parameters[i][11] = lottery.getZuThirdPosition245();
			parameters[i][12] = lottery.getZuThirdPosition345();
			parameters[i][13] = lottery.getZuFive();
		}
		MysqlDBHelper.getConnection();
		MysqlDBHelper.exeuteUpdate(sql, parameters);
	}
	
	private static void queryAndSaveNeedCaipiao(int day,String num){
		List<CqTimeLottery> list = HttpClientJsonUtils.getEveryDayData(day);
		List<CqTimeLottery> newlist = new ArrayList<CqTimeLottery>();
		if(num!=null){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getSortNo().equals(num)){
					for(int j=i+1;j<list.size();j++){
						newlist.add(list.get(j));
					}
					break;
				}
			}
		}else{
			newlist = list;
		}
		int length = newlist.size();
		String[] sql = new String[length];
		String[][] parameters = new String[length][14];
		for (int i = 0; i < length; i++) {
			sql[i] = "insert into cq_time_lottery(open_date,sort_no,open_num,zu_third_position123,zu_third_position124,zu_third_position125,"
					+ "zu_third_position134,zu_third_position135,zu_third_position145,zu_third_position234,zu_third_position235,zu_third_position245,zu_third_position345,zu_five) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";			CqTimeLottery lottery = newlist.get(i);
			parameters[i][0] = lottery.getOpenDate().toString();
			parameters[i][1] = lottery.getSortNo();
			parameters[i][2] = lottery.getOpenNum();
			parameters[i][3] = lottery.getZuThirdPosition123();
			parameters[i][4] = lottery.getZuThirdPosition124();
			parameters[i][5] = lottery.getZuThirdPosition125();
			parameters[i][6] = lottery.getZuThirdPosition134();
			parameters[i][7] = lottery.getZuThirdPosition135();
			parameters[i][8] = lottery.getZuThirdPosition145();
			parameters[i][9] = lottery.getZuThirdPosition234();
			parameters[i][10] = lottery.getZuThirdPosition235();
			parameters[i][11] = lottery.getZuThirdPosition245();
			parameters[i][12] = lottery.getZuThirdPosition345();
			parameters[i][13] = lottery.getZuFive();
		}
		MysqlDBHelper.getConnection();
		MysqlDBHelper.exeuteUpdate(sql, parameters);
	}
	
	
	
	
/*	public static void comment() throws Exception {
		String table = "user_info";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = (Connection) DriverManager.getConnection(
				"jdbc:mysql://192.168.10.229:3306/carltest?useUnicode=true&characterEncoding=utf8", "mysql",
				"oRcl_123");
		Map map = new HashMap();
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery("show full columns from " + table);
		System.out.println("【" + table + "】");
		while (rs.next()) {
			System.out.println(rs.getString("Field") + ": " + rs.getString("Type") + " :" + rs.getString("Comment"));
		}
		rs.close();
		stmt.close();
		conn.close();
	}

	public static void jiegou() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://192.168.10.229:3306/carltest?useUnicode=true&characterEncoding=utf8", "mysql",
					"oRcl_123");
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("select * from user_info");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsme = (ResultSetMetaData) rs.getMetaData();

			int columnCount = rsme.getColumnCount();
			System.out.println("ResultSet对象中的列数" + columnCount);
			for (int i = 1; i < columnCount; i++) {
				System.out.println();
				System.out.println("列名称: " + rsme.getColumnName(i));
				System.out.println("列类型(DB): " + rsme.getColumnTypeName(i));
				// System.out.println("长度: "+ rsme.getPrecision(i) );
				// System.out.println("是否自动编号: "+ rsme.isAutoIncrement(i));
				// System.out.println("是否可以为空: "+ rsme.isNullable(i));
				// System.out.println("是否可以写入: "+ rsme.isReadOnly(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
