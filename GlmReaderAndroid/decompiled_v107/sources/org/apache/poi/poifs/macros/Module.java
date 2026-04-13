package org.apache.poi.poifs.macros;

/* loaded from: classes10.dex */
public interface Module {

    /* loaded from: classes10.dex */
    public enum ModuleType {
        Document,
        Module,
        Class
    }

    ModuleType geModuleType();

    String getContent();
}
