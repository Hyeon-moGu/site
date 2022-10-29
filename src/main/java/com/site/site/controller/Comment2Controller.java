package com.site.site.controller;

import com.site.site.form.Comment2Form;
import com.site.site.model.entity.Comment2;
import com.site.site.model.entity.Files;
import com.site.site.model.entity.SiteUser;
import com.site.site.service.Comment2Service;
import com.site.site.service.FilesService;
import com.site.site.service.SiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/comment2")
@RequiredArgsConstructor
@Controller
public class Comment2Controller {

    @Autowired
    FilesService filesService;
    @Autowired
    Comment2Service comment2Service;

    private final SiteUserService siteUserService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id,
                                @Valid Comment2Form comment2Form, BindingResult bindingResult, Principal principal) {
        Files files = this.filesService.getFiles(id);
        SiteUser siteUser = this.siteUserService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("files", files);
            return "picture_detail";
        }
        Comment2 comment2 = this.comment2Service.create(files, comment2Form.getContent(), siteUser);
        return String.format("redirect:/picture/detail/%s#comment2_%s", comment2.getFiles().getFno(), comment2.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String commentModify(Comment2Form comment2Form, @PathVariable("id") Integer id, Principal principal) {
        Comment2 comment2 = this.comment2Service.getComment(id);
        if (!comment2.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        comment2Form.setContent(comment2.getContent());
        return "comment2_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String commentModify(@Valid Comment2Form comment2Form, BindingResult bindingResult,
                               @PathVariable("id") Integer id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "comment2_form";
        }
        Comment2 comment2 = this.comment2Service.getComment(id);
        if (!comment2.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.comment2Service.modify(comment2, comment2Form.getContent());
        return String.format("redirect:/picture/detail/%s#comment_%s", comment2.getFiles().getFno(), comment2.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String commentDelete(Principal principal, @PathVariable("id") Integer id) {
        Comment2 comment2 = this.comment2Service.getComment(id);
        if (!comment2.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.comment2Service.delete(comment2);
        return String.format("redirect:/picture/detail/%s", comment2.getFiles().getFno());
    }

}
