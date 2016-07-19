<%@include file="/WEB-INF/common/taglib.jsp" %>

<html lang="en">
<head>
    <title>Mobile Software Store</title>

    <!-- Bootstrap javascript, jQuery-->
    <script src="${serviceServerPath}/resources/js/bootstrap.min.js"></script>
    <script src="${serviceServerPath}/resources/js/jquery-3.1.0.min.js"></script>

    <!-- Bootstrap styles -->
    <link rel="stylesheet" type="text/css" href="${serviceServerPath}/resources/css/bootstrap.min.css"/>

</head>
<body>

<div class="container">
    <!-- Most popular -->

    <div class="row">
        <h2>Most Popular</h2>
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


</body>
</html>












