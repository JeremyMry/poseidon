package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.impl.RuleNameServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class RuleNameServiceTest {

    @Autowired
    RuleNameServiceImpl ruleNameService;

    @Autowired
    RuleNameRepository ruleNameRepository;

    @Test
    public void getSpecificBidListTest() {
        RuleName ruleName = new RuleName("a", "ab", "abc", "abcd", "abcde", "abcdef");

        ruleNameRepository.save(ruleName);

        Assertions.assertNotNull(ruleNameService.getSpecificRuleNameById(1));
        Assertions.assertEquals(ruleNameService.getSpecificRuleNameById(1).getName(), ruleName.getName());
        Assertions.assertEquals(ruleNameService.getSpecificRuleNameById(1).getDescription(), ruleName.getDescription());
        Assertions.assertEquals(ruleNameService.getSpecificRuleNameById(1).getJson(), ruleName.getJson());
        Assertions.assertEquals(ruleNameService.getSpecificRuleNameById(1).getTemplate(), ruleName.getTemplate());
        Assertions.assertEquals(ruleNameService.getSpecificRuleNameById(1).getSqlStr(), ruleName.getSqlStr());
        Assertions.assertEquals(ruleNameService.getSpecificRuleNameById(1).getSqlPart(), ruleName.getSqlPart());
    }

    @Test
    public void getSpecificBidListThatDoesntExistTest() {
        Assertions.assertNull(ruleNameService.getSpecificRuleNameById(1));
    }

    @Test
    public void getAllBidListTest() {
        RuleName ruleName = new RuleName("a", "ab", "abc", "abcd", "abcde", "abcdef");
        RuleName ruleName1 = new RuleName("ab", "abc", "abcd", "abcde", "abcdef", "abcdefg");

        ruleNameRepository.save(ruleName);
        ruleNameRepository.save(ruleName1);

        Assertions.assertEquals(ruleNameService.getAllRuleName().size(), 2);
    }

    @Test
    public void getAllBidListWhenThereIsNone() {
        Assertions.assertEquals(ruleNameService.getAllRuleName().size(), 0);
        Assertions.assertNotNull(ruleNameService.getAllRuleName());
    }

    @Test
    public void createBidListTest() {
        RuleName ruleName = new RuleName("a", "ab", "abc", "abcd", "abcde", "abcdef");

        ruleNameService.createRuleName(ruleName);

        Assertions.assertEquals(ruleNameRepository.findById(1).get().getName(), ruleName.getName());
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getDescription(), ruleName.getDescription());
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getJson(), ruleName.getJson());
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getTemplate(), ruleName.getTemplate());
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getSqlStr(), ruleName.getSqlStr());
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getSqlPart(), ruleName.getSqlPart());
    }

    @Test
    public void updateBidListTest() {
        RuleName ruleName = new RuleName("a", "ab", "abc", "abcd", "abcde", "abcdef");
        ruleNameRepository.save(ruleName);

        RuleName ruleName1 = new RuleName("ab", "abc", "abcd", "abcde", "abcdef", "abcdefg");
        ruleNameService.updateRuleName(1, ruleName1);

        Assertions.assertEquals(ruleNameRepository.findById(1).get().getName(), "ab");
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getDescription(), "abc");
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getJson(), "abcd");
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getTemplate(), "abcde");
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getSqlStr(), "abcdef");
        Assertions.assertEquals(ruleNameRepository.findById(1).get().getSqlPart(), "abcdefg");
        Assertions.assertEquals(ruleNameRepository.findAll().size(), 1);
    }

    @Test
    public void deleteBidListTest() {
        RuleName ruleName = new RuleName("a", "ab", "abc", "abcd", "abcde", "abcdef");
        ruleNameRepository.save(ruleName);

        ruleNameService.deleteRuleName(1);

        Assertions.assertFalse(ruleNameRepository.findById(1).isPresent());
    }
}
