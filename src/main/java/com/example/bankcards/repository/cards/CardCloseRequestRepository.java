package com.example.bankcards.repository.cards;

import com.example.bankcards.entity.cards.Card;
import com.example.bankcards.entity.cards.CardCloseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardCloseRequestRepository extends JpaRepository<CardCloseRequest, Integer> {
    Optional<CardCloseRequest> findByCardNumber(long cardNumber);
}
