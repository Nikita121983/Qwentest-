package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ss.usermodel.ChildAnchor;

/* loaded from: classes10.dex */
public abstract class HSSFAnchor implements ChildAnchor {
    protected boolean _isHorizontallyFlipped;
    protected boolean _isVerticallyFlipped;

    protected abstract void createEscherAnchor();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract EscherRecord getEscherAnchor();

    public abstract boolean isHorizontallyFlipped();

    public abstract boolean isVerticallyFlipped();

    public HSSFAnchor() {
        createEscherAnchor();
    }

    public HSSFAnchor(int dx1, int dy1, int dx2, int dy2) {
        createEscherAnchor();
        setDx1(dx1);
        setDy1(dy1);
        setDx2(dx2);
        setDy2(dy2);
    }

    public static HSSFAnchor createAnchorFromEscher(EscherContainerRecord container) {
        if (container.getChildById(EscherChildAnchorRecord.RECORD_ID) != null) {
            return new HSSFChildAnchor((EscherChildAnchorRecord) container.getChildById(EscherChildAnchorRecord.RECORD_ID));
        }
        if (container.getChildById(EscherClientAnchorRecord.RECORD_ID) != null) {
            return new HSSFClientAnchor((EscherClientAnchorRecord) container.getChildById(EscherClientAnchorRecord.RECORD_ID));
        }
        return null;
    }
}
