let socket;
getHostAddress();
loadChatHistory();
// document.getElementById("login").showModal();

let chatTextarea = document.getElementById("chat-textarea");
let sendButton = document.getElementById("send-button");
let messages = document.getElementsByClassName("messages")[0];
let messagesBox = document.getElementsByClassName("messages-box")[0];
let chatInput = document.getElementsByClassName("chat-input")[0];

function onInput() {
    chatInput.style.height = 0;
    chatInput.style.height = (this.scrollHeight) + "px";
    // console.log(this);
}

chatTextarea.addEventListener("input", onInput, false);

chatTextarea.addEventListener("keydown", function (e) {
    if (e.key == "Enter") {
        e.preventDefault();
        sendMessage();
        chatTextarea.value = "";
        onInput.call(chatTextarea);
        scrollChatTextAreaToBottom(); 
    }
});

window.addEventListener("resize", function (e) {
    onInput.call(chatTextarea);
});

sendButton.addEventListener("click", function(e) {
    sendMessage(e);
});

function sendMessage() {
    if (chatTextarea.value.trim() != "") {
        socket.send(chatTextarea.value);
    }
}

function showMessage(messageText) {
    let div = document.createElement("div");
    div.innerHTML = messageText;
    div.setAttribute("class", "message");
    messages.appendChild(div);
}

function scrollChatTextAreaToBottom() {
    messagesBox.scrollTop = messagesBox.scrollHeight;
}

function postMessage(messageText) {
    let response = fetch("/message", {
        method: "POST",
        headers: {
            "Content-Type": "text/html"
        },
        body: messageText
    });
}

function getHostAddress() {
    fetch("address")
        .then(response => response.text())
        .then(address => connect(address));
}

function connect() {
    socket = new WebSocket(`ws://${window.location.host}/chat`);
    
    socket.onmessage = (event) => {
        showMessage(event.data);
    }
}

function loadChatHistory() {
    fetch("messages")
        .then(response => response.text())
        .then(json => JSON.parse(json, function(key, value) {
            if (key == "content") showMessage(value);
        }));
}