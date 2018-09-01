<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
    <head>
        <title>BookList</title>
    </head>
    <body>
        <br/>
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Edit</th>
                <th>Delate</th>
            </tr>
            <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.authorId.name}</td>
                    <td><a href="<c:url value='/edit/${book.id}' />" >Edit</a></td>
                    <td><a href="<c:url value='/delete/${book.id}' />" >Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    <br/>
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
        <input type="submit" value="<spring:message text='Add Book'/>" />
    </form:form>
</body>
</html>
