package dev.haguel.patientservice.service;

import dev.haguel.patientservice.dto.PatientRequestDTO;
import dev.haguel.patientservice.dto.PatientResponseDTO;
import dev.haguel.patientservice.entity.Patient;
import dev.haguel.patientservice.exception.EmailAlreadyExistsException;
import dev.haguel.patientservice.exception.PatientNotFoundException;
import dev.haguel.patientservice.mapper.PatientMapper;
import dev.haguel.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOs = patients.stream()
                .map(patientMapper::patientToPatientResponseDTO)
                .toList();

        return patientResponseDTOs;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email " + patientRequestDTO.getEmail() + " already exists");
        }

        Patient mappedPatient = patientMapper.patientRequestDTOToPatient(patientRequestDTO);
        Patient patient = patientRepository.save(mappedPatient);

        return patientMapper.patientToPatientResponseDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient with id " + id + " not found"));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email " + patientRequestDTO.getEmail() + " already exists");
        }

        Optional.ofNullable(patientRequestDTO.getName()).ifPresent(patient::setName);
        Optional.ofNullable(patientRequestDTO.getEmail()).ifPresent(patient::setEmail);
        Optional.ofNullable(patientRequestDTO.getAddress()).ifPresent(patient::setAddress);
        Optional.ofNullable(patientRequestDTO.getDateOfBirth()).ifPresent(patient::setDateOfBirth);
        Optional.ofNullable(patientRequestDTO.getRegisteredDate()).ifPresent(patient::setRegisteredDate);

        patient = patientRepository.save(patient);

        return patientMapper.patientToPatientResponseDTO(patient);
    }
}
