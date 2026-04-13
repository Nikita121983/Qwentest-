package org.apache.commons.codec.language.bm;

/* loaded from: classes9.dex */
public enum NameType {
    ASHKENAZI("ash"),
    GENERIC("gen"),
    SEPHARDIC("sep");

    private final String name;

    NameType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
