package org.apache.poi.common.usermodel;

/* loaded from: classes10.dex */
public interface Hyperlink {
    String getAddress();

    String getLabel();

    HyperlinkType getType();

    void setAddress(String str);

    void setLabel(String str);
}
