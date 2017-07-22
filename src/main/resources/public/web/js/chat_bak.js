////////////////////////////////////////////////////////////////////////
// チャット画面
////////////////////////////////////////////////////////////////////////
navigator.getUserMedia = navigator.getUserMedia
                      || navigator.webkitGetUserMedia
                      || navigator.mozGetUserMedia;

var localStream = null;
var remoteStream = null;
var peer = null;

// 呼び出し元 Peer ID
var callerPeerId = null;

// 呼び出し先 Peer ID
var calleePeerId = null;
var calleeName = null;

var mediaConnection = null;
var urlQueries = { };
var socket = null;

var hangingUp = false;

// スマートグラス呼び出し
function callPeer() {
  console.log('calling to ' + calleePeerId);

  showProgressMessage('' + calleeName + ' : ' + calleePeerId + ' を呼び出しています...');

  mediaConnection = peer.call(calleePeerId, localStream);

  mediaConnection.on('stream', function(stream) {
    console.log('media event: stream');

    $('#video_remote').prop('src', URL.createObjectURL(stream));
    remoteStream = stream;

    var msg = {
      state:'talking',
      desktop_peer_id:callerPeerId,
      smartglass_peer_id:calleePeerId,
    };
    socket.emit('smartglass_talking', JSON.stringify(msg));

    showProgressMessage('通話中です。');
  });

  mediaConnection.on('close', function() {
    // ブラウザーの戻るボタンを押した場合など
    console.log('media event: close');

    var msg = {
      state:'hangup',
      desktop_peer_id:callerPeerId,
      smartglass_peer_id:calleePeerId,
    };
    socket.emit('smartglass_talking', JSON.stringify(msg));

    $('#video_remote').prop('src', "");

    if (!hangingUp)
    {
      alert('グラスが切断されました。');
    }

    remoteStream = null;
    mediaConnection = null;

    shutdownUserMedia();

    shutdownPeer();

    showProgressMessage('切断しています...');
  });

  mediaConnection.on('error', function(err) {
    console.log('media event: error');
    console.log('err', err);

    mediaConnection = null;

    shutdownUserMedia();

    shutdownPeer();
  });
}

function setupPeer() {
  showProgressMessage('Peer ID を取得しています...');

  peer = new Peer({ key:API_KEY });

  peer.on('open', function(id) {
    console.log('peer open:');

    console.log('My peer ID is: ' + id);

    callerPeerId = id;

    var msg = {
        state:'calling',
        desktop_peer_id:callerPeerId,
        smartglass_peer_id:calleePeerId,
    };

    socket.emit('smartglass_talking', JSON.stringify(msg));

    // このあとグラス情報が送られてくる
  });

  peer.on('close', function() {
    console.log('peer close:');

    $('#video_local').prop('src', "");
    localStream = null;

    shutdownPeer();

    gotoDevices();
  });

  peer.on('call', function(call) {
    console.log('peer call:');
    // 今回の実装ではハンドリング不要
  });

  peer.on('error', function(err) {
    console.log('peer error:');
    console.log('error', err);

    showProgressMessage('Peer ID の取得に失敗しました。');

    shutdownPeer();
  });
}

function shutdownPeer() {
  if (null === peer) return;

  if (!peer.destroyed) {
    console.log('destroying peer...');
    peer.destroy();
  }
  else {
    peer = null;

    shutdownSocket();
  }
}

function setupUserMedia() {
  showProgressMessage('カメラデバイスの準備をしています...');

  navigator.getUserMedia(
    {
      audio:true,
      video:{
        mandatory:{
          maxWidth:VIDEO_WIDTH,
          maxHeight:VIDEO_HEIGHT,
          maxFrameRate:VIDEO_FRAME,
        }
      }
    },
    function (stream) {
      $('#video_local').prop('src', URL.createObjectURL(stream));
      localStream = stream;
      setupPeer();
    },
    function(e) {
      // error
      showProgressMessage('カメラデバイスの初期化に失敗しました。');
      alert('カメラデバイスの初期化に失敗しました。');
      console.log(e);
    });
}

function shutdownUserMedia() {
  if (null === localStream) return;

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

  urlQueries = { };

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
          }
          else {
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

  if (null === peer) return;
  peer.destroy();
}

function setupSocket() {
  socket = io();

  socket.on('connect', function() {
    console.log('socket connected');
    setupUserMedia();
  });

  socket.on('disconnect', function() {
    console.log('socket disconnected.');
    shutdownPeer();
  });

  socket.on('smartglass_info', function(data) {
    console.log('socket smartglass_info.');

    try {
      var json = JSON.parse(data);
      console.log(json);
      calleeName = json.name;
      var label = json.name + ' (' + json.peer_id + ')';
      $('#callee_name').html(label);

      callPeer();
    }
    catch (e) {
      console.log(e);
    }
  });

  socket.on('smartglass_offline', function(data) {
    alert('グラスが切断されました。');
    hangup();
  });

  showProgressMessage('センターサーバーに接続しています...');
}

function shutdownSocket() {
  if (null === socket) return;
  socket.disconnect();
  showProgressMessage('センターサーバーから切断しています...');
}

function logout() {
  window.location.href = '/logout';
}

function gotoDevices() {
  window.location.href = '/devices';
}

function showProgressMessage(text) {
  $('#progress-message').html(text);
}

$(function() {
  parseLocation();

  if (urlQueries.pid) {
    calleePeerId = urlQueries.pid;
    setupSocket();
  }
  else {
    alert('URL クエリーが正しくありません。');
    gotoDevices();
  }
})

// vim:set ts=2 sts=2 sw=2 et:
