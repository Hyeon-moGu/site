package com.site.site.service;

import com.site.site.applicationException;
import com.site.site.model.entity.Files;
import com.site.site.model.entity.SiteUser;
import com.site.site.repository.FilesRepository;
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

@Service
@RequiredArgsConstructor
public class FilesService {

    private final FilesRepository filesRepository;

    public void save(Files files, SiteUser siteUser) {
        Files f = new Files();
        f.setFilename(files.getFilename());
        f.setFileOriName(files.getFileOriName());
        f.setFileurl(files.getFileurl());
        f.setTitle(files.getTitle());
        f.setContent(files.getContent());
        f.setExtension(files.getExtension());
        f.setCreateDate(LocalDateTime.now());
        f.setAuthor(siteUser);

        filesRepository.save(f);
    }

    public Page<Files> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 7, Sort.by(sorts));
        return this.filesRepository.findAll(pageable);
    }

    public Files getFiles(Integer fno){
        Optional<Files> files = this.filesRepository.findById(fno);
        if(files.isPresent()) {
            return files.get();
        } else {
            throw new applicationException("Files not found");
        }
    }

    public String getPath(Integer id){
        Files files = this.filesRepository.findByFno(id);
        return files.getFileurl() + files.getFilename();
    }

    public String getOriName(Integer id){
        Files files = this.filesRepository.findByFno(id);
        return files.getFileOriName().replaceAll("\\s", "");
    }

}
