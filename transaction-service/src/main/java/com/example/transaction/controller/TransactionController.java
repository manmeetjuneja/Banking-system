package com.example.transaction.controller;

import com.example.transaction.model.Transaction;
import com.example.transaction.repository.TransactionRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionRepository repo;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionController(TransactionRepository repo, KafkaTemplate<String, String> kafkaTemplate) {
        this.repo = repo; this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestBody Transaction tx) {
        tx.setType("DEPOSIT");
        Transaction saved = repo.save(tx);
        kafkaTemplate.send("transactions", "Deposited " + tx.getAmount() + " to account " + tx.getAccountId());
        return saved;
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestBody Transaction tx) {
        tx.setType("WITHDRAW");
        Transaction saved = repo.save(tx);
        kafkaTemplate.send("transactions", "Withdrawn " + tx.getAmount() + " from account " + tx.getAccountId());
        return saved;
    }
    @GetMapping
    public List<Transaction> all() {
        kafkaTemplate.send("transactions","Listing all transactions");
        return repo.findAll(); }
}
