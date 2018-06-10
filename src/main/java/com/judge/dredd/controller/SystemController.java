package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.dto.SytemInfoDTO;

@RestController
public class SystemController {

	@Value(value = "${application.name}")
	private String applicationName;

	@Value(value = "${build.version}")
	private String applicationVersion;

	@Value(value = "${build.timestamp}")
	private String timestamp;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/dredd/api/version")
	public ResponseEntity<SytemInfoDTO> getVersion( Authentication auth) {

		SytemInfoDTO system = new SytemInfoDTO(applicationName, applicationVersion, timestamp);
		System.out.println(auth.getPrincipal());

		System.out.println(system.getName());
		return ResponseEntity.ok().body(system);
	}

	@PostMapping("/dredd/api/version")
	public ResponseEntity<SytemInfoDTO> save(@RequestBody SytemInfoDTO system, Authentication auth) {
		
		System.out.println(auth.getPrincipal());

		System.out.println(system.getName());
		
		return ResponseEntity.ok().body(system);
	}
}
