package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.impl.CurvePointServiceImpl;
import com.nnk.springboot.services.impl.CustomUserDetailsService;
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

@WithMockUser
@WebMvcTest(CurveController.class)
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointServiceImpl curvePointService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void getAllCurvePointControllerTest() throws Exception {
        List<CurvePoint> curvePointList = new ArrayList<>();

        when(curvePointService.getAllCurvePoint()).thenReturn(curvePointList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoints"));
    }

    @Test
    public void addCurveFormControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/add"))
                .andExpect(model().attributeExists("curvePointDto"));
    }

    @Test
    public void validateCurvePointControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate").with(csrf()).contentType(MediaType.ALL)
                .param("curveId", "1").param("term", "15.0").param("value", "15.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    public void validateCurvePointControllerWithErrorTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/validate").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/add"))
                .andExpect(model().attributeExists("curvePointDto"));
    }

    @Test
    public void showUpdateFormControllerTest() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.0);
        curvePoint.setValue(11.0);

        when(curvePointService.getSpecificCurvePointById(Mockito.any(Integer.class))).thenReturn(curvePoint);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/update/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePointDto"));
    }

    @Test
    public void updateCurvePointControllerTest() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.0);
        curvePoint.setValue(11.0);

        when(curvePointService.getSpecificCurvePointById(Mockito.any(Integer.class))).thenReturn(curvePoint);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1").with(csrf()).contentType(MediaType.ALL)
                .param("curveId", "1").param("term", "15.0").param("value", "15.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }

    @Test
    public void updateCurveControllerWithErrorTest() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.0);
        curvePoint.setValue(11.0);

        when(curvePointService.getSpecificCurvePointById(Mockito.any(Integer.class))).thenReturn(curvePoint);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/curvePoint/update/1").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    public void deleteCurveControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/delete/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"));
    }
}
