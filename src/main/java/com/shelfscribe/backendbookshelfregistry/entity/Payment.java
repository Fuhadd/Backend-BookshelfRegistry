package com.shelfscribe.backendbookshelfregistry.entity;
import com.shelfscribe.backendbookshelfregistry.enums.PaymentType;
import com.shelfscribe.backendbookshelfregistry.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.ORDINAL)
    private PaymentType PaymentType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // Getters, setters, and constructors
}