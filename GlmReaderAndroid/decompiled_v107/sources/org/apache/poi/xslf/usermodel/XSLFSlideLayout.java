package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout;
import org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument;

/* loaded from: classes10.dex */
public class XSLFSlideLayout extends XSLFSheet implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private final CTSlideLayout _layout;
    private XSLFSlideMaster _master;

    @Override // org.apache.poi.sl.usermodel.MasterSheet
    public /* bridge */ /* synthetic */ SimpleShape<XSLFShape, XSLFTextParagraph> getPlaceholder(Placeholder placeholder) {
        return super.getPlaceholder(placeholder);
    }

    public XSLFSlideLayout(PackagePart part) throws IOException, XmlException {
        super(part);
        InputStream stream = getPackagePart().getInputStream();
        try {
            SldLayoutDocument doc = SldLayoutDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this._layout = doc.getSldLayout();
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

    public String getName() {
        return this._layout.getCSld().getName();
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    @Internal
    public CTSlideLayout getXmlObject() {
        return this._layout;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    protected String getRootElementName() {
        return "sldLayout";
    }

    public XSLFSlideMaster getSlideMaster() {
        if (this._master == null) {
            for (POIXMLDocumentPart p : getRelations()) {
                if (p instanceof XSLFSlideMaster) {
                    this._master = (XSLFSlideMaster) p;
                }
            }
        }
        if (this._master == null) {
            throw new IllegalStateException("SlideMaster was not found for " + this);
        }
        return this._master;
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public MasterSheet<XSLFShape, XSLFTextParagraph> getMasterSheet() {
        return getSlideMaster();
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public XSLFTheme getTheme() {
        return getSlideMaster().getTheme();
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.sl.usermodel.Sheet
    public boolean getFollowMasterGraphics() {
        return this._layout.getShowMasterSp();
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.sl.usermodel.Sheet
    public Background<XSLFShape, XSLFTextParagraph> getBackground() {
        CTBackground bg = this._layout.getCSld().getBg();
        if (bg != null) {
            return new XSLFBackground(bg, this);
        }
        return getMasterSheet().getBackground();
    }

    public void copyLayout(XSLFSlide slide) {
        XSLFTextShape tsh;
        Placeholder ph;
        for (XSLFShape sh : getShapes()) {
            if ((sh instanceof XSLFTextShape) && (ph = (tsh = (XSLFTextShape) sh).getTextType()) != null) {
                switch (ph) {
                    case DATETIME:
                    case SLIDE_NUMBER:
                    case FOOTER:
                        break;
                    default:
                        slide.getSpTree().addNewSp().set(tsh.getXmlObject().copy());
                        break;
                }
            }
        }
    }

    public SlideLayout getType() {
        int ordinal = this._layout.getType().intValue() - 1;
        return SlideLayout.values()[ordinal];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public String mapSchemeColor(String schemeColor) {
        return mapSchemeColor(this._layout.getClrMapOvr(), schemeColor);
    }
}
