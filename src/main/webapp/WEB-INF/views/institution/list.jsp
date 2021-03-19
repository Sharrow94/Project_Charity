<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../admin/header.jsp" %>

<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista instytucji</h6>
            <sec:authorize access="hasRole('ADMIN')">
                <a href='<c:url value="/admin/institution/add"/>'
                   class="btn btn-primary"
                   style="background-color:#f6c23e; color:#3a3b45;position: absolute;  right: 8%;width: 170px;margin:-25px; border: 10px #f6c23e;">
                    Dodaj instytucję</a>
            </sec:authorize>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div class="col-sm-12">
                    <table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0"
                           role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                        <thead>
                        <tr role="row">
                            <th class="sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1"
                                colspan="1" aria-sort="ascending"
                                aria-label="Name: activate to sort column descending" style="width: 60px;">
                                Id</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                aria-label="Position: activate to sort column ascending" style="width:250px;">
                                Nazwa</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                aria-label="Position: activate to sort column ascending" style="width:200px;">
                                Cel</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                                aria-label="Salary: activate to sort column ascending" style="width: 250px;">
                                Akcje</th>
                        </thead>
                        <tfoot>
                        <tr>
                            <th rowspan="1" colspan="1">Id</th>
                            <th rowspan="1" colspan="1">Nazwa</th>
                            <th rowspan="1" colspan="1">Cel</th>
                            <th rowspan="1" colspan="1">Akcje</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${institutions}" var="institution">
                            <tr role="row" class="odd">
                                <td><c:out value="${institution.id}"/></td>
                                <td><c:out value="${institution.name}"/></td>
                                <td><c:out value="${institution.description}"/></td>
                                <td>
                                    <sec:authorize access="hasRole('ADMIN')">
                                        <a href='<c:url value="/admin/institution/edit/${institution.id}"/>'
                                           class="btn btn-primary"
                                           style="background-color:#f6c23e; border-color:#f6c23e;color:#3a3b45">Edytuj</a>
                                        <a href='<c:url value="/admin/institution/delete/${institution.id}"/>'
                                           class="btn btn-primary"
                                           style="background-color:#f6c23e; border-color:#f6c23e;color:#3a3b45">Usuń</a>
                                    </sec:authorize>
                                </td>

                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../admin/footer.jsp" %>