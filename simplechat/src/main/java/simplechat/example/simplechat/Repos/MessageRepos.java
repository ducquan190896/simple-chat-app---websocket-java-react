package simplechat.example.simplechat.Repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import simplechat.example.simplechat.Models.Message;

@Repository
public interface MessageRepos extends JpaRepository<Message, Long> {
    List<Message> findAllByRoom(String room);
}
