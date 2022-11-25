package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.User;
import com.udacity.jdnd.course3.critter.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetDTO savePet(PetDTO petDTO) {
        return Optional.ofNullable(petDTO)
                .map(PetMapper::map)
                // workaround for UT
                .map(pet -> {
                    User u = userRepository.findById(petDTO.getOwnerId()).orElse(new User());
                    u.getPets().add(pet);
                    pet.setOwner(u);
                    return pet;
                })
                .map(petRepository::save)
                .map(PetMapper::map)
                .orElseThrow(RuntimeException::new);
    }

    public PetDTO getPet(long petId) {
        return petRepository.findById(petId).map(PetMapper::map).orElse(null);
    }

    public List<PetDTO> getPets() {
        return petRepository.findAll().stream().map(PetMapper::map).collect(Collectors.toList());
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        return petRepository.findByOwnerId(ownerId).stream().map(PetMapper::map).collect(Collectors.toList());
    }
}
