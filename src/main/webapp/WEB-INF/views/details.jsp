<%@include file="/WEB-INF/common/taglib.jsp" %>
<%@include file="/WEB-INF/common/header.jsp" %>

<div class="container">


    <div class="row">
        <h2><a href="/">Home</a> / Program details</h2>
        <div id="top-downloads"></div>
    </div>

    <div class="row">
        <!-- Categories -->
        <div class="col-xs-6 col-md-4">
            <div class="row">
                <h2>Categories</h2>
                <c:forEach items="${allCategories}" var="category">
                    <a href="${serviceServerPath}/?categoryId=${category.id}">${category.name}</a><br>
                </c:forEach>
            </div>
            <br/><br/>
            <div class="row">
                <h4><a href="/submit">Submit new program</a></h4>
            </div>
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

            <div class="row">
                <h2>${programDetails.name}</h2>
            </div>
            <br/>
            <div class="row">
                ${programDetails.description}
            </div>
            <br/>
            <div class="row">
                <p></p>
                <img src="${serviceServerPath}/resources/images/download.gif"
                     alt=""/> Downloads: ${programDetails.downloads}
                </span>
                <span>
                    <img src="${serviceServerPath}/resources/images/category.gif"
                         alt=""/> Category: ${programDetails.categoryName}
                </span>
                <span>
                    <img src="${serviceServerPath}/resources/images/category.gif"
                         alt=""/> Time uploaded: ${programDetails.timeUploaded}
                </span>
                </p>
            </div>

            <div class="row text-align-center">
                <h3><a href="/download/${programDetails.id}">Download</a></h3>
            </div>
            <br/><br/>

            <div class="row text-align-center">
                <a href="/">Back to main page</a>
            </div>

        </div>
    </div>


</div>

<%@include file="/WEB-INF/common/footer.jsp" %>
