//js object for common methods
var common_js = common_js || {};
//js object for customer orders page
var buildings_js = buildings_js || {};

common_js.common_js = common_js.common_js || function () {


    // var checkImportStatus = function (jobId) {
    //     var param = {
    //         'jobId': jobId
    //     };
    //     ajaxUtil.ajaxUtil.ajaxPost(checkImportStatusUrl, param, true, showImportResult, showErrors);
    // };
    // var showImportResult = function (data) {
    //     var completed = data['completed'];
    //     var msg = data['msg'];
    //     var jobId = data['jobId'];
    //     if (completed == true) {
    //         $.unblockUI();
    //         if (msg != undefined && msg != '') {
    //             alert(msg);
    //             window.location.reload();
    //         }
    //     } else {
    //         setTimeout(checkImportStatus, waitTime, jobId);
    //     }
    // };
    // var showErrors = function (failures) {
    //     alert("与服务器通信失败，请重试（若一直失败，请联系系统管理员）。");
    // };

    //数据隔行变色
    $(document).ready(function () {
        $("#product-table tr:even").css("background-color", "#ececec");
    });


    return{

        //阻塞画面
        blockUI: function () {
            $.blockUI({
                message: '<h1>请稍候...<h1>',
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .5,
                    color: '#fff'
                }
            });
        },
        //画面输入框初始化
        reset: function () {
            $("input:text").removeAttr('value');
        },
        //日期选择框初始化
        initDatePicker: function () {
            $('#start-date-pick').datePicker(
                    {
                        startDate: '01/01/2005',
                    })
                    .bind(
                            // when the link is clicked display the date picker
                            'click',
                            function ()
                            {
                                $(this).dpDisplay();
                                return false;
                            }
                    ).bind(
                    // when a date is selected update the SELECTs
                    'dateSelected',
                    function (e, selectedDate, $td)
                    {
                        var formatedDate = formatDate(selectedDate);
                        $('#startDate').val(formatedDate);
                    }
            ).bind(
                    'dpClosed',
                    function (selected)
                    {
                    }
            );
            $('#end-date-pick')
                    .datePicker({
                        startDate: '01/01/2005',
                    }).bind(
                    'click',
                    function ()
                    {
                        $(this).dpDisplay();
                        return false;
                    }
            ).bind(
                    // when a date is selected update the SELECTs
                    'dateSelected',
                    function (e, selectedDate, $td)
                    {
                        var formatedDate = formatDate(selectedDate);
                        $('#endDate').val(formatedDate);
                    }
            ).bind(
                    'dpClosed',
                    function (e, selected)
                    {
                    }
            );

            var formatDate = function (selectedDate)
            {
                var date = new Date(selectedDate);
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var day = date.getDate();
                if (month < 10) {
                    month = '0' + month;
                }
                if (day < 10) {
                    day = '0' + day;
                }
                var formatDate = year.toString() + month.toString() + day.toString();
                return formatDate;
            };
        },
    };
}();
buildings_js.buildings_js = buildings_js.buildings_js || function () {
    var defaults = {
        Empty: '',
        JAVASCRIPT0: 'javascript:void(0);',
        InitUrl: '/buildings/allbuildings',

    };
    var options = {};
    // var changeTableData = function (data) {
    //     alert(data);
    //     $.each(data[''], function (i, v) {
    //         $('#product-table').append(
    //                 '<tr class="csutomerOrders_data">\n\
    //                         <td><input type="checkbox" id="cbx' + i + '"/></td>\n\
    //                         <td>' + v["jianhao"] + '<input type="hidden" id="jianhao' + i + '" value="' + v["jianhao"] + '"/></td>\n\
    //                         <td>' + v["base"] + '<input type="hidden" id="base' + i + '" value="' + v["base"] + '"/></td>\n\
    //                         <td>' + v["mdl"] + '<input type="hidden" id="mdl' + i + '" value="' + v["mdl"] + '"/></td>\n\
    //                         <td>' + $msc + '<input type="hidden" id="msc' + i + '" value="' + v["msc"] + '"/></td>\n\
    //                         <td>' + v["ext"] + '<input type="hidden" id="ext' + i + '" value="' + v["ext"] + '"/></td>\n\
    //                         <td>' + v["final_on"] + '<input type="hidden" id="final_on' + i + '" value="' + v["final_on"] + '"/></td>\n\
    //                         <td>' + v["counts"] + '<input type="hidden" id="counts' + i + '" value="' + v["counts"] + '"/></td>\n\
    //                         </tr>');
    //     });
    //     $('input').checkBox();
    //     $('#toggle-all').removeClass('toggle-checked');
    //     $("#product-table tr:even").css("background-color", "#ececec");
    // };
    var InitData = function (data) {
        alert(data);
        var building_id = data['building_id'];
        sessionStorage.removeItem('building_id');
        sessionStorage.setItem('building_id', JSON.stringify(building_id));
        alert(building_id);
        //window.open(common_js.common_js.WINDOWS_OPEN_AONURL, common_js.common_js.WINDOWS_OPEN_NAME, common_js.common_js.WINDOWS_OPEN_PROPERTY);
    };
    var showErrors = function (failResult) {
        alert("与服务器通信失败，请重试（若一直失败，请联系系统管理员）。");
    };

    return {
        // 画面初期表示
        init: function (opts) {
            options = $.extend({}, defaults, opts);
            var param = [];
            alert('i m in js now');
            ajaxUtil.ajaxUtil.ajaxPost(options.InitUrl, param, true, InitData, showErrors);
        },

        //根据条件显示客户订单
        getCustomersByPara: function () {

            ajaxUtil.ajaxUtil.ajaxPost(options.ShowOrderByParaUrl, param, true, changeTableData, showErrors);
        },
    };
}();