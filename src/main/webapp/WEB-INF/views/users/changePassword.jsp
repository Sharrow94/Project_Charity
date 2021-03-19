<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../header.jsp"%>

<section class="login-page">
    <h2>Zmień hasło</h2>
    <form:form method="post" action="/passwordChanged">
        <input type="hidden"  name="id" value="${id}">
        <input type="hidden" name="token" value="${token}">
        <div class="form-group">
            <input type="password" name="password1" placeholder="Nowe hasło" />
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz nowe hasło" />
        </div>
        <div class="form-group form-group--buttons">
            <input type="submit" value="Resetuj hasło" class="btn"/>
        </div>
    </form:form>
</section>

<%@include file="../footer.jsp"%>