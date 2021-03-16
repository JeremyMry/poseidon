package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.IRuleNameService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RuleNameServiceImpl implements IRuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private Logger logger;

    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository, Logger logger) {
        this.ruleNameRepository = ruleNameRepository;
        this.logger = logger;
    }

    @Override
    public RuleName getSpecificRuleNameById(Integer id) {
        logger.info("RuleName " + id + " find");
        return ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Rule Name Id: " + id));
    }

    @Override
    public List<RuleName> getAllRuleName() {
        logger.info("RuleName List find");
        return ruleNameRepository.findAll();
    }

    @Override
    @Transactional
    public void createRuleName(RuleName ruleName) {
        logger.info("RuleName created");
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
        logger.info("RuleName " + id + " updated");
        ruleNameRepository.save(ruleNameToUpdate);
    }

    @Override
    @Transactional
    public void deleteRuleName(Integer id) {
        logger.info("RuleName " + id + " deleted");
        ruleNameRepository.deleteById(id);
    }
}
