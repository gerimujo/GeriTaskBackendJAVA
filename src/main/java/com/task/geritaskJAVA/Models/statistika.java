package com.task.geritaskJAVA.Models;

public class statistika {
    
    private String name;
    private Integer totali;





    
    public statistika(String name, Integer totali) {
        this.name = name;
        this.totali = totali;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getTotali() {
        return totali;
    }
    public void setTotali(Integer totali) {
        this.totali = totali;
    }



    
}
