package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.impl.RuleNameServiceImpl;
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
public class RuleNameController {

    private RuleNameServiceImpl ruleNameService;

    private UserInfoImpl userInfoImpl;

    public RuleNameController(RuleNameServiceImpl ruleNameService, UserInfoImpl userInfoImpl) {
        this.ruleNameService = ruleNameService;
        this.userInfoImpl = userInfoImpl;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model, Principal user) {
        model.addAttribute("user", userInfoImpl.getUserInfo(user));
        model.addAttribute("ruleNames", ruleNameService.getAllRuleName());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result) {
        if(result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.createRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RuleName ruleName) {
        model.addAttribute("ruleNameUpdate", ruleNameService.getSpecificRuleNameById(id));
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("ruleNameUpdate", ruleNameService.getSpecificRuleNameById(id));
            return "ruleName/update";
        }
        ruleNameService.updateRuleName(id, ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {
        ruleNameService.deleteRuleName(id);
        return "redirect:/ruleName/list";
    }
}
