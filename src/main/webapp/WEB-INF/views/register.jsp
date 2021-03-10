<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="header.jsp"%>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form modelAttribute="user" action="/user/registerDone" method="post">
        <div class="form-group">
            <form:input path="email" type="email"  placeholder="Email"/>
        </div>
        <div class="form-group">
            <form:input path="firstName" type="text" placeholder="Imię" />
        </div>
        <div class="form-group">
            <form:input path="lastName" type="text" placeholder="Nazwisko" />
        </div>
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Hasło" />
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="/user/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@include file="footer.jsp"%>
