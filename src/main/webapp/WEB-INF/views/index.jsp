<%@include file="/WEB-INF/common/taglib.jsp" %>
<%@include file="/WEB-INF/common/header.jsp" %>

<div class="container">
    <!-- Most popular -->

    <div class="row">
        <h2>Most Popular</h2>
        <div id="top-downloads"></div>
        <span id="top-downloads-loader" class="hidden">
        <img src='${serviceServerPath}/resources/images/ajax-loader-small.gif'/>
    </span>
    </div>

    <div class="row">
        <!-- Categories -->
        <div class="col-xs-6 col-md-4">
            <c:forEach items="${allCategories}" var="category">
                <a href="${serviceServerPath}/?category=${category.id}">${category.name}</a><br>
            </c:forEach>
        </div>
        <!-- Content -->
        <div class="col-xs-12 col-sm-6 col-md-8">.col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8
            .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12
            .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6
            .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8
            .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12
            .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6
            .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8
            .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12
            .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6
            .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8 .col-xs-12 .col-sm-6 .col-md-8
        </div>
    </div>

</div>
</div>


<%@include file="/WEB-INF/common/footer.jsp" %>













