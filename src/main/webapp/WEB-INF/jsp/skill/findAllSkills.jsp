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
        <h5>Find skill</h5>
        <div style ="margin-top:10px;margin-left:10px;">
            <form action="/skills" method="get">
                   <label for="skillId"> Skill ID: <label>
                   <input type="text" id="skillId" name="skillId">
                   <button type="submit" name="command" value="find">Find</button>
                   <button type="submit" name="command" value="findAll">Find all skills</button>
            </form>
        </div>
        <div style ="margin-top:10px;margin-left:10px;">
           <c:if test="${not empty skills}">
            <table style ="border: 2px solid black">
                            <thead style ="border: 2px solid black">
                                    <tr>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">ID</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Language</td>
                                        <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Level</td>
                                    </tr>
                            </thead>
                            <tbody style ="border: 2px solid black">
                               <c:forEach var = "skill" items="${skills}">
                                <tr>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${skill.skillId}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${skill.language}"/></td>
                                    <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${skill.level}"/></td>
                                </tr>
                              </c:forEach>
                            </tbody>
                        </table>
                                </c:if>
                                <c:if test="${empty skills}">
                                    <p>There are no skills in DB</p>
                                </c:if>
        </div>
    </body>
</html>