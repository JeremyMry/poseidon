package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

public interface IRuleNameService {

    public RuleName getSpecificRuleNameById(Integer id);

    public List<RuleName> getAllRuleName();

    public void createRuleName(RuleName ruleName);

    public void updateRuleName(Integer id, RuleName ruleName);

    public void deleteRuleName(Integer id);
}
