package com.judge.dredd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.judge.dredd.dto.SettingsDTO;
import com.judge.dredd.model.Event;
import com.judge.dredd.model.Settings;
import com.judge.dredd.repository.EventRepository;
import com.judge.dredd.repository.SettingsRepository;
import com.judge.dredd.service.DtoService;
import com.judge.dredd.service.SettingsService;

@Service
public class SettingsServiceImpl implements SettingsService {
	
	@Autowired
	private DtoService dtoService;
	
	@Autowired
	private SettingsRepository settingsRepository;
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<SettingsDTO> getSettingsByEvent(long eventId) {
		List<SettingsDTO> response = new ArrayList<>();
		List<Settings> settings = settingsRepository.findByEvent_id(eventId);
		settings.forEach(s -> response.add(dtoService.convertToDTO(s)));
		return response;
	}

	@Override
	public SettingsDTO addSetting(SettingsDTO settingsDTO) {
		Event event = eventRepository.findById(settingsDTO.getEventId()).orElse(null);
		if(null != event){
			Settings settings = dtoService.convertToModel(settingsDTO);
			settings.setEvent(event);
			settings = settingsRepository.save(settings);
			settingsDTO = dtoService.convertToDTO(settings);
			settingsDTO.setStatus(HttpStatus.OK);
			return settingsDTO;
		}else{
			settingsDTO.setMessage("event id: "+settingsDTO.getEventId()+" not found");
			settingsDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return settingsDTO;
		}
				
	}

	@Override
	public List<SettingsDTO> getAllSettings() {
		List<SettingsDTO> response = new ArrayList<>();
		List<Settings> settings = Lists.newArrayList(settingsRepository.findAll());
		settings.forEach(s -> response.add(dtoService.convertToDTO(s)));
		return response;
	}

	@Override
	public SettingsDTO updateSetting(long settingsId, SettingsDTO settingsDTO) {

		Settings setting = settingsRepository.findById(settingsId).orElse(null);
		if(null != setting){
			if(null != settingsDTO.getKey()){
				setting.setKey(settingsDTO.getKey());
			}
			
			if(null != settingsDTO.getValue()){
				setting.setValue(settingsDTO.getValue());
			}
			
			if(null != settingsDTO.getEventId()){
				Event e = eventRepository.findById(settingsDTO.getEventId()).orElse(null);
				if(e != null){
					setting.setEvent(e);
				}else{
					settingsDTO.setMessage("event id "+settingsDTO.getEventId()+ " not found");
					settingsDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
					return settingsDTO;
				}
			}
			
			setting = settingsRepository.save(setting);
			
			settingsDTO = dtoService.convertToDTO(setting);
			
			settingsDTO.setMessage("done");
			settingsDTO.setStatus(HttpStatus.OK);
			return settingsDTO;
		}else{
			settingsDTO.setMessage("setting id "+settingsId+ " not found");
			settingsDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return settingsDTO;
		}
	}

}
