package org.apache.poi.ooxml.util;

import javax.xml.transform.TransformerFactory;
import org.apache.poi.util.Removal;
import org.apache.poi.util.XMLHelper;

@Removal(version = "6.0.0")
@Deprecated
/* loaded from: classes10.dex */
public final class TransformerHelper {
    private TransformerHelper() {
    }

    public static TransformerFactory getFactory() {
        return XMLHelper.getTransformerFactory();
    }
}
