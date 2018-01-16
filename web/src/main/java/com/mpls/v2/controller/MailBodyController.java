package com.mpls.v2.controller;

import com.mpls.v2.dto.MailBodyDTO;
import com.mpls.v2.model.MailBody;
import com.mpls.v2.service.MailBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/mail-body")
public class MailBodyController {

    @Autowired
    MailBodyService mailBodyService;

    @PostMapping("/save")
    private ResponseEntity<MailBodyDTO> save(@RequestBody MailBodyDTO mailBody){
        return new ResponseEntity<>(map(mailBodyService.save(map(mailBody,MailBody.class)),MailBodyDTO.class), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<MailBodyDTO>> findAll(){
        return new ResponseEntity<>(mailBodyService.findAll().stream()
                .map(mailBody -> map(mailBody,MailBodyDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one")
    private ResponseEntity<MailBodyDTO> findOne(@PathVariable Long id){
        return new ResponseEntity<>(map(mailBodyService.findOne(id),MailBodyDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        return new ResponseEntity(mailBodyService.delete(id)? HttpStatus.OK:HttpStatus.CONFLICT);
    }

    @GetMapping("/find-by-name")
    private ResponseEntity<MailBodyDTO> findByName(@RequestParam String name){
        return new ResponseEntity<>(map(mailBodyService.findByName(name),MailBodyDTO.class),HttpStatus.OK);
    }
}



