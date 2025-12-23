package com.example.bankcards.dto.cards;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CardDTO {
    private long cardNumber;

    private int monthEnd;

    private int yearEnd;
}
