package com.example.dividend.persist;

import com.example.dividend.persist.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    boolean existsByTicker(String ticker);

    // Optional로 값이 없는 경우 처리
    Optional<CompanyEntity> findByName(String name);

}
