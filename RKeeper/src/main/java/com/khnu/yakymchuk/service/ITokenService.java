package com.khnu.yakymchuk.service;

import com.khnu.yakymchuk.model.Token;

public interface ITokenService {

    void addToken(String token);

    Token getTokenAndDelete(String tokenNumber);

}
