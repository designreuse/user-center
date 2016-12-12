<html>
<head>
    <title>${title}</title>
</head>
<body>
<p> 500 page !!</p>

error:${error?if_exists}<br>
status:${status?if_exists}<br>
exception:${exception?if_exists}<br>
message:${message?if_exists}<br>
<#if stackTrace??>
    stackTrace:<br>
    <#list stackTrace as st>
        ${st}<br>
    </#list>
</#if>
</body>

</html>