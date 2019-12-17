<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 12/17/2019
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="title" type="java.lang.String" scope="request"/>
<jsp:useBean id="books" type="java.util.List<by.dziamidka.entity.book.Book>" scope="request"/>
<jsp:useBean id="authors" type="java.util.List<by.dziamidka.entity.author.Author>" scope="request"/>
<jsp:useBean id="libraries" type="java.util.List<by.dziamidka.entity.library.Library>" scope="request"/>
<jsp:useBean id="genres" type="java.util.List<by.dziamidka.entity.genre.Genre>" scope="request"/>
<jsp:useBean id="publishers" type="java.util.List<by.dziamidka.entity.publisher.Publisher>" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>

<table border="1" width="40%" cellpadding="5">
    <caption>Books</caption>
    <tr>
        <th>Title</th>
        <th>Author ID</th>
        <th>Year of publication</th>
        <th>Publisher ID</th>
        <th>Genre ID</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.title}</td>
            <td>${book.authorId}</td>
            <td>${book.yearOfPublication}</td>
            <td>${book.publisherId}</td>
            <td>${book.genreId}</td>
        </tr>
    </c:forEach>
</table>

<table border="1" width="40%" cellpadding="5">
    <caption>Authors</caption>
    <tr>
        <th>AuthorId</th>
        <th>Name</th>
        <th>Surname</th>
    </tr>
    <c:forEach var="author" items="${authors}">
        <tr>
            <td>${author.id}</td>
            <td>${author.name}</td>
            <td>${author.surname}</td>
        </tr>
    </c:forEach>
</table>

<table border="1" width="40%" cellpadding="5">
    <caption>Libraries</caption>
    <tr>
        <th>Library ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="library" items="${libraries}">
        <tr>
            <td>${library.id}</td>
            <td>${library.title}</td>
        </tr>
    </c:forEach>
</table>

<table border="1" width="40%" cellpadding="5">
    <caption>Publishers</caption>
    <tr>
        <th>Publisher ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="publisher" items="${publishers}">
        <tr>
            <td>${publisher.id}</td>
            <td>${publisher.title}</td>
        </tr>
    </c:forEach>
</table>

<table border="1" width="40%" cellpadding="5">
    <caption>Genres</caption>
    <tr>
        <th>Genre ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="genre" items="${genres}">
        <tr>
            <td>${genre.id}</td>
            <td>${genre.title}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
