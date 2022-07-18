package com.example.dungngu.restapi;

import com.example.dungngu.dto.AccountLoginDto;
import com.example.dungngu.dto.AccountRegisterDto;
import com.example.dungngu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    final AccountService accountService;
    @RequestMapping(path = "register",method = RequestMethod.POST)
    public RepositoryEntity<?>  register(@RequestBody AccountRegisterDto accountRegisterDto){
        return RepositoryEntity.ok().body(AccountService.login(AccountLoginDto));
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getInformation(){
        return "";
    }
}
