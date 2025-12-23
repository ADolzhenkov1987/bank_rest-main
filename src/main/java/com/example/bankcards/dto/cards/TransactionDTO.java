package com.example.bankcards.dto.cards;

import lombok.Data;

@Data
public class TransactionDTO {
    CardDTO cardSource;
    CardDTO cardRecipient;
    double summ;
}
