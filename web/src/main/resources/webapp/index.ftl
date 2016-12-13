<html>
<head>
    <title>${title}</title>
</head>
<body>
<p> index page !!</p>
<@shiro.hasRole name="admin">admin<br/></@shiro.hasRole>
<@shiro.hasRole name="manager">user<br/></@shiro.hasRole>

</body>

</html>