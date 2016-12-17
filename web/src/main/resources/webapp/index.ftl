<@override name="body">
<div class="container">
    index
    <@shiro.hasRole name="admin">admin<br/></@shiro.hasRole>
    <@shiro.hasRole name="manager">user<br/></@shiro.hasRole>
</div>
</@override>
<@extends name="/common/base.ftl"/>