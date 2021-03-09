package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.ICurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class CurvePointServiceImpl implements ICurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    @Override
    public CurvePoint getSpecificCurvePointById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    @Override
    public List<CurvePoint> getAllCurvePoint() {
        return curvePointRepository.findAll();
    }

    @Override
    @Transactional
    public void createCurvePoint(CurvePointDto curvePointDto) {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(curvePointDto.getCurveId());
        curvePoint.setTerm(curvePointDto.getTerm());
        curvePoint.setValue(curvePointDto.getValue());
        curvePoint.setCreationDate(Timestamp.from(Instant.now()));
        curvePointRepository.save(curvePoint);
    }

    @Override
    @Transactional
    public void updateCurvePoint(Integer id, CurvePointDto curvePointDto) {
        CurvePoint curvePoint = getSpecificCurvePointById(id);
        curvePoint.setCurveId(curvePointDto.getCurveId());
        curvePoint.setTerm(curvePointDto.getTerm());
        curvePoint.setValue(curvePointDto.getValue());
        curvePoint.setAsOfDate(Timestamp.from(Instant.now()));
        curvePointRepository.save(curvePoint);
    }

    @Override
    @Transactional
    public void deleteCurvePoint(Integer id) {
        curvePointRepository.deleteById(id);
    }

}
