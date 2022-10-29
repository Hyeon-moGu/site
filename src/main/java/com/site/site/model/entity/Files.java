package com.site.site.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer fno;

    String filename;
    String fileOriName;
    String fileurl;
    String extension;

    @Column(name = "title", length = 200)
    String title;

    @Column(name = "content", columnDefinition = "TEXT")
    String content;

    @ManyToOne
    SiteUser author;

    LocalDateTime createDate;

    @OneToMany(mappedBy = "files", cascade = CascadeType.REMOVE)
    private List<Comment2> comment2List;
}
