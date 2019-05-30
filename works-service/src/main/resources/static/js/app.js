function app() {

    this.grid_selector = "#grid-table";
    this.pager_selector = "#grid-pager";
    this.curEditId = "";

    this.pageSize = function () {
        let winW, winH;
        if (window.innerHeight) {
            winW = window.innerWidth;
            winH = window.innerHeight;
        } else if (document.documentElement && document.documentElement.clientHeight) {
            winW = document.documentElement.clientWidth;
            winH = document.documentElement.clientHeight;
        } else if (document.body) {
            winW = document.body.clientWidth;
            winH = document.body.clientHeight;
        }
        return {
            WinW: winW,
            WinH: winH
        };
    };

    this.parent_column = $(this.grid_selector).closest('[class*="col-"]');

    this.resize = function(offset){
        let that = this;
        $(window).on('resize.jqGrid', function () {
            $(that.grid_selector).jqGrid('setGridWidth', that.parent_column.width());
            let pageObj = that.pageSize();
            $(that.grid_selector).jqGrid('setGridHeight', pageObj.WinH - offset);

        });

        $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
            if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
                setTimeout(function () {
                    $(that.grid_selector).jqGrid('setGridWidth', that.parent_column.width());
                    let pageObj = that.pageSize();
                    $(that.grid_selector).jqGrid('setGridHeight', pageObj.WinH - offset);
                }, 20);
            }
        });
    };

    this.updatePagerIcons = function () {
        let replacement = {
            'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
            'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
            'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
            'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            let icon = $(this);
            let $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        })
    };

    this.enableTooltips = function (table) {
        $('.navtable .ui-pg-button').tooltip({
            container: 'body'
        });
        $(table).find('.ui-pg-div').tooltip({
            container: 'body'
        });
    };

    //刷新表格
    this.reloadGrid = function(url) {
        jQuery("#grid-table").jqGrid('setGridParam', {
            url: url,
            page: 1
        }).trigger("reloadGrid");
    };

    this.reloadGrid = function(url){
        let page = $(this.grid_selector).getGridParam('page');
        jQuery(this.grid_selector).jqGrid('setGridParam',{url:url,page:page}).trigger("reloadGrid");
    };

}
