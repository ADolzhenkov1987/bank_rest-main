package com.example.bankcards.controller.cards;

import com.example.bankcards.dto.cards.CardDTO;
import com.example.bankcards.dto.cards.TransactionDTO;
import com.example.bankcards.entity.cards.Card;
import com.example.bankcards.entity.cards.CardCloseRequest;
import com.example.bankcards.service.cards.CardCloseRequestService;
import com.example.bankcards.service.cards.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;
    private final CardCloseRequestService cardCloseRequestService;

    @PostMapping
    public ResponseEntity<?> addCard(@RequestBody Card card) {
        cardService.save(card);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{cardNumber}")
    public Card findCardByNumber(@PathVariable("cardNumber") long cardNumber) {
        return cardService.findByNumber(cardNumber);
    }

    @GetMapping
    public List<Card> findAllCards() {
        return cardService.findAll();
    }

    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<?> deleteCard(@PathVariable("cardNumber") long cardNumber) {
        cardService.deleteCard(cardNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateCard(@RequestBody CardDTO cardDTO) {
        cardService.updateCard(cardDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> setTransaction(@RequestBody TransactionDTO transactionDTO) {
        cardService.setTransaction(transactionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/close/request")
    public ResponseEntity<?> setCardCloseRequest(@RequestBody CardCloseRequest cardCloseRequest) {
        cardCloseRequestService.save(cardCloseRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/close")
    public List<CardCloseRequest> findCardCloseRequests() {
        return cardCloseRequestService.getCardCloseRequests();
    }

    @PostMapping("/close")
    public ResponseEntity<?> closeCards(@RequestBody List<CardCloseRequest> cardCloseRequests) {
        List<String> errors = cardCloseRequestService.checkCards(cardCloseRequests);
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        cardService.closeCards(cardCloseRequests);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
