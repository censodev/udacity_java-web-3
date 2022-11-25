package com.udacity.jdnd.course3.critter.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        return Optional.of(scheduleDTO)
                .map(ScheduleMapper::map)
                .map(scheduleRepository::save)
                .map(ScheduleMapper::map)
                .orElseThrow(RuntimeException::new);
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleMapper::map)
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        return scheduleRepository.findByPetsIdContains(petId).stream()
                .map(ScheduleMapper::map)
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findByEmployeesIdContains(employeeId).stream()
                .map(ScheduleMapper::map)
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        return scheduleRepository.getForCustomer(customerId).stream()
                .map(ScheduleMapper::map)
                .collect(Collectors.toList());
    }
}
