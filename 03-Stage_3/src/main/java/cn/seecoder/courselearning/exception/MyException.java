package cn.seecoder.courselearning.exception;

import cn.seecoder.courselearning.enums.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class MyException extends RuntimeException {
    private final ExceptionType type;
    private final String msg;

    @Override
    public String getMessage() {
        return msg;
    }
}
