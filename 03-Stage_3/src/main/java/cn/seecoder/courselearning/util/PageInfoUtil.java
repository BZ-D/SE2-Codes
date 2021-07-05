package cn.seecoder.courselearning.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

public class PageInfoUtil {
    /**
     * 将PageInfo对象泛型中的PO对象转化为VO对象后返回
     * @param <P> PO类型
     * @param <V> VO类型
     */
    public static <P, V> PageInfo<V> convert(PageInfo<P> pageInfoPO, Class<V> vClass) {
        // 创建Page对象，实际上是一个ArrayList类型的集合
        Page<V> page = new Page<>(pageInfoPO.getPageNum(), pageInfoPO.getPageSize());
        page.setTotal(pageInfoPO.getTotal());
        for (P p : pageInfoPO.getList()) {
            V v = null;
            try {
                v = vClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(p, v);
            page.add(v);
        }
        return new PageInfo<>(page);
    }
}
