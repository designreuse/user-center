<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link href="/static/css/bootstrap.min.css"  rel="stylesheet">
    <link href="/static/css/bootstrap-theme.min.css"  rel="stylesheet">
    <link href="/static/css/customer.css"  rel="stylesheet">
    <script src="/static/js/jquery-3.1.1.min.js" type="application/javascript"></script>
    <script src="/static/js/bootstrap.min.js" type="application/javascript"></script>
    <@block name="head"></@block>
</head>
<body>
    <@block name="header">
        <nav class="navbar navbar-blue navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">Club</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <#if springMacroRequestContext.requestUri?matches("/cms[/]*\\w*")>
                            <@shiro.hasRole name="admin">
                                <li <#if springMacroRequestContext.requestUri == "/cms/apps">class="active"</#if>><a href="/cms/apps">应用管理</a></li>
                            </@shiro.hasRole>
                        <#--<li <#if springMacroRequestContext.requestUri == "/cms/perms">class="active"</#if>><a href="/cms/perms">权限管理</a></li>-->
                            <@shiro.hasAnyRoles name="admin,manager">
                                <li <#if springMacroRequestContext.requestUri == "/cms/roles">class="active"</#if>><a href="/cms/roles">角色管理</a></li>
                                <li <#if springMacroRequestContext.requestUri == "/cms/users">class="active"</#if>><a href="/cms/users">用户管理</a></li>
                            </@shiro.hasAnyRoles>
                        </#if>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <#if online??>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${online.name}<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#">个人信息</a></li>
                                    <li><a href="#">修改密码</a></li>
                                    <li><a href="#">消息 <span class="badge">4</span></a></li>
                                    <li class="divider"></li>
                                    <@shiro.hasAnyRoles name="admin,manager">
                                        <li><a href="/cms/index">权限管理</a></li>
                                        <li class="divider"></li>
                                    </@shiro.hasAnyRoles>
                                    <li><a href="/logout">登出</a></li>
                                </ul>
                            </li>
                        <#else>
                            <li><a href="/login">登录</a></li>
                            <li><a href="#">注册</a></li>
                        </#if>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </@block>
    <@block name="body">
        <div class="container-fluid">
            <div class="panel panel-default">
                <div class="panel-body">
                    Basic panel example
                </div>
            </div>
        </div>
    </@block>
    <@block name="footer">
        <div class="panel-footer navbar-fixed-bottom">
            <p class="text-center">友情链接:
                <a href="http://www.yingchengpeng.com" target="_blank">yingchengpeng</a>
                <a href="http://www.bootcss.com" target="_blank">Bootstrap</a>
            </p>
            <p class="text-center">&copy;Ycp 2016.12.17</p>
        </div>
    </@block>
</body>
</html>