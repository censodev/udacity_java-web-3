package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper {
    public static User map(CustomerDTO dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .notes(dto.getNotes())
                .pets(Optional.ofNullable(dto.getPetIds())
                        .map(petIds -> petIds.stream()
                                .map(id -> Pet.builder().id(id).build())
                                .collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .build();
    }

    public static User map(EmployeeDTO dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .daysAvailable(dto.getDaysAvailable())
                .skills(dto.getSkills())
                .build();
    }

    public static CustomerDTO map2Customer(User model) {
        return CustomerDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .notes(model.getNotes())
                .phoneNumber(model.getPhoneNumber())
                .petIds(Optional.ofNullable(model.getPets())
                        .map(pets -> pets.stream()
                                .map(Pet::getId)
                                .collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .build();
    }

    public static EmployeeDTO map2Employee(User model) {
        return EmployeeDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .skills(model.getSkills())
                .daysAvailable(model.getDaysAvailable())
                .build();
    }
}
