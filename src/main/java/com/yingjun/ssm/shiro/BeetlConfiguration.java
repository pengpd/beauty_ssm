package com.yingjun.ssm.shiro;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

/**
 *@description 
 *配置权限解析标签的方法
 * 
 */

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {
    @Override
    protected void initOther() {
        groupTemplate.registerFunctionPackage("panshiro", new ShiroExt());
    }
}
