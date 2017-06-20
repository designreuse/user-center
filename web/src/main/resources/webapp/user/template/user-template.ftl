<#macro user_from >
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal data-info" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label"> 名称</label>
                        <div class="col-sm-10">
                            <input type="hidden" name="id">
                            <input type="text" class="form-control" name="name" placeholder="名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"> 用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="userName" placeholder="用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"> 密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"> 邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" placeholder="邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"> 手机号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="mobile" placeholder="手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"> QQ</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="qq" placeholder="QQ">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"> 外部ID</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="outerId" placeholder="外部ID">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer add-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary js-add">添加</button>
                <button type="button" class="btn btn-primary js-update">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</#macro>