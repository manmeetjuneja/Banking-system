package com.example.account.controller;

import com.example.account.model.Account;
import com.example.account.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountRepository repo;
    public AccountController(AccountRepository repo) { this.repo = repo; }

    @PostMapping
    public Account create(@RequestBody Account acc) {
        acc.setBalance(0.0);
        return repo.save(acc);
    }

    @GetMapping
    public List<Account> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Account one(@PathVariable Long id) { return repo.findById(id).orElseThrow(null); }
}
