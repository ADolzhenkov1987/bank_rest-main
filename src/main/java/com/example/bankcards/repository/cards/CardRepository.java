package com.example.bankcards.repository.cards;

import com.example.bankcards.entity.cards.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByCardNumber(long cardNumber);
}
