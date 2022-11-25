package com.udacity.jdnd.course3.critter.pet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public PetDTO savePet(PetDTO petDTO) {
        return Optional.ofNullable(petDTO)
                .map(PetMapper::map)
                .map(petRepository::save)
                .map(PetMapper::map)
                .orElseThrow(RuntimeException::new);
    }

    public PetDTO getPet(long petId) {
        return petRepository.findById(petId).map(PetMapper::map).orElse(null);
    }

    public List<PetDTO> getPets(){
        return petRepository.findAll().stream().map(PetMapper::map).collect(Collectors.toList());
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        return petRepository.findByOwnerId(ownerId).stream().map(PetMapper::map).collect(Collectors.toList());
    }
}
