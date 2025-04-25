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
    @Mapping(target = "registeredDate", expression = "java(java.time.LocalDate.parse(patientRequestDTO.getRegisteredDate()))")
    @Mapping(target = "dateOfBirth", expression = "java(java.time.LocalDate.parse(patientRequestDTO.getDateOfBirth()))")
    public abstract Patient patientRequestDTOToPatient(PatientRequestDTO patientRequestDTO);
}
