package com.youyu.cotenant.web.rest;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.UserService;
import com.youyu.cotenant.web.rest.vm.user.ProposalInVM;
import com.youyu.cotenant.web.rest.vm.user.UserCollegeVM;
import com.youyu.cotenant.web.rest.vm.user.UserInfoInVM;
import com.youyu.cotenant.web.rest.vm.user.UserRegisterInVM;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     *
     * @param userRegisterInVM
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@Valid @RequestBody UserRegisterInVM userRegisterInVM) {
        int result = userService.register(userRegisterInVM);
        return result == NumberUtils.INTEGER_ZERO ? ResponseResult.fail() : ResponseResult.success();
    }

    /**
     * info
     *
     * @return
     */
    @GetMapping("/info")
    public ResponseResult info() {
        return ResponseResult.success().body(userService.info());
    }

    /**
     * 查询用户个人资料
     *
     * @return
     */
    @GetMapping("/detail")
    public ResponseResult detail() {
        return ResponseResult.success().body(userService.detail());
    }

    /**
     * 完善/更新个人资料
     *
     * @param userInfoInVM
     * @return
     */
    @PutMapping("/update")
    public ResponseResult update(@Valid @RequestBody UserInfoInVM userInfoInVM) {
        userService.update(userInfoInVM);
        return ResponseResult.success();
    }

    @PostMapping("/proposal/reported")
    public ResponseResult reportedProposal(@Valid @RequestBody ProposalInVM proposalInVM) {
        userService.reportedProposal(proposalInVM);
        return ResponseResult.success();
    }

    /**
     * 获取用户学校信息
     *
     * @return
     */
    @GetMapping("/list/colleges")
    public ResponseResult getColleges() {
        return ResponseResult.success().body(userService.getColleges());
    }

    @PostMapping("/add/colleges")
    public ResponseResult saveColleges(@RequestBody UserCollegeVM userCollegeVM) {
        userService.saveCollege(userCollegeVM);
        return ResponseResult.success();
    }

    @PutMapping("/update/colleges")
    public ResponseResult updateColleges(@RequestBody UserCollegeVM userCollegeVM) {
        userService.updateCollege(userCollegeVM);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/colleges/{id}")
    public ResponseResult deleteColleges(@PathVariable ("id") Long id) {
        userService.deleteCollege(id);
        return ResponseResult.success();
    }

}