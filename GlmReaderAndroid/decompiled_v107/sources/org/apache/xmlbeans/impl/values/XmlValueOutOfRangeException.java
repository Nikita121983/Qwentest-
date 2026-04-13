package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.XmlError;

/* loaded from: classes11.dex */
public class XmlValueOutOfRangeException extends IllegalArgumentException {
    public XmlValueOutOfRangeException() {
    }

    public XmlValueOutOfRangeException(String message) {
        super(message);
    }

    public XmlValueOutOfRangeException(String code, Object[] args) {
        super(XmlError.formattedMessage(code, args));
    }
}
