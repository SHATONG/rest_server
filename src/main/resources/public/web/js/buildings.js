/**
 * Created by shatong on 2017/07/19.
 */

//在信息输入表后追加两行
function project_numbers() {
    var selectValue = $('.project_number').find('option:selected').val();
    if (selectValue == 0 && $('.append_line')) {
        $('.append_line').remove();
    } else if (selectValue != 0) {
        if ($('.append_line')) {
            $('.append_line').remove();
        }
        for (var i = 1; i <= selectValue; i++) {
            $('#buildingInfosInputTable').append(
                '<tr class = "append_line">\n\
                        <th></th>\n\
                        <td><input type="text"  id="project_name' + i + '" value="' + i + '" /></td>\n\
                         </tr>\n\
                        ');
        }
    } else {

    }
}

//页面跳转
function newFormat_button() {
    window.location = "add_building.html";
}

function frontPageBack() {
    history.back()
}
