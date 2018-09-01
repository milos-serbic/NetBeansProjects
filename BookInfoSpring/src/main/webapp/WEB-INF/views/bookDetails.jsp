<%-- 
    Document   : bookDetails
    Created on : Jul 10, 2016, 1:55:24 AM
    Author     : Milos
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
    <head>
        <title>BookDetails</title>
    </head>
    <body>
    <br/>
    <c:url var="saveOrUpdate" value="/saveOrUpdate" ></c:url>
    <form:form action="${saveOrUpdate}" commandName="book">
        <form:hidden path="id" />
        <table>
            <tr>
                <td>
                    <form:label path="title">
                        <spring:message text="Book Title:"/>
                    </form:label>
                </td>
                <td><form:input path="title" /></td>
            </tr>
            <tr>
                <td>
                    <form:label path="authorId.name">
                        <spring:message text="Book Author:"/>
                    </form:label>
                </td>
                <td><form:input path="authorId.name" /></td>
            </tr>
        </table>
        <input type="submit" value="<spring:message text='Update'/>" />
    </form:form>
    </body>
</html>
