package dev.haguel.patientservice.mapper;

import dev.haguel.patientservice.dto.PatientResponseDTO;
import dev.haguel.patientservice.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PatientMapper {
    public abstract PatientResponseDTO patientToPatientResponseDTO(Patient patient);
}
