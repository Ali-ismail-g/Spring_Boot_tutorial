<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">

        <%--        import Bootstrap--%>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    </head>
    <body background="${pageContext.request.contextPath}/resources/images/background.jpg">
        <form:form action="processForm" modelAttribute="userModel">
            <form:input type="text" placeholder="username" path="username"/>
            <form:input type="password" placeholder="password" path="password"/>
            <form:input type="text" placeholder="salary" path="salary"/>

            <%--   dropdown         --%>
            <form:select path="country">
                <form:option value="Egypt" label="Egy"/>
                <form:option value="Brazil" label="Brazil"/>
                <form:option value="Saudia Arabia" label="KSA"/>
            </form:select>

            <%--   radiobutton         --%>
            Java <form:radiobutton path="programmingLanguage" value="Java" />
            C# <form:radiobutton path="programmingLanguage" value="C#" />
            PHP <form:radiobutton path="programmingLanguage" value="PHP" />
            Ruby <form:radiobutton path="programmingLanguage" value="Ruby" />
            <br><br>

            Linux <form:checkbox path="operatingSystem" value="Linux" />
            Mac OS <form:checkbox path="operatingSystem" value="Mac OS" />
            MS Windows <form:checkbox path="operatingSystem" value="MS Windows" />
            <br><br>
            <input type="submit" class="btn btn-danger">
        </form:form>
    </body>
</html>

