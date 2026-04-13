package org.apache.poi.poifs.crypt;

/* loaded from: classes10.dex */
public enum ChainingMode {
    ecb("ECB", 1, null),
    cbc("CBC", 2, "ChainingModeCBC"),
    cfb("CFB8", 3, "ChainingModeCFB");

    public final int ecmaId;
    public final String jceId;
    public final String xmlId;

    ChainingMode(String jceId, int ecmaId, String xmlId) {
        this.jceId = jceId;
        this.ecmaId = ecmaId;
        this.xmlId = xmlId;
    }

    public static ChainingMode fromXmlId(String xmlId) {
        for (ChainingMode cm : values()) {
            if (cm.xmlId != null && cm.xmlId.equals(xmlId)) {
                return cm;
            }
        }
        return null;
    }
}
