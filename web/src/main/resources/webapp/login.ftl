<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    <p> login page !!</p>
    <div>
    <#if param??>
        <#if param.error??>
            用户名或密码错
        <#elseif param.logout??>
            您已注销成功
        </#if>
    <#else>
        无错误
    </#if>
    </div>
    <form action="/login" method="post">
        登录名:<input type="text" name="username">
        密  码:<input type="password" name="password">
        <button type="submit" value="登录">登录</button>
    </form>
</div>
</body>

</html>