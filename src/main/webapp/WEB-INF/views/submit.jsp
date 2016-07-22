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
        <span>${error}</span>
    </div>

    <div class="row">
        <!-- Categories -->
        <div class="col-xs-6 col-md-4">
            ...
        </div>
        <!-- Content -->
        <div class="col-xs-12 col-sm-6 col-md-8">

            <form:form modelAttribute="programForm" encType="multipart/form-data" role="form" method="POST"
                       cssClass="form">

                <div class="form-group">
                    <label for="name">Application name</label><br>
                    <form:errors cssClass="bg-danger" path="name"/>
                    <form:input path="name" type="text" value="${programForm.name}" cssClass="form-control"
                                placeholder="Application name"/>
                </div>
                <div class="form-group">
                    <label for="name">Category</label><br>
                    <form:errors cssClass="bg-danger" path="category"/>
                    <c:forEach var="category" items="${allCategories}">
                        <c:out value="${category}"/>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <label for="description">Description</label><br>
                    <form:errors cssClass="bg-danger" path="description"/>
                    <form:textarea path="description" value="${programForm.description}" cssClass="form-control"
                                   placeholder="Description"/>
                </div>
                <div class="form-group">
                    <label for="file">File</label><br>
                    <span class="bg-danger">${fileError}</span>
                    <form:errors cssClass="bg-danger" path="file"/>
                    <input type="file" name="file" id="file">
                    <p class="help-block">* Maximum file size is 15MB</p>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form:form>


        </div>
    </div>


</div>
</div>

</body>
</html>













