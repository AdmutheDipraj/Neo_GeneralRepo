package com.neosoft.repository;

import com.neosoft.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends CrudRepository<User, Serializable> {

    public User findById(Integer id);
}
