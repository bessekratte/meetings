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
        <div id="meetings">
            <g:set var="isFirst" value="${true}"/>
            <g:each in="${meetings}">
                <g:if test="${isFirst}">
                    <table class="first-item">
                    <g:set var="isFirst" value="${false}"/>
                </g:if>
                <g:else>
                    <table>
                </g:else>
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
                <sec:ifLoggedIn>
                    <td>Participants</td><br>
                    <td>
                        <g:if test="${!it.participants.contains(user)}">
                            <g:link controller="meeting" action="addUserToMeeting"
                                    params="${[id: it.id]}">
                                <button>Join meeting</button>
                            </g:link>
                        </g:if>
                    </td>
                    </tr>
                    <g:each in="${it.participants}">
                        <tr>
                            <td>- ${it.username}</td>
                        </tr>
                    </g:each>
                    </tr>
                </sec:ifLoggedIn>
                </table>
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