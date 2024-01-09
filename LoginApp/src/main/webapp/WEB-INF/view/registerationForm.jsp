<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
        <%--        import Bootstrap--%>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <style>
            .error{color:red}
        </style>
</head>
<body>
 <form:form action="processForm" modelAttribute="employeeModel">
            <div class="form-group">
                <label for="username">username</label>
                 <form:input type="text" class="form-control" id="exampleInputUsername" aria-describedby="usernameHelp" placeholder="username" path="username"/>
                 <form:errors path="username" cssClass="error" />
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <form:input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email address" path="email"/>
                <form:errors path="email" cssClass="error" />
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <form:input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" path="password" />
                <form:errors path="password" cssClass="error" />
              </div>
               <div class="form-group">
               <label for="exampleInputPassword1">Confirm Password</label>
               <form:input type="password" class="form-control" id="exampleInputConfirmPassword1" placeholder="confirmPassword" path="confirmPassword" />
               <form:errors path="confirmPassword" cssClass="error" />
               </div>
             <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</body>
</html>