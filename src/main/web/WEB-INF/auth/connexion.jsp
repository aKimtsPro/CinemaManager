<%--
  Created by IntelliJ IDEA.
  User: akimt
  Date: 20-12-22
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>connexion</title>
  <%@include file="/WEB-INF/parts/style.jsp"%>
</head>
<body>

<%@include file="/WEB-INF/parts/header.jsp"%>

  <div class="container">

    <h1 class="display-1">Se connecter</h1>

    <form action="<%=request.getContextPath()%>/login" method="post">

      <div class="mb-3">
        <label for="in-username" class="form-label">username</label>
        <input type="text" name="username" class="form-control" id="in-username">
      </div>

      <div class="mb-3">
        <label for="in-password" class="form-label">password</label>
        <input type="password" name="password" class="form-control" id="in-password">
      </div>

      <button type="submit" class="btn btn-success">envoyer</button>

    </form>

  </div>

<%@include file="/WEB-INF/parts/footer.jsp"%>

<%@include file="/WEB-INF/parts/script.jsp"%>
</body>
</html>
