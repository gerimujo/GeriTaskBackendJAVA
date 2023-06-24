package com.task.geritaskJAVA.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "id_laptop")
    private Integer id_laptop;

    @Column(name="id_pjese")
    private Integer id_pjese;
    
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "laptoppjese_id")
    private pjese laptoppjese;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    public ticket() {
    }

  

    public ticket(Integer id, Integer id_laptop, Integer id_pjese, String description, pjese laptoppjese,
            StatusEnum status) {
        this.id = id;
        this.id_laptop = id_laptop;
        this.id_pjese = id_pjese;
        this.description = description;
        this.laptoppjese = laptoppjese;
        this.status = status;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_laptop() {
        return id_laptop;
    }

    public void setId_laptop(Integer id_laptop) {
        this.id_laptop = id_laptop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public pjese getLaptoppjese() {
        return laptoppjese;
    }

    public void setLaptoppjese(pjese laptoppjese) {
        this.laptoppjese = laptoppjese;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getId_pjese() {
        return id_pjese;
    }

    public void setId_pjese(Integer id_pjese) {
        this.id_pjese = id_pjese;
    }
}
