package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.User;

public class PetMapper {
    public static Pet map(PetDTO dto) {
        return Pet.builder()
                .id(dto.getId())
                .type(dto.getType())
                .name(dto.getName())
                .owner(User.builder().id(dto.getOwnerId()).build())
                .birthDate(dto.getBirthDate())
                .notes(dto.getNotes())
                .build();
    }

    public static PetDTO map(Pet model) {
        return PetDTO.builder()
                .id(model.getId())
                .type(model.getType())
                .name(model.getName())
                .ownerId(model.getOwner() == null ? 0 : model.getOwner().getId())
                .birthDate(model.getBirthDate())
                .notes(model.getNotes())
                .build();
    }
}
