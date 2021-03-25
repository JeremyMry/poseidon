package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.services.impl.CurvePointServiceImpl;
import com.nnk.springboot.services.impl.UserInfoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CurveController {

    private CurvePointServiceImpl curvePointService;

    private UserInfoImpl userInfoImpl;

    public CurveController(CurvePointServiceImpl curvePointService, UserInfoImpl userInfoImpl) {
        this.curvePointService = curvePointService;
        this.userInfoImpl = userInfoImpl;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model, Principal user) {
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoint());
        model.addAttribute("user", userInfoImpl.getUserInfo(user));
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePointDto curvePointDto) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePointDto curvePointDto, BindingResult result) {
        if(result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.createCurvePoint(curvePointDto);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, CurvePointDto curvePointDto) {
        model.addAttribute("curvePoint", curvePointService.getSpecificCurvePointById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePointDto curvePointDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("curvePoint", curvePointService.getSpecificCurvePointById(id));
            return "curvePoint/update";
        }
        curvePointService.updateCurvePoint(id, curvePointDto);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
