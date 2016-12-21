<#import "../../common/pagination-js.ftl" as pagination/>
<@override name="body">
<div class="container-fluid">

    <div class="panel panel-default">
        <div class="panel-heading">
            <ol class="breadcrumb transparent">
                <li><a href="/cms/apps">应用管理</a></li>
                <li class="active">权限列表</li>
            </ol>
        </div>
        <table class="table">
            <thead>
            <tr><th>ID</th><th>名称</th><th>是否需要权限</th><th>权限key</th><th>url</th><th>操作</th></tr>
            </thead>
            <tbody class="js-app-info-content">
            </tbody>
        </table>
        <@pagination.pagination_js
        pageNo=pageNo
        pageSize=pageSize
        url="/api/cms/perms"
        param_map=params?if_exists
        page_js="/static/js/auth/authority.js" />
    </div>
</div>

</@override>
<@extends name="/common/base.ftl"/>

</html>