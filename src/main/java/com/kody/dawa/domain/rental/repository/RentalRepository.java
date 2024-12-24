package com.kody.dawa.domain.rental.repository;

import com.kody.dawa.domain.rental.entity.Rental;
import com.kody.dawa.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query("SELECT r FROM Rental r WHERE r.user = ?1 ORDER BY r.createAt DESC")
    List<Rental> findRentalsByUserOrderByCreateAtDesc(User user);
    @Query("SELECT r FROM Rental r ORDER BY r.createAt DESC")
    List<Rental> findAllRentalsOrderByCreateAtDesc();
}
