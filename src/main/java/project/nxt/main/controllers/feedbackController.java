package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.DTO.feedbackDTO;
import project.nxt.main.models.feedback;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class feedbackController {
    @Autowired
    private project.nxt.main.repositories.feedbackRepository feedbackRepository;
    @Autowired
    private project.nxt.main.repositories.residentRepository residentRepository;

    @GetMapping("/getall")
    List<feedback> getall(){
        return feedbackRepository.findAll();
    }
    @DeleteMapping("/{id}")
    String delete(@PathVariable String id){
        try{
            feedbackRepository.deleteById(id);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
    @PostMapping("/updatestatus/{id}")
    String update(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        try{
            feedback fb =feedbackRepository.findById(id).get();
            fb.setStatus(requestBody.get("status"));
            feedbackRepository.save(fb);
            return "ok";
        }catch(Exception e){
            return "fail";
        }
    }
    @PostMapping("/add/")
    String add(@RequestBody feedbackDTO feedbackDTO){
        try{
            feedback tmp = new feedback();
            tmp.setStatus("pending");
            tmp.setTitle(feedbackDTO.getTitle());
            tmp.setContent(feedbackDTO.getContent());
            tmp.setResident(residentRepository.findById(feedbackDTO.getResident_id()).get());
            tmp.setRoom(tmp.getResident().getRoom());
            feedbackRepository.save(tmp);
            return "ok";
        }catch (Exception e){
            return "fail";
        }
    }
}
