package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.DTO.purchaseDTO;
import project.nxt.main.models.purchase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class purchaseController {
    @Autowired
    private project.nxt.main.repositories.purchaseRepository purchaseRepository;
    @Autowired
    private project.nxt.main.repositories.roomRepository roomRepository;

    @PostMapping("/add")
    String add(@RequestBody purchaseDTO purchaseDTO){
        try {
            purchase tmp = new purchase();
            tmp.setRoom(roomRepository.findById(purchaseDTO.getRoom_id()).get());
            tmp.setAmount(purchaseDTO.getAmount());
            tmp.setNote(purchaseDTO.getNote());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime localDateTime = LocalDateTime.parse(purchaseDTO.getTime_record(), formatter);
            tmp.setTime_record(localDateTime);

            purchaseRepository.save(tmp);
            return "ok";
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            return "fail";
        }
    }
    @PostMapping("/update/{id}")
    String update(@PathVariable String id,@RequestBody purchaseDTO purchaseDTO){
        try {
            purchase tmp = purchaseRepository.findById(id).get();
            tmp.setRoom(roomRepository.findById(purchaseDTO.getRoom_id()).get());
            tmp.setAmount(purchaseDTO.getAmount());
            tmp.setNote(purchaseDTO.getNote());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime localDateTime = LocalDateTime.parse(purchaseDTO.getTime_record(), formatter);
            tmp.setTime_record(localDateTime);

            purchaseRepository.save(tmp);
            return "ok";
        } catch (Exception ex){
            return "fail";
        }
    }
    @DeleteMapping("/{id}")
    String delete(@PathVariable String id){
        try{
            purchaseRepository.deleteById(id);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }

    @GetMapping("/getbyroom/{id}")
    List<purchase> getbyroom(@PathVariable String id){
        return purchaseRepository.findByroomid(id);
    }
}
