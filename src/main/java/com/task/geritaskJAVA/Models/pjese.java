package com.task.geritaskJAVA.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class pjese {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="cmimi")
    private double cmimi;
    @Column(name="stock")
    private Integer stock;

@OneToMany(mappedBy = "laptoppjese")
 @JsonIgnore
private List<ticket> tickets;

public pjese(){
    
}   

    public pjese(Integer id) {
    this.id = id;
}


public void decreaseStock() {
        if (stock > 0) {
            stock--;
        } else {
            throw new RuntimeException("No stock available for part: " + name);
        }



    }

    


public pjese(Integer id, String name, String description, double cmimi, Integer stock, List<ticket> tickets) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.cmimi = cmimi;
    this.stock = stock;
    this.tickets = tickets;
}




public Integer getId() {
    return id;
}


public void setId(Integer id) {
    this.id = id;
}


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}


public String getDescription() {
    return description;
}


public void setDescription(String description) {
    this.description = description;
}


public double getCmimi() {
    return cmimi;
}


public void setCmimi(double cmimi) {
    this.cmimi = cmimi;
}


public Integer getStock() {
    return stock;
}


public void setStock(Integer stock) {
    this.stock = stock;
}


public List<ticket> getTickets() {
    return tickets;
}


public void setTickets(List<ticket> tickets) {
    this.tickets = tickets;
}


    

    
}
