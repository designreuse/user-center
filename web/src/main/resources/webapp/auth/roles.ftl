<#import "../common/pagination.ftl" as pagination/>
<@override name="body">
<div class="container-fluid">

    <div class="panel panel-default">
        <div class="panel-heading">
            <ol class="breadcrumb transparent">
                <li><a href="/cms/roles">角色管理</a></li>
                <li class="active">角色列表</li>
            </ol>
        </div>
        <table class="table">
            <thead>
            <tr><th>ID</th><th>角色名</th><th>操作</th></tr>
            </thead>
            <tbody>
                <#list roles.datas as role>
                <tr><td>${role.id}</td><td>${role.name}</td></tr>
                </#list>
            </tbody>
        </table>
        <@pagination.page pageNo=roles.pageNo pageSize=roles.pageSize total=roles.total url="/cms/users" />
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>

</html>