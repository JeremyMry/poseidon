package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.impl.CurvePointServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class CurvePointServiceTest {

    @Autowired
    CurvePointServiceImpl curvePointService;

    @Autowired
    CurvePointRepository curvePointRepository;

    @Test
    public void getSpecificBidListTest() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(11.0);
        curvePoint.setValue(15.0);

        curvePointRepository.save(curvePoint);

        Assertions.assertNotNull(curvePointService.getSpecificCurvePointById(1));
        Assertions.assertEquals(curvePointService.getSpecificCurvePointById(1).getCurveId(), curvePoint.getCurveId());
        Assertions.assertEquals(curvePointService.getSpecificCurvePointById(1).getTerm(), curvePoint.getTerm());
        Assertions.assertEquals(curvePointService.getSpecificCurvePointById(1).getValue(), curvePoint.getValue());
    }

    @Test
    public void getSpecificBidListThatDoesntExistTest() {
        Assertions.assertNull(curvePointService.getSpecificCurvePointById(1));
    }

    @Test
    public void getAllBidListTest() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(11.0);
        curvePoint.setValue(15.0);

        CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setCurveId(2);
        curvePoint1.setTerm(13.0);
        curvePoint1.setValue(25.0);


        curvePointRepository.save(curvePoint);
        curvePointRepository.save(curvePoint1);

        Assertions.assertEquals(curvePointService.getAllCurvePoint().size(), 2);
    }

    @Test
    public void getAllBidListWhenThereIsNone() {
        Assertions.assertEquals(curvePointService.getAllCurvePoint().size(), 0);
        Assertions.assertNotNull(curvePointService.getAllCurvePoint());
    }

    @Test
    public void createBidListTest() {
        CurvePointDto curvePointDto = new CurvePointDto(1, 11.0, 15.0);

        curvePointService.createCurvePoint(curvePointDto);

        Assertions.assertEquals(curvePointRepository.findById(1).get().getCurveId(), curvePointDto.getCurveId());
        Assertions.assertEquals(curvePointRepository.findById(1).get().getTerm(), curvePointDto.getTerm());
        Assertions.assertEquals(curvePointRepository.findById(1).get().getValue(), curvePointDto.getValue());
    }

    @Test
    public void updateBidListTest() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(11.0);
        curvePoint.setValue(15.0);
        curvePointRepository.save(curvePoint);
        CurvePointDto curvePointDto = new CurvePointDto(2, 13.0, 22.0);

        curvePointService.updateCurvePoint(1, curvePointDto);

        Assertions.assertEquals(java.util.Optional.of(curvePointRepository.findById(1).get().getCurveId()), java.util.Optional.ofNullable(2));
        Assertions.assertEquals(java.util.Optional.ofNullable(curvePointRepository.findById(1).get().getTerm()), java.util.Optional.ofNullable(13.0));
        Assertions.assertEquals(java.util.Optional.ofNullable(curvePointRepository.findById(1).get().getValue()), java.util.Optional.ofNullable(22.0));
        Assertions.assertEquals(curvePointRepository.findAll().size(), 1);
    }

    @Test
    public void deleteBidListTest() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(11.0);
        curvePoint.setValue(15.0);
        curvePointRepository.save(curvePoint);

        curvePointService.deleteCurvePoint(1);

        Assertions.assertFalse(curvePointRepository.findById(1).isPresent());
    }
}
