package com.task.geritaskJAVA.Reprositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.geritaskJAVA.Models.pjese;

@Repository
public interface pjeseRep extends JpaRepository <pjese, Integer> {
     
}
