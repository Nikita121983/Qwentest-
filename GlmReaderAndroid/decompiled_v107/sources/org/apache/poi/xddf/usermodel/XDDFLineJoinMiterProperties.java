package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;

/* loaded from: classes10.dex */
public class XDDFLineJoinMiterProperties implements XDDFLineJoinProperties {
    private CTLineJoinMiterProperties join;

    public XDDFLineJoinMiterProperties() {
        this(CTLineJoinMiterProperties.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFLineJoinMiterProperties(CTLineJoinMiterProperties join) {
        this.join = join;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTLineJoinMiterProperties getXmlObject() {
        return this.join;
    }

    public Integer getLimit() {
        if (this.join.isSetLim()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.join.xgetLim()));
        }
        return null;
    }

    public void setLimit(Integer limit) {
        if (limit == null) {
            if (this.join.isSetLim()) {
                this.join.unsetLim();
                return;
            }
            return;
        }
        this.join.setLim(limit);
    }
}
