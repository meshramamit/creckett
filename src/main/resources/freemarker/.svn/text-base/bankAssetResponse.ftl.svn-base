<marketHistory>
<#if userMatchAmountLeftDTOs?has_content><#list userMatchAmountLeftDTOs as userMatchAmountLeftDTO>
<user name='${userMatchAmountLeftDTO.userInfo.userName}' bankasset='${userMatchAmountLeftDTO.userInfo.bankAsset}' matchesPlayed='${userMatchAmountLeftDTO.userInfo.matchesPlayed}' avg='${userMatchAmountLeftDTO.userInfo.average}'>
<#list userMatchAmountLeftDTO.matchAmountInfos as matchAmountInfo>
 <match name='${matchAmountInfo.matchName}' matchDate='${matchAmountInfo.matchDate}' crecks='${matchAmountInfo.crecks}' />
</#list>
</user> 
</#list>
</#if>
</marketHistory>

