package kr.hs.dgsw.dgswblog.Domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String storedPath;
    @Column(nullable = false)
    private String originName;
}
