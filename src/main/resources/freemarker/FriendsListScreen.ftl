<root>
<result>SUCCESS</result>
<friends>
<#if friends?has_content><#list friends as friend><friend name='${friend.friendName}' imageurl='${friend.profilePhotoLink}'/></#list></#if>		
</friends>
</root>