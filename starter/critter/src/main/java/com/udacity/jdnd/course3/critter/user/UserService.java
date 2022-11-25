package com.udacity.jdnd.course3.critter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return Optional.of(userRepository.save(UserMapper.map(customerDTO)))
                .map(UserMapper::map2Customer)
                .orElseThrow(RuntimeException::new);
    }

    public List<CustomerDTO> getAllCustomers() {
        return userRepository.findAll().stream()
                .map(UserMapper::map2Customer)
                .collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(long petId) {
        return userRepository.findByPetsIdContains(petId)
                .map(UserMapper::map2Customer)
                .orElseThrow(RuntimeException::new);
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return Optional.of(userRepository.save(UserMapper.map(employeeDTO)))
                .map(UserMapper::map2Employee)
                .orElseThrow(RuntimeException::new);
    }

    public EmployeeDTO getEmployee(long employeeId) {
        return userRepository.findById(employeeId)
                .map(UserMapper::map2Employee)
                .orElseThrow(() -> new RuntimeException("user is not found"));
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        userRepository.findById(employeeId)
                .ifPresentOrElse(u -> u.setDaysAvailable(daysAvailable), () -> {
                    throw new RuntimeException("user is not found");
                });
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        return userRepository.findByDaysAvailableContains(employeeDTO.getDate().getDayOfWeek())
                .stream()
                .filter(u -> matchSkills(employeeDTO.getSkills(), u.getSkills()))
                .map(UserMapper::map2Employee)
                .collect(Collectors.toList());
    }

    private boolean matchSkills(Set<EmployeeSkill> expectedSkills, Set<EmployeeSkill> skills) {
        var matchedMap = expectedSkills.stream()
                .collect(Collectors.toMap(Enum::name, e -> 0));
        skills.forEach(sk -> {
            if (matchedMap.containsKey(sk.name())) {
                matchedMap.put(sk.name(), 1);
            }
        });
        return matchedMap.values().stream().mapToInt(i -> i).sum() == matchedMap.size();
    }
}
