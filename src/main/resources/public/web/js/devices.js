////////////////////////////////////////////////////////////////////////
// 接続可能スマートグラス表示画面
////////////////////////////////////////////////////////////////////////

// このソケットは部門の room に入るだけ
var socket = null;

function connectPeer(pid) {
    $('#peers_list').empty();
    window.location.href = 'chat.html?pid=' + pid;
}

function setGlassList(peer_ids) {
    try {
        $('#peers_list').empty();
        if (0 < peer_ids.length) {
            $('#peers .panel-body').hide(0);
            $('#peers_list').show(0);
            for (var i in peer_ids) {
                var pid = peer_ids[i].peerID;
                var nickname = peer_ids[i].nickname;
                $('#peers_list').append('<li class="list-group-item" onclick="connectPeer(\'' + pid + '\')">' + nickname + ' : ' + pid + '</li>')
                console.log('PeerID: ' + pid);
            }
        }
        else {
            $('#peers_list').hide(0);
            $('#peers .panel-body').show(0);
        }
    }
    catch (e) {
        console.log(e);
    }
}




function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}

