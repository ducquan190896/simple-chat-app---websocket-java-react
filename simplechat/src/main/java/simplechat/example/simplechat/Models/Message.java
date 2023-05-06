package simplechat.example.simplechat.Models;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;



@Entity(name = "Message")
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String content;

    private String room;

    private String username;
    
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdDateTime;

    public Message(String content, String room, String username) {
        this.content = content;
        this.room = room;
        this.username = username;
    }

    
}
