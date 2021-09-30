<?xml version="1.0" encoding="UTF-8"?>
<root>
<#list authenticators as authenticator>
	<authProvider name='${authenticator.getAuthProvider()}' url='${authenticator.getLoginURL()}'/>
</#list>
</root>