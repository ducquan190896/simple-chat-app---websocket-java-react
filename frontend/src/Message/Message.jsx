import React, { useCallback, useEffect, useRef, useState } from "react";
import { RiSendPlaneLine, RiSendPlaneFill } from "react-icons/ri";
import { MessageList } from "./MessageList";
import SockJS from "sockjs-client";
import { over } from "stompjs";
import axios from "axios";
import "./Message.css";
import "./Chat.css";
import { MessageItem } from "./MessageItem";

let stompClient = null;
export const Message = ({ room, username }) => {
  const [messageInput, setMessageInput] = useState("");
  const [messageList, setMessageList] = useState([]);
  const messagesEndRef = useRef(null);

  const scrollToBottom = () => {
    messagesEndRef.current.scrollIntoView({ behavior: "smooth" });
  };

  useEffect(scrollToBottom, [messageList]);

  const loadMessages = useCallback(async () => {
    const res = await axios.get("http://localhost:8080/room/" + room);
    const data = await res.data;
    console.log(data);
    setMessageList(data);
  }, [room])

  useEffect(() => {
    connect();
  }, [])
  
  useEffect(() => {
    console.log("load data")
    loadMessages();
  }, [room])

  const connect = () => {
    let sock = new SockJS("http://localhost:8080/service");
    stompClient = over(sock);
    stompClient.connect({}, connected, notConnected);
  }

  const connected = (frame) => {
    console.log(frame)
    stompClient.subscribe("/chatroom/" + room, messageReceived);
    console.log("subscribed");
}

  const notConnected = () => {
    alert("connection failed");
  }

  const messageReceived = (payload) => {
    console.log("received");
    console.log(payload.body);
    setMessageList(prev =>[...prev, JSON.parse(payload.body)]);
    console.log(messageList);

  }


  const sendMessage = (e) => {
    e.preventDefault();
    if(messageInput == "") {
      return;
    }
    let message = {
      content: messageInput,
      room: room,
      username: username
    }
    console.log(message);
    stompClient.send("/app/message/" + room, {}, JSON.stringify(message));
    setMessageInput("")
  }

  if(!messageList) {
    return (
      <p>No message</p>
    )
  }

 return (
  <div className="message_root_div">
      <span className="room_name">Room: {room} </span>
      <span className="user_name">Welcome: {username} </span>
      <div className="message_component">
        {/* <MessageList username={username} messageList={messageList} /> */}
        <div className="message_list">
          {messageList.map((x, idx) => (
            <MessageItem key={idx} message={x} username={username} />
          ))}
          <div ref={messagesEndRef} />
        </div>
        <form className="chat-input" onSubmit={(e) => sendMessage(e)}>
          <input
            type="text"
            value={messageInput}
            onChange={(e) => setMessageInput(e.target.value)}
            placeholder="Type a message"
          />
          <button type="submit">
            {messageInput == "" ? (
              <RiSendPlaneLine size={25} />
            ) : (
              <RiSendPlaneFill color="#2671ff" size={25} />
            )}
          </button>
        </form>
      </div>
    </div>

 )
};
