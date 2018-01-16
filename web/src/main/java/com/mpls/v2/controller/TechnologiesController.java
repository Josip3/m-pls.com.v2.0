package com.mpls.v2.controller;

import com.mpls.v2.dto.TechnologiesDto;
import com.mpls.v2.model.Technologies;
import com.mpls.v2.service.TechnologiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/technologies")
public class TechnologiesController {

    @Autowired
    TechnologiesService technologiesService;

    @PostMapping("/save")
    private ResponseEntity<TechnologiesDto> save(@RequestBody TechnologiesDto technologies) {
        return new ResponseEntity<>(map(technologiesService.save(map(technologies, Technologies.class)), TechnologiesDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<TechnologiesDto>> getList() {
        return new ResponseEntity<>(technologiesService.findAll().stream()
                .map(technologies -> map(technologies, TechnologiesDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<TechnologiesDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(technologiesService.findOne(id), TechnologiesDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return new ResponseEntity(technologiesService.delete(id) ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/find-by-name")
    private ResponseEntity<TechnologiesDto> findByName(@RequestParam String name){
        return new ResponseEntity<>(map(technologiesService.findByName(name),TechnologiesDto.class),HttpStatus.OK);
    }
}
