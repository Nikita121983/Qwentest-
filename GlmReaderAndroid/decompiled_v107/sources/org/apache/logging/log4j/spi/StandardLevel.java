package org.apache.logging.log4j.spi;

import com.google.android.material.card.MaterialCardViewHelper;
import java.util.EnumSet;
import java.util.Iterator;
import org.apache.poi.common.usermodel.fonts.FontHeader;

/* loaded from: classes10.dex */
public enum StandardLevel {
    OFF(0),
    FATAL(100),
    ERROR(200),
    WARN(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION),
    INFO(FontHeader.REGULAR_WEIGHT),
    DEBUG(500),
    TRACE(600),
    ALL(Integer.MAX_VALUE);

    private static final EnumSet<StandardLevel> LEVELSET = EnumSet.allOf(StandardLevel.class);
    private final int intLevel;

    StandardLevel(final int val) {
        this.intLevel = val;
    }

    public int intLevel() {
        return this.intLevel;
    }

    public static StandardLevel getStandardLevel(final int intLevel) {
        StandardLevel level = OFF;
        Iterator it = LEVELSET.iterator();
        while (it.hasNext()) {
            StandardLevel lvl = (StandardLevel) it.next();
            if (lvl.intLevel() > intLevel) {
                break;
            }
            level = lvl;
        }
        return level;
    }
}
