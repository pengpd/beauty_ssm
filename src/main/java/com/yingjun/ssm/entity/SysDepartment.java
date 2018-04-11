package com.yingjun.ssm.entity;

import java.io.Serializable;

/**
 * sys_department
 * @author 
 */
public class SysDepartment implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 部门编码
     */
    private String departmentKey;

    /**
     * 部门名称
     */
    private String departmentValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 上级部门编码
     */
    private String parentDepartmentkey;

    /**
     * 创建时间
     */
    private String createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentKey() {
        return departmentKey;
    }

    public void setDepartmentKey(String departmentKey) {
        this.departmentKey = departmentKey;
    }

    public String getDepartmentValue() {
        return departmentValue;
    }

    public void setDepartmentValue(String departmentValue) {
        this.departmentValue = departmentValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentDepartmentkey() {
        return parentDepartmentkey;
    }

    public void setParentDepartmentkey(String parentDepartmentkey) {
        this.parentDepartmentkey = parentDepartmentkey;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysDepartment other = (SysDepartment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDepartmentKey() == null ? other.getDepartmentKey() == null : this.getDepartmentKey().equals(other.getDepartmentKey()))
            && (this.getDepartmentValue() == null ? other.getDepartmentValue() == null : this.getDepartmentValue().equals(other.getDepartmentValue()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getParentDepartmentkey() == null ? other.getParentDepartmentkey() == null : this.getParentDepartmentkey().equals(other.getParentDepartmentkey()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDepartmentKey() == null) ? 0 : getDepartmentKey().hashCode());
        result = prime * result + ((getDepartmentValue() == null) ? 0 : getDepartmentValue().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getParentDepartmentkey() == null) ? 0 : getParentDepartmentkey().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", departmentKey=").append(departmentKey);
        sb.append(", departmentValue=").append(departmentValue);
        sb.append(", description=").append(description);
        sb.append(", parentDepartmentkey=").append(parentDepartmentkey);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}