package com.shgame.APIGameWallet.virtualwallet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shgame.APIGameWallet.virtualwallet.models.UserWallet;
@Repository
public interface UserWalletRepository extends CrudRepository<UserWallet, Integer> {

}
