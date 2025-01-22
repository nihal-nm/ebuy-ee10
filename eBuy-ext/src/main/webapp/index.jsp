<!DOCTYPE html>
<html>
<head>
<title>Testing websockets</title>
</head>
<body>
  <div>
    <input id="inputmessage" type="text" />
  </div>
  <div>
    <input type="submit" value="Broadcast message11" onclick="send()" />
  </div>
  <div id="messages"></div>
  <script language="javascript" type="text/javascript">
    var webSocket = 
      new WebSocket('ws://qiaoxi.cn.ibm.com:9080/eBuy-ext/websocket');

    webSocket.onerror = function(event) {
      onError(event)
    };

    webSocket.onopen = function(event) {
      onOpen(event)
    };

    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    

    function onMessage(event) {
      document.getElementById('messages').innerHTML 
        += '<br />Received message: ' + event.data;
    }

    function onOpen(event) {
      document.getElementById('messages').innerHTML 
        = 'Connection established';
    }

    function onError(event) {
      alert(event.data);
    }

    function send() {
      var txt = document.getElementById('inputmessage').value;
      webSocket.send(txt);
      return false;
    }    
  </script>
</body>
</html>
