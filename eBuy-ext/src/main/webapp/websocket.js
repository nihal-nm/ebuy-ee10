var URL = "ws://qiaoxi.cn.ibm.com:9080/eBuy-ext/websocket/web-client";
var websocket;

$(document).ready(function() {
	connect();
});

function connect() {
	websocket = new WebSocket(URL);
	websocket.onopen = function(evnt) {
		onOpen(evnt);
	};
	websocket.onmessage = function(evnt) {
		onMessage(evnt);
	};
	websocket.onerror = function(evnt) {
		onError(evnt);
	};
}

function sendMessage() {
	websocket.send($("#message").val());
}

function onOpen() {
	updateStatus("connected");
}

function onMessage(evnt) {
	if (typeof evnt.data == "string") {

		$("#received_messages").append(
				$('').append($('').text("1"))
						.append(
								$('').text(
										evnt.data.substring(0, evnt.data
												.indexOf(":")))).append(
								$('').text(
										evnt.data.substring(evnt.data
												.indexOf(":") + 1))));
	}
}
function onError(evnt) {
	alert('ERROR: ' + evnt.data);
}
function updateStatus(status) {
	if (status == "connected") {
		$("#status").removeClass(function(index, css) {
			return (css.match(/\blabel-\S+/g) || []).join(' ');
		});
		$("#status").text(status).addClass("label-success");
	}
}