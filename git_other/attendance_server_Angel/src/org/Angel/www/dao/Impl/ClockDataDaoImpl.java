package org.Angel.www.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Angel.www.dao.ClockDataDao;
import org.Angel.www.db.JdbcUtils;
import org.Angel.www.domain.ClockData;

public class ClockDataDaoImpl implements ClockDataDao {

	public ClockDataDaoImpl(Connection conn) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException {
		Connection conn = JdbcUtils.getConn();
		String sql = "select work_date,employee_no,clock_in_time,clock_off_time,"
					+" case" 
					        +" when clock_in_diff<-120"
							+" then '旷工'"
						+" when clock_in_diff<0" 
							+" then '迟到'"
						+" when clock_in_diff>0"
							+" then '正常'"
						+" else '忘记打卡'"
					+" end as diff_in_status,"
					+" case" 
					        +" when clock_off_diff>120"
							+" then '旷工'"
						+" when clock_off_diff>0" 
							+" then '早退'"
						+" when clock_off_diff<0"
							+" then '正常'"
						+" else '忘记打卡'"
					+" end as diff_off_status"
				+" from"
				+"("
					+"select" 
						+" work_date,"
						+" employee_no,clock_in_time,clock_off_time,"
						+" TIMESTAMPDIFF(MINUTE,clock_in_time,concat(work_date,' 09:00:00')) as clock_in_diff,"
						+" TIMESTAMPDIFF(MINUTE,clock_off_time,concat(work_date,' 18:00:00')) as clock_off_diff"
						+" from t_work_date w left join t_clock_info t "
						+" on w.work_date=t.clock_date"
				+") t;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<ClockData> clockDataList = new ArrayList<>();
		while (rs.next()) {
			java.util.Date workDate = rs.getDate("work_date");
			String employeeNo = rs.getString("employee_no");
			java.util.Date clockInTime = rs.getDate("clock_in_time");
			java.util.Date clockOffTime = rs.getDate("clock_off_time");
			String diffInStatus = rs.getString("diff_in_status");
			String diffOffStatus = rs.getString("diff_off_status");
			
			ClockData clockData = new ClockData();
			clockData.setWorkDate(workDate);
			clockData.setEmployeeNo(employeeNo);
			clockData.setClockInTime(clockInTime);
			clockData.setClockOffTime(clockOffTime);
			clockData.setDiffInStatus(diffInStatus);
			clockData.setDiffOffStatus(diffOffStatus);
			clockDataList.add(clockData);
			
		}
		System.out.println(clockDataList);
		
	}

	@Override
	public List<ClockData> queryClockDataList() {
		Connection conn = JdbcUtils.getConn();
		ArrayList<ClockData> clockDataList = null;
		try {
			String sql = "select work_date,employee_no,clock_in_time,clock_off_time,"
						+" case" 
						        +" when clock_in_diff<-120"
								+" then '旷工'"
							+" when clock_in_diff<0" 
								+" then '迟到'"
							+" when clock_in_diff>0"
								+" then '正常'"
							+" else '忘记打卡'"
						+" end as diff_in_status,"
						+" case" 
						        +" when clock_off_diff>120"
								+" then '旷工'"
							+" when clock_off_diff>0" 
								+" then '早退'"
							+" when clock_off_diff<0"
								+" then '正常'"
							+" else '忘记打卡'"
						+" end as diff_off_status"
					+" from"
					+"("
						+"select" 
							+" work_date,"
							+" employee_no,clock_in_time,clock_off_time,"
							+" TIMESTAMPDIFF(MINUTE,clock_in_time,concat(work_date,' 09:00:00')) as clock_in_diff,"
							+" TIMESTAMPDIFF(MINUTE,clock_off_time,concat(work_date,' 18:00:00')) as clock_off_diff"
							+" from t_work_date w left join t_clock_info t "
							+" on w.work_date=t.clock_date"
					+") t;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			clockDataList = new ArrayList<>();
			while (rs.next()) {
				java.util.Date workDate = rs.getDate("work_date");
				String employeeNo = rs.getString("employee_no");
				java.util.Date clockInTime = rs.getDate("clock_in_time");
				java.util.Date clockOffTime = rs.getDate("clock_off_time");
				String diffInStatus = rs.getString("diff_in_status");
				String diffOffStatus = rs.getString("diff_off_status");
				
				ClockData clockData = new ClockData();
				clockData.setWorkDate(workDate);
				clockData.setEmployeeNo(employeeNo);
				clockData.setClockInTime(clockInTime);
				clockData.setClockOffTime(clockOffTime);
				clockData.setDiffInStatus(diffInStatus);
				clockData.setDiffOffStatus(diffOffStatus);
				clockDataList.add(clockData);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clockDataList;
	}

}
