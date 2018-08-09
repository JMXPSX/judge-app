package com.judge.dredd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.judge.dredd.model.Settings;

public interface SettingsRepository extends CrudRepository<Settings, Long>{

	public List<Settings> findByEvent_id(long eventId);
}
