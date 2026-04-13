package org.apache.poi.sl.usermodel;

/* loaded from: classes10.dex */
public interface TabStop {
    double getPositionInPoints();

    TabStopType getType();

    void setPositionInPoints(double d);

    void setType(TabStopType tabStopType);

    /* loaded from: classes10.dex */
    public enum TabStopType {
        LEFT(0, 1),
        CENTER(1, 2),
        RIGHT(2, 3),
        DECIMAL(3, 4);

        public final int nativeId;
        public final int ooxmlId;

        TabStopType(int nativeId, int ooxmlId) {
            this.nativeId = nativeId;
            this.ooxmlId = ooxmlId;
        }

        public static TabStopType fromNativeId(int nativeId) {
            for (TabStopType tst : values()) {
                if (tst.nativeId == nativeId) {
                    return tst;
                }
            }
            return null;
        }

        public static TabStopType fromOoxmlId(int ooxmlId) {
            for (TabStopType tst : values()) {
                if (tst.ooxmlId == ooxmlId) {
                    return tst;
                }
            }
            return null;
        }
    }
}
