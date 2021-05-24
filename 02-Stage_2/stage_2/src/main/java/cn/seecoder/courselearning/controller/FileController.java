package cn.seecoder.courselearning.controller;

import cn.seecoder.courselearning.service.file.FileService;
import cn.seecoder.courselearning.vo.file.FileInfoVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    FileService fileService;

    @PostMapping("/upload")
    public ResultVO<FileInfoVO> upload(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping("/download/{originName:.+}")
    public void download(@PathVariable String originName, @RequestParam String newName, HttpServletResponse response) {
        fileService.downloadFile(originName, newName, response);
    }
}