package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;
import org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument;

/* loaded from: classes10.dex */
public class XSLFSlideMaster extends XSLFSheet implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private Map<String, XSLFSlideLayout> _layouts;
    private CTSlideMaster _slide;

    @Override // org.apache.poi.sl.usermodel.MasterSheet
    public /* bridge */ /* synthetic */ SimpleShape<XSLFShape, XSLFTextParagraph> getPlaceholder(Placeholder placeholder) {
        return super.getPlaceholder(placeholder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFSlideMaster(PackagePart part) throws IOException, XmlException {
        super(part);
        InputStream stream = getPackagePart().getInputStream();
        try {
            SldMasterDocument doc = SldMasterDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this._slide = doc.getSldMaster();
            if (stream != null) {
                stream.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public CTSlideMaster getXmlObject() {
        return this._slide;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    protected String getRootElementName() {
        return "sldMaster";
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public MasterSheet<XSLFShape, XSLFTextParagraph> getMasterSheet() {
        return null;
    }

    private Map<String, XSLFSlideLayout> getLayouts() {
        if (this._layouts == null) {
            this._layouts = new HashMap();
            for (POIXMLDocumentPart p : getRelations()) {
                if (p instanceof XSLFSlideLayout) {
                    XSLFSlideLayout layout = (XSLFSlideLayout) p;
                    this._layouts.put(layout.getName().toLowerCase(Locale.ROOT), layout);
                }
            }
        }
        return this._layouts;
    }

    public XSLFSlideLayout[] getSlideLayouts() {
        return (XSLFSlideLayout[]) getLayouts().values().toArray(new XSLFSlideLayout[this._layouts.size()]);
    }

    public XSLFSlideLayout getLayout(SlideLayout type) {
        for (XSLFSlideLayout layout : getLayouts().values()) {
            if (layout.getType() == type) {
                return layout;
            }
        }
        return null;
    }

    public XSLFSlideLayout getLayout(String name) {
        return getLayouts().get(name.toLowerCase(Locale.ROOT));
    }

    protected CTTextListStyle getTextProperties(Placeholder textType) {
        CTSlideMasterTextStyles txStyles = getXmlObject().getTxStyles();
        switch (textType) {
            case TITLE:
            case CENTERED_TITLE:
            case SUBTITLE:
                CTTextListStyle props = txStyles.getTitleStyle();
                return props;
            case BODY:
                CTTextListStyle props2 = txStyles.getBodyStyle();
                return props2;
            default:
                CTTextListStyle props3 = txStyles.getOtherStyle();
                return props3;
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.sl.usermodel.Sheet
    public Background<XSLFShape, XSLFTextParagraph> getBackground() {
        CTBackground bg = this._slide.getCSld().getBg();
        if (bg != null) {
            return new XSLFBackground(bg, this);
        }
        return null;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    boolean isSupportTheme() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public String mapSchemeColor(String schemeColor) {
        String masterColor = mapSchemeColor(this._slide.getClrMap(), schemeColor);
        return masterColor == null ? schemeColor : masterColor;
    }
}
