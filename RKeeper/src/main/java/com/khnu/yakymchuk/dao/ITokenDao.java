package com.khnu.yakymchuk.dao;

import com.khnu.yakymchuk.model.Token;

public interface ITokenDao {

    void addToken(String token);

    Token getTokenAndDelete(String tokenName);

}
