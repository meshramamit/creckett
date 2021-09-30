<betStatsData>
<#if sessionbets?has_content><#list sessionbets as sessionBet><session${sessionBet.session}>
<#if sessionBet.bets?has_content><#list sessionBet.bets?keys as betover>
<betStats over='${betover}'<#if sessionBet.bets[betover].userBets?has_content><#list sessionBet.bets[betover].userBets?keys as user> ${user}='${sessionBet.bets[betover].userBets[user]}'</#list></#if>/></#list></#if>
<#if sessionBet.users?has_content><userDisplay<#list sessionBet.users?keys as user> ${sessionBet.users[user]}='${user}'</#list>/></#if>
</session${sessionBet.session}></#list></#if>
</betStatsData>