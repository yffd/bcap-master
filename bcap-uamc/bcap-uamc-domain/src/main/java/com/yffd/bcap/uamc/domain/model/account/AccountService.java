package com.yffd.bcap.uamc.domain.model.account;

import com.yffd.bcap.uamc.domain.model.user.UserRepo;

public class AccountService {

    /**
     *账号注册
     * @param accountData
     * @param accountRepo
     * @param userRepo
     */
    public void registAccount(AccountData accountData, AccountRepo accountRepo, UserRepo userRepo) {
        //1.账号注册时，由系统自动生成一个用户编号，用于关联用户信息；
        //2.同时生成用户信息，即用户表中添加数据
        //TODO
    }

    public void checkLogin() {
        //TODO
    }

    public void cancel(AccountRepo repo) {
        //1.账户注销是客户行为，是否删除个人账号，建议逻辑删除账户；
        //TODO
    }

    public void resetPwd(AccountRepo repo) {

    }

    public void encryptPwd(AccountRepo repo) {

    }

}
