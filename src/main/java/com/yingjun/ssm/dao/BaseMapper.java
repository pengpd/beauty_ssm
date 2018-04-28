package com.yingjun.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yingjun.ssm.entity.SysUser;

public interface BaseMapper<T,ET> {
	
	 	long countByExample(ET example);

	    int deleteByExample(ET example);

	    int deleteByPrimaryKey(Long id);

	    int insert(T record);

	    int insertSelective(T record);

	    List<SysUser> selectByExample(ET example);

	    SysUser selectByPrimaryKey(Long id);

	    int updateByExampleSelective(@Param("record") T record, @Param("example") ET example);

	    int updateByExample(@Param("record") T record, @Param("example") ET example);

	    int updateByPrimaryKeySelective(T record);

	    int updateByPrimaryKey(T record);

}
