package org.Angel.www.dao;

import java.util.List;

import org.Angel.www.domain.ClockData;

/**
 * 查询所有员工考勤记录
 * @author HP
 *
 */
public interface ClockDataDao {

	List<ClockData>queryClockDataList();
	
	
}
