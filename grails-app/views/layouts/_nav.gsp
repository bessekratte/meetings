<asset:javascript src="index.js"/>
<asset:stylesheet href="nav.css"/>
<div id="nav">
    <ul class="unordered-list">
        <li class="list-item"><a href="${createLink(controller: "meeting", action: 'upcoming')}">Meetings</a>
        <li class="list-item"><a href="${createLink(controller: "meeting", action: 'create')}">Create meeting</a></li>
        </li>
        <sec:ifLoggedIn>
            <li class="list-item-right">logged as ${user.username}</li>
            <li class="list-item-right"><g:link controller='logout'>Logout</g:link></li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li class="list-item-right"><a href="${createLink(controller: "login", action: 'auth')}">Login</a></li>
        </sec:ifNotLoggedIn>

    </ul>
</div>
