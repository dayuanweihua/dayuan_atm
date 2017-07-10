<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
   <meta charset="UTF-8">
   <title>上传</title>
 </head>

 <body>
   <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
     <label>选择一个文件：</label>
     <input type="file" name="file"><br>
     <input type="submit" value="上传"><br>
   </form>
 </body>
</html>