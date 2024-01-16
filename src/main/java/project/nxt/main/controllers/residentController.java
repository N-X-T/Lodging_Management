package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.DTO.residentDTO;
import project.nxt.main.models.resident;

import java.util.List;

@RestController
@RequestMapping("/api/resident")
public class residentController {
    @Autowired
    private project.nxt.main.repositories.residentRepository residentRepository;
    @GetMapping("/getall")
    List<resident> getall(){
        return residentRepository.findAll();
    }
    @GetMapping("/{id}")
    resident getByid(@PathVariable String id){
        return residentRepository.findById(id).get();
    }
    @PostMapping("/add")
    String add(@RequestBody residentDTO residentDTO){
        try{
            resident tmp = new resident();
            tmp.setFirst_name(residentDTO.getFirst_name());
            tmp.setLast_name(residentDTO.getLast_name());
            tmp.setDate_of_birth(residentDTO.getDate_of_birth());
            tmp.setCitizen_id(residentDTO.getCitizen_id());
            tmp.setPhone_number(residentDTO.getPhone_number());
            tmp.setOwner(residentDTO.isOwner());
            residentRepository.save(tmp);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @DeleteMapping("/{id}")
    String delete(@PathVariable String id){
        try{
            residentRepository.deleteById(id);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @PostMapping("/update/{id}")
    String update(@PathVariable String id,@RequestBody residentDTO residentDTO){
        try{
            resident tmp = residentRepository.findById(id).get();
            tmp.setFirst_name(residentDTO.getFirst_name());
            tmp.setLast_name(residentDTO.getLast_name());
            tmp.setDate_of_birth(residentDTO.getDate_of_birth());
            tmp.setCitizen_id(residentDTO.getCitizen_id());
            tmp.setPhone_number(residentDTO.getPhone_number());
            tmp.setOwner(residentDTO.isOwner());
            residentRepository.save(tmp);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
}
