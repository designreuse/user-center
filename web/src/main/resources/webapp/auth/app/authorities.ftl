<#import "../../common/pagination-tag.ftl" as pagination/>
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
            <tbody>
                <#list authorities.datas as authority>
                <tr><td>${authority.id}</td><td>${authority.name}</td><td>${authority.auth}</td><td>${authority.permKey?if_exists}</td><td>${authority.url}</td></tr>
                </#list>
            </tbody>
        </table>
        <@pagination.page pageNo=authorities.pageNo pageSize=authorities.pageSize total=authorities.total url="/cms/perms" params="?appId=${appId?if_exists}" />
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>

</html>