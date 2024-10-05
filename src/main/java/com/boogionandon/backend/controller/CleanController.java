package com.boogionandon.backend.controller;

import com.boogionandon.backend.dto.CleanRequestDTO;
import com.boogionandon.backend.service.CleanService;
import com.boogionandon.backend.util.CustomFileUtil;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/clean")
public class CleanController {

  private final CustomFileUtil fileUtil;
  private final CleanService cleanService;

  @PostMapping("/")
  public Map<String, String> insertClean(CleanRequestDTO cleanDTO) {
    log.info("..........CleanController........");
    log.info("cleanDTO: " + cleanDTO);

    List<MultipartFile> beforeFiles = cleanDTO.getBeforeFiles();
    List<String> beforeUploadedFileNames = fileUtil.saveFiles(beforeFiles, "B_");
    cleanDTO.setBeforeUploadedFileNames(beforeUploadedFileNames);

    List<MultipartFile> afterFiles = cleanDTO.getAfterFiles();
    List<String> afterUploadedFileNames = fileUtil.saveFiles(afterFiles, "A_");
    cleanDTO.setAfterUploadedFileNames(afterUploadedFileNames);

    cleanService.insertClean(cleanDTO);

    return Map.of("result", "success");
  }

  @GetMapping("/view/{fileName}")
  public ResponseEntity<Resource> viewFileGET(@PathVariable("fileName") String fileName) {
    return fileUtil.getFile(fileName);
  }

}
