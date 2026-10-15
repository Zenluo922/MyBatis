package com.powernode.bank.service;

import com.powernode.bank.exceptions.MoneyNotEnoughException;
import com.powernode.bank.exceptions.TransferException;

public interface AccontServlet {
    void transfer(String fromActno,String toAct,double money) throws MoneyNotEnoughException, TransferException;
}
