package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.IRuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RuleNameServiceImpl implements IRuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    @Override
    public RuleName getSpecificRuleNameById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    @Override
    public List<RuleName> getAllRuleName() {
        return ruleNameRepository.findAll();
    }

    @Override
    @Transactional
    public void createRuleName(RuleName ruleName) {
        ruleNameRepository.save(ruleName);
    }

    @Override
    @Transactional
    public void updateRuleName(Integer id, RuleName ruleName) {
        RuleName ruleNameToUpdate = getSpecificRuleNameById(id);
        ruleNameToUpdate.setName(ruleName.getName());
        ruleNameToUpdate.setDescription(ruleName.getDescription());
        ruleNameToUpdate.setJson(ruleName.getJson());
        ruleNameToUpdate.setTemplate(ruleName.getTemplate());
        ruleNameToUpdate.setSqlPart(ruleName.getSqlPart());
        ruleNameToUpdate.setSqlStr(ruleName.getSqlStr());
        ruleNameRepository.save(ruleNameToUpdate);
    }

    @Override
    @Transactional
    public void deleteRuleName(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
