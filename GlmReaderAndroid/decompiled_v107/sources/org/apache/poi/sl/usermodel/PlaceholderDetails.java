package org.apache.poi.sl.usermodel;

import java.time.format.DateTimeFormatter;

/* loaded from: classes10.dex */
public interface PlaceholderDetails {

    /* loaded from: classes10.dex */
    public enum PlaceholderSize {
        quarter,
        half,
        full
    }

    Placeholder getPlaceholder();

    PlaceholderSize getSize();

    String getText();

    boolean isVisible();

    void setPlaceholder(Placeholder placeholder);

    void setSize(PlaceholderSize placeholderSize);

    void setText(String str);

    void setVisible(boolean z);

    default String getUserDate() {
        return null;
    }

    default DateTimeFormatter getDateFormat() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }
}
