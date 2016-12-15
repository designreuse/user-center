<html>
<head>
    <title>${title}</title>
</head>
<body>
<p> index page !!</p>
<@shiro.hasRole name="admin">admin<br/></@shiro.hasRole>
<@shiro.hasRole name="manager">user<br/></@shiro.hasRole>
<a href="/cms/user/list">用户页</a>
<a href="/cms/app/list">应用页</a>
<a href="/cms/authority/list?appId=1">权限页</a>

</body>

</html>