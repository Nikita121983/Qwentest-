package org.apache.xmlbeans.impl.xpath;

import org.apache.xmlbeans.impl.store.Cur;

/* loaded from: classes11.dex */
public interface XPathEngine {
    boolean next(Cur cur);

    void release();
}
