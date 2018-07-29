package com.vote.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vote.model.Booth;

@Repository
public interface BoothRepository extends CrudRepository<Booth, Long>{

}
