

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<div>
    <h2>Create new item</h2>

<sf:form method="POST" modelAttribute="item">
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="user_name">Name:</label></th>
                <td><sf:input path="name" size="15" id="user_name"/></td>
            </tr>
            <th><label for="price">Price:</label></th>
            <td><sf:input path="price" size="15" maxlength="15"
                          id="price"/> </td>
            </tr>
            <th><label for="description">Description:</label></th>
            <td><sf:input path="description" size="15" maxlength="15"
                          id="description"/> </td>
            </tr>
        </table>
    </fieldset>
    <td><input name="commit" type="submit"
               value="I accept" /></td>
</sf:form>
    <br>
    <a href="/main" class="btn btn-default">Back...</a>
</div>
</body>
</html>