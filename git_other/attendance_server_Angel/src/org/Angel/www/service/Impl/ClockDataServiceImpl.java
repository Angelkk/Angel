package org.Angel.www.service.Impl;

import java.util.Iterator;
import java.util.List;

import org.Angel.www.dao.ClockDataDao;
import org.Angel.www.dao.Impl.ClockDataDaoImpl;
import org.Angel.www.db.JdbcUtils;
import org.Angel.www.domain.ClockData;
import org.Angel.www.domain.Employee;
import org.Angel.www.service.ClockDataService;

public class ClockDataServiceImpl implements ClockDataService{

	private  ClockDataDao clockDataDao;
	
	public ClockDataServiceImpl(){;
		clockDataDao = new ClockDataDaoImpl(JdbcUtils.getConn());
	}

	@Override
	public List<ClockData> adminCount() {
		List<ClockData> clockData = clockDataDao.queryClockDataList();
		return clockData;
}
	public static void main(String[] args) {
		ClockDataDao clockDataDao = new ClockDataDaoImpl(JdbcUtils.getConn());
		List<ClockData> clockData = clockDataDao.queryClockDataList();
		Employee employee = new Employee();
		String employeeNo = employee.getEmployeeNo();
		for (ClockData clockData2:clockData) {
			
			
		}
	}
}