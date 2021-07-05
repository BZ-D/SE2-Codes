package cn.seecoder.courselearning.util;
import cn.seecoder.courselearning.enums.ExceptionType;
import cn.seecoder.courselearning.exception.MyException;
import cn.seecoder.courselearning.vo.file.FileInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
public class FileHelper {
    /**
     * 保存文件
     * @param directoryPath 目录路径（以 / 结尾）
     * @param file 文件
     * @return 保存成功后的文件名
     */
    public static FileInfoVO save(String directoryPath, MultipartFile file) throws IOException{
        if(!checkDirectoryPath(directoryPath)){
            throw MyException.of(ExceptionType.SERVER_ERROR, "服务器端错误，用于存放上传文件的文件夹不存在或创建失败！");
        }
        // 原文件名
        String originalName = file.getOriginalFilename();
        String newName;
        String type = "unknown";
        String size = String.format("%.2f", (file.getSize() * 1.0 / 1024 / 1024)) + " MB";
        if(originalName!=null && originalName.lastIndexOf(".")!=-1){
            // 取扩展名
            String ext = originalName.substring(originalName.lastIndexOf("."));
            type = originalName.substring(originalName.lastIndexOf(".")+1);
            // 构造新文件名
            newName = UUID.randomUUID() + ext;
        }else
            newName = UUID.randomUUID().toString();
        // 根据目标地址构造文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream(directoryPath + newName);
        // 将文件复制到映射的地址
        FileCopyUtils.copy(file.getInputStream(),fileOutputStream);

        return new FileInfoVO(newName, type, size);
    }

    /**
     * 加载文件为资源
     * @param directoryPath 目录路径（以 / 结尾）
     * @param fileName 文件名
     * @return 输入流资源
     */
    public static Resource loadFileAsResource(String directoryPath, String fileName) {
        try {
            Path filePath = Paths.get(directoryPath+fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
                return resource;
        } catch (MalformedURLException ex) {
            throw MyException.of(ExceptionType.SERVER_ERROR, "服务端错误，加载文件资源时出错！");
        }
        return null;
    }

    public static boolean delete(String directoryPath, String fileName){
        if(StringUtils.hasText(fileName)){
            File file = new File(directoryPath + fileName);
            if(file.exists()) {
                // 当且仅当文件被成功删除后返回true
                return file.delete();
            }
        }
        return false;
    }

    /**
     * 检查目录路径是否有效，若当前路径对应的目录不存在，则尝试创建目录
     * @param directoryPath 目录路径
     * @return 若目录不存在且创建失败则返回false，否则返回true
     */
    private static boolean checkDirectoryPath(String directoryPath) {
        File dir = new File(directoryPath);
        // 如果文件夹不存在则创建
        if(!dir.exists() && !dir.isDirectory()){
            log.debug("用于存放上传文件的文件夹不存在，正在尝试创建..");
            return dir.mkdirs();
        }
        return true;
    }
}
