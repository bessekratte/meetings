<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div id="meetings">
    <g:each in="${meetings}">
        <table>
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
            <tr>
                <g:link controller="meeting" action="editMeeting"
                        params="${[id: it.id]}">
                    <button>Edit</button>
                </g:link>
            </tr>
        </table>
    </g:each>
</div>
</body>
</html>