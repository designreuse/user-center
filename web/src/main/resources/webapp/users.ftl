<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    <p> user page !!</p>
    <table>
        <thead>
        <tr><th>姓名</th></tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr><td>${user.name}</td></tr>
        </#list>
        </tbody>
    </table>
</div>
</body>

</html>