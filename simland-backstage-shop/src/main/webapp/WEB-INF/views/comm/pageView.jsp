<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<<c:import url=""></c:import>
	
	<c:set var="currentPage" value="${pageView.currentPage}" scope="page"/>
	<c:set var="pageSize" value="${pageView.pageSize}" scope="page"/>
	<c:set var="totalPage" value="${pageView.totalPage}" scope="page"/>
	
	<c:set var="parameter" value="${parameter}&amp;pageSize=${pageSize}" scope="page"/>


	<#list parameterMap?keys as key>
		<#-- <#if parameterMap[key] != null && parameterMap[key] != "">  -->
			<#local parameter = parameter + "&" + key + "=" + parameterMap[key] />
		<#-- </#if> -->
	</#list>	
	