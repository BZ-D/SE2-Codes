package cn.seecoder.courselearning.serviceimpl.file;

import cn.seecoder.courselearning.exception.MyException;
import cn.seecoder.courselearning.service.file.FileService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.util.FileHelper;
import cn.seecoder.courselearning.vo.file.FileInfoVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${web.file-upload-path}")
    private String path;

    @Override
    public ResultVO<FileInfoVO> uploadFile(MultipartFile file) {
        try {
            FileInfoVO fileInfoVO = FileHelper.save(path, file);
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "文件上传成功", fileInfoVO);
        } catch (MyException myException){
            logger.error("用于存放上传文件的文件夹不存在或创建失败，请检查路径是否有效", myException);
        } catch (IOException ioException){
            logger.error("文件复制时出错", ioException);
        }
        return new ResultVO<>(-1, "服务器错误，请联系网站管理员。");
    }

    @Override
    public void downloadFile(String originName, String newName, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        response.setContentType("application/x-msdownload");
        try {
            Resource resource = FileHelper.loadFileAsResource(path, originName);
            if(resource == null)
                return;
            inputStream = resource.getInputStream();
            //1.设置文件ContentType类型
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream = response.getOutputStream();
            //2.转码  UTF_8为传入的newName编码的格式 ISO_8859_1为浏览器默认编码
            String convertName = new String(newName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            //3.设置 header  Content-Disposition
            response.setHeader("Content-Disposition", "attachment; filename=" + convertName);
            int b = 0;
            byte[] buffer = new byte[2048];
            while (b != -1) {
                b = inputStream.read(buffer);
                if (b != -1) {
                    outputStream.write(buffer, 0, b);
                }
            }
        } catch (IOException | MyException e) {
            logger.error("文件下载时出错", e);
        } finally {
            try {
                if(inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                logger.error("输入流或输出流关闭时出错！", e);
            }
        }
    }
}
