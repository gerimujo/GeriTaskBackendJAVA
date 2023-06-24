package com.task.geritaskJAVA.Models;

public class ticketget {

    private Integer id;
    private Integer id_pjese;
    private String PjeseName;
    private String Descrition;
    private Integer id_laptop;
    private String StudentName;
    private StatusEnum status;



    
    public ticketget(Integer id, Integer id_pjese, String pjeseName, String descrition, Integer id_laptop,
            String studentName, StatusEnum status) {
        this.id = id;
        this.id_pjese = id_pjese;
        PjeseName = pjeseName;
        Descrition = descrition;
        this.id_laptop = id_laptop;
        StudentName = studentName;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId_pjese() {
        return id_pjese;
    }
    public void setId_pjese(Integer id_pjese) {
        this.id_pjese = id_pjese;
    }
    public String getPjeseName() {
        return PjeseName;
    }
    public void setPjeseName(String pjeseName) {
        PjeseName = pjeseName;
    }
    public String getDescrition() {
        return Descrition;
    }
    public void setDescrition(String descrition) {
        Descrition = descrition;
    }
    public Integer getId_laptop() {
        return id_laptop;
    }
    public void setId_laptop(Integer id_laptop) {
        this.id_laptop = id_laptop;
    }
    public String getStudentName() {
        return StudentName;
    }
    public void setStudentName(String studentName) {
        StudentName = studentName;
    }
    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    
    
}
