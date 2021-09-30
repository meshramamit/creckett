<?xml version="1.0" encoding="UTF-8"?>
<root>
<#if matchScoresMap?has_content>
<match matchId='${matchId}'>
<#list matchScoresMap?keys as sessionId>
<session${sessionId}>
<#assign matchScores=matchScoresMap[sessionId]>
<#list matchScores as matchScore> 
<overs invalidate='${matchScore.invalidate?string}' <#if matchScore.matchOver?has_content> over='${matchScore.matchOver}' </#if> <#if matchScore.runs?has_content> runs='${matchScore.runs}' </#if> <#if matchScore.wickets?has_content> wickets='${matchScore.wickets}'</#if> <#if matchScore.score?has_content> score='${matchScore.score}'</#if>/>
</#list>
</session${sessionId}>
</#list>
</match>
</#if>
</root> 