package com.casablanca.SpringConnect.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casablanca.SpringConnect.Entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer>{
}

