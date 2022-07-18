package com.example.dungngu.service;

import com.example.dungngu.dto.AccountLoginDto;
import com.example.dungngu.dto.AccountRegisterDto;
import com.example.dungngu.entity.Account;
import com.example.dungngu.entity.Credential;
import com.example.dungngu.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    final AccountRepository accountRepository;
    final PasswordEncoder passwordEncoder;

    public AccountRegisterDto register(AccountRegisterDto accountRegisterDto) {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(accountRegisterDto.getUsername());
        if (optionalAccount.isPresent()) {
            return null;
        }
        Account account = Account.builder()
                .username(accountRegisterDto.getUsername())
                .passwordHash(passwordEncoder.encode(accountRegisterDto.getPasswordHash()))
                .role(accountRegisterDto.getRole())
                .build();
        accountRepository.save(account);
        accountRegisterDto.setId(account.getId());
        return accountRegisterDto;
    }

    public static Credential login(AccountRegisterDto accountRegisterDto) {
        Optional<Account>optionalAccount = accountRepository.findAccountByUsername(AccountLoginDto.getUsername());
        if (!optionalAccount.isPresent()){
            throw  new UsernameNotFoundException("user is not found");
        }
        Account account = optionalAccount.get();
        4fr4nnnnnnnnnnnnnnnnnnnnboolean

    }

    public void getInformation() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findAccountByUsername(username);
        return null;
    }
}