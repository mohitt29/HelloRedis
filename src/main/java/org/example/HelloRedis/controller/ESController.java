package org.example.HelloRedis.controller;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.example.HelloRedis.entity.BranchES;
import org.example.HelloRedis.repository.BranchESRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ESController {
    private final BranchESRepository branchESRepository;

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    @GetMapping("saveData")
    public Iterable<BranchES> saveData() {
        List<BranchES> branchESList = new ArrayList<>();
        BranchES branchES = BranchES.builder()
                .id(1L)
                .branchCode("CG")
                .countryCode("US")
                .branchDesc("Chicago")
                .countryId("1")
                .populationDensity(12.23)
                .toUsdRate(BigDecimal.TEN)
                .activityStatus("A")
                .build();

        BranchES branchES2 = BranchES.builder()
                .id(2L)
                .branchCode("MU")
                .countryCode("IN")
                .branchDesc("Mumbai")
                .countryId("1")
                .populationDensity(12112.2)
                .toUsdRate(BigDecimal.TEN)
                .activityStatus("A")
                .build();

        BranchES branchES3 = BranchES.builder()
                .id(3L)
                .branchCode("TK")
                .countryCode("JP")
                .branchDesc("Tokyo")
                .countryId("1")
                .populationDensity(975983745.340975)
                .toUsdRate(BigDecimal.TEN)
                .activityStatus("I")
                .build();

        BranchES branchES4 = BranchES.builder()
                .id(4L)
                .branchCode("BJ")
                .countryCode("CH")
                .branchDesc("Beijing")
                .countryId("1")
                .populationDensity(12112.2)
                .toUsdRate(BigDecimal.TEN)
                .activityStatus("A")
                .build();
        branchESList.add(branchES);
        branchESList.add(branchES2);
        branchESList.add(branchES3);
        branchESList.add(branchES4);

        return branchESRepository.saveAll(branchESList);
    }

    @GetMapping("/getAll")
    public Iterable<BranchES> getAll() {
        return branchESRepository.findAll();
    }

    @GetMapping("/all")
    public List<BranchES> findAllOrderBy() {
        return branchESRepository.findAllByActivityStatusOrderByBranchCodeAsc("A");
    }

    @GetMapping("/{id}")
    public BranchES findById(@PathVariable("id") String id) {
        return branchESRepository.findById(id).get();
    }

    @GetMapping("/type")
    public List<BranchES> findByType(@RequestParam Map<String, Object> input) {
        Criteria criteria = new Criteria();
        for (Map.Entry<String, Object> entry : input.entrySet())
            criteria = criteria.and(entry.getKey()).is(entry.getValue());
//        Criteria criteria = new Criteria("branchCode").is("TK");
//        criteria = criteria.and("activityStatus").is("A");
        Query query = new CriteriaQuery(criteria);
        query.addSort(Sort.by("branchCode").ascending());
//        query.withSort(SortBuilders.fieldSort("fieldName").order(SortOrder.DESC))

        return elasticsearchTemplate.search(query, BranchES.class)
                .getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        branchESRepository.deleteAll();
    }
}
