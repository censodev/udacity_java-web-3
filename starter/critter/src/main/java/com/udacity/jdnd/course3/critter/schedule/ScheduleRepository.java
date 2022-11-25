package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("select s from Schedule s where :petId in (select s_p.id from s.pets s_p)")
    List<Schedule> findByPetsIdContains(long petId);

    @Query("select s from Schedule s where :employeesId in (select s_e.id from s.employees s_e)")
    List<Schedule> findByEmployeesIdContains(long employeesId);

    @Query(
            nativeQuery = true,
            value = "select s.* from schedules s where s.id in (select sp.schedule_id from schedule_pets sp where sp.pet_id in (select p.id from pets p where p.owner_id = :customerId))")
    List<Schedule> getForCustomer(long customerId);
}