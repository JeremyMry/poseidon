package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;

import java.util.List;

public interface ICurvePointService {

    public CurvePoint getSpecificCurvePointById(Integer id);

    public List<CurvePoint> getAllCurvePoint();

    public void createCurvePoint(CurvePointDto curvePointDto);

    public void updateCurvePoint(Integer id, CurvePointDto curvePointDto);

    public void deleteCurvePoint(Integer id);
}
