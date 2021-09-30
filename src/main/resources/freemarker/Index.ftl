<?xml version="1.0" encoding="UTF-8"?>
<matches>
	<#list listOfUpcomingMatches as match><#assign team1 = match.team1><#assign team2 = match.team2>
	<match matchId='${match.id}' team1='${team1.teamName}' teamAlias1='${team1.teamAlias}'<#if team1.teamLogoPath?has_content> teamLogo1='${team1.teamLogoPath}' </#if>team2='${team2.teamName}' teamAlias2='${team2.teamAlias}'<#if team2.teamLogoPath?has_content> teamLogo2='${team2.teamLogoPath}' </#if> overs='${match.over}' matchDateString="${dateUtils.formatDateAndTime(match.getMatchDate())}">
	</match>
	</#list>
</matches>