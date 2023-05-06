package simplechat.example.simplechat.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import simplechat.example.simplechat.Models.Message;
import simplechat.example.simplechat.Service.MessageService;

@RestController
@RequestMapping("")
public class MessageController {
    @Autowired
    MessageService messageService;

   
    @GetMapping("/room/{room}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String room) {
        return ResponseEntity.ok(messageService.getMessages(room));
    }
    
    @MessageMapping("/message/{id}")
    @SendTo("/chatroom/{id}")
    public Message receiveMessage(@Payload Message message){
        System.out.println(message);
        return messageService.saveMessage(message);
    }
}