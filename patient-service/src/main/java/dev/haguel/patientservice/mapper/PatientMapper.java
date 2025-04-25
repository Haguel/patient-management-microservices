package dev.haguel.patientservice.mapper;

import dev.haguel.patientservice.dto.PatientRequestDTO;
import dev.haguel.patientservice.dto.PatientResponseDTO;
import dev.haguel.patientservice.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PatientMapper {
    public abstract PatientResponseDTO patientToPatientResponseDTO(Patient patient);

    @Mapping(target = "id", ignore = true)
    public abstract Patient patientRequestDTOToPatient(PatientRequestDTO patientRequestDTO);
}
