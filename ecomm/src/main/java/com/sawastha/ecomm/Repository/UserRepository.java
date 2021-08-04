package com.sawastha.ecomm.Repository;

import com.sawastha.ecomm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
