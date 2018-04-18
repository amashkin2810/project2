<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>MAIN</title>
</head>
<body>

<a href="/items/item" class="btn btn-default">Create item</a>
<br>
<a href="/items" class="btn btn-default">View items</a>
<br>
<a href="/providers/provider" class="btn btn-default">Add item to provider</a>
<br>
<a href="/providers" class="btn btn-default">View providers</a>
<br>
<a href="/warehouses/warehouse" class="btn btn-default">Transfer item to warehouse</a>
<br>
<a href="/warehouses" class="btn btn-default">View warehouses</a>
<br>
<a href="/customers/customer" class="btn btn-default">Transfer item to customer</a>
<br>
<a href="/customers" class="btn btn-default">View customers</a>

</body>
</html>
