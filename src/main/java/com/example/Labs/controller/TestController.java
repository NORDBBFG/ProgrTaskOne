package com.example.Labs.controller;

import com.example.Labs.model.DocumentEntity;
import com.example.Labs.service.TestService;
import com.example.Labs.service.dto.DtoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;



    @PostMapping("/entity")
    public @ResponseBody DtoEntity insertEntity(@RequestBody DtoEntity dtoEntity){
        return testService.insertEntity(dtoEntity);
    }

    @PostMapping("/entity/money/transfer")
    public @ResponseBody String executeMoneyTransfer(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam Long amount){
        return testService.executeMoneyTransfer(senderId, receiverId, amount);
    }

    @PostMapping("/EntityUpdate")
    public @ResponseBody DtoEntity updateEntity(@RequestBody DtoEntity dtoEntity, @RequestParam int id){
        return testService.updateEntity(id, dtoEntity);
    }

    @GetMapping("/entity")
    public @ResponseBody List<DtoEntity> findById(@RequestParam Long id){
        return testService.findDocumentsById(id);
    }

    @DeleteMapping("/entity")
    public void deleteById(@RequestParam Long id){
        testService.deleteById(id);
    }

}
