package com.task.geritaskJAVA.Controller;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.task.geritaskJAVA.Models.pjese;
import com.task.geritaskJAVA.Models.ticket;
import com.task.geritaskJAVA.Models.users;
import com.task.geritaskJAVA.Reprositories.pjeseRep;
import com.task.geritaskJAVA.Reprositories.usersRep;
import com.task.geritaskJAVA.Security.TokenUtil;
import com.task.geritaskJAVA.Services.Services;

@RestController
public class Controller {

    @Autowired
    private Services service;
    @Autowired
    private usersRep usersRep;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private pjeseRep pjeseRep;


    @PostMapping(value="insertdata")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public ResponseEntity<String> insetdatatotable(@RequestBody List<users> data ){
        try{
return service.insert(data);
        }catch(Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }
    }
     @PostMapping(value="login")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> login(@RequestBody users data){
        try{
            return service.login(data);

        }catch(Exception e){
                        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }

     }
     @PostMapping(value="insertpjese")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> InsertPjese(@RequestHeader("Authorization") String token, @RequestBody pjese data){
        try{
            if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.insertPjese(data);
            }else{
                        return  ResponseEntity.status(400).body("Something went wrong");
         
            }
           

        }catch(Exception e){
                        return  ResponseEntity.status(400).body("Something went wrong");
        }

     }
         @GetMapping(value="getpjeset")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> getPjeset(@RequestHeader("Authorization") String token){
        try{
           if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
           
 return service.getpjesetDHeStudentet();
       }else{
                    return  ResponseEntity.status(400).body("Something went wrong");
         
     }
           

       }catch(Exception e){
        
      
                       return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
       }

     } 
            @PutMapping(value="updatepjesa")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> updatePjeset(@RequestHeader("Authorization") String token, @RequestBody pjese data){
        try{
            if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.updatePjeset(data);
            }else{
                           return ResponseEntity.ok("something went wrong");
         
            }
           

        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());
        }

     } 
        @DeleteMapping(value="deletepjes/{id}")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> deletePjese(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        try{
            if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.deletepjes(id);
            }else{
                           return ResponseEntity.ok("something went wrong");
         
            }
           

        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());
        }

     } 
    
    @GetMapping(value="getStudentet")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> getStudentet(@RequestHeader("Authorization") String token){
        try{
            if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.getStudent();
            }else{
                        return  ResponseEntity.status(400).body("Something went wrong");
         
            }
           

        }catch(Exception e){
                        return  ResponseEntity.status(400).body("Something went wrong");
        }

     } 
        @PostMapping(value="insertTicket")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> insertTicket(@RequestHeader("Authorization") String token, @RequestBody List<ticket> data){
        try{
            if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.insertticket(data);
            }else{
                        return  ResponseEntity.status(400).body("Something went wrong");
         
            }
           

        }catch(Exception e){
                        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }

     }

     @GetMapping(value="getTicket")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> getTicket(@RequestHeader("Authorization") String token){
        try{
           if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.getTicket();
           }else{
                       return  ResponseEntity.status(400).body("Something went wrong");
         
            }
           

        }catch(Exception e){
                     return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
      }

     }
       @PutMapping(value="updateStatus/{id}")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> uodateStatus(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        try{
          if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.updateStatus(id);
          }else{
                   
                         return  ResponseEntity.status(400).body("something went wrong");
         
            }
           

        }catch(Exception e){
              
                        return  ResponseEntity.status(400).body(e.toString());
        }

     } 
       @DeleteMapping(value="deleteticket/{id}")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> deleteticket(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        try{
          if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.deletTicket(id);
          }else{
                    
                        return  ResponseEntity.status(400).body("something went wrong");
         
            }
           

        }catch(Exception e){
             
                        return  ResponseEntity.status(400).body(e.toString());
        }

     } 
        @GetMapping(value="getTicketPerstudnet/{id}")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> getTicketPerStudent(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        try{
           if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.getTicketPerStudent(id);
           }else{
                       return  ResponseEntity.status(400).body("Something went wrong");
         
            }
           

        }catch(Exception e){
                     return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
      }

     }
       @GetMapping(value="getTicketPerstudnet")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
     public ResponseEntity<String> getTicketPerStudentFromStudent(@RequestHeader("Authorization") String token){
        try{
           if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("student"))){
 return service.getTicketPerStudent(tokenUtil.getIdFromToken(token));
           }else{
                       return  ResponseEntity.status(400).body("Something went wrong");
         
            }
           

        }catch(Exception e){
                     return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
      }

     }
     
      @GetMapping(value="getSTatisktik")
     @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
   public ResponseEntity<String> gertStatistik(@RequestHeader("Authorization") String token){
      
        try{
           if(tokenUtil.isValidToken(token)&&(tokenUtil.getRoleFromToken(token).equals("admin"))){
 return service.statistik();
           }else{
                       return  ResponseEntity.status(400).body("Something went wrong");
         
            }
           

        }catch(Exception e){
                     return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
      }
   }
}
