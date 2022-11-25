package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPetsIdContains(long petId);

    @Query("select u from User u where :dayOfWeek in u.daysAvailable and u.skills = :skills")
    List<User> findForServices(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);
}