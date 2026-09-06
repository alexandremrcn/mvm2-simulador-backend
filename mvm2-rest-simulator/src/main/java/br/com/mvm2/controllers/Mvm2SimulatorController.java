package br.com.mvm2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mvm2.dto.Mvm2SimulatorRequestDTO;
import br.com.mvm2.dto.Mvm2SimulatorResponseDTO;
import br.com.mvm2.mapper.Mvm2SimulatorMapper;
import mvm2services.models.SimulatorInput;
import mvm2services.models.SimulatorResult;
import mvm2services.services.SimulatorService;

@RestController
@RequestMapping("/simulator")
public class Mvm2SimulatorController {

	@Autowired
	private SimulatorService simulatorService;

	@PostMapping
	public Mvm2SimulatorResponseDTO simulate(@RequestBody Mvm2SimulatorRequestDTO request) throws Exception {
		SimulatorInput input = Mvm2SimulatorMapper.toInput(request);
		SimulatorResult result = simulatorService.simulate(input);
		return Mvm2SimulatorMapper.toDTO(result);
	}
}
