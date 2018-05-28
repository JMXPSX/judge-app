package com.judge.dredd.repository;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Tabulator;

public interface TabulatorRepository extends CrudRepository<Tabulator, Long> {

	Tabulator findByEvent_IdAndEntry_entryId(long eventId, long entryId);
}
