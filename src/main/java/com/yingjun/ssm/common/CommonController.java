package com.yingjun.ssm.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yingjun.ssm.dto.PageResult;


public abstract class CommonController {
	
	protected Log log = LogFactory.getLog(getClass());

	private static final String[] EMPTY_S_ARRAY = new String[0];
	


	protected Map<String, String> getParamterMap(String[] fields, HttpServletRequest request) {
		return getParamterMap(fields, request, false);
	}

	protected Map<String, String> getParamterMap(String[] fields, HttpServletRequest request, boolean flag) {
		Map<String, String> param = new TreeMap<String, String>();
		for (int i = 0; i < fields.length; i++) {
			String v = StringUtils.trimToNull(request.getParameter(fields[i]));
			if (v != null || flag) {
				param.put(fields[i], v);
			}
		}
		return param;
	}


	protected String[] getValues(HttpServletRequest request, String name) {
		String[] v = request.getParameterValues(name);
		String[] ret = null;
		if (v == null) {
			ret = EMPTY_S_ARRAY;
			
		}else{
			ret = new String[v.length];
			for(int i = 0 ; i < v.length ; i++ ){
				String item = v[i];
				item = StringUtils.trim(item).replace("\t", "");
				ret[i] = item;
			}
			
		}
		return ret;
	}


	protected String getParameter(HttpServletRequest request, String key) {
		return StringUtils.trim(request.getParameter(key));
	}

	protected String getParameter(HttpServletRequest request, String key, String def) {
		String val = request.getParameter(key);
		return StringUtils.isBlank(val) ? def : val;
	}



	protected boolean needCheckLogin(HttpServletRequest request) {
		return true;
	}
	
	protected  String getSort(Map<String, Object> reqparams , String defaultSort){
		String order = (String) reqparams.get("order");
		String sort = (String) reqparams.get("sort");
		if( StringUtils.isNotBlank(sort) ){
			if( "asc".equals(order) ){
				sort = "-"+sort;
			}
		}else{
			sort = defaultSort;
		}
		return sort;
	}



	public interface ParamConvert {
		void execute(Map<String, Object> params);
	}



	/**
	 * 将json数组字符串转换成对象。 调用方式如：parseArray(json, Map.class)
	 * 
	 * @return
	 */
	public <T> List<T> parseArray(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}

	/**
	 * 将json字符串转换成对象。 调用方式如：parseObject(json, Map.class)
	 * 
	 * @return
	 */
	public <T> T parseObject(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}


	/**
	 * java对象转成json格式数据
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToJsonData(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * 返回$.getJSON跨域类型的请求
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	protected void writeCallbackJson(HttpServletRequest request, HttpServletResponse response, Object model) throws IOException {
		String jsoncallback = request.getParameter("jsoncallback");
		StringBuilder result = new StringBuilder();
		result.append(jsoncallback + "(");
		result.append(objectToJsonData(model));
		result.append(")");
		writeJson(response, result.toString());
	}

	/**
	 * 将map对象转换为json格式后输出，类似{"total":23,...}
	 * 
	 * @param response
	 * @param map
	 * @throws IOException
	 */
	protected void writeJson(HttpServletResponse response, Map<?, ?> map) throws IOException {
		writeJson(response, objectToJsonData(map));
	}

	/**
	 * 将list对象转换为json格式后输出，类似[{...},{...}...]
	 * 
	 * @param response
	 * @param list
	 * @throws IOException
	 */
	protected void writeJson(HttpServletResponse response, Collection<?> list) throws IOException {
		writeJson(response, objectToJsonData(list));
	}
	
	protected PageResult wrapperResult(List<?> list){
		PageResult baseResult = new PageResult(list,1);
		return baseResult;
	}

	protected void writeRootJson(HttpServletResponse response, Collection<?> list) throws IOException {
		PrintWriter rw = response.getWriter();
		rw.write("{\"root\":");
		rw.write(objectToJsonData(list));
		rw.write("}");
	}

	/**
	 * 当表格不需要分页时调用
	 * 
	 * @param response
	 * @param list
	 * @throws IOException
	 */
	protected void writePageJson(HttpServletResponse response, List<?> list) throws IOException {
		writePageJson(response, list, list.size());
	}


	protected void writePageJson(HttpServletResponse response, List<?> list, int total) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		StringBuilder result = new StringBuilder("{\"total\":").append(total).append(",\"rows\":");
		result.append(objectToJsonData(list));
		result.append("}");
		response.getWriter().write(result.toString());
	}

	/**
	 * 向客户端输出一段字符串
	 * 
	 * @param response
	 * @param str
	 * @throws IOException
	 */
	protected void writeString(HttpServletResponse response, String str) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(str);
	}

	/**
	 * 输出xml格式数据到客户端
	 * 
	 * @param response
	 * @param xml
	 * @throws IOException
	 */
	protected void writeXML(HttpServletResponse response, String xml) throws IOException {
		response.setContentType("application/xml;charset=utf-8");
		response.getWriter().write(xml);
	}

	/**
	 * 向客户端输出一段javascript
	 * 
	 * @param response
	 * @param script
	 * @throws IOException
	 */
	protected void writeJavaScript(HttpServletResponse response, String script) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rw = response.getWriter();
		rw.write("<script>");
		rw.write(script);
		rw.write("</script>");
		rw.flush();
	}

	/**
	 * 向客户端输出一段json字符串
	 * 
	 * @param response
	 * @param json
	 * @throws IOException
	 */
	protected void writeJson(HttpServletResponse response, String json) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}

	/**
	 * 返回成功处理请求
	 * 
	 * @param response
	 * @throws IOException
	 */
	protected void returnSucc(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{\"success\":true}");
	}

	/**
	 * 返回成功处理请求，并返回消息
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	protected void returnSucc(HttpServletResponse response, String msg) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{\"success\":true,\"msg\":\"" + msg + "\"}");
	}

	/**
	 * 返回成功信息，并带回一个属性
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	protected void writeSuccessObj(HttpServletResponse response, String key, Object msg) throws IOException {
		StringBuilder json = new StringBuilder("{\"success\":true,\"");
		json.append(key).append("\":");
		if (msg == null || msg instanceof Boolean || msg instanceof Integer) {
			json.append(String.valueOf(msg));
		} else {
			json.append("\"").append(msg).append("\"");
		}
		json.append("}");
		writeString(response, json.toString());
	}

	/**
	 * jquery easyui验证结果返回
	 * 
	 * @param response
	 * @param exists
	 * @throws IOException
	 */
	protected void writeValidateResult(HttpServletResponse response, boolean exists) throws IOException {
		writeSuccessObj(response, "notExists", !exists);
	}

	/**
	 * 处理请求发生错误
	 * 
	 * @param response
	 * @throws IOException
	 */
	protected void returnFail(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{\"success\":false}");
	}

	/**
	 * 处理请求发生错误，并返回错误消息
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	protected void returnFail(HttpServletResponse response, String msg) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{\"success\":false,\"msg\":\"" + msg + "\"}");
	}

	protected String getTermIP(HttpServletRequest request) {
		return request.getRemoteAddr();
	}




	private static final String SPLIT_STR_REGEX = "[，,\r\n]";//匹配掉字符串中的中文和英文的逗号

	protected static String[] splitByComma(String str) {
		return str == null ? null : str.trim().split(SPLIT_STR_REGEX);
	}

	/**
	 * 页面查询条件，参数获取
	 * 
	 * @param request
	 * @param searchkey
	 * @param params
	 */
	protected void putQueryParam(HttpServletRequest request, String[] searchkey, Map<String, Object> params) {
		if (searchkey != null && searchkey.length >= 1) {
			for (String key : searchkey) {
				String keyval = request.getParameter(key);
				if (StringUtils.isNotBlank(keyval)) {
					params.put(key, keyval);
				}
			}
		}
	}
	

	/**
	 * 检查是否为空，若未空，则设置为默认值
	 * 
	 * @param params
	 * @param key
	 * @param def
	 */
	protected void checkValueIsNull(Map<String, String> params, String key, String def) {
		String val = params.get(key);
		if (StringUtils.isBlank(val)) {
			params.put(key, def);
		}
	}

	/**
	 * MAC地址转换
	 * 
	 * @param 00-1F-D0-C7-13-EC
	 * @return
	 */
	protected static String convertDevMac(String mac) {
		mac = StringUtils.upperCase(StringUtils.replace(mac, "-", ":"));
		if (mac != null && mac.length() != 17) {
			if (mac.matches("[a-fA-F\\d]{4}:[a-fA-F\\d]{4}:[a-fA-F\\d]{4}")) {
				StringBuilder smac = new StringBuilder(mac);
				smac.insert(12, ":");
				smac.insert(7, ":");
				smac.insert(2, ":");
				mac = smac.toString();
			} else if (mac.matches("[a-fA-F\\d]{12}")) {
				StringBuilder smac = new StringBuilder(mac);
				smac.insert(10, ":");
				smac.insert(8, ":");
				smac.insert(6, ":");
				smac.insert(4, ":");
				smac.insert(2, ":");
				mac = smac.toString();
			}
		}
		return mac;
	}

	public static void main(String[] args) {
		System.out.println(convertDevMac("000C-294B-5D9B"));

		String[] strs = splitByComma("aa,b\\b，c{c,e}e，xx");
		for (String string : strs) {// IE版本太低或启用了兼容模式
			System.out.println(string);
		}

		String json = "[\"aa\", \"bb\", \"cc\"]";
		JSONArray sss = JSON.parseArray(json);
		System.out.println(sss.get(1).getClass());
	}

	/**
	 * 获取客户端请求参数
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	protected Map<String, Object> getRequestParams(HttpServletRequest request) {
		return getRequestParams(request, new HashMap<String, Object>());
	}

	protected Map<String, Object> getRequestParams(HttpServletRequest request, Map<String, Object> params) {
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> set = map.entrySet();
		Iterator<Entry<String, String[]>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			String value = StringUtils.trim(entry.getValue()[0]).replace("\t", "");
			params.put(entry.getKey(), value);
		}
		return params;
	}

	/**
	 * 判断数组不为空
	 * 
	 * @param arr
	 * @return
	 */
	protected boolean arrayIsNotEmpty(String[] arr) {
		return arr != null && arr.length >= 1;
	}

	/**
	 * 字符串连接
	 */
	protected String concat(String... string) {
		if (string == null) {
			return null;
		}
		if (string.length == 1) {
			return string[0];
		}
		StringBuilder sb = new StringBuilder();
		for (String s : string) {
			sb.append(s);
		}
		return sb.toString();
	}

	protected String formatJsonStr(String str) {
		if (str == null) {
			return "";
		}
		str = str.replaceAll("\\\\", "\\\\\\\\");
		str = str.replaceAll("\n", "\\\\n");
		str = str.replaceAll("\r", "\\\\r");
		str = str.replaceAll("\"", "\\\\\"");
		str = str.replaceAll("'", "\\\\'");
		return str;
	}


	/**
	 * 返回页面使用encodeURIComponent(encodeURIComponent("中文2"))编码的字符串
	 * 
	 * @param keyword
	 * @return
	 */
	protected static String decodeKeyWord(String keyword) {
		try {
			return keyword == null ? null : URLDecoder.decode(keyword, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}



	/**
	 * 获取指定颜色的文本 字符串
	 * 
	 * @param text
	 * @param color
	 * @return
	 * @date 2013-5-15 上午10:12:37
	 */
	public String getColorText(String text, String color) {
		StringBuilder sb = new StringBuilder();
		sb.append("<span class=\"").append(color).append("\">").append(text).append("</span>");

		return sb.toString();
	}

	/**
	 * 获取插入操作的日志描述
	 * 
	 * @param names
	 * @param values
	 * @return
	 * @date 2013-5-15 上午10:18:11
	 */
	public String getInsertLogDesc(String[] names, String[] values) {
		StringBuilder insertdesc = new StringBuilder();

		insertdesc.append("<table class=\"log_table\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		insertdesc.append("<tr>");
		insertdesc.append("<td class=\"log_tr1\">").append("名称").append("</td>");
		insertdesc.append("<td class=\"log_tr2\">").append("值").append("</td>");
		insertdesc.append("</tr>");

		boolean blank = true;
		for (int i = 0; i < names.length; i++) {
			if (StringUtils.isNotBlank(getBlankValue(values[i]))) {
				blank = false;

				insertdesc.append("<tr>");
				insertdesc.append("<td class=\"log_tr1\">").append(getBlankValue(names[i])).append("</td>");
				insertdesc.append("<td class=\"log_tr2\">").append(getColorText(getBlankValue(values[i]), "blue")).append("</td>");
				insertdesc.append("</tr>");
			}
		}
		insertdesc.append("</table>");

		return blank ? "" : insertdesc.toString();
	}

	public String getBlankValue(String value) {
		return StringUtils.isNotBlank(value) ? value : "";
	}


	public File getFile(File file) {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}


	/**
	 * 校验验证吗是否正确
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	protected boolean checkSafeCode(HttpServletRequest request) throws IOException {
		String att = request.getParameter("type");
		return checkSafeCode(request, concat(att, "_Code"));
	}

	protected boolean checkSafeCode(HttpServletRequest request, String att) throws IOException {
		String safecode = request.getParameter("safecode");
		if (StringUtils.isNotBlank(safecode)) {
			safecode = safecode.trim();
			HttpSession session = request.getSession();
			String scode = (String) session.getAttribute(att);
			session.removeAttribute(att);
			return safecode.equals(scode);
		}
		return false;
	}


	protected String get_sql(String sql, String... param) {
		if (param != null && param.length >= 1) {
			for (int i = 0; i < param.length; i++) {
				sql = sql.replaceAll("%" + i + "%", StringUtils.trimToEmpty(param[i]));
			}
		}
		return sql;
	}
	
	


}
