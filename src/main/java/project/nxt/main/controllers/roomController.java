package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.DTO.roomDTO;
import project.nxt.main.models.room;

import java.util.List;


@RestController
@RequestMapping(path = "/api/room")
public class roomController {
    @Autowired
    private project.nxt.main.repositories.roomRepository roomRepository;
    @Autowired
    private project.nxt.main.repositories.contractRepository contractRepository;
    @Autowired
    private project.nxt.main.repositories.roomtypeRepository roomtypeRepository;

    @GetMapping("/getall")
    public List<room> getall(){
        return roomRepository.findAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody roomDTO roomDTO){
        try{
            room tmp = new room();
            tmp.setNumber(roomDTO.getNumber());
            tmp.setRoomtype(roomtypeRepository.findById(roomDTO.getType_id()).get());
            tmp.setNumber_of_bed(roomDTO.getNumber_of_bed());
            tmp.setNumber_of_fridge(roomDTO.getNumber_of_fridge());
            tmp.setNumber_of_ac(roomDTO.getNumber_of_ac());
            tmp.setNumber_of_desk(roomDTO.getNumber_of_desk());
            roomRepository.save(tmp);
            return "ok";
        }catch (Exception ex){
            return "fail";
        }
    }

    @GetMapping("/status/empty")
    public List<String> getEmpty(){
        return roomRepository.findEmptyRoom();
    }
    @GetMapping("/{id}")
    public room getByid(@PathVariable String id){
        return roomRepository.findById(id).get();
    }
    @GetMapping("/status/id/{id}")
    public String getStatus(@PathVariable String id){
        return contractRepository.getStatus(id) == null?"\"empty\"":"\"occupied\"";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        try{
            roomRepository.deleteById(id);
            return "ok";
        } catch (Exception ex){
            return "fail";
        }
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @RequestBody roomDTO roomDTO){
        try{
            room tmp = roomRepository.findById(id).get();
            tmp.setNumber(roomDTO.getNumber());
            tmp.setRoomtype(roomtypeRepository.findById(roomDTO.getType_id()).get());
            tmp.setNumber_of_bed(roomDTO.getNumber_of_bed());
            tmp.setNumber_of_fridge(roomDTO.getNumber_of_fridge());
            tmp.setNumber_of_ac(roomDTO.getNumber_of_ac());
            tmp.setNumber_of_desk(roomDTO.getNumber_of_desk());
            roomRepository.save(tmp);
            return "ok";
        }catch (Exception ex){
            return "fail";
        }
    }
}
