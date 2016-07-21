<%@include file="/WEB-INF/common/taglib.jsp" %>

<div class="container" align="center">
    <h1>Our application encountered an error</h1>
    ${exception.message}<br>

</div>

<!--
Information for technical support:
Failed URL: ${url}
Exception: ${exception.message}
Cause: ${exception.cause}

Stack trace:
<c:forEach items="${exception.stackTrace}" var="printStackTrace">${printStackTrace}
</c:forEach>
-->
