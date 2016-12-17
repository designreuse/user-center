<#import "../../common/pagination.ftl" as pagination/>
<@override name="body">
<div class="container-fluid">

    <div class="panel panel-default">
        <div class="panel-heading">
            <ol class="breadcrumb transparent">
                <li><a href="/cms/apps">应用管理</a></li>
                <li class="active">应用列表</li>
            </ol>
        </div>
        <table class="table">
            <thead>
            <tr><th>应用ID</th><th>应用名称</th><th>应用Key</th><th>应用域名</th><th>操作</th></tr>
            </thead>
            <tbody>
                <#list apps.datas as app>
                    <tr data="${app}">
                        <td>${app.id}</td>
                        <td>${app.name}</td>
                        <td>${app.key}</td>
                        <td>${app.domain}</td>
                        <td>
                            <a href="/cms/perms?appId=${app.id}">权限列表</a>
                            <a href="#">编辑</a>
                            <a href="#">删除</a>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <@pagination.page pageNo=apps.pageNo pageSize=apps.pageSize total=apps.total url="/cms/apps" />
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>

</html>