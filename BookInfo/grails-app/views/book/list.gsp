<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BookList</title>
    </head>
    <body>
        <h1></h1>
        <br/>
        <table border="1">
            <tr>
                <th>Book title</th>
                <th>Author</th> 
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <g:each in="${bookList}" var="book">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.author.name}</td>
                    <td>
                        <g:link action="show" id="${book.id}">Edit</g:link>
                    </td>
                    <td>
                        <g:link action="delete" id="${book.id}">Delete</g:link>
                    </td>
                </tr>
            </g:each>
        </table>
        <br>Message: ${message}
        <br><br>
        <g:form name="myForm" url="[controller:'book',action:'save']">
            <table>
                <tr>
                    <td>Book title: </td>
                    <td><g:textField name="title" value="${title}" /></td>    
                </tr>
                <tr>
                    <td>Author: </td>
                    <td><g:textField name="author" value="${author}" /></td>    
                </tr>
            </table>
            <input type="submit" value="Add Book"/>
        </g:form>
    </body>
</html>
