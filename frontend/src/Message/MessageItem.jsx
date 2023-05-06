import React, { useState } from "react";

import "./Message.css";
import { timeStampConverter } from "../util/timeUtils";

export const MessageItem = ({ message, username }) => {
  const self = message.username == username ? "_self" : "";
  const time = timeStampConverter(message.createdDateTime);

  return (
    <div className={"message_item_" + self}>
        <span className="message_item_username">{message.username}</span>     
        <span className="message_content_value">{message.content}</span>
        <span>{time}</span>    
    </div>
  );
};
