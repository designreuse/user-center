<#macro authority_from currentAppId >
<script src="/static/js/serialize.js"></script>
<div class="modal fade" id="authorityModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">权限</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal data-info" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                            <input type="hidden" name="id">
                            <input type="hidden" name="pid" value="0">
                            <input type="hidden" name="appId" value="${currentAppId}">
                            <input type="text" class="form-control" name="name" placeholder="名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">权限类型</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="auth" value="anon"> 白名单
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="auth" value="authc"> 权限
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">权限值</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="permKey" placeholder="权限值，例如 auth:user:list">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色值</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="roleKey" placeholder="角色值，例如 admin">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">URI</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="url" placeholder="URI">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer add-footer">
                <button type="button" class="btn btn-default js-cancel" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary js-add">添加</button>
                <button type="button" class="btn btn-primary js-update">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</#macro>