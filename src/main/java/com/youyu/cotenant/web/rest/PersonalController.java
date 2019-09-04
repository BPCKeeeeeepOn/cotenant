package com.youyu.cotenant.web.rest;


import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.PersonalService;
import com.youyu.cotenant.web.rest.vm.personal.GroupExamineInVM;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/personal")
public class PersonalController {


    private PersonalService personalService;

    @GetMapping("/list")
    public ResponseResult list(@RequestParam("role") Integer role) {
        return ResponseResult.success().body(personalService.list(role));
    }

    @GetMapping("/group/{id}/detail")
    public ResponseResult detail(@PathVariable("id") Long id) {
        return ResponseResult.success().body(personalService.detail(id));
    }

    @PostMapping("/group/{id}/dismiss")
    public ResponseResult dismiss(@PathVariable("id") Long id) {
        personalService.dismiss(id);
        return ResponseResult.success();
    }

    @PostMapping("/examine")
    public ResponseResult examine(@RequestBody @Valid GroupExamineInVM groupExamineInVM) {
        personalService.examine(groupExamineInVM);
        return ResponseResult.success();
    }

    @PostMapping("/group/{id}/cancel")
    public ResponseResult cancel(@PathVariable("id") Long id) {
        personalService.cancel(id);
        return ResponseResult.success();
    }

}
