<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../header.jsp"%>

<section class="login-page">
    <h2>Edytuj swoje dane</h2>
    <form:form modelAttribute="user" action="/user/edit" method="post">
        <form:hidden path="id"/>
        <form:hidden path="password"/>
        <form:hidden path="enabled"/>
        <form:hidden path="username"/>
        <div class="form-group">
            <h1>Adres email:</h1>
            <form:input path="email" type="email"  placeholder="Email"/>
        </div>
        <div class="form-group">
            <h1>Imię:</h1>
            <form:input path="firstName" type="text" placeholder="Imię" />
        </div>
        <div class="form-group">
            <h1>Nazwisko:</h1>
            <form:input path="lastName" type="text" placeholder="Nazwisko" />
        </div>

        <div class="form-group form-group--buttons">
            <button type="submit" class="btn">Zatwierdź</button>
            <a href="/" class="btn">Cofnij</a>
            <a href="/user/${user.id}/changePassword" class="btn">Zmień hasło</a>
        </div>
    </form:form>
</section>

<%@include file="../footer.jsp"%>
