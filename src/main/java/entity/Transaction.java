package entity;

import enums.Status;
import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_number")
    private Long customerNumber;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "status")
    private Status status;

    @Column(name = "created_at")
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;

    @JoinColumn(name = "store_id", insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @JoinColumn(name = "product_id", insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "user_id", insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
