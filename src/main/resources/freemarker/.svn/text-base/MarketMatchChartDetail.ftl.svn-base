<?xml version="1.0" encoding="UTF-8"?>
<#compress>
<root>
<result>SUCCESS</result>
<betStatsData>
	<#list sessions as matchSession>
	<${matchSession.sessionId}>
		<#list matchSession.marketUserAmountDetail as marketMatchAmount>
		<betStats over='${marketMatchAmount.over}' <#list marketMatchAmount.userAmountDetail?keys as user>${user}='${marketMatchAmount.userAmountDetail[user]}' </#list> />
		</#list>
		<userDisplay <#list matchSession.marketUserDetail?keys as user> ${matchSession.getMarketUserDetailValue(user)}='${user.name}' </#list> />
	</${matchSession.sessionId}>	
	</#list>
</betStatsData>
</root>
</#compress>