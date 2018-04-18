

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
    <meta charset="utf-8">
    <title>Provider</title>
</head>
<body>
<div>
    <h2>Add new item</h2>

    <sf:form method="POST" modelAttribute="provider">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><label for="name">Name provider:</label></th>
                    <td><sf:input path="name" size="15" id="name"/></td>
                </tr>
                <th><label for="tin">TIN provider:</label></th>
                <td><sf:input path="tin" size="15" maxlength="15"
                              id="tin"/> </td>
                </tr>
            </table>

        <sf:form method="POST" modelAttribute="providerItem">

                <table cellspacing="0">
                    <tr>
                        <th><label for="qty">Quantity items:</label></th>
                        <td><sf:input path="qty" size="15" id="qty"/></td>
                    </tr>
                </table>
            <select class="selectpicker" id ="item2" name ="itemChoose">
                <c:forEach items="${itemlist}" var="item">
                    <option value = "${item.id}">${item.name}</option>
                </c:forEach>
            </select>
        </sf:form>

        <td><input name="commit" type="submit"
                   value="I accept" /></td>
        </fieldset>
    </sf:form>
    <br>
    <a href="/main" class="btn btn-default">Back...</a>
</div>
</body>
</html>