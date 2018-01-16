package com.mpls.v2.controller;

import com.mpls.v2.dto.IndustriesFullDto;
import com.mpls.v2.dto.IndustriesShortDto;
import com.mpls.v2.model.Industries;
import com.mpls.v2.service.IndustriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/industries")
public class IndustriesController {

    @Autowired
    IndustriesService industriesService;

    @PostMapping
    private ResponseEntity<IndustriesFullDto> save(@RequestBody IndustriesFullDto industries){
        return new ResponseEntity<>(map(industriesService.save(map(industries, Industries.class)),IndustriesFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<IndustriesShortDto>> findAll(){
        return new ResponseEntity<>(industriesService.findAll().stream()
                .map(industries -> map(industries,IndustriesShortDto.class)).collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<IndustriesFullDto> findOne(@PathVariable Long id){
        return new ResponseEntity<>(map(industriesService.findOne(id),IndustriesFullDto.class),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        return new ResponseEntity(industriesService.delete(id)? HttpStatus.OK:HttpStatus.CONFLICT);
    }

    @GetMapping("/find-by-name")
    private ResponseEntity<IndustriesFullDto> findByName(@RequestParam String name){
        return new ResponseEntity<>(map(industriesService.findByName(name),IndustriesFullDto.class),HttpStatus.OK);
    }


}
