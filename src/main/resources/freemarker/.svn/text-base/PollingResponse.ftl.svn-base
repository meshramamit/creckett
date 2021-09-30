<?xml version="1.0" encoding="UTF-8"?>
<root>
<pollingResponse>
<#if invalidatedOver?has_content><current session='${invalidatedOver.sessionId}' over='${invalidatedOver.matchOver}'<#if (invalidatedOver.matchOver>0)> invalidate="true" freezeTime="${overInvalidationTime}"</#if>/></#if>
<#if blackListOversList?has_content><groupIdBlackList <#list blackListOversList?keys as sessions>s${sessions}='${blackListOversList[sessions]}'</#list>/></#if>
<#if betStates?has_content><#list betStates as sessionBet><${sessionBet.session}>
<#list sessionBet.bets as bet>
<over name='Over ${bet.over} (<#if bet.winningBet?has_content>${bet.winners}<#else>Awaiting...</#if>)' groupId='${bet.groupOverId}' resultRun='<#if (bet.winningBet?has_content)><#if (bet.overScore.runs?has_content)>${bet.overScore.runs}</#if></#if>' resultWkt='<#if (bet.winningBet?has_content)><#if (bet.overScore.wickets?has_content)>${bet.overScore.wickets}</#if></#if>' show='false'  expand='false' update='true'>
<#if bet.userBetStates?has_content><#list bet.userBetStates as userBet>
<user name='${userBet.userName}' runs='<#if userBet.runsBet?has_content>${userBet.runsBetDisplay}</#if>' resultRun='<#if userBet.runs?has_content>${userBet.runs}</#if>' wickets='<#if userBet.wicketsBet?has_content><#if (userBet.wicketsBet>=0)>${userBet.wicketsBet}</#if></#if>' resultWkt='<#if userBet.wickets?has_content>${userBet.wickets}</#if>' resultAmount='${userBet.resultAmount}' timeFactor='<#if userBet.timefactor?has_content>${userBet.timefactor}</#if>' isWicketsWinner='<#if userBet.isWicketsWinner?has_content>${userBet.isWicketsWinner}</#if>' isRunsWinner='<#if userBet.isRunsWinner?has_content>${userBet.isRunsWinner}</#if>'/> 
</#list>
</#if>
</over>
</#list>
</${sessionBet.session}>
</#list>
</#if>
<#if amountLeft?has_content><amountLeft>
<#list amountLeft as userAmount><user name='${userAmount.userId.name}' userId="${userAmount.userId.id}" amount='${userAmount.leftAmount}'/>
</#list>
</amountLeft>
</#if>
<#if latestScore?has_content><matchScore matchString="${latestScore.matchId.description}" score="${latestScore.score}" overs="Over ${latestScore.matchOver}"/></#if>
<#if matchStatus?has_content><matchStatus>${matchStatus}</matchStatus></#if>
</pollingResponse>
</root>