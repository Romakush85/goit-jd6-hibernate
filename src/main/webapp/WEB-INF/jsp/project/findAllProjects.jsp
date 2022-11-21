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
        <h5>Find project</h5>
        <div style ="margin-top:10px;margin-left:10px;">
            <form action="/projects" method="get">
                   <label for="projectId"> Project ID: <label>
                   <input type="text" id="projectId" name="projectId">
                   <button type="submit" name="command" value="find">Find</button>
                   <button type="submit" name="command" value="findAll">Find all projects</button>
            </form>
        </div>
        <div style ="margin-top:10px;margin-left:10px;">
           <c:if test="${not empty projects}">
            <table style ="border: 2px solid black">
                            <thead style ="border: 2px solid black">
                                    <tr>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">ID</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Project name</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Customer ID</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Cost</td>
                                    </tr>
                            </thead>
                            <tbody style ="border: 2px solid black">
                              <c:forEach var = "project" items="${projects}">
                                <tr>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${project.projectId}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${project.name}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${project.customerId}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${project.cost}"/></td>
                                </tr>
                              </c:forEach>
                            </tbody>
            </table>
          </c:if>
          <c:if test="${empty projects}">
                <p>There are no projects in DB</p>
          </c:if>
        </div>
    </body>
</html>