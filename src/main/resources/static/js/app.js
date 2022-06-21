var stompClient = null;
var enId = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function subscribe() {
    enId = $("#enId").val();
    console.log(enId);
    stompClient.subscribe('/topic/' + enId, function (chat) {
        console.log("hi");
        console.log(chat);
        showChat(JSON.parse(chat.body).text);
    });
}

function sendMessage() {
    stompClient.send("/app/chat", {}, JSON.stringify({'text': $("#message").val(), 'enId' : enId}));
}

function showChat(message) {
    $("#chat").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#subscribe" ).click(function() { subscribe(); });
    $( "#send" ).click(function() { sendMessage(); });
});