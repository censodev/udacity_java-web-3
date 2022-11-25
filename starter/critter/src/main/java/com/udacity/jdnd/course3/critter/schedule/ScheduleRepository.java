package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPetsIdContains(long petId);

    List<Schedule> findByEmployeesIdContains(long employeesId);

    @Query(
            nativeQuery = true,
            value = "select s.* from schedules s where s.id in (select sp.schedule_id from schedule_pets sp where sp.pet_id in (select p.id from pets p where p.owner_id = :customerId))")
    List<Schedule> getForCustomer(long customerId);
}