

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
    <meta charset="utf-8">
    <title>Customer</title>
</head>
<body>
<div>
    <h2>Transfer item from Warehouse</h2>

    <sf:form method="POST" modelAttribute="customer">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><label for="firstname">Firstname:</label></th>
                    <td><sf:input path="firstname" size="15" id="firstname"/></td>
                </tr>
                <tr>
                    <th><label for="lastname">Lastname:</label></th>
                    <td><sf:input path="lastname" size="15" id="lastname"/></td>
                </tr>
                <th><label for="passport">Passport:</label></th>
                <td><sf:input path="passport" size="15" maxlength="15"
                              id="passport"/> </td>
                </tr>
            </table>

        <sf:form method="POST" modelAttribute="customerItem">

            <select class="selectpicker" id ="item2" name ="itemChoose">
                <c:forEach items="${customerSummarylist}" var="item">
                    <option value = "${item.idItem}">Name: ${item.nameItem}: ${item.nameEntity}</option>
                </c:forEach>
            </select>
                <table cellspacing="0">
                    <tr>
                        <th><label for="qty">Quantity items:</label></th>
                        <td><sf:input path="qty" size="15" id="qty"/></td>
                    </tr>
                </table>

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