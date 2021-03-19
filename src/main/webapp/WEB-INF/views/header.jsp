<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="pl.coderslab.charity.service.UserService" %>
<%@ page import="pl.coderslab.charity.model.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
    <%
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
        UserService userService = context.getBean(UserService.class);
        Users user = userService.findByEmail(email);
        pageContext.setAttribute("currentUser", user);
    %>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>CharityApp</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <sec:authorize access="isAuthenticated()==false">
                <li><a href="/user/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()==false">
                <li><a href="/user/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </sec:authorize>
        </ul>

        <sec:authorize access="isAuthenticated()">
            <ul class="nav--actions">
                <li class="logged-user">
                    Witaj ${currentUser.firstName} ${currentUser.lastName}
                    <ul class="dropdown">
                        <li><a href="/user/edit">Edytuj dane</a></li>
                        <li><a href="/user/donation/list">Moje donacje</a></li>
                        <li>
                            <form action="<c:url value="/logout"/> " method="post">
                                <input type="submit" value="Wyloguj" class="btn-link">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </sec:authorize>

        <ul>
            <sec:authorize access="isAuthenticated()">
            <li><a href="/donation/new" class="btn btn--without-border active">Start</a></li>
            </sec:authorize>
            <li><a href="#one" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#two" class="btn btn--without-border">O nas</a></li>
            <li><a href="#third" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#fourth" class="btn btn--without-border">Kontakt</a></li>
        </ul>

    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>
