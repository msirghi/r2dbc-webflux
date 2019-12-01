package com.user.r2dbc.controllers;

import com.user.r2dbc.models.Account;
import com.user.r2dbc.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

  @Autowired
  private AccountRepository accountDao;

  @GetMapping("/accounts/{id}")
  public Mono<ResponseEntity<Account>> getAccount(@PathVariable("id") Long id) {
    return accountDao.findById(id)
            .doOnSuccess(System.out::println)
            .doOnError(System.out::println)
            .map(acc -> new ResponseEntity<>(acc, HttpStatus.OK))
            .switchIfEmpty(Mono.just(new ResponseEntity<>(null, HttpStatus.NOT_FOUND)));
  }

  @GetMapping("/accounts")
  public Flux<Account> getAllAccounts() {
    return accountDao.findAll();
  }

  @PostMapping("/accounts")
  public Mono<ResponseEntity<Account>> postAccount(@RequestBody Account account) {
    return accountDao.createAccount(account)
            .doOnSuccess(System.out::println)
            .map(acc -> new ResponseEntity<>(acc, HttpStatus.CREATED))
            .doOnError(e -> ResponseEntity.notFound())
            .log();
  }

}
