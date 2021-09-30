<result>
<#if score?has_content>
	<score over="${score.over}" run="${score.run}" wicket="${score.wicket}" battingSide="${score.battingSide}"/>
</#if>
<#if commentaries?has_content>
	<commentaries>
		<#list commentaries as commentary>
			<commentary id="${commentary.commentaryId}" 
						text="${commentary.commentaryText}"/>
		</#list> 
	</commentaries>
</#if>
</result>