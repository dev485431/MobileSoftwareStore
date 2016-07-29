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
            ...
        </div>
        <!-- Content -->
        <div class="col-xs-12 col-sm-6 col-md-8">
            <div class="row">
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">
                        <span class="bg-success">${successMessage}</span>
                    </div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">
                        <span class="bg-danger">${errorMessage}</span>
                    </div>
                </c:if>
            </div>
            </br>

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
                    <form:errors cssClass="bg-danger" path="categoryId"/>
                    <form:select path="categoryId">
                        <c:forEach var="category" items="${allCategories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="form-group">
                    <label for="description">Description</label><br>
                    <form:errors cssClass="bg-danger" path="description"/>
                    <form:textarea path="description" value="${programForm.description}" cssClass="form-control"
                                   placeholder="Description"/>
                </div>
                <div class="form-group">
                    <label for="file">File</label><br>
                    <form:errors cssClass="bg-danger" path="file"/>
                    <input type="file" name="file" id="file">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form:form>
            <small class="text-muted">* Maximum program file size is ${maxFileSizeKb} Kb</small>
            </br>
            <small class="text-muted">** The program file must be a zip file.It cannot be empty.</br>
                It must contain only the following files in the main zip file folder: app.apk, info.txt, 128x128.jpg,
                512x512.jpg
            </small>

        </div>
    </div>


</div>
</div>

</body>
</html>













