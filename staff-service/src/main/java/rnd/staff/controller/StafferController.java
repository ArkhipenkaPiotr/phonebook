package rnd.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rnd.staff.entity.Staffer;
import rnd.staff.service.StafferService;

import javax.ws.rs.Path;
import java.util.List;

@RestController
public class StafferController {
    @Autowired
    StafferService stafferService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    private ResponseEntity<List<Staffer>> getAllStaffers(){
        return new ResponseEntity<>(stafferService.getAllStaffers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    private ResponseEntity<Staffer> getStafferById(@PathVariable("id") Long id){
        return new ResponseEntity<>(stafferService.getStafferById(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    private ResponseEntity<Staffer> saveStaffer(@RequestBody Staffer staffer){
        return new ResponseEntity<>(stafferService.saveStaffer(staffer),HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    private ResponseEntity<Void> deleteStaffer(@PathVariable("id") Long id){
        stafferService.deleteStaffer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
