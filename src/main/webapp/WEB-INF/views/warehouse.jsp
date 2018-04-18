

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
    <meta charset="utf-8">
    <title>Warehouse</title>
</head>
<body>
<div>
    <h2>Transfer item from provider</h2>

    <sf:form method="POST" modelAttribute="warehouseItem">
        <fieldset>
            <select class="selectpicker" id ="warehouse2" name ="warehouseChoose">
                <c:forEach items="${warehouselist}" var="warehouse2">
                    <option value = "${warehouse2.id}">${warehouse2.name}</option>
                </c:forEach>
            </select>


            <table cellspacing="0">
                <tr>
                    <th><label for="qty">Quantity items:</label></th>
                        <td><sf:input path="qty" size="15" id="qty4"/></td>
                    </tr>
                </table>

                <select class="selectpicker" id ="wttt2" name ="providerSummaryChoose">
                    <c:forEach items="${providerSummarylist}" var="providerSummary2">
                        <option value = "${providerSummary2.nameItem}">${providerSummary2.nameItem}: ${providerSummary2.nameEntity}</option>
                    </c:forEach>
                </select>


        <td><input name="commit" type="submit"
                   value="I accept" /></td>
        </fieldset>
        <sf:errors path="*" cssClass="error" />

    </sf:form>

    <br>
    <a href="/main" class="btn btn-default">Back...</a>

</div>
</body>
</html>