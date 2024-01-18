package entity;

import enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Status status;

    @Column(name = "created_at")
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> productList;
}
