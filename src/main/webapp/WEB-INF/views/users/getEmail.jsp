<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../header.jsp"%>

<section class="login-page">
    <h2>Podaj adres email aby zresetować hasło</h2>
    <form:form method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
        </div>
        <div class="form-group form-group--buttons">
            <input type="submit" value="Resetuj hasło" class="btn"/>
        </div>
    </form:form>
</section>

<%@include file="../footer.jsp"%>
