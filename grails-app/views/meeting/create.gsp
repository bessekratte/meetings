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
        <g:if test="${alreadyMeetings?:false}">
            This date is already taken
        </g:if>
        <g:form action="save">
            <label for="subject">Subject:</label><br>
            <input type="text" name="subject" value="" id="subject"><br>
            <label for="start">Starts:</label><br>
            <g:datePicker name="start" precision="minute" years="${2020..2021}"/> <br>
            <label for="end">Ends:</label><br>
            <g:datePicker name="end" precision="minute" years="${2020..2021}"/> <br>
            <g:submitButton name="create" value="Create"/>
        </g:form>
        <g:hasErrors bean="${this.meeting}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.meeting}" var="error">
                    <li
                        <g:if test="${error in org.springframework.validation.FieldError}">
                            data-field-id="${error.field}"</g:if>>
                        <g:message error="${error}"/>
                    </li>
                </g:eachError>
            </ul>
        </g:hasErrors>
    </div>
</div>
</body>
</html>