package org.apache.xmlbeans;

import javax.xml.namespace.QName;

/* loaded from: classes.dex */
public interface SchemaTypeElementSequencer {
    boolean next(QName qName);

    boolean peek(QName qName);
}
