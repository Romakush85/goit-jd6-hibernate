<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>ProjectManagementSystem</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <c:import url="${contextPath}/WEB-INF/jsp/navbar.jsp"/>
        <h5>Find company</h5>
        <div style ="margin-top:10px;margin-left:10px;">
            <form action="/companies" method="get">
                   <label for="companyId"> Company ID: <label>
                   <input type="text" id="companyId" name="companyId">
                   <button type="submit" name="command" value="find">Find</button>
                   <button type="submit" name="command" value="findAll">Find all companies</button>
            </form>
        </div>
        <div style ="margin-top:10px;margin-left:10px;">
           <c:if test="${not empty companies}">
            <table style ="border: 2px solid black">
                 <thead style ="border: 2px solid black">
                      <tr>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Company ID</td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Company name</td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Country</td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Contact person</td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Email</td>

                      </tr>
                 </thead>
                 <tbody style ="border: 2px solid black">
                   <c:forEach var = "company" items="${companies}">
                      <tr>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${company.companyId}"/></td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${company.name}"/></td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${company.country}"/></td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${company.contactPerson}"/></td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${company.email}"/></td>
                       </tr>
                   </c:forEach>
                 </tbody>
            </table>
            </c:if>
            <c:if test="${empty companies}">
                 <p>There is no companies in DB</p>
            </c:if>
        </div>
    </body>
</html>