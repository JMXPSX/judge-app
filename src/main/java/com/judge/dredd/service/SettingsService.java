package com.judge.dredd.service;

import java.util.List;

import com.judge.dredd.dto.SettingsDTO;

public interface SettingsService {

	public List<SettingsDTO> getSettingsByEvent(long eventId);
	
	public List<SettingsDTO> getAllSettings();
	
	public SettingsDTO addSetting(SettingsDTO settingsDTO);
}
