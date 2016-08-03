<%@include file="/WEB-INF/common/taglib.jsp" %>
<%@include file="/WEB-INF/common/header.jsp" %>

<div class="container">
    <!-- Most popular -->

    <div class="row">
        <h2>Most Popular</h2>
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
        </div>
        <!-- Content -->
        <div class="col-xs-12 col-sm-6 col-md-8">
            <div id="programs-pagination">


                <div class="dropdown text-align-right">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Items per page
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                        <c:forEach items="${itemsPerPageOptions}" var="option">
                            <li><a href="/?categoryId=${categoryId}&itemsPerPage=${option}">${option}</a></li>
                        </c:forEach>
                    </ul>
                </div>

                <c:forEach items="${pageContent}" var="program">
                    <div id="program-item">
                        <h3><a href="${serviceServerPath}/details/${program.id}">${program.name}</a></h3>
                        <p>${program.description}</p>
                        <p class="date">
                            <span>
                                <img src="${serviceServerPath}/resources/images/more.gif" alt="More details"/> <a
                                    href="${serviceServerPath}/details/${program.id}">More details</a>
                            </span>
                            <span>
                                <img src="${serviceServerPath}/resources/images/download.gif"
                                     alt=""/> Downloads: ${program.downloads}
                            </span>
                            <span>
                                <img src="${serviceServerPath}/resources/images/category.gif"
                                     alt=""/> Category: ${program.categoryName}
                            </span>
                        </p>
                    </div>
                </c:forEach>

                <div id="programs-pagination-nav" class="text-align-right">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li class="page-item <c:if test="${pageNumber == 0}">disabled</c:if>">
                                <a class="page-link"
                                   href="/?categoryId=${categoryId}&itemsPerPage=${itemsPerPage}&pageNumber=${pageNumber == 0 ? 0 : pageNumber - 1}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <li <c:if test="${pageNumber == 0}">class="disabled"</c:if>><a
                                    href="/?categoryId=${categoryId}&itemsPerPage=${itemsPerPage}&pageNumber=0">First</a>
                            </li>
                            <li class="page-item active"><span class="page-link">${pageNumber}<span class="sr-only">(current)</span></span>
                            </li>
                            <li <c:if test="${pageNumber == maxPage}">class="disabled"</c:if>><a
                                    href="/?categoryId=${categoryId}&itemsPerPage=${itemsPerPage}&pageNumber=${maxPage}">Last
                                (${maxPage})</a></li>
                            <li class="page-item <c:if test="${pageNumber == maxPage}">disabled</c:if>">
                                <a class="page-link"
                                   href="/?categoryId=${categoryId}&itemsPerPage=${itemsPerPage}&pageNumber=${pageNumber == maxPage ? maxPage : pageNumber + 1}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>

            </div>

        </div>
    </div>

</div>


<%@include file="/WEB-INF/common/footer.jsp" %>













