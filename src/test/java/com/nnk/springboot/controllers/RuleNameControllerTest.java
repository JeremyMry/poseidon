package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.impl.CustomUserDetailsService;
import com.nnk.springboot.services.impl.RuleNameServiceImpl;
import com.nnk.springboot.services.impl.UserInfoImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser
@WebMvcTest(RuleNameController.class)
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameServiceImpl ruleNameService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private UserInfoImpl userInfo;

    @Test
    public void bidListGetAllRuleNameControllerTest() throws Exception {
        List<RuleName> ruleNameList = new ArrayList<>();

        when(ruleNameService.getAllRuleName()).thenReturn(ruleNameList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleNames"));
    }

    @Test
    public void addRuleNameControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("ruleName/add"))
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    public void validateRuleNameControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate").with(csrf()).contentType(MediaType.ALL)
                .param("name", "a").param("description", "a").param("json", "a").param("template", "a").param("sqlStr", "a").param("sqlPart", "a"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void validateRuleNameControllerWithErrorTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("ruleName/add"))
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    public void showUpdateFormRuleNameControllerTest() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("a");
        ruleName.setDescription("a");
        ruleName.setTemplate("a");
        ruleName.setJson("a");
        ruleName.setSqlPart("a");
        ruleName.setSqlStr("a");

        when(ruleNameService.getSpecificRuleNameById(Mockito.any(Integer.class))).thenReturn(ruleName);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/update/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    public void updateRuleNameControllerTest() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("a");
        ruleName.setDescription("a");
        ruleName.setTemplate("a");
        ruleName.setJson("a");
        ruleName.setSqlPart("a");
        ruleName.setSqlStr("a");

        when(ruleNameService.getSpecificRuleNameById(Mockito.any(Integer.class))).thenReturn(ruleName);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1").with(csrf()).contentType(MediaType.ALL)
                .param("name", "a").param("description", "a").param("json", "a").param("template", "a").param("sqlStr", "a").param("sqlPart", "a"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }

    @Test
    public void updateRuleNameControllerWithErrorTest() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("a");
        ruleName.setDescription("a");
        ruleName.setTemplate("a");
        ruleName.setJson("a");
        ruleName.setSqlPart("a");
        ruleName.setSqlStr("a");

        when(ruleNameService.getSpecificRuleNameById(Mockito.any(Integer.class))).thenReturn(ruleName);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    public void deleteRuleNameControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/delete/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"));
    }
}
