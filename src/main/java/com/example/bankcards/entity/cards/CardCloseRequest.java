package com.example.bankcards.entity.cards;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "card_close_request")
public class CardCloseRequest {
    @Id
    @Column(name = "card_number", nullable = false)
    private long cardNumber;
}
