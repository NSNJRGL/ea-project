package cs544.team1.controller;

import cs544.team1.model.AcademicBlock;
import cs544.team1.service.AcademincBlockSericeImpl;
import cs544.team1.service.IAcademicBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blocks")
public class BlockController {

    @Autowired
     IAcademicBlockService service;

    @GetMapping
    public List<AcademicBlock> findAll(){
        return service.findAll();
    }
}
