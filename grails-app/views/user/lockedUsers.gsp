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
        <g:each in="${users}">
            <table>
                <tr>
                    <td>Username: </td>
                    <td>${it.username}</td>
                    <td><g:link controller="user" action="unlockUser"
                                resource="${it}">
                        <button>Unlock</button>
                    </g:link>
                    </td>
                </tr>
            </table>
        </g:each>
    </div>
</div>
</body>
</html>