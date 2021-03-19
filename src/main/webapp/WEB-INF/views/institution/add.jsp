<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Dodaj instytucję</title>
</head>
<%@ include file="../admin/header.jsp" %>
<body>
<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <form:form method="post" modelAttribute="institution">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Dodaj instytucję</h1>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Nazwa:</td>
                                    <form:input path="name"  placeholder="Nazwa"
                                                class="form-control form-control-user"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <td>Cel:</td>
                                    <form:textarea path="description" placeholder="cel"
                                                class="form-control form-control-user"/>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                Dodaj</button>
                            <div style="margin-top: 10px">
                                <a href="/admin/institution/list" class="btn btn-primary btn-user btn-block">
                                    Cofnij</a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="../admin/footer.jsp" %>
</html>
