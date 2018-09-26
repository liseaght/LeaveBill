package xin.snowsteps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xin.snowsteps.common.Const;
import xin.snowsteps.dao.EmployeMapper;
import xin.snowsteps.pojo.Employe;
import xin.snowsteps.service.IUserService;

@Service("iUserService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private EmployeMapper employeMapper;

	@Override
	public boolean login(String username) {
		if(username!=null&&username.equals(""))
			return false;
		Const.MANAGER = manager(username);
		return true;
	}
	
	private String manager(String username) {
		Employe employe = employeMapper.selectByUsername(username);
		Integer managerId = employe.getManagerId();
		if(managerId!=null)
			return employeMapper.selectByPrimaryKey(managerId).getName();
		else 
			return null;
	}

}
