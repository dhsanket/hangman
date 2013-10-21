<%@ page import="com.hangman.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'userId', 'error')} ">
	<label for="userId">
		<g:message code="game.userId.label" default="User Id" />
		
	</label>
	<g:textField name="userId" value="${gameInstance?.userId}"/>
</div>

