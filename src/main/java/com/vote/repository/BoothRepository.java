package com.vote.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vote.model.Booth;

@Repository
public interface BoothRepository extends CrudRepository<Booth, Long>{

	public List<Booth> findByBoothIdIn(List<Long> boothIds);
}
