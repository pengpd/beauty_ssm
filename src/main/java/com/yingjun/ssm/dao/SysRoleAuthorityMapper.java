package com.yingjun.ssm.dao;

import com.yingjun.ssm.entity.SysRoleAuthority;
import com.yingjun.ssm.entity.SysRoleAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleAuthorityMapper {
    long countByExample(SysRoleAuthorityExample example);

    int deleteByExample(SysRoleAuthorityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleAuthority record);

    int insertSelective(SysRoleAuthority record);

    List<SysRoleAuthority> selectByExample(SysRoleAuthorityExample example);

    SysRoleAuthority selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRoleAuthority record, @Param("example") SysRoleAuthorityExample example);

    int updateByExample(@Param("record") SysRoleAuthority record, @Param("example") SysRoleAuthorityExample example);

    int updateByPrimaryKeySelective(SysRoleAuthority record);

    int updateByPrimaryKey(SysRoleAuthority record);
}