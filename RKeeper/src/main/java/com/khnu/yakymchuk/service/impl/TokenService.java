package com.khnu.yakymchuk.service.impl;

import com.khnu.yakymchuk.dao.ITokenDao;
import com.khnu.yakymchuk.model.Token;
import com.khnu.yakymchuk.service.ITokenService;

public class TokenService implements ITokenService {

    private ITokenDao tokenDao;

    public TokenService(ITokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Override
    public void addToken(String token) {
        tokenDao.addToken(token);
    }

    @Override
    public Token getTokenAndDelete(String tokenNumber) {
        return tokenDao.getTokenAndDelete(tokenNumber);
    }

}
