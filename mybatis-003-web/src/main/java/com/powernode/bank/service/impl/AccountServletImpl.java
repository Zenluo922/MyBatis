package com.powernode.bank.service.impl;

import com.powernode.bank.dao.AccountDao;
import com.powernode.bank.dao.Impl.AccountDaoImpl;
import com.powernode.bank.exceptions.MoneyNotEnoughException;
import com.powernode.bank.exceptions.TransferException;
import com.powernode.bank.pojo.Account;
import com.powernode.bank.service.AccontServlet;

public class AccountServletImpl implements AccontServlet {
    private AccountDao accountDao = new AccountDaoImpl();
    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance()<money) {
            throw new MoneyNotEnoughException("对不起余额不足！");
        }
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance()-money);
        toAct.setBalance(toAct.getBalance()+money);
        int count = accountDao.updataActno(fromAct);

        count+=accountDao.updataActno(toAct);

        if (count!=2){
            throw new TransferException("转账异常，无法处理");
        }
    }
}
