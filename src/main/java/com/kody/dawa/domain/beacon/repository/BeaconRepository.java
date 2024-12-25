package com.kody.dawa.domain.beacon.repository;

import com.kody.dawa.domain.beacon.entity.Beacon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeaconRepository extends JpaRepository<Beacon, Long> {
    Optional<Beacon> findByUuid(String uuid);
}
