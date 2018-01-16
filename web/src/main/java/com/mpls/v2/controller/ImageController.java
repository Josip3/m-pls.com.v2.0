package com.mpls.v2.controller;

import com.mpls.v2.dto.ImageDTO;
import com.mpls.v2.model.Image;
import com.mpls.v2.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/save")
    private ResponseEntity<ImageDTO> save(@RequestBody ImageDTO image) {
        return new ResponseEntity<ImageDTO>(map(imageService.save(map(image, Image.class)), ImageDTO.class), HttpStatus.OK);
    }

    @PostMapping("/upload-image/{id}")
    private ResponseEntity<ImageDTO> saveImg(@RequestParam MultipartFile multipartFile,@PathVariable Long id){
        return new ResponseEntity<ImageDTO>(map(imageService.upload(multipartFile,id),ImageDTO.class),HttpStatus.OK);
    }
    
    @GetMapping("/find-all")
    private ResponseEntity<List<ImageDTO>> findAll(){
        return new ResponseEntity<>(imageService.findAll().stream()
                .map(image -> map(image,ImageDTO.class)).collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ImageDTO> findOne(@PathVariable Long id){
        return new ResponseEntity<>(map(imageService.findOne(id),ImageDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        return new ResponseEntity(imageService.delete(id)? HttpStatus.OK:HttpStatus.CONFLICT);
    }

    @GetMapping("/find-by-name")
    private ResponseEntity<ImageDTO> findByName(@RequestParam String name){
        return new ResponseEntity<>(map(imageService.findByOriginalName(name),ImageDTO.class),HttpStatus.OK);
    }


}
