package com.site.site.service;

import com.site.site.applicationException;
import com.site.site.model.entity.Comment;
import com.site.site.model.entity.Question;
import com.site.site.model.entity.SiteUser;
import com.site.site.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private Specification<Question> search(String searchWord) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복 제거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Comment> a = q.join("commentList", JoinType.LEFT);
                Join<Comment, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + searchWord + "%"), // 제목
                        cb.like(q.get("content"), "%" + searchWord + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + searchWord + "%"),    // 글 작성자
                        cb.like(a.get("content"), "%" + searchWord + "%"),      // 댓글 내용
                        cb.like(u2.get("username"), "%" + searchWord + "%"));   // 댓글 작성자
            }
        };
    }

    public Page<Question> getList(int page, String searchWord) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(searchWord);
        return this.questionRepository.findAll(spec, pageable);
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
