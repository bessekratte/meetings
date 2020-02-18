<asset:javascript src="index.js"/>
<asset:stylesheet href="nav.css"/>
<div id="nav">
    <ul class="unordered-list">
        <li class="list-item"><a href="${createLink(controller: "meeting", action: 'upcoming')}">Meetings</a>
        <sec:ifLoggedIn>
            <li class="list-item"><a href="${createLink(controller: "meeting", action: 'create')}">Create meeting</a>
            <li class="list-item"><a href="${createLink(controller: "meeting", action: 'userMeetings')}">User Meetings</a>
            <li class="list-item-right">logged as ${user.username}<br><g:link controller='logout'>Logout</g:link></li>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <li class="list-item-right"><g:link controller='user' action="lockedUsers">Locked Users</g:link></li>
            </sec:ifAllGranted>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li class="list-item-right"><a href="${createLink(controller: "login", action: 'auth')}">Login</a></li>
        </sec:ifNotLoggedIn>

    </ul>
</div>
