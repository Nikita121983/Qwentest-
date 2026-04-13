package org.apache.xmlbeans.impl.tool;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class Extension {
    private Class<?> className;
    private final List<Param> params = new ArrayList();

    public Class<?> getClassName() {
        return this.className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }

    public void setClassName(String className) throws ClassNotFoundException {
        this.className = Class.forName(className);
    }

    public List<Param> getParams() {
        return this.params;
    }

    public Param createParam() {
        Param p = new Param();
        this.params.add(p);
        return p;
    }

    /* loaded from: classes11.dex */
    public static class Param {
        private String name;
        private String value;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
