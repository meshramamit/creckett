<?xml version="1.0" encoding="UTF-8"?>
<root>
<result>SUCCESS</result>
<user userId="${user.id}" userName="${user.name}" emailId="${user.emailId}" loginCount="${user.loginCount}"/>
<currentTime>${dateUtils.getCurrentTimeString()}</currentTime>
<#if fbFriends?has_content>
<friends names="${fbFriends}" />
</#if>
<matches>
	<#list matches as matchWithMarket><#assign match = matchWithMarket.matchMaster><#assign team1 = match.team1><#assign team2 = match.team2>
	<match matchId='${match.id}' team1='${team1.teamName}' teamAlias1='${team1.teamAlias}'<#if team1.teamLogoPath?has_content> teamLogo1='${team1.teamLogoPath}' </#if>team2='${team2.teamName}' teamAlias2='${team2.teamAlias}'<#if team2.teamLogoPath?has_content> teamLogo2='${team2.teamLogoPath}' </#if> overs='${match.over}'  matchDateString="${dateUtils.formatDateAndTime(match.getMatchDate())}">
	<#if matchWithMarket.markets?has_content> 
	  <#list matchWithMarket.markets as market><market matchId='${match.id}' marketToken='${market.marketCode}' marketId='${market.id}' moderator='${market.moderatorId.name}' marketStart='<#if (market.timeRemain > 0)>Challenge starts ${dateUtils.getformated30MinutesBeforeMatchTime(market.match.getMatchDate())}<#else>Challenge open for Play</#if>' isModerator='<#if user.id==market.moderatorId.id>true<#else>false</#if>' enable="<#if (market.timeRemain>0)>false<#else>true</#if>" groupOverVal="${market.groupOverValue}" stackIndex="1" noOfUsers="${market.getNoOfUsers()}" noofUserToolTip="No. of users joined:: ${market.getUserTooltip()}" timeStartString="${market.getTimeStartString()}"/></#list>
	</#if>
	<#if matchWithMarket.invites?has_content>
	  <challenges> 
	  <#list matchWithMarket.invites as invite><market matchId='${match.id}' marketToken='${invite.marketCode}' marketId='${invite.id}' moderator='${invite.moderatorId.name}' marketStart='<#if (invite.timeRemain > 0)>Challenge starts ${dateUtils.getformated30MinutesBeforeMatchTime(invite.match.getMatchDate())}<#else>Challenge open for Play</#if>' isModerator='<#if user.id==invite.moderatorId.id>true<#else>false</#if>' enable="<#if (invite.timeRemain>0)>false<#else>true</#if>" groupOverVal="${invite.groupOverValue}" stackIndex="1" noOfUsers="${invite.getNoOfUsers()}" noofUserToolTip="No. of users joined:: ${invite.getUserTooltip()}" timeStartString="${invite.getTimeStartString()}"/></#list>
	  </challenges>
	</#if>		
	</match>
	</#list>
</matches>
<user_preferences>
    <show_video_tutorials value="<#if userpreference?has_content><#if userpreference.showVideoTutorialStr?has_content>${userpreference.showVideoTutorialStr}<#else>Y</#if><#else>Y</#if>"/>
    <show_assistant value="<#if userpreference?has_content><#if userpreference.showAssistantStr?has_content>${userpreference.showAssistantStr}<#else>Y</#if><#else>Y</#if>"/>
    <terms_accepted value="<#if userpreference?has_content><#if userpreference.termsAcceptedStr?has_content>${userpreference.termsAcceptedStr}<#else>N</#if><#else>Y</#if>"/>
</user_preferences>
<#if marketStatus?has_content><MarketStatus>${marketStatus}</MarketStatus></#if>
</root>
