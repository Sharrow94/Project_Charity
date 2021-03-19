<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../header.jsp"%>

<section class="login-page">
    <h2>Szczegóły donacji</h2>
    <h1>Organizacja: ${donation.institution.name}</h1>
    <h1>Data utworzenia wpisu:${donation.createDate}</h1>
    <h1>Data przekazania daru:${donation.pickUpDate}</h1>
    <h1>Przekazane dary:
        <c:forEach items="${donation.categories}" var="category">
            ${category.name},
        </c:forEach>
    </h1>
    <h1>Ilość worków:5</h1>
    <h1>Status:
        <c:if test="${donation.received==true}">
            odebrany
        </c:if>
        <c:if test="${donation.received==false}">
            nieodebrany
        </c:if>
    </h1><br>
    <div>
        <c:if test="${donation.received==false}">
            <a href='<c:url value="/donation/received/${donation.id}"/>'
               class="btn btn--with-border">Zmień status</a>
        </c:if>
        <a href='<c:url value="/user/donation/list"/>'
           class="btn btn--with-border">Powrót</a>
    </div>
</section>

<%@include file="../footer.jsp"%>

