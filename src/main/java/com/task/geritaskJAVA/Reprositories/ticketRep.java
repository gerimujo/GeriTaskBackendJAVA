package com.task.geritaskJAVA.Reprositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task.geritaskJAVA.Models.ticket;
@Repository
public interface ticketRep extends JpaRepository<ticket,Integer>{
 @Query("SELECT t FROM ticket t WHERE t.id_laptop = :id")
    List<ticket> findTicketsByLaptopId(@Param("id") int laptopId);
    

    @Query("SELECT t FROM ticket t WHERE t.id_pjese = :id")
    List<ticket> findTicketsByPjeseId(@Param("id") int pjeseId);


    @Query("SELECT DISTINCT t.id_pjese FROM ticket t")
    List<Integer> findDistinctIdPjese();
    

    @Query("SELECT DISTINCT t.id_laptop FROM ticket t")
    List<Integer> findDistinctIdLaptop();

    
}
