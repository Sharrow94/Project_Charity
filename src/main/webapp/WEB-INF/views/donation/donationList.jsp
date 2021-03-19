<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../header.jsp"%>

<section class="login-page">
    <h2>Moje donacje</h2>
    <table class="myTable">
        <thead>
        <tr class="custom-tr-dark">
            <th class="custom-th">Instytucja</th>
            <th class="custom-th">Kategorie</th>
            <th class="custom-th">Ilość</th>
            <th class="custom-th">Status</th>
            <th class="custom-th">Data odebrania</th>
            <th class="custom-th">Akcje</th>
        </thead>
        <tbody>
        <c:forEach items="${donations}" var="donation">
            <tr role="row" class="custom-tr">
                <td class="custom-td"><c:out value="${donation.institution.name}"/></td>
                <td class="custom-td">
                    <c:forEach items="${donation.categories}" var="category">
                        <c:out value="${category.name}, "/>
                    </c:forEach>
                </td>
                <td class="custom-td"><c:out value="${donation.quantity}"/></td>
                <td class="custom-td">
                    <c:if test="${donation.received==true}">
                        odebrany
                    </c:if>
                    <c:if test="${donation.received==false}">
                        nieodebrany
                    </c:if>
                </td>
                <td class="custom-td nowrap">
                    <c:out value="${donation.pickUpDate}"/>
                </td>
                <td class="custom-td nowrap">
                    <a href='<c:url value="/donation/${donation.id}"/>'
                       class="btn btn--without-border">Szczegóły</a>
                    <c:if test="${donation.received==false}">
                        <a href='<c:url value="/admin/admin/edit/${donation.id}"/>'
                           class="btn btn--without-border">Edytuj</a>
                        <a href='<c:url value="/donation/delete/${donation.id}"/>'
                           class="btn btn--without-border">Usuń</a>
                    </c:if>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
<%@include file="../footer.jsp"%>




