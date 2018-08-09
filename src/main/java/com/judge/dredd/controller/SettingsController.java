package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.SettingsDTO;
import com.judge.dredd.service.SettingsService;

@RestController
public class SettingsController {
	
	@Autowired
	private SettingsService settingsService;

	@GetMapping("/dredd/api/settings/{eventId}")
	public ResponseEntity<?> getSettingsByEvent (@PathVariable long eventId){
		return new ResponseEntity<>(settingsService.getSettingsByEvent(eventId), HttpStatus.OK);
	}
	
	@GetMapping("/dredd/api/settings")
	public ResponseEntity<?> getAllSettings (){
		return new ResponseEntity<>(settingsService.getAllSettings(), HttpStatus.OK);
	}
	
	@PostMapping("/dredd/api/settings/")
	public ResponseEntity<?> addSettings (@RequestBody SettingsDTO settingsDTO){
		SettingsDTO dto = settingsService.addSetting(settingsDTO);
		return new ResponseEntity<>(dto, dto.getStatus());
	}
}
