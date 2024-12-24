package com.kody.dawa.domain.rental.repository;

import com.kody.dawa.domain.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}
