package com.powernode.bank.dao.Impl;

import com.powernode.bank.dao.AccountDao;
import com.powernode.bank.pojo.Account;
import com.powernode.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String acton) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Account account = (Account) sqlSession.selectOne("account.selectByActno", acton);
        sqlSession.close();
        return account;
    }

    @Override
    public int updataActno(Account act) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count  = sqlSession.update("account.updataByActno", act);
        sqlSession.commit();
        return count;
    }
}
