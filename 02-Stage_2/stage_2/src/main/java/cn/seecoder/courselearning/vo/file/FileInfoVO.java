package cn.seecoder.courselearning.vo.file;

import lombok.Data;

@Data
public class FileInfoVO {
    private String name;
    private String type;
    private String size;

    public FileInfoVO(String name, String type, String size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }
}
