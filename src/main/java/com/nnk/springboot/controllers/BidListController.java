package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.services.impl.BidListServiceImpl;
import com.nnk.springboot.services.impl.UserInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class BidListController {

    private BidListServiceImpl bidListService;

    private UserInfoImpl userInfoImpl;

    public BidListController(BidListServiceImpl bidListService, UserInfoImpl userInfoImpl) {
        this.bidListService = bidListService;
        this.userInfoImpl = userInfoImpl;
    }


    @GetMapping("/bidList/list")
    public String home(Model model, Principal user) {
        model.addAttribute("bidLists", bidListService.getAllBidList());
        model.addAttribute("user", userInfoImpl.getUserInfo(user));
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidListDto bidListDto) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidListDto bidListDto, BindingResult result) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.createBidList(bidListDto);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, BidListDto bidListDto) {
        model.addAttribute("bidList", bidListService.getSpecificBidListById(id));
        return "bidList/update"; }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidListDto bidListDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("bidList", bidListService.getSpecificBidListById(id));
            return "bidList/update";
        }
        bidListService.updateBidList(id, bidListDto);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        bidListService.deleteBidList(id);
        return "redirect:/bidList/list";
    }
}
