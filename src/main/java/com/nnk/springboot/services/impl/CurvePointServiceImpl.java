package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.ICurvePointService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class CurvePointServiceImpl implements ICurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Autowired
    private Logger logger;

    @Override
    public CurvePoint getSpecificCurvePointById(Integer id) {
        logger.info("CurvePoint" + id + " find");
        return curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Curve Point Id: " + id));
    }

    @Override
    public List<CurvePoint> getAllCurvePoint() {
        logger.info("CurvePoint List find");
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

        logger.info("CurvePoint created");
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
        logger.info("CurvePoint " + id + " updated");
        curvePointRepository.save(curvePoint);
    }

    @Override
    @Transactional
    public void deleteCurvePoint(Integer id) {
        logger.info("CurvePoint " + id + " updated");
        curvePointRepository.deleteById(id);
    }

}
