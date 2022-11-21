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
        <h5>Find developer</h5>
        <div style ="margin-top:10px;margin-left:10px;">
            <form action="/developers" method="get">
                   <label for="devId"> Developer ID: <label>
                   <input type="text" id="devId" name="devId">
                   <button type="submit" name="command" value="find">Find</button>
                   <button type="submit" name="command" value="findAll">Find all developers</button>
            </form>
        </div>
        <div style ="margin-top:10px;margin-left:10px;">
           <c:if test="${not empty developers}">
            <table style ="border: 2px solid black">
                            <thead style ="border: 2px solid black">
                                    <tr>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">ID</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">First name</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Last name</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Birth date</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Gender</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Salary</td>
                                    </tr>
                            </thead>
                            <tbody style ="border: 2px solid black">
                              <c:forEach var = "developer" items="${developers}">
                                <tr>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${developer.devId}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${developer.firstName}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${developer.lastName}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${developer.birthDate}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${developer.gender}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${developer.salary}"/></td>
                                </tr>
                              </c:forEach>
                            </tbody>
                        </table>
                                </c:if>
                                <c:if test="${empty developers}">
                                    <p>There are no developers in DB</p>
                                </c:if>
        </div>
    </body>
</html>