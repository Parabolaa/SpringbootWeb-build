package org.example.SpringbootWeb.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.SpringbootWeb.pojo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    public Result uploadImage(String username, Integer age, MultipartFile image) {
        log.info("文件上传: {},{},{}", username, age, image);
        // 原始文件名
        String originalFilename = image.getOriginalFilename();
        //构造唯一文件名-uuid
        String uuid = UUID.randomUUID().toString(); //.replace("-", "");
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String newFileName = uuid + suffix;
        log.info("新的文件名: {}", newFileName);
        try {
            image.transferTo(new File(System.getProperty("user.home")
                    +"/Desktop/stu/beautycollect/javaweb-back"
                    +newFileName));
            return Result.success();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
