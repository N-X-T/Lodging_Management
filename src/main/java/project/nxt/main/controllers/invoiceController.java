package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.DTO.invoice2DTO;
import project.nxt.main.DTO.invoiceDTO;
import project.nxt.main.models.*;
import project.nxt.main.repositories.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/invoice")
public class invoiceController {
    @Autowired
    private project.nxt.main.repositories.invoiceRepository invoiceRepository;
    @Autowired
    private project.nxt.main.repositories.contractRepository contractRepository;
    @Autowired
    private project.nxt.main.repositories.purchaseRepository purchaseRepository;
    @Autowired
    private service_indexRepository siRepository;
    @Autowired
    private project.nxt.main.repositories.serviceRepository serviceRepository;

    @PostMapping("/create")
    String create(@RequestBody invoiceDTO invoiceDTO){
        try{
            for(String contract_id : invoiceDTO.getContracts()){
                invoice tmp = new invoice();
                tmp.setContract(contractRepository.findById(contract_id).get());
                tmp.setName(invoiceDTO.getName());
                tmp.setTime(invoiceDTO.getTime());
                tmp.setStatus("new");
                invoiceRepository.save(tmp);
            }
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @PostMapping("/updatestatus/")
    String updatestatus(@RequestBody Map<String,String> requestbody){
        try{
            invoice i = invoiceRepository.findById(requestbody.get("id")).get();
            i.setStatus(requestbody.get("status"));
            invoiceRepository.save(i);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @DeleteMapping("/{id}")
    String delete(@PathVariable String id){
        try{
            invoiceRepository.deleteById(id);
            return "ok";
        } catch (Exception e){
            return "fail";
        }
    }
    @PostMapping("/getbytime")
    List<invoice2DTO> getbytime(@RequestBody Map<String, Integer> time){
        List<invoice2DTO> ret = new ArrayList<>();
        List<invoice> invoiceList = invoiceRepository.findByDate(time.get("month"),time.get("year"));
        for(invoice i : invoiceList){
            invoice2DTO invoice2DTO = new invoice2DTO();
            invoice2DTO.setUuid(i.getUuid());
            invoice2DTO.setName(i.getName());
            invoice2DTO.setMonth(i.getTime().getMonthValue());
            invoice2DTO.setYear(i.getTime().getYear());
            invoice2DTO.setRent_cost(i.getContract().getRent_cost_per_month());

            int purchase = 0;
            for(project.nxt.main.models.purchase p : purchaseRepository.findByroomid(i.getContract().getRoomId())){
                purchase+=p.getAmount();
            }
            invoice2DTO.setPurchase(purchase);

            invoice2DTO.setStatus(i.getStatus());
            invoice2DTO.setCreate_time(i.getTime());
            invoice2DTO.setFrom_date(LocalDateTime.of(i.getTime().getYear(),i.getTime().getMonthValue(),15,0,0,0).minusMonths(1L));
            invoice2DTO.setTo_date(LocalDateTime.of(i.getTime().getYear(),i.getTime().getMonthValue(),15,0,0,0));
            invoice2DTO.setContract(i.getContract());

            List<Map<String, Object>> detail = new ArrayList<>();
            for(contract_service cs : i.getContract().getContractServices()){
                HashMap<String, Object> hs = new HashMap<>();
                hs.put("invoice_id",i.getUuid());
                hs.put("service_id",cs.service_id());
                hs.put("used_amount",cs.getRegister_amount());
                hs.put("price",cs.getService().getPrice());
                hs.put("name",cs.getSerivce_name());
                detail.add(hs);
            }

            for(service_index si : siRepository.findByRoomId(i.getContract().getRoomId())){
                HashMap<String, Object> hs = new HashMap<>();
                hs.put("invoice_id",i.getUuid());
                hs.put("service_id",si.getServiceId());
                hs.put("used_amount",si.getIndex());
                hs.put("price",si.getService().getPrice());
                hs.put("name",si.getService().getName());
                detail.add(hs);
            }

            invoice2DTO.setDetail(detail);
            ret.add(invoice2DTO);
        }
        return ret;
    }
    @PostMapping("/getcombinestatistics")
    List<invoice2DTO> getcombinestatistics(@RequestBody Map<String, Integer> time){
        List<invoice2DTO> ret = new ArrayList<>();
        List<invoice> invoiceList = invoiceRepository.findByDate(time.get("month"),time.get("year"));
        for(invoice i : invoiceList){
            invoice2DTO invoice2DTO = new invoice2DTO();
            invoice2DTO.setUuid(i.getUuid());
            invoice2DTO.setName(i.getName());
            invoice2DTO.setMonth(i.getTime().getMonthValue());
            invoice2DTO.setYear(i.getTime().getYear());
            invoice2DTO.setRent_cost(i.getContract().getRent_cost_per_month());

            int purchase = 0;
            for(purchase p : purchaseRepository.findByroomid(i.getContract().getRoomId())){
                purchase+=p.getAmount();
            }
            invoice2DTO.setPurchase(purchase);

            invoice2DTO.setStatus(i.getStatus());
            invoice2DTO.setCreate_time(i.getTime());
            invoice2DTO.setFrom_date(LocalDateTime.of(i.getTime().getYear(),i.getTime().getMonthValue(),15,0,0,0).minusMonths(1L));
            invoice2DTO.setTo_date(LocalDateTime.of(i.getTime().getYear(),i.getTime().getMonthValue(),15,0,0,0));
            invoice2DTO.setContract(i.getContract());

            List<Map<String, Object>> detail = new ArrayList<>();
            Set<String> service_id = new HashSet<>(serviceRepository.findAlluuid());

            for(contract_service cs : i.getContract().getContractServices()){
                service_id.remove(i.getUuid());

                HashMap<String, Object> hs = new HashMap<>();
                hs.put("invoice_id",i.getUuid());
                hs.put("service_id",cs.service_id());
                hs.put("used_amount",cs.getRegister_amount());
                hs.put("price",cs.getService().getPrice());
                hs.put("name",cs.getSerivce_name());
                detail.add(hs);
            }

            for(service_index si : siRepository.findByRoomId(i.getContract().getRoomId())){
                service_id.remove(i.getUuid());

                HashMap<String, Object> hs = new HashMap<>();
                hs.put("invoice_id",i.getUuid());
                hs.put("service_id",si.getServiceId());
                hs.put("used_amount",si.getIndex());
                hs.put("price",si.getService().getPrice());
                hs.put("name",si.getService().getName());
                detail.add(hs);
            }

            for(String sid:service_id){
                service tmp = serviceRepository.findById(sid).get();
                HashMap<String, Object> hs = new HashMap<>();
                hs.put("invoice_id",i.getUuid());
                hs.put("service_id",sid);
                hs.put("used_amount",0);
                hs.put("price",tmp.getPrice());
                hs.put("name",tmp.getName());
                detail.add(hs);
            }

            invoice2DTO.setRoom(invoice2DTO.getContract().getRoomNumber());

            invoice2DTO.setDetail(detail);
            ret.add(invoice2DTO);
        }
        return ret;
    }
    @GetMapping("/getbycontractid/{id}")
    List<invoice2DTO> getbycontractid(@PathVariable String id){
        List<invoice2DTO> ret = new ArrayList<>();
        List<invoice> invoiceList = invoiceRepository.findByContractId(id);
        for(invoice i : invoiceList){
            invoice2DTO invoice2DTO = new invoice2DTO();
            invoice2DTO.setUuid(i.getUuid());
            invoice2DTO.setName(i.getName());
            invoice2DTO.setMonth(i.getTime().getMonthValue());
            invoice2DTO.setYear(i.getTime().getYear());
            invoice2DTO.setRent_cost(i.getContract().getRent_cost_per_month());

            int purchase = 0;
            for(purchase p : purchaseRepository.findByroomid(i.getContract().getRoomId())){
                purchase+=p.getAmount();
            }
            invoice2DTO.setPurchase(purchase);

            invoice2DTO.setStatus(i.getStatus());
            invoice2DTO.setCreate_time(i.getTime());
            invoice2DTO.setFrom_date(LocalDateTime.of(i.getTime().getYear(),i.getTime().getMonthValue(),15,0,0,0).minusMonths(1L));
            invoice2DTO.setTo_date(LocalDateTime.of(i.getTime().getYear(),i.getTime().getMonthValue(),15,0,0,0));
            invoice2DTO.setContract(i.getContract());

            List<Map<String, Object>> detail = new ArrayList<>();
            Set<String> service_id = new HashSet<>(serviceRepository.findAlluuid());

            for(contract_service cs : i.getContract().getContractServices()){
                service_id.remove(i.getUuid());

                HashMap<String, Object> hs = new HashMap<>();
                hs.put("invoice_id",i.getUuid());
                hs.put("service_id",cs.service_id());
                hs.put("used_amount",cs.getRegister_amount());
                hs.put("price",cs.getService().getPrice());
                hs.put("name",cs.getSerivce_name());
                detail.add(hs);
            }

            for(service_index si : siRepository.findByRoomId(i.getContract().getRoomId())){
                service_id.remove(i.getUuid());

                HashMap<String, Object> hs = new HashMap<>();
                hs.put("invoice_id",i.getUuid());
                hs.put("service_id",si.getServiceId());
                hs.put("used_amount",si.getIndex());
                hs.put("price",si.getService().getPrice());
                hs.put("name",si.getService().getName());
                detail.add(hs);
            }

            for(String sid:service_id){
                service tmp = serviceRepository.findById(sid).get();
                HashMap<String, Object> hs = new HashMap<>();
                hs.put("invoice_id",i.getUuid());
                hs.put("service_id",sid);
                hs.put("used_amount",0);
                hs.put("price",tmp.getPrice());
                hs.put("name",tmp.getName());
                detail.add(hs);
            }

            invoice2DTO.setRoom(invoice2DTO.getContract().getRoomNumber());

            invoice2DTO.setDetail(detail);
            ret.add(invoice2DTO);
        }
        return ret;
    }
}
