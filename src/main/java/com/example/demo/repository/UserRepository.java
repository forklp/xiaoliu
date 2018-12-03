package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserAccount(String account);

    User findByUserAccountAndUserPassword(String account,String password);


    //增加用户的积分
    //增加操作必须加这个不然可能会报错
    @Modifying
    @Transactional
    @Query(value = "update User set userPoint = userPoint + 1 where userCode = ?1")
    void addUserPoint(String userCode);


    //注意这里用实体变量的名称，jpa的所有操作都是操作java实体
    //如果是返回User的话，会缺少字段无法返回
    @Query(value = "select userAccount,userCode,userPoint from User where userAccount = ?1")
    Object findByUserAccountO(String account);

}
