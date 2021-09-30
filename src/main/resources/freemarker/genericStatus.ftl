<?xml version="1.0" encoding="UTF-8"?>
<root>
<result><#assign tranStatus = status><#if tranStatus == 1>SUCCESS<#else>FAILURE</#if></result>
<#if message?has_content><message>${message}</message></#if>
</root> 