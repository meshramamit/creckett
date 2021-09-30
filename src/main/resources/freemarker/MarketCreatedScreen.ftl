<?xml version="1.0" encoding="UTF-8"?>
<root>
	<result>SUCCESS</result>
	<message>Challenge created successfully.</message>
	<currentTime>${dateUtils.getCurrentTimeString()}</currentTime>
	<market matchId='${market.match.id}' marketToken='${market.marketCode}' marketId='${market.id}' moderator='${market.moderatorId.name}' marketStart='<#if (market.timeRemain > 0)>Market starts ${dateUtils.getformated30MinutesBeforeMatchTime(market.match.getMatchDate())}<#else>Challenge open for Play</#if>' isModerator='<#if user.id==market.moderatorId.id>true<#else>false</#if>' enable="<#if (market.timeRemain > 0)>false<#else>true</#if>" groupOverVal="${market.groupOverValue}" stackIndex="1" noOfUsers="${market.getNoOfUsers()}" noofUserToolTip="No. of users joined:: ${market.getUserTooltip()}" timeStartString="${market.getTimeStartString()}"/>
</root>