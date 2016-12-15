<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    <p> authority page !!</p>
    <table>
        <thead>
        <tr><th>名称</th><th>是否需要权限</th><th>权限key</th><th>url</th></tr>
        </thead>
        <tbody>
        <#list authorities as authority>
            <tr><td>${authority.name}</td><td>${authority.auth}</td><td>${authority.permKey?if_exists}</td><td>${authority.url}</td></tr>
        </#list>
        </tbody>
    </table>

</div>
</body>

</html>