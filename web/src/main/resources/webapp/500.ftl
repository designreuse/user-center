<html>
<head>
    <title>${title}</title>
</head>
<body>
<p> 500 page !!</p>

code:${code?if_exists}<br>
message:${message?if_exists}<br>
stackTrace:<br>
<#list stackTrace as st>
    ${st}<br>
</#list>
</body>

</html>