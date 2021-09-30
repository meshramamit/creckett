<?xml version="1.0" encoding="UTF-8"?>
<matches>
<#if match?has_content>
<result>SUCCESS</result>
<#assign team1 = match.team1><#assign team2 = match.team2>
<match result='SUCCESS' matchId='${match.id}' team1='${team1.teamName}' teamAlias1='${team1.teamAlias}'<#if team1.teamLogoPath?has_content> teamLogoPath1='${team1.teamLogoPath}' </#if> team2='${team2.teamName}' teamAlias2='${team2.teamAlias}'<#if team2.teamLogoPath?has_content> teamLogoPath2='${team2.teamLogoPath}' </#if> overs='${match.over}' matchDate="${dateUtils.formatDateInDDMMYYYY(match.getMatchDate())}" matchTime="${dateUtils.formatSimpleTime(match.getMatchDate())}" matchDateString="${dateUtils.formatDateAndTime(match.getMatchDate())}"/>
<#else>
<match result='FAILURE'/>
</#if>
</matches>