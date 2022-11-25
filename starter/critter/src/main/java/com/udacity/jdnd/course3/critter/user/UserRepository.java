package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where :petId in (select u_p.id from u.pets u_p)")
    Optional<User> findByPetsIdContains(long petId);

    List<User> findByDaysAvailableContains(DayOfWeek daysAvailable);
}