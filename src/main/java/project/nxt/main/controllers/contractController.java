package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.DTO.contractDTO;
import project.nxt.main.DTO.purchaseDTO;
import project.nxt.main.models.contract;
import project.nxt.main.models.contract_service;
import project.nxt.main.models.resident;
import project.nxt.main.models.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/contract")
public class contractController {
    @Autowired
    private project.nxt.main.repositories.contractRepository contractRepository;
    @Autowired
    private project.nxt.main.repositories.roomRepository roomRepository;
    @Autowired
    private project.nxt.main.repositories.residentRepository residentRepository;

    @Autowired
    private purchaseController purchaseController;
    @Autowired
    private project.nxt.main.repositories.serviceRepository serviceRepository;
    @Autowired
    private project.nxt.main.repositories.contract_serviceRepository contract_serviceRepository;

    @GetMapping("/getall")
    public List<contract> getall(){
        return contractRepository.findAll();
    }
    @PostMapping("/add")
    String add(@RequestBody contractDTO contractDTO){
        try{
            contract tmp = new contract();
            tmp.setRoom(roomRepository.findById(contractDTO.getRoom_id()).get());

            resident resident = residentRepository.findById(contractDTO.getSigner()).get();

            tmp.setSigner(resident);
            tmp.setFrom_date(contractDTO.getFrom_date());
            tmp.setTo_date(contractDTO.getTo_date());
            tmp.setRent_cost_per_month(contractDTO.getRent_cost_per_month());
            tmp.setDeposit(contractDTO.getDeposit());
            tmp.setType(contractDTO.getType());
            tmp.setStatus(true);
            contractRepository.save(tmp);

            resident.setOwner(true);
            resident.setContract(tmp);
            residentRepository.save(resident);

            //Xử lý dịch vụ đăng kí và thêm thành viên
            int amount = 0;
            String note = "Tiền dịch vụ: ";
            for(String key : contractDTO.getOption_service().keySet()){
                service serviceTMP = serviceRepository.findById(key).get();
                int money = serviceTMP.getPrice()*contractDTO.getOption_service().get(key);
                amount+=money;
                note+=serviceTMP.getName()+", ";

                if(contractDTO.getOption_service().get(key)!=0){
                    contract_service cs = new contract_service();
                    cs.setSerivce_name(serviceTMP.getName());
                    cs.setRegister_amount(contractDTO.getOption_service().get(key));
                    cs.setService(serviceTMP);
                    cs.setContract(tmp);
                    contract_serviceRepository.save(cs);
                }
            }
            if(amount != 0){
                purchaseDTO purchaseDTO = new purchaseDTO();
                purchaseDTO.setAmount(amount);
                purchaseDTO.setNote(note);
                purchaseDTO.setRoom_id(contractDTO.getRoom_id());

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                String formattedDateTime = currentDateTime.format(formatter);
                purchaseDTO.setTime_record(formattedDateTime);
                purchaseController.add(purchaseDTO);
            }
            return "ok";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "fail";
        }
    }
    @PostMapping("/update/{id}")
    String update(@PathVariable String id, @RequestBody contractDTO contractDTO){
        try{
            contract tmp = contractRepository.findById(id).get();
            tmp.setDeposit(contractDTO.getDeposit());
            tmp.setFrom_date(contractDTO.getFrom_date());
            tmp.setRent_cost_per_month(contractDTO.getRent_cost_per_month());
            tmp.setRoom(roomRepository.findById(contractDTO.getRoom_id()).get());
            tmp.setSigner(residentRepository.findById(contractDTO.getSigner()).get());
            tmp.setTo_date(contractDTO.getTo_date());
            tmp.setType(contractDTO.getType());
            contractRepository.save(tmp);
            return "ok";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "fail";
        }
    }
    @PostMapping("/status")
    String status(@RequestBody Map<String, Object> requestBody){
        try{
            contract tmp = contractRepository.findById((String)requestBody.get("id")).get();
            tmp.setStatus((boolean) requestBody.get("status"));
            contractRepository.save(tmp);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @DeleteMapping("/{id}")
    String delete(@PathVariable String id){
        try{
            contract tmp = contractRepository.findById(id).get();
            resident resident = tmp.getResident();
            resident.setOwner(false);
            resident.setContract(null);

            contract_serviceRepository.deleteAllById(contract_serviceRepository.findUUIDBycontractid(id));

            contractRepository.delete(tmp);

            residentRepository.save(resident);
            return "ok";
        } catch (Exception e){
            return "fail";
        }
    }
    @GetMapping("/getbycontractid/{id}")
    List<contract_service> getbycontractid(@PathVariable String id){
        return contract_serviceRepository.findBycontractid(id);
    }
    @GetMapping("/status/{status}")
    List<contract> getStatusContract(@PathVariable boolean status){
        return contractRepository.getStatusContract(status);
    }
    @GetMapping("/signer/{id}")
    contract signer(@PathVariable String id){
        return contractRepository.findBySigner(id);
    }
}
