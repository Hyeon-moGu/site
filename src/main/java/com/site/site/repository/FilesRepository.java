package com.site.site.repository;

import com.site.site.model.entity.Files;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  FilesRepository extends JpaRepository<Files, Integer> {

    Files findByFno(int fno);
    Page<Files> findAll(Pageable pageable);
}
