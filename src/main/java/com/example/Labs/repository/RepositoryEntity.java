package com.example.Labs.repository;

import com.example.Labs.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryEntity extends JpaRepository<DocumentEntity, Long> {

    List<DocumentEntity> findByIdGreaterThan(Long id);
    List<DocumentEntity> findById(int id);
    void deleteById(Long id);
}
