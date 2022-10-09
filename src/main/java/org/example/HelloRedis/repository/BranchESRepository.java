package org.example.HelloRedis.repository;

import org.example.HelloRedis.entity.BranchES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchESRepository extends ElasticsearchRepository<BranchES, String> {

    List<BranchES> findAllByActivityStatusOrderByBranchCodeAsc(String activityStatus);

}
