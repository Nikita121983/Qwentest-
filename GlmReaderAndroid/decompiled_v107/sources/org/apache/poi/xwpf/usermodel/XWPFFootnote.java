package org.apache.poi.xwpf.usermodel;

import java.util.Iterator;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

/* loaded from: classes10.dex */
public class XWPFFootnote extends XWPFAbstractFootnoteEndnote {
    @Internal
    public XWPFFootnote(CTFtnEdn note, XWPFAbstractFootnotesEndnotes xFootnotes) {
        super(note, xFootnotes);
    }

    @Internal
    public XWPFFootnote(XWPFDocument document, CTFtnEdn body) {
        super(document, body);
    }

    @Override // org.apache.poi.xwpf.usermodel.XWPFAbstractFootnoteEndnote
    public void ensureFootnoteRef(XWPFParagraph p) {
        XWPFRun r = null;
        if (!p.runsIsEmpty()) {
            XWPFRun r2 = p.getRuns().get(0);
            r = r2;
        }
        if (r == null) {
            r = p.createRun();
        }
        CTR ctr = r.getCTR();
        boolean foundRef = false;
        Iterator<CTFtnEdnRef> it = ctr.getFootnoteReferenceList().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CTFtnEdnRef ref = it.next();
            if (getId().equals(ref.getId())) {
                foundRef = true;
                break;
            }
        }
        if (!foundRef) {
            ctr.addNewRPr().addNewRStyle().setVal("FootnoteReference");
            ctr.addNewFootnoteRef();
        }
    }
}
