<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    <p> login page !!</p>
    <div>
    <#if message??>
        ${message}
    </#if>
    </div>
    <form action="/login" method="post">
        <input type="hidden" name="remember" value="true">
        <input type="hidden" name="target" value="">
        登录名:<input type="text" name="username">
        密  码:<input type="password" name="password">
        <button type="submit" value="登录">登录</button>
    </form>
</div>
</body>

</html>