package com.site.site.repository;

import com.site.site.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findByTitle(String title);
    Question findByTitleAndContent(String title, String content);
}
