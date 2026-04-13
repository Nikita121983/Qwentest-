package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class FootnoteEndnoteIdManager {
    private XWPFDocument document;

    public FootnoteEndnoteIdManager(XWPFDocument document) {
        this.document = document;
    }

    public BigInteger nextId() {
        List<BigInteger> ids = new ArrayList<>();
        for (XWPFAbstractFootnoteEndnote note : this.document.getFootnotes()) {
            ids.add(note.getId());
        }
        for (XWPFAbstractFootnoteEndnote note2 : this.document.getEndnotes()) {
            ids.add(note2.getId());
        }
        int cand = ids.size();
        BigInteger newId = BigInteger.valueOf(cand);
        while (ids.contains(newId)) {
            cand++;
            newId = BigInteger.valueOf(cand);
        }
        return newId;
    }
}
