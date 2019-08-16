(function($) {
    var defaultConfig = {
        version: "V1.0.0",
        config: {
            datagrid: {
                size: "rows",
                page: "page",
                rows: "rows",
                total: "total"
            },
            postJson: !0,
            appendRefererParam: !1,
            statusCode: {
                success: 200,
                fail: 300
            }
        },
        language: {
            message: {
                showType: {
                    slide: "slide",
                    fade: "fade",
                    show: "show"
                },
                title: {
                    operationTips: "操作提示",
                    confirmTips: "确认提示"
                },
                msg: {
                    success: "操作成功",
                    failed: "操作失败",
                    error: "未知错误",
                    unSelect: "请先选择要操作的数据",
                    singleSelect: "只能选择一条要操作的数据",
                    checkSelfGrid: "请先勾选要操作的数据",
                    selectSelfGrid: "请先选中要操作的一条数据",
                    selectParentGrid: "请先选中主表中要操作的一条数据",
                    permissionDenied: "对不起，你没有操作权限",
                    confirmDelete: "你确定要删除所选的数据吗？",
                    confirmMsg: "确定要执行该操作吗？",
                    noData: "没有查询到数据"
                },
                icon: {
                    error: "messager-error",
                    question: "messager-question",
                    info: "messager-info",
                    warning: "messager-warning"
                }
            },
            edatagrid: {
                destroyMsg: {
                    norecord: {
                        title: "操作提示",
                        msg: "没有选中要操作的记录！"
                    },
                    confirm: {
                        title: "操作确认",
                        msg: "确定要删除选中的记录吗？"
                    }
                }
            }
        },
    }

    window['bcap'] = defaultConfig;
})(jQuery)