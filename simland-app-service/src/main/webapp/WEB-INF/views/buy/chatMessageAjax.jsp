<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:forEach items="${list}" var="item">
<div class="${(item.sendType==0)?('msg'):('msg_s')}">
	<div class="u_msg">
		<div class="u_out">
			${item.message}
		</div>
	</div>
	
	
	
	
	
	<div class="u_img"><img alt="" src="${pageContext.request.contextPath}${(item.sendType==0)?('/images/user/auction_1_r6_c6.jpg'):('/images/tmp/258926-15011416445038.gif')}"></div>
	<div class="clear"></div>				
</div>
</c:forEach>
