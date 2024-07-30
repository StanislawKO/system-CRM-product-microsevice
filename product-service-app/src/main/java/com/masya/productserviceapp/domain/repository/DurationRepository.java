package com.masya.productserviceapp.domain.repository;

import com.masya.productserviceapp.domain.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DurationRepository extends JpaRepository<Duration, Long> {
}
