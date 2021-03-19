<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="header.jsp"%>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form class="user" method="post">
        <div class="form-group">
            <input type="email" name="username" placeholder="Email" />
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" />
            <a href="/resetPassword/step1" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/user/register" class="btn btn--without-border">Załóż konto</a>
            <input type="submit" value="Zaloguj się" class="btn"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
    </form>
</section>

<%@include file="footer.jsp"%>

