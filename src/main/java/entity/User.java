package entity;

import jakarta.persistence.*;
import lombok.Data;
import enums.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import enums.Status;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "status")
    private Status status;

    @Column(name = "created_at")
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Store store;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Transaction> transactions;
}
