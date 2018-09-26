package xin.snowsteps.dao;

import java.util.List;

import xin.snowsteps.pojo.LeaveBill;

public interface LeaveBillMapper {
    int deleteByPrimaryKey(Integer leaveId);

    int insert(LeaveBill record);

    int insertSelective(LeaveBill record);

    LeaveBill selectByPrimaryKey(Integer leaveId);

    int updateByPrimaryKeySelective(LeaveBill record);

    int updateByPrimaryKey(LeaveBill record);
    
    List<LeaveBill> selectListByUserId(Integer userId);
}