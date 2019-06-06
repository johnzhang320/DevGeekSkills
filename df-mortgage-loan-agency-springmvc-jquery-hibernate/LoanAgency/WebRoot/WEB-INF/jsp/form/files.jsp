<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head><title>Upload and Download files using Spring</title></head>
  <body>
    <table width="80%" border="1" cellspacing="0" cellpadding="5">
        <tr>
            <th width="4%">No</th>
            <th width="30%">Filename</th>
            <th width="30%">Notes</th>
            <th width="16%">Type</th>
            <th width="20%">&nbsp;</th>
        </tr>
        <c:choose>
            <c:when test="${files != null}">
                <c:forEach var="file" items="${files}" varStatus="counter">
                    <tr>
                        <td>${counter.index + 1}</td>
                        <td>${file.filename}</td>
                        <td>${file.notes}</td>
                        <td>${file.type}</td>
                        <td><div align="center"><a href="download.htm?id=${file.id}">Download</a> /
                            <a href="delete.htm?id=${file.id}">Delete</a></div>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
    </table>
 
    <h2>Add New File</h2>
    <form action="upload.html" method="post" enctype="multipart/form-data">
        <table width="60%" border="1" cellspacing="0">
            <tr>
                <td width="35%"><strong>File to upload</strong></td>
                <td width="65%"><input type="file" name="file" /></td>
            </tr>
            <tr>
                <td><strong>Notes</strong></td>
                <td><input type="text" name="notes" width="60" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="submit" value="Add"/></td>
            </tr>
        </table>
    </form>
  </body>
</html>
