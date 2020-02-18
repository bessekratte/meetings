<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <asset:javascript src="index.js"/>
    <asset:stylesheet href="style.css"/>
</head>

<body>
<div id="wrapper">
    <g:render template="/layouts/nav"/>
    <div id="content">
        <g:each in="${meetings}">
            <table>
                <tr>
                    <g:link controller="meeting" action="edit"
                            resource="${it}">
                        <button>Edit</button>
                    </g:link>
                </tr>
                <tr>
                    <td>Subject:</td>
                    <td>${it.subject}</td>
                </tr>
                <tr>
                    <td>Starts at:</td>
                    <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${it.start}"/></td>
                </tr>
                <tr>
                    <td>Ends at:</td>
                    <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${it.end}"/></td>
                </tr>
            </table>
        </g:each>
    </div>
</div>
</body>
</html>