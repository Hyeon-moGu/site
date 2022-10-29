package com.site.site.service;

import com.site.site.model.entity.Comment2;
import com.site.site.model.entity.Files;
import com.site.site.model.entity.SiteUser;
import com.site.site.repository.Comment2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class Comment2Service {

    private final Comment2Repository comment2Repository;


    public Comment2 create(Files files, String content, SiteUser author) {
        Comment2 comment2 = new Comment2();
        comment2.setContent(content);
        comment2.setCreateDate(LocalDateTime.now());
        comment2.setFiles(files);
        comment2.setAuthor(author);
        this.comment2Repository.save(comment2);
        return comment2;
    }

    public Comment2 getComment(Integer id) {
        Optional<Comment2> comment2 = this.comment2Repository.findById(id);
        if (comment2.isPresent()) {
            return comment2.get();
        } else {
            throw new ApplicationContextException("comment not found");
        }
    }

    public void modify(Comment2 comment2, String content) {
        comment2.setContent(content);
        comment2.setModifyDate(LocalDateTime.now());
        this.comment2Repository.save(comment2);
    }

    public void delete(Comment2 comment2) {
        this.comment2Repository.delete(comment2);
    }
}
