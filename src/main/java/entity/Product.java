package entity;

import enums.Role;
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
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "category_id")
    private Long categoryId;


    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private Status status;

    @Column(name = "created_at")
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;

    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<Transaction> transactions;
}
