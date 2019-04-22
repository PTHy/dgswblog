package kr.hs.dgsw.dgswblog.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String content;

    private String storedPath;
    private String originName;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;

    public Post(Post c) {
        this.id = c.getId();
        this.userId = c.getUserId();
        this.content = c.getContent();
        this.created = c.getCreated();
        this.modified = c.getModified();
        this.storedPath = c.getStoredPath();
        this.originName = c.getOriginName();
    }

    public Post(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
