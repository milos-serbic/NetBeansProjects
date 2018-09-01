<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BookDetails</title>
    </head>
    <body>
        <br/>
        <g:form name="myForm" url="[controller:'book',action:'update']">
            %{--Book ID: <g:textField name="id" value="${book.id}" /><br>--}%
            <g:hiddenField name="id" value="${book.id}" />
             %{--Author ID: <g:textField name="authorId" value="${book.author.id}" /><br>--}%
            <g:hiddenField name="authorId" value="${book.author.id}" />
            <table>
                <tr>
                    <td>Book title: </td>
                    <td><g:textField name="title" value="${book.title}" /></td>    
                </tr>
                <tr>
                    <td>Author: </td>
                    <td><g:textField name="author" value="${book.author.name}" /></td>    
                </tr>
            </table>
            <input type="submit" value="Update"/>
        </g:form>
    </body>

</html>
