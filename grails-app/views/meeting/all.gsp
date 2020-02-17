<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <asset:javascript src="index.js"/>
    <asset:stylesheet href="style.css"/>
</head>

<body>
<div id="wrapper">
    <g:render template="/layouts/nav" />
    <div id="content">
        <div id="meetings">
            <g:each in="${meetings}">
                Subject: ${it.subject}<br>
                Starts at: ${it.start}<br>
                Ends at: ${it.end}<br>
                Participants:<br>
                <g:each in="${it.participants}">
                    ${it.name} <br/>
                </g:each>
            </g:each>
        </div>

        <div id="pagination">
            <g:each in="${pages}" var="pageNumber">
                <g:link controller="meeting" action="upcoming" params="${[offset: pageSize * (pageNumber - 1)]}">
                    ${pageNumber}
                </g:link>
            </g:each>
        </div>
    </div>
</div>
</body>
</html>