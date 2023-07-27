<%@page import="product.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page isELIgnored="false" %>


<%
	ProductDAO dao2 = new ProductDAO();
	List<ProductVO> list1 = dao2.indexNatrix1(1);
    for (ProductVO productVO : list1) {
        System.out.println("Product Name: " + productVO.getP_name());
    }
%>




<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
        content="Furrever admin is super flexible, powerful, clean &amp; modern responsive bootstrap 5 admin template with unlimited possibilities.">
    <meta name="keywords"
        content="admin template, Furrever admin template, dashboard template, flat admin template, responsive admin template, web app">
    <meta name="author" content="Tha102G3">
</head>

<body>
    <div class="page-wrapper compact-wrapper" id="pageWrapper">
        <div class="page-body-wrapper">
            <div class="page-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xl-6 col-md-12">
                            <div class="card o-hidden card-hover">
<p>1111</p>
    <c:forEach var="xxx" items="${list1}">
        <p>產品名稱：${xxx.getP_name()}</p>
    </c:forEach>


<p><%=list1.size()%></p>
<p>xxxxxxxxxxxxxxxxxxxxxxxxx</p>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>