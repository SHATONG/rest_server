////////////////////////////////////////////////////////////////////////
// チャット画面
////////////////////////////////////////////////////////////////////////
navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

var localStream = null;
var localCameraStream = null;
var remoteStream = null;
var peer = null;
var peer_id;
var chatConnection = null;

// 呼び出し元 Peer ID
var callerPeerId = null;

// 呼び出し先 Peer ID
var calleePeerId = null;
var calleeName = null;

var mediaConnection = null;
var urlQueries = {};


var hangingUp = false;

var screenShareText = "スクリーンシェア";
var cameraChangeText = "カメラ切替";

var timeSecond = 0;
var timeCount;

var receivedImage;

var screen = new SkyWay.ScreenShare({
    debug: false
});

// ガラス側動画保存用
var mediaRecorder;

// スマートグラス呼び出し
function callPeer() {
    console.log('calling to ' + calleePeerId);

    showProgressMessage('' + calleeName + ' : ' + calleePeerId + ' を呼び出しています...');

    mediaConnection = peer.call(calleePeerId, localStream);

    mediaConnection.on('stream', function (stream) {
        console.log('media event: stream');

        $('#video_remote').prop('src', URL.createObjectURL(stream));
        remoteStream = stream;
        iconInit()

        var msg = {
            state: 'talking',
            desktop_peer_id: callerPeerId,
            smartglass_peer_id: calleePeerId,
        };


        showProgressMessage('通話中です。');
    });

    mediaConnection.on('close', function () {// ブラウザーの戻るボタンを押した場合など
        //console.log('media event: close');
        //
        //var msg = {
        //  state:'hangup',
        //  desktop_peer_id:callerPeerId,
        //  smartglass_peer_id:calleePeerId,
        //};
        //socket.emit('smartglass_talking', JSON.stringify(msg));

        //$('#video_remote').prop('src', "");

        //if (!hangingUp)
        //{
        //  alert('グラスが切断されました。');
        //}
        //
        //remoteStream = null;
        //mediaConnection = null;
        //
        //shutdownUserMedia();
        //
        //shutdownPeer();
        //
        //showProgressMessage('切断しています...');
    });

    //mediaConnection.on('error', function(err) {
    //  console.log('media event: error');
    //  console.log('err', err);
    //
    //  mediaConnection = null;
    //
    //  shutdownUserMedia();
    //
    //  shutdownPeer();
    //});
}

function setupPeer() {
    showProgressMessage('Peer ID を取得しています...');

    peer = new Peer({
        key: '86837bee-5cb9-40c9-b8bb-ad208e75267c'
    });

    peer.on('open', function (id) {
        console.log('peer open:');

        console.log('My peer ID is: ' + id);

        $('#my_peerId').text(peer.id);

        callerPeerId = id;

        var msg = {
            state: 'calling',
            desktop_peer_id: callerPeerId,
            smartglass_peer_id: calleePeerId,
        };


        // このあとグラス情報が送られてくる
    });

    peer.on('close', function () {
        console.log('peer close:');

        $('#video_local').prop('src', "");
        localStream = null;

        shutdownPeer();

        gotoDevices();
    });

    peer.on('call', function (call) {
        console.log('peer call:');
        if (mediaConnection != null) {
            mediaConnection.close();
            mediaConnection = null;
        }
        call.answer(localStream);
        call.on('stream', function (stream) {
            remoteStream = stream;
            $('#video_remote').prop('src', URL.createObjectURL(stream));
            mediaConnection = call;
        });
        // 今回の実装ではハンドリング不要
    });

    peer.on('error', function (err) {
        console.log('peer error:');
        console.log('error', err);

        showProgressMessage('Peer ID の取得に失敗しました。');

        shutdownPeer();
    });

    peer.on('connection', function (connection) {
        chatConnection = connection;
        peer_id = connection.peer;
        chatConnection.on('data', handleMessage);
        $('#targetPeerId').text(peer_id);
    });
}

function handleMessage(data) {
    if (data.indexOf('data:image') == 0) {
        //$("#receivedImage").append($("<img style='height:100px;' src='" + data + "' />"));
        receivedImage = data;
        window.open('/receivedImage');
    }
}

function shutdownPeer() {
    if (null === peer)
        return;

    if (!peer.destroyed) {
        console.log('destroying peer...');
        peer.destroy();
    } else {
        peer = null;


    }
}

function setupUserMedia() {
    showProgressMessage('カメラデバイスの準備をしています...');

    navigator.getUserMedia({
        audio: true,
        video: {
            mandatory: {
                <!--TODO -->
                maxWidth: 640,
                maxHeight: 480,
                //maxFrameRate: VIDEO_FRAME,
            }
        }
    }, function (stream) {
        $('#video_local').prop('src', URL.createObjectURL(stream));
        localStream = stream;
        setupPeer();
        callPeer();
    }, function (e) {
        // error
        showProgressMessage('カメラデバイスの初期化に失敗しました。');
        console.log(e);
    });
}

function shutdownUserMedia() {
    if (null === localStream)
        return;

    var tracks = localStream.getTracks();
    for (var i in tracks) {
        var track = tracks[i];
        track.stop();
    }
    console.log('closed all local mediastream tracks.');

    $('#video_local').prop('src', "");
    localStream = null;
}

function parseLocation() {
    var url = window.location.href;
    var re = /^[^\?]+\?([^#]*)$/;

    urlQueries = {};

    var matched = url.match(re);
    if (matched && 1 < matched.length) {
        var paramPairs = matched[1].split('&');
        if (paramPairs) {
            for (var p in paramPairs) {
                var paramPair = paramPairs[p];
                var param = paramPair.split('=');

                if (param) {
                    if (1 < param.length) {
                        urlQueries[param[0]] = param[1];
                    } else {
                        urlQueries[param[0]] = null;
                    }
                }
            }
        }
    }
}

function hangup() {
    console.log('hangup.');

    hangingUp = true;

    if (null === peer)
        return;
    peer.destroy();
}

function logout() {
    window.location.href = '/logout';
}
//----------------------以下スクリーンシェア用----------------------------------
function modeChange() {
    var modeChangeBtnText = $("#modeChangeBtn").text()
    if (modeChangeBtnText == screenShareText) {
        screenShare();
    } else {
        cameraChange();
    }
}

function screenShare() {
    if (screen.isEnabledExtension()) {
        $("#modeChangeBtn").text(cameraChangeText)
        screen.startScreenShare({
            Width: 640,
            Height: 480 //FrameRate: 1
        }, function (stream) {
            localStream.getVideoTracks().forEach(function (track) {
                localStream.removeTrack(track);
            });
            stream.getVideoTracks().forEach(function (track) {
                localStream.addTrack(track);
            });
            attachMediaStream_($('#video_local')[0], localStream);
            if (mediaConnection != null) {
                mediaConnection.close();
            }
            callPeer();
        }, function (error) {
            console.log(error);
        }, function () {
            alert('ScreenShareを終了しました');
        });
    } else {
        alert('ExtensionまたはAddonをインストールして下さい');
    }
}

function cameraChange() {
    $("#modeChangeBtn").text(screenShareText);
    if (localCameraStream == null) {
        getUM(function (stream) {
            localStream.getVideoTracks().forEach(function (track) {
                localStream.removeTrack(track);
            });
            stream.getVideoTracks().forEach(function (track) {
                localStream.addTrack(track);
            });
            attachMediaStream_($('#video_local')[0], localStream);
            if (mediaConnection != null) {
                mediaConnection.close();
            }
            callPeer();
        }, function (error) {
        });
    }
}

function getUM(success, error) {
    navigator.getUserMedia({
        audio: true,
        video: true
    }, function (stream) {
        success(stream);
    }, function (err) {
        error(err);
    });
}

function attachMediaStream_(videoDom, stream) {
    // Adapter.jsをインクルードしている場合はそちらのFunctionを利用する
    if (typeof (attachMediaStream) !== 'undefined' && attachMediaStream) {
        attachMediaStream(videoDom, stream);
    } else {
        videoDom.setAttribute('src', URL.createObjectURL(stream));
    }
}
//-----------------------以上スクリーンシェア用---------------------------------
function gotoDevices() {
    window.location.href = '/web/devices.html';
}

function showProgressMessage(text) {
    $('#progress-message').html(text);
}

//-----------------------以下動画保存用---------------------------------
function iconInit() {
    var videoHeight = $("#video_remote").height() - 40;
    $("#iconDIV").css("top", videoHeight + "px")
}

function saveMediaRecorder(stream) {
    mediaRecorder = new MediaStreamRecorder(stream);
    mediaRecorder.stream = stream;
    mediaRecorder.videoWidth = $("#video_remote").width();
    mediaRecorder.videoHeight = $("#video_remote").height();
    mediaRecorder.start();
}

function recordBegin() {
    $("#recordBegin").hide();
    $("#recordEnd").show();
    $("#recordTime").show();
    recordTimeCountBegin();
    saveMediaRecorder(remoteStream);
}

function recordEnd() {
    $("#recordEnd").hide();
    $("#recordBegin").show();
    $("#recordTime").hide();
    recordTimeCountEnd();
    mediaRecorder.stop();
    setTimeout(mediaRecorder.save, 500);
}

function recordTimeCountBegin() {
    var recMin = Math.floor(timeSecond / 60);
    var recSec = timeSecond - recMin * 60;
    $('#recordTime').text(addZero(recMin) + ":" + addZero(recSec));
    timeSecond = timeSecond + 1
    timeCount = setTimeout("recordTimeCountBegin()", 1000)
}

function recordTimeCountEnd() {
    timeSecond = 0;
    clearTimeout(timeCount);
    setTimeout("$('#recordTime').text('00:00')", 0);
}

function addZero(inputVal) {
    if (inputVal < 10) {
        return "0" + inputVal;
    }
    return inputVal;
}

//$(window).on("resize", function() {
//    iconInit();
//})
//
//$("#video_remote").on("resize", function() {
//    iconInit();
//})
//-----------------------以上動画保存用---------------------------------

$(function () {

    parseLocation();
    //iconInit();

    if (urlQueries.pid) {

        calleePeerId = urlQueries.pid;
        setupUserMedia();

    } else {
        gotoDevices();
    }
})

// vim:set ts=2 sts=2 sw=2 et:
