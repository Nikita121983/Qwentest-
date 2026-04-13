package org.apache.poi.common.usermodel;

import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public enum HyperlinkType {
    NONE(-1),
    URL(1),
    DOCUMENT(2),
    EMAIL(3),
    FILE(4);


    @Internal(since = "3.15 beta 3")
    @Deprecated
    private final int code;

    @Internal(since = "3.15 beta 3")
    @Deprecated
    HyperlinkType(int code) {
        this.code = code;
    }

    @Internal(since = "3.15 beta 3")
    @Deprecated
    int getCode() {
        return this.code;
    }
}
