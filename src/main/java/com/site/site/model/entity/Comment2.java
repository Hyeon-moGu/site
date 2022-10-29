package com.site.site.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;

    @Column(name = "content",columnDefinition = "TEXT")
     String content;

     LocalDateTime createDate;

     LocalDateTime modifyDate;

    @ManyToOne
    Files files;

    @ManyToOne
    SiteUser author;
}
