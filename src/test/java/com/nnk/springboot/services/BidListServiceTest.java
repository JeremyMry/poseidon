package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.BidListServiceImpl;
import com.nnk.springboot.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class BidListServiceTest {

    @Autowired
    BidListServiceImpl bidListService;

    @Autowired
    BidListRepository bidListRepository;

    @Test
    public void getSpecificBidListTest() {
        BidList bidList = new BidList();
        bidList.setAccount("paul");
        bidList.setType("transfer");
        bidList.setBidQuantity(14.0);

        bidListRepository.save(bidList);

        Assertions.assertNotNull(bidListService.getSpecificBidListById(1));
        Assertions.assertEquals(bidListService.getSpecificBidListById(1).getBidQuantity(), bidList.getBidQuantity());
        Assertions.assertEquals(bidListService.getSpecificBidListById(1).getAccount(), bidList.getAccount());
        Assertions.assertEquals(bidListService.getSpecificBidListById(1).getType(), bidList.getType());
    }

    @Test
    public void getSpecificBidListThatDoesntExistTest() {
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> bidListService.getSpecificBidListById(1));

        String expectedMessage = "Invalid bidList Id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getAllBidListTest() {
        BidList bidList = new BidList();
        bidList.setAccount("paul");
        bidList.setType("transfer");
        bidList.setBidQuantity(14.0);

        BidList bidList1 = new BidList();
        bidList1.setAccount("paul");
        bidList1.setType("transfer");
        bidList1.setBidQuantity(14.0);

        bidListRepository.save(bidList);
        bidListRepository.save(bidList1);

        Assertions.assertEquals(bidListService.getAllBidList().size(), 2);
    }

    @Test
    public void getAllBidListWhenThereIsNone() {
        Assertions.assertEquals(bidListService.getAllBidList().size(), 0);
        Assertions.assertNotNull(bidListService.getAllBidList());
    }

    @Test
    public void createBidListTest() {
        BidListDto bidListDto = new BidListDto("john", "transaction", 15.0);

        bidListService.createBidList(bidListDto);

        Assertions.assertEquals(bidListRepository.findById(1).get().getBidQuantity(), bidListDto.getBidQuantity());
        Assertions.assertEquals(bidListRepository.findById(1).get().getAccount(), bidListDto.getAccount());
        Assertions.assertEquals(bidListRepository.findById(1).get().getType(), bidListDto.getType());
    }

    @Test
    public void updateBidListTest() {
        BidList bidList = new BidList();
        bidList.setAccount("paul");
        bidList.setType("transfer");
        bidList.setBidQuantity(14.0);
        bidListRepository.save(bidList);
        BidListDto bidListDto = new BidListDto("paulo", "money transfer", 155.25);

        bidListService.updateBidList(1, bidListDto);

        Assertions.assertEquals(java.util.Optional.of(bidListRepository.findById(1).get().getBidQuantity()), java.util.Optional.of(155.25));
        Assertions.assertEquals(bidListRepository.findById(1).get().getAccount(), "paulo");
        Assertions.assertEquals(bidListRepository.findById(1).get().getType(), "money transfer");
        Assertions.assertEquals(bidListRepository.findAll().size(), 1);
    }

    @Test
    public void deleteBidListTest() {
        BidList bidList = new BidList();
        bidList.setAccount("paul");
        bidList.setType("transfer");
        bidList.setBidQuantity(14.0);
        bidListRepository.save(bidList);

        bidListService.deleteBidList(1);

        Assertions.assertFalse(bidListRepository.findById(1).isPresent());
    }
}