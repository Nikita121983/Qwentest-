package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public interface ConditionalFormattingThreshold {
    String getFormula();

    RangeType getRangeType();

    Double getValue();

    void setFormula(String str);

    void setRangeType(RangeType rangeType);

    void setValue(Double d);

    /* loaded from: classes10.dex */
    public enum RangeType {
        NUMBER(1, "num"),
        MIN(2, "min"),
        MAX(3, "max"),
        PERCENT(4, "percent"),
        PERCENTILE(5, "percentile"),
        UNALLOCATED(6, null),
        FORMULA(7, "formula");

        public final int id;
        public final String name;

        @Override // java.lang.Enum
        public String toString() {
            return this.id + " - " + this.name;
        }

        public static RangeType byId(int id) {
            if (id <= 0 || id > values().length) {
                return null;
            }
            return values()[id - 1];
        }

        public static RangeType byName(String name) {
            for (RangeType t : values()) {
                if (t.name == null && name == null) {
                    return t;
                }
                if (t.name != null && t.name.equals(name)) {
                    return t;
                }
            }
            return null;
        }

        RangeType(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
