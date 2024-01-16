package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.DTO.roomtypeDTO;
import project.nxt.main.models.roomtype;

import java.util.List;

@RestController
@RequestMapping("/api/roomtype")
public class roomtypeController {
    @Autowired
    private project.nxt.main.repositories.roomtypeRepository roomtypeRepository;

    @GetMapping("/getall")
    public List<roomtype> getall(){
        return roomtypeRepository.findAll();
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @RequestBody roomtypeDTO roomtypeDTO){
        try{
            roomtype rt = roomtypeRepository.findById(id).get();
            rt.update(roomtypeDTO);
            roomtypeRepository.save(rt);
            return "oke";
        } catch (Exception ex){
            return "fail";
        }
    }
}
