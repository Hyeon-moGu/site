package com.site.site.controller;

import com.site.site.form.Comment2Form;
import com.site.site.model.entity.Files;
import com.site.site.model.entity.SiteUser;
import com.site.site.repository.FilesRepository;
import com.site.site.service.FilesService;
import com.site.site.service.SiteUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.util.List;

@RequestMapping("/picture")
@Controller
@RequiredArgsConstructor
public class PictureController {

    @Autowired
    FilesService filesService;

    @Autowired
    SiteUserService siteUserService;

    @Autowired
    FilesRepository filesRepository;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Files> paging = this.filesService.getList(page);
        model.addAttribute("paging", paging);
        return "picture_list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, Comment2Form comment2Form) {
        Files files = this.filesService.getFiles(id);
        model.addAttribute("files", files);
        return "picture_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/insert")
    public String Insert() {
        return "picture_insert";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/fileinsert")
    public String fileinsert(HttpServletRequest request, Principal principal, @RequestPart MultipartFile files, @RequestParam String title,
                             @RequestParam String content) throws Exception{
        Files file = new Files();

        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:\\Users\\rnlck\\Desktop\\site\\src\\main\\resources\\static\\upload\\";
        SiteUser siteUser = this.siteUserService.getUser(principal.getName());


        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileurl(fileUrl);
        file.setTitle(title);
        file.setContent(content);
        file.setExtension(sourceFileNameExtension);
        file.setAuthor(siteUser);
        filesService.save(file, siteUser);
        return "redirect:/picture/list";
    }

}
