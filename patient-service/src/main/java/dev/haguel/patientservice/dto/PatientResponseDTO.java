package dev.haguel.patientservice.dto;

import lombok.Builder;

@Builder
public class PatientResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;
    private String registeredDate;
}
