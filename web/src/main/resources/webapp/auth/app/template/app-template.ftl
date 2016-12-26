<#macro app_from >
<script src="/static/js/serialize.js"></script>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加应用</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal app-add" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="addName" name="name" placeholder="名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">域名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="addDomain" name="domain" placeholder="域名">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer add-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary js-app-add">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑应用</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal app-update" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name" placeholder="名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="domain" class="col-sm-2 control-label">域名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="domain" name="domain" placeholder="域名">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary js-app-update">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</#macro>