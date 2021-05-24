package cn.seecoder.courselearning.util;

/**
 * 可变的整形引用类型，与Integer不同，Integer是immutable的
 */
public class IntHolder {
    public int value;

    public IntHolder(int value) {
        this.value = value;
    }
}
