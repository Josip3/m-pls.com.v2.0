package com.mpls.v2.controller;

import com.mpls.v2.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mpls.v2.dto.GroupFullDTO;
import com.mpls.v2.dto.GroupShortDTO;
import com.mpls.v2.model.Group;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping("/save")
    private ResponseEntity<GroupFullDTO> save(@RequestBody Group group){
        return new ResponseEntity<>(map(groupService.save(map(group,Group.class)),GroupFullDTO.class), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<GroupShortDTO>> findAll(){
        return new ResponseEntity<>(groupService.findAll().stream()
                .map(group -> map(group,GroupShortDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<GroupFullDTO> findOne(@PathVariable Long id){
        return new ResponseEntity<>(map(groupService.findOne(id),GroupFullDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<GroupFullDTO> delete(@PathVariable Long id){
        return new ResponseEntity<>(map(groupService.delete(id),GroupFullDTO.class),HttpStatus.OK);
    }

    @GetMapping("/find-by-name")
    private ResponseEntity<GroupFullDTO> findByName(@RequestParam String name){
        return new ResponseEntity<>(map(groupService.findByName(name),GroupFullDTO.class),HttpStatus.OK);
    }

}
