package org.example.HelloRedis.repository;

import org.example.HelloRedis.entity.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends CrudRepository<Branch, String> {
    List<Branch> findByBranchCode(String branchCode);
}
