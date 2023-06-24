package com.task.geritaskJAVA.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.geritaskJAVA.Models.StatusEnum;
import com.task.geritaskJAVA.Models.pjese;
import com.task.geritaskJAVA.Models.statistika;
import com.task.geritaskJAVA.Models.ticket;
import com.task.geritaskJAVA.Models.ticketget;
import com.task.geritaskJAVA.Models.users;
import com.task.geritaskJAVA.Reprositories.pjeseRep;
import com.task.geritaskJAVA.Reprositories.ticketRep;
import com.task.geritaskJAVA.Reprositories.usersRep;
import com.task.geritaskJAVA.Security.TokenUtil;

@Service
public class Services {
     @Autowired
    private usersRep usersRep;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private pjeseRep pjeseRep;

    @Autowired
    private ticketRep ticketRep;

    public ResponseEntity<String> insert(List<users> data){
        try{
for(int i =0; i<data.size();i++){
    List<users> data1 =usersRep.findByUsername(data.get(i).getUsername());
    if(data1.size()==0){
  usersRep.save(data.get(i));

    }
  
}
return ResponseEntity.ok("done");
        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

        }

    }  public ResponseEntity<String> login( users data){
        try{
            List<users> searchRes = usersRep.findByUsername(data.getUsername());
            if(searchRes.size()==0){
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data is not correct");

            }else{
                if(searchRes.get(0).getPassword().equals(data.getPassword())){
                    List<String> res =  new ArrayList();
                   
                    res.add(tokenUtil.generateToken(searchRes.get(0)));
                     res.add(searchRes.get(0).getRole());
                    ObjectMapper obj = new ObjectMapper();
                    String res1 =  obj.writeValueAsString(res);

                    return ResponseEntity.ok(res1);
                }else{
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data is not correct");

                }
            }
           

        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
     public ResponseEntity<String> insertPjese( pjese data){
        try{
            pjeseRep.save(data);
            return ResponseEntity.ok("Ok");

        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
        public ResponseEntity<String> getpjesetDHeStudentet(){
        try{
         
      List<pjese> pjese = pjeseRep.findAll();
        List<users> student =  usersRep.findByRole("student");
        List<List> response1 = new ArrayList<>();
response1.add(pjese);
response1.add(student);

        ObjectMapper resp1 =  new ObjectMapper();
   return ResponseEntity.ok(resp1.writeValueAsString(response1));
        }catch(Exception e){
            
                 
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
   
         public ResponseEntity<String> updatePjeset(pjese data){
        try{
            Optional<pjese> searchRes =  pjeseRep.findById(data.getId());
          pjese updatedPjesa = searchRes.get();
          updatedPjesa.setCmimi(data.getCmimi());
          updatedPjesa.setDescription(data.getDescription());
          updatedPjesa.setName(data.getName());
          updatedPjesa.setStock(data.getStock());
          pjeseRep.save(updatedPjesa);

        return ResponseEntity.ok("done");

        }catch(Exception e){
           
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
     public ResponseEntity<String> deletepjes(Integer id){
        try{
      pjeseRep.deleteById(id);

        return ResponseEntity.ok("done");

        }catch(Exception e){

                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
       public ResponseEntity<String> getStudent(){
        try{
   List<users> res =  usersRep.findByRole("student");
   ObjectMapper obj = new ObjectMapper();


        return ResponseEntity.ok(obj.writeValueAsString(res));

        }catch(Exception e){

                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
        public ResponseEntity<String> insertticket(List<ticket> data){
        try{
          
            Optional<pjese> pjes = pjeseRep.findById(data.get(0).getId_pjese());
            pjese pjes1 = pjes.get();
            if((pjes1.getStock()==data.size()||pjes1.getStock()>data.size())&&(pjes1.getStock()!=0)){
                for(int i =0; i<data.size();i++){
                    ticketRep.save(data.get(i));
                    pjes1.decreaseStock();
                    pjeseRep.save(pjes1);
                }
             
        return ResponseEntity.ok("done");

            }else{
              return  ResponseEntity.status(400).body("nuk ka ne gjendje");
            }





        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
     public ResponseEntity<String> getTicket(){
        try{
            List<ticketget> response = new ArrayList<>();
          
        List<ticket> ticketall =  ticketRep.findAll();
        for(int i =0; i<ticketall.size(); i++){
            ticketget cell =  new ticketget(ticketall.get(i).getId(), ticketall.get(i).getId_pjese(), pjeseRep.findById(ticketall.get(i).getId_pjese()).get().getName(), ticketall.get(i).getDescription(), ticketall.get(i).getId_laptop(), usersRep.findById(ticketall.get(i).getId_laptop()).get().getUsername(), ticketall.get(i).getStatus());
      response.add(cell);
        }

ObjectMapper obj = new ObjectMapper();
return ResponseEntity.ok(obj.writeValueAsString(response));

        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
     public ResponseEntity<String> updateStatus(Integer id){
        try{
           Optional<ticket> obj = ticketRep.findById(id);
           ticket updateTicket = obj.get();
           updateTicket.setStatus(updateTicket.getStatus().equals(StatusEnum.OPEN)?StatusEnum.CLOSED:StatusEnum.OPEN);
ticketRep.save(updateTicket);
return ResponseEntity.ok("ok");
      

        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
        public ResponseEntity<String> deletTicket(Integer id){
        try{
        ticketRep.deleteById(id);
return ResponseEntity.ok("ok");
      

        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }
       public ResponseEntity<String> getTicketPerStudent(Integer id){
        try{
            List<ticketget> response = new ArrayList<>();
          
        List<ticket> ticketall =  ticketRep.findTicketsByLaptopId(id);
        for(int i =0; i<ticketall.size(); i++){
            ticketget cell =  new ticketget(ticketall.get(i).getId(), ticketall.get(i).getId_pjese(), pjeseRep.findById(ticketall.get(i).getId_pjese()).get().getName(), ticketall.get(i).getDescription(), ticketall.get(i).getId_laptop(), usersRep.findById(ticketall.get(i).getId_laptop()).get().getUsername(), ticketall.get(i).getStatus());
      response.add(cell);
        }

ObjectMapper obj = new ObjectMapper();
return ResponseEntity.ok(obj.writeValueAsString(response));

        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

        }

     }public ResponseEntity<String> statistik(){
        try{

            List<Integer> laptopid=  ticketRep.findDistinctIdLaptop();
            List<Integer> pjese_id=  ticketRep.findDistinctIdPjese();
            List<statistika> reponseLaptopId =  new ArrayList<>();
            List<statistika> reponsePjeseId =  new ArrayList<>();

            for(int i =0; i<laptopid.size();i++){
                List<ticket> size1= ticketRep.findTicketsByLaptopId(laptopid.get(i));
              Optional<users> user1 =  usersRep.findById(laptopid.get(i));
              users user2 =  user1.get();
              String name = user2.getUsername();
              statistika st = new statistika(name, size1.size());
              reponseLaptopId.add(st);

            }

  for(int i =0; i<pjese_id.size();i++){
                List<ticket> size1= ticketRep.findTicketsByPjeseId(pjese_id.get(i));
              Optional<pjese> user1 =  pjeseRep.findById(pjese_id.get(i));
              pjese user2 =  user1.get();
              String name = user2.getName();
              statistika st = new statistika(name, size1.size());
              reponsePjeseId.add(st);

            }
            List<List> reponsetotal = new ArrayList<>();
            reponsetotal.add(reponseLaptopId);
            reponsetotal.add(reponsePjeseId);

            ObjectMapper obj = new ObjectMapper();

            return ResponseEntity.ok(obj.writeValueAsString(reponsetotal));




        }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());
        }
     }
     
    
}
