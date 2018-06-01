package com.zjw.light.helper.scanner;

import java.lang.annotation.Annotation;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/31
 */
public abstract class AnnotationClassTemplate extends ClassTemplate{

    protected final Class<? extends Annotation> annotationClass;

    public AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }
}
