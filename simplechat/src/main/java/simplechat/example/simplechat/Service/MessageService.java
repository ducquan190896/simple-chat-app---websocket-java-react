package simplechat.example.simplechat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simplechat.example.simplechat.Models.Message;
import simplechat.example.simplechat.Repos.MessageRepos;

@Service
public class MessageService {
    @Autowired
    MessageRepos messageRepos;

    public List<Message> getMessages(String room) {
        return messageRepos.findAllByRoom(room);
    }

    public Message saveMessage(Message message) {
        return messageRepos.save(message);
    }
}
