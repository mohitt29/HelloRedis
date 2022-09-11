package org.example.HelloRedis.controller;

import lombok.RequiredArgsConstructor;
import org.example.HelloRedis.entity.Address;
import org.example.HelloRedis.entity.Branch;
import org.example.HelloRedis.entity.BusDetails;
import org.example.HelloRedis.entity.Place;
import org.example.HelloRedis.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class HelloRedisController {
    private final BranchRepository branchRepository;

    @Autowired
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test")
    public String testMapping() {
        return "Hello from the other side!";
    }

    @GetMapping("/addData")
    public Iterable<Branch> addDataToRedisCache() {
        List<String> scanLocations = new ArrayList<>();
        scanLocations.add("MU");
        scanLocations.add("CG");
        scanLocations.add("LI");

        List<Place> places = new ArrayList<>();
        places.add(Place.builder().name("Palace").nickName("Palace").build());
        places.add(Place.builder().name("Beach").nickName("WaterWater").build());

        Map<String, Double> ccyRates = new HashMap<>();
        ccyRates.put("INR", 1.0);
        ccyRates.put("GBP", 100.0);

        Map<Long, BusDetails> busDetailsMap = new HashMap<>();
        busDetailsMap.put(1L, BusDetails.builder().busNo(123L).source("221 Baker Street").destination("Vienna").build());


        List<Branch> branchList = new ArrayList<>();
        Branch branch = Branch.builder()
                .id(1L)
                .branchCode("CG")
                .branchDesc("Chicago")
                .countryId("1")
                .toUsdRate(new BigDecimal("10.34"))
                .populationDensity(1.234)
                .scanLocations(scanLocations)
                .address(Address.builder()
                        .city("Chicago U.S.")
                        .pinCode(123456L)
                        .build())
                .places(places)
                .ccyRates(ccyRates)
                .pinCodeToBusMapping(busDetailsMap)
                .build();

        Branch branchTwo = Branch.builder()
                .id(2L)
                .branchCode("NY")
                .branchDesc("New York")
                .countryId("1")
                .toUsdRate(new BigDecimal("10.34"))
                .populationDensity(1.234)
                .scanLocations(scanLocations)
                .address(Address.builder()
                        .city("Chicago U.S.")
                        .pinCode(123456L)
                        .build())
                .places(places)
                .ccyRates(ccyRates)
                .pinCodeToBusMapping(busDetailsMap)
                .build();


        Branch branchThree = Branch.builder()
                .id(3L)
                .branchCode("AH")
                .branchDesc("Ahmedabad")
                .countryId("1")
                .toUsdRate(new BigDecimal("10.34"))
                .populationDensity(1.234)
                .scanLocations(scanLocations)
                .address(Address.builder()
                        .city("Chicago U.S.")
                        .pinCode(123456L)
                        .build())
                .places(places)
                .ccyRates(ccyRates)
                .pinCodeToBusMapping(busDetailsMap)
                .build();

        branchList.add(branch);
        branchList.add(branchTwo);
        branchList.add(branchThree);


        branchRepository.saveAll(branchList);

        return branchRepository.findAll();

    }

    @GetMapping("/testRepository")
    public List<Branch> findBranches() {
        return branchRepository.findByBranchCode("CG");
    }

    @GetMapping("/templateTest")
    public void templateTest() {
        redisTemplate.boundSetOps("name");
    }
}
