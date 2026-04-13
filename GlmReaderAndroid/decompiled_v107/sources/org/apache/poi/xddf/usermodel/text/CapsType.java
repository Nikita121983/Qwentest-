package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;

/* loaded from: classes10.dex */
public enum CapsType {
    ALL(STTextCapsType.ALL),
    NONE(STTextCapsType.NONE),
    SMALL(STTextCapsType.SMALL);

    private static final HashMap<STTextCapsType.Enum, CapsType> reverse = new HashMap<>();
    final STTextCapsType.Enum underlying;

    static {
        for (CapsType value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    CapsType(STTextCapsType.Enum caps) {
        this.underlying = caps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CapsType valueOf(STTextCapsType.Enum caps) {
        return reverse.get(caps);
    }
}
