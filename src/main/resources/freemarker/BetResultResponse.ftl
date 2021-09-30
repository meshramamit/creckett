<betResultResponse>
<#if betStates?has_content><#list betStates as sessionBet><${sessionBet.session}>
<#list sessionBet.bets as bet>
<over name='Over ${bet.over}<#if bet.overScore.score?has_content><#if bet.winners?has_content> (${bet.winners})<#else></#if><#else> (Awaiting...)</#if>' groupId='${bet.groupOverId}' resultRun='<#if (bet.overScore.score?has_content)><#if (bet.overScore.runs?has_content)>${bet.overScore.runs}</#if></#if>' resultWkt='<#if (bet.overScore.score?has_content)><#if (bet.overScore.wickets?has_content)>${bet.overScore.wickets}</#if></#if>' show='false'  expand='false' update='true'>
<#if bet.userBetStates?has_content><#list bet.userBetStates as userBet>
<user name='${userBet.userName}' runs='<#if userBet.runsBet?has_content>${userBet.runsBet}</#if>' resultRun='<#if userBet.runs?has_content>${userBet.runs}</#if>' wickets='<#if userBet.wicketsBet?has_content>${userBet.wicketsBet}</#if>' resultWkt='<#if userBet.wickets?has_content>${userBet.wickets}</#if>' resultAmount='${userBet.resultAmount}'/> 
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
<#if matchStatus?has_content><matchStatus>${matchStatus}</matchStatus></#if>
</betResultResponse>
