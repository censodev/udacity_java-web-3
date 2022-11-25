package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.StreamUtils;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.User;

import java.util.stream.Collectors;

public class ScheduleMapper {
    public static ScheduleDTO map(Schedule model) {
        return ScheduleDTO.builder()
                .id(model.getId())
                .activities(model.getActivities())
                .date(model.getDate())
                .employeeIds(StreamUtils.safeMap(model.getEmployees(), User::getId).collect(Collectors.toList()))
                .petIds(StreamUtils.safeMap(model.getPets(), Pet::getId).collect(Collectors.toList()))
                .build();
    }

    public static Schedule map(ScheduleDTO dto) {
        return Schedule.builder()
                .id(dto.getId())
                .activities(dto.getActivities())
                .date(dto.getDate())
                .employees(StreamUtils.safeMap(dto.getEmployeeIds(), id -> User.builder().id(id).build()).collect(Collectors.toList()))
                .pets(StreamUtils.safeMap(dto.getPetIds(), id -> Pet.builder().id(id).build()).collect(Collectors.toList()))
                .build();
    }
}
