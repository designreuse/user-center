<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link href="/static/css/bootstrap.min.css"  rel="stylesheet">
    <link href="/static/css/customer.css"  rel="stylesheet">
    <script src="/static/js/jquery-3.1.1.min.js" type="application/javascript"></script>
    <script src="/static/js/bootstrap.min.js" type="application/javascript"></script>
<@block name="head"></@block>
</head>
<body class="login-body">
    <div class="container">
        <div class="sign-in-inner">
            <p class="h3 mb-50 text-center">用户中心-登录</p>
            <form class="form-horizontal" role="form" action="/login" method="post">
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <input name="username" type="text" class="form-control" placeholder="用户名">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <input name="password" type="password" class="form-control" placeholder="密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input name="remember" type="checkbox"> 记住我
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-10 mb-10 text-center">
                        <button type="submit" class="btn btn-info btn-md btn-block">登录</button>
                    </div>
                    <div class="col-sm-offset-1 col-sm-10 text-center">
                        <a href="/forgot">忘记密码</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>