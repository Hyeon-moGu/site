package com.site.site.service;

import com.site.site.applicationException;
import com.site.site.model.entity.Question;
import com.site.site.model.entity.SiteUser;
import com.site.site.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new applicationException("Question not found");
        }
    }

    public void create(String title, String content, SiteUser siteUser) {
        Question q = new Question();
        q.setTitle(title);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(siteUser);
        this.questionRepository.save(q);
    }

    public void modify(Question question, String title, String content) {
        question.setTitle(title);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    public void rec(Question question, SiteUser siteUser) {
        question.getRec().add(siteUser);
        this.questionRepository.save(question);
    }

    public void nrec(Question question, SiteUser siteUser) {
        question.getNrec().add(siteUser);
        this.questionRepository.save(question);
    }

}
