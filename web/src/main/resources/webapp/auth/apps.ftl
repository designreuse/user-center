<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    <p> app page !!</p>
    <table>
        <thead>
        <tr><th>应用名称</th><th>应用Key</th><th>应用域名</th></tr>
        </thead>
        <tbody>
        <#list apps as app>
            <tr><td>${app.name}</td><td>${app.key}</td><td>${app.domain}</td></tr>
        </#list>
        </tbody>
    </table>

</div>
</body>

</html>