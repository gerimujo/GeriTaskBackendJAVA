package com.task.geritaskJAVA.Reprositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.geritaskJAVA.Models.users;
import java.util.List;

@Repository
public interface usersRep  extends JpaRepository<users, Integer>{
    List<users> findByUsername(String username);
    List<users> findByRole(String role);
  
}
