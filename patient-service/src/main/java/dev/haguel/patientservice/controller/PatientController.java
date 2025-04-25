package dev.haguel.patientservice.controller;

import dev.haguel.patientservice.dto.PatientRequestDTO;
import dev.haguel.patientservice.dto.PatientResponseDTO;
import dev.haguel.patientservice.service.PatientService;
import dev.haguel.patientservice.util.EndPoints;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    private final PatientService patientService;

    @GetMapping(EndPoints.Patient.GET_PATIENTS)
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        log.info("Get patients request received");
        List<PatientResponseDTO> patients = patientService.getPatients();

        log.info("Get patients request completed");
        return ResponseEntity.ok(patients);
    }

    @PostMapping(EndPoints.Patient.CREATE_PATIENT)
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientResponseDTO) {
        log.info("Create patient request received");
        PatientResponseDTO createdPatient = patientService.createPatient(patientResponseDTO);

        log.info("Create patient request completed");
        return ResponseEntity.ok().body(createdPatient);
    }
}
