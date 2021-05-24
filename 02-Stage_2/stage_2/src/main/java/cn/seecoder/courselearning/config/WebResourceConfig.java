package cn.seecoder.courselearning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 */
@Configuration
public class WebResourceConfig implements WebMvcConfigurer {
    @Value("${web.file-upload-path}")
    private String fileUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置资源映射路径
        // 在当前配置下，如果后端在本地运行，访问上传的文件资源，只需访问路径 http://localhost:8081/file/{file_name}
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+fileUploadPath);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 为所有的RestController暴露的服务接口url 添加前缀/api
        configurer.addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
    }
}
