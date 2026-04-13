package org.apache.commons.math3.exception.util;

import java.io.Serializable;
import java.util.Locale;

/* loaded from: classes10.dex */
public interface Localizable extends Serializable {
    String getLocalizedString(Locale locale);

    String getSourceString();
}
