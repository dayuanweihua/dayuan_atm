<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <html>
    <head>
        <title>xx系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    </head>
 
    <body>
        <div align="center"> <font size=" 2" color="#FF6633">用户登录</font>
    </div>
    <form id="form1" name="form1" method="post" action="loginServlet">
    <table width="357" border="0" align="center">
        <tr>
          <td width="128">用户名：</td>
          <td width="219"><label>
            <input name="user" type="text" id="user" />
          </label></td>
        </tr>
        <tr>
          <td>密　码：</td>
          <td><label>
            <input name="pwd" type="password" id="pwd" />
          </label></td>
        </tr>
        <tr>
          <td><label>
            <input type="submit" name="Submit" value="登录" />
          </label></td>
          
        </tr>
    </table>
    <p>&nbsp;</p>
    </form>
    </body>
    </html>