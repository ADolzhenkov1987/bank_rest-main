package com.example.bankcards.entity.cards;

import com.example.bankcards.entity.users.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @Column(name = "card_number", nullable = false)
    private long cardNumber;

    @Column(name = "month_end", nullable = false)
    private int monthEnd;

    @Column(name = "year_end", nullable = false)
    private int yearEnd;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
