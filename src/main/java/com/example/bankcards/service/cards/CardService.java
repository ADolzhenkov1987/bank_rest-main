package com.example.bankcards.service.cards;

import com.example.bankcards.dto.cards.CardDTO;
import com.example.bankcards.dto.cards.TransactionDTO;
import com.example.bankcards.entity.cards.Card;
import com.example.bankcards.entity.cards.CardCloseRequest;
import com.example.bankcards.repository.cards.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public void save(Card card) {
        cardRepository.save(card);
    }

    public Card findByNumber(long number) {
        return cardRepository.findByCardNumber(number).orElse(null);
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Transactional
    public void updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber()).orElse(null);
        if (card != null) {
            if (card.getMonthEnd() > cardDTO.getMonthEnd() && card.getMonthEnd() > cardDTO.getYearEnd()) {
                card.setMonthEnd(cardDTO.getMonthEnd());
                card.setYearEnd(cardDTO.getYearEnd());
                cardRepository.save(card);
            }
        }
    }

    @Transactional
    public void deleteCard(long cardNumber) {
        cardRepository.delete(Objects.requireNonNull(cardRepository.findByCardNumber(cardNumber).orElse(null)));
    }

    @Transactional
    public void setTransaction(TransactionDTO transactionDTO) {
        Card cardSource = cardRepository.findByCardNumber(transactionDTO.getCardSource().getCardNumber()).orElse(null);
        Card cardRecipient = cardRepository.findByCardNumber(transactionDTO.getCardRecipient().getCardNumber()).orElse(null);
        if (cardSource != null && cardRecipient != null) {
            if (transactionDTO.getSumm() <= cardSource.getBalance()) {
                cardSource.setBalance(cardSource.getBalance() - transactionDTO.getSumm());
                cardRepository.save(cardSource);
                cardRecipient.setBalance(cardRecipient.getBalance() + transactionDTO.getSumm());
                cardRepository.save(cardRecipient);
            }
        }
    }

    @Transactional
    public void closeCards(List<CardCloseRequest> cardCloseRequests) {
        for (CardCloseRequest cardCloseRequest : cardCloseRequests) {
            Card card = cardRepository.findByCardNumber(cardCloseRequest.getCardNumber()).orElse(null);
            if (card != null) {
                card.setStatus("Blocked");
                cardRepository.save(card);
            }
        }
    }
}
