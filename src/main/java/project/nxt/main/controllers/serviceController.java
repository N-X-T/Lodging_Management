package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.DTO.serviceDTO;
import project.nxt.main.DTO.service_indexDTO;
import project.nxt.main.models.service_index;
import project.nxt.main.models.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("")
public class serviceController {
    @Autowired
    private project.nxt.main.repositories.serviceRepository serviceRepository;
    @Autowired
    private project.nxt.main.repositories.service_indexRepository service_indexRepository;
    @Autowired
    private project.nxt.main.repositories.roomRepository roomRepository;
    @Autowired
    private project.nxt.main.repositories.contract_serviceRepository contract_serviceRepository;

    @GetMapping("/api/service/getall")
    List<service> getall(){
        return serviceRepository.findAll();
    }
    @PostMapping("/api/service/update/{id}")
    String update(@PathVariable String id, @RequestBody serviceDTO serviceDTO){
        try{
            service tmp = serviceRepository.findById(id).get();
            tmp.update(serviceDTO);
            serviceRepository.save(tmp);
            return "ok";
        } catch (Exception e){
            return "fail";
        }
    }
    @GetMapping("/api/service-index/type/{service}")
    List<service_index> service_index(@PathVariable String service,@RequestParam(name = "room-id") String room_id){
        switch(service){
            case "electric": return service_indexRepository.findByRoomIdAndServiceId(room_id, "fddb21bf-62b9-4e79-a035-8947bd6ebffa");
            case "water": return service_indexRepository.findByRoomIdAndServiceId(room_id, "0574bd96-ec49-477d-a581-7aeeaec72772");
            case "laundry": return service_indexRepository.findByRoomIdAndServiceId(room_id, "d06ddf97-d6ce-48d8-8730-de3cbe397260");
        }
        return new ArrayList<>();
    }
    @PostMapping("/api/service-index/add")
    String add(@RequestBody service_indexDTO service_indexDTO){
        try{
            service_index tmp = new service_index();
            tmp.setRoom(roomRepository.findById(service_indexDTO.getRoom_id()).get());
            tmp.setIndex(service_indexDTO.getIndex());
            switch(service_indexDTO.getType()){
                case "electric": tmp.setService(serviceRepository.findById("fddb21bf-62b9-4e79-a035-8947bd6ebffa").get());break;
                case "water": tmp.setService(serviceRepository.findById("0574bd96-ec49-477d-a581-7aeeaec72772").get());break;
                case "laundry": tmp.setService(serviceRepository.findById("d06ddf97-d6ce-48d8-8730-de3cbe397260").get());break;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime localDateTime = LocalDateTime.parse(service_indexDTO.getTime_record(), formatter);
            tmp.setTime_record(localDateTime);

            service_indexRepository.save(tmp);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @DeleteMapping("/api/service-index/{id}")
    String delete(@PathVariable String id){
        try{
            service_indexRepository.deleteById(id);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @PostMapping("/api/service-index/update/{id}")
    String update(@PathVariable String id, @RequestBody service_indexDTO service_indexDTO){
        try{
            service_index tmp = service_indexRepository.findById(id).get();
            tmp.setRoom(roomRepository.findById(service_indexDTO.getRoom_id()).get());
            tmp.setIndex(service_indexDTO.getIndex());
            switch(service_indexDTO.getType()){
                case "electric": tmp.setService(serviceRepository.findById("fddb21bf-62b9-4e79-a035-8947bd6ebffa").get());break;
                case "water": tmp.setService(serviceRepository.findById("0574bd96-ec49-477d-a581-7aeeaec72772").get());break;
                case "laundry": tmp.setService(serviceRepository.findById("d06ddf97-d6ce-48d8-8730-de3cbe397260").get());break;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime localDateTime = LocalDateTime.parse(service_indexDTO.getTime_record(), formatter);
            tmp.setTime_record(localDateTime);

            service_indexRepository.save(tmp);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @PostMapping("/api/statistics/gettotalservice")
    List<serviceDTO> gettotalservice(@RequestBody Map<String, Integer> time){
        List<serviceDTO> ret = new ArrayList<>();
        for(service s : serviceRepository.findAll()){
            serviceDTO tmp = new serviceDTO();
            tmp.setUuid(s.getUuid());
            tmp.setType(s.getType());
            tmp.setCalculation_method(s.getCalculation_method());
            tmp.setName(s.getName());
            tmp.setPrice(s.getPrice());
            tmp.setUnit(s.getUnit());

            switch (s.getName()){
                case "Điện":
                case "Nước":
                case "Giặt là": {
                    Optional<Integer> amount = service_indexRepository.SumUsedService(s.getUuid());
                    tmp.setUsed_amount(amount.isPresent()?amount.get():0);
                    tmp.setTotal_service(tmp.getUsed_amount()*tmp.getPrice());
                    break;
                }
                case "Vệ sinh riêng":
                case "Gửi xe":{
                    Optional<Integer> amount = contract_serviceRepository.SumUsedService(s.getUuid());
                    tmp.setUsed_amount(amount.isPresent()?amount.get():0);
                    tmp.setTotal_service(tmp.getUsed_amount()*tmp.getPrice());
                    break;
                }
            }
            ret.add(tmp);
        }
        return ret;
    }
}
