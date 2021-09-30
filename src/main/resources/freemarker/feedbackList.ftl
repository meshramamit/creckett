<root>
<result><#assign tranStatus = status><#if tranStatus == 1>SUCCESS<#else>FAILED</#if></result>
<feedbacklist>
<#if feedbacklist?has_content> 
		<#list feedbacklist as feedback>
					<feedback name='${feedback.user.name}'
							  type='${feedback.feedbackType}'
							  msg='${feedback.feedbackMsg}'
							  created='${feedback.createdDate}'/>
		</#list>
</#if>		
</feedbacklist>
</root>