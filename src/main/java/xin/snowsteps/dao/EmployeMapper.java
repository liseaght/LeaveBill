package xin.snowsteps.dao;

import xin.snowsteps.pojo.Employe;

public interface EmployeMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Employe record);

    int insertSelective(Employe record);

    Employe selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Employe record);

    int updateByPrimaryKey(Employe record);
    
    Employe selectByUsername(String name);
}