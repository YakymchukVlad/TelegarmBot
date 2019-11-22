package com.khnu.yakymchuk.service.impl;

import com.khnu.yakymchuk.dao.ITokenDao;
import com.khnu.yakymchuk.model.Token;
import com.khnu.yakymchuk.service.ITokenService;
import com.khnu.yakymchuk.utils.assertion.Assert;

public class TokenService implements ITokenService {

    private ITokenDao tokenDao;

    public TokenService(ITokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Override
    public void addToken(String token) {
        Assert.asserHasText(token, "token cannot be null or empty");
        tokenDao.addToken(token);
    }

    @Override
    public Token getTokenAndDelete(String tokenNumber) {
        Assert.asserHasText(tokenNumber, "token number cannot be null or empty");
        return tokenDao.getTokenAndDelete(tokenNumber);
    }
}
