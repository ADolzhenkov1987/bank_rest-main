package com.example.bankcards.service.cards;

import com.example.bankcards.entity.cards.CardCloseRequest;
import com.example.bankcards.repository.cards.CardCloseRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardCloseRequestService {
    private final CardCloseRequestRepository cardCloseRequestRepository;

    @Transactional
    public void save(CardCloseRequest cardCloseRequest) {
        cardCloseRequestRepository.save(cardCloseRequest);
    }

    @Transactional
    public void delete(CardCloseRequest cardCloseRequest) {
        cardCloseRequestRepository.delete(cardCloseRequest);
    }

    public List<CardCloseRequest> getCardCloseRequests() {
       return cardCloseRequestRepository.findAll();
    }

    public List<String> checkCards(List<CardCloseRequest> cardCloseRequests) {
        List<String> errors = new ArrayList<>();
        for (CardCloseRequest cardCloseRequest : cardCloseRequests) {
            if (cardCloseRequestRepository.findByCardNumber(cardCloseRequest.getCardNumber()).isEmpty()) {
                errors.add("Card number " + cardCloseRequest.getCardNumber() + " is not found");
            }
        }
        return errors;
    }
}
