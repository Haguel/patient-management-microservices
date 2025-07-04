package dev.haguel.patientservice.controller;

import dev.haguel.patientservice.dto.PatientRequestDTO;
import dev.haguel.patientservice.dto.PatientResponseDTO;
import dev.haguel.patientservice.dto.validators.CreatePatientValidationGroup;
import dev.haguel.patientservice.service.PatientService;
import dev.haguel.patientservice.util.EndPoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Patient", description = "Patient management API")
public class PatientController {
    private final PatientService patientService;

    @GetMapping(EndPoints.Patient.GET_PATIENTS)
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        log.info("Get patients request received");
        List<PatientResponseDTO> patients = patientService.getPatients();

        log.info("Get patients request completed");
        return ResponseEntity.ok(patients);
    }

    @PostMapping(EndPoints.Patient.CREATE_PATIENT)
    @Operation(summary = "Create Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientResponseDTO) {
        log.info("Create patient request received");
        PatientResponseDTO createdPatient = patientService.createPatient(patientResponseDTO);

        log.info("Create patient request completed");
        return ResponseEntity.ok().body(createdPatient);
    }

    @PutMapping(EndPoints.Patient.UPDATE_PATIENT + "/{id}")
    @Operation(summary = "Update Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        log.info("Update patient request received");
        PatientResponseDTO updatedPatient = patientService.updatePatient(id, patientRequestDTO);

        log.info("Update patient request completed");
        return ResponseEntity.ok().body(updatedPatient);
    }

    @DeleteMapping(EndPoints.Patient.DELETE_PATIENT + "/{id}")
    @Operation(summary = "Delete Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        log.info("Delete patient request received");
        patientService.deletePatient(id);

        log.info("Delete patient request completed");
        return ResponseEntity.ok().build();
    }
}
