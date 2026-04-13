package org.apache.poi.xslf.usermodel;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderSize;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderType;

/* loaded from: classes10.dex */
public class XSLFPlaceholderDetails implements PlaceholderDetails {
    private static final QName[] NV_CONTAINER = {new QName(XSSFRelation.NS_PRESENTATIONML, "nvSpPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "nvCxnSpPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "nvGrpSpPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "nvPicPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "nvGraphicFramePr")};
    private static final QName[] NV_PROPS = {new QName(XSSFRelation.NS_PRESENTATIONML, "nvPr")};
    private CTPlaceholder _ph;
    private final XSLFShape shape;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFPlaceholderDetails(XSLFShape shape) {
        this.shape = shape;
    }

    @Override // org.apache.poi.sl.usermodel.PlaceholderDetails
    public Placeholder getPlaceholder() {
        CTPlaceholder ph = getCTPlaceholder(false);
        if (ph == null) {
            return null;
        }
        if (!ph.isSetType() && !ph.isSetIdx()) {
            return null;
        }
        return Placeholder.lookupOoxml(ph.getType().intValue());
    }

    public XSLFSimpleShape getPlaceholderShape() {
        CTPlaceholder ph = getCTPlaceholder(false);
        if (ph == null) {
            return null;
        }
        XSLFSheet sheet = (XSLFSheet) this.shape.getSheet().getMasterSheet();
        return sheet.getPlaceholder(ph);
    }

    @Override // org.apache.poi.sl.usermodel.PlaceholderDetails
    public void setPlaceholder(Placeholder placeholder) {
        CTPlaceholder ph = getCTPlaceholder(placeholder != null);
        if (ph != null) {
            if (placeholder != null) {
                ph.setType(STPlaceholderType.Enum.forInt(placeholder.ooxmlId));
                return;
            }
            CTApplicationNonVisualDrawingProps nvProps = getNvProps();
            if (nvProps != null) {
                nvProps.unsetPh();
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.PlaceholderDetails
    public boolean isVisible() {
        CTPlaceholder ph = getCTPlaceholder(false);
        if (ph == null || !ph.isSetType()) {
            return true;
        }
        CTHeaderFooter hf = getHeaderFooter(false);
        if (hf == null) {
            return false;
        }
        Placeholder pl = Placeholder.lookupOoxml(ph.getType().intValue());
        if (pl == null) {
            return true;
        }
        switch (pl) {
            case DATETIME:
                if (hf.isSetDt() && !hf.getDt()) {
                    return false;
                }
                return true;
            case FOOTER:
                if (hf.isSetFtr() && !hf.getFtr()) {
                    return false;
                }
                return true;
            case HEADER:
                if (hf.isSetHdr() && !hf.getHdr()) {
                    return false;
                }
                return true;
            case SLIDE_NUMBER:
                if (hf.isSetSldNum() && !hf.getSldNum()) {
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    @Override // org.apache.poi.sl.usermodel.PlaceholderDetails
    public void setVisible(boolean isVisible) {
        Function<CTHeaderFooter, Consumer<Boolean>> fun;
        Placeholder ph = getPlaceholder();
        if (ph == null) {
            return;
        }
        switch (ph) {
            case DATETIME:
                fun = new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return XSLFPlaceholderDetails.lambda$setVisible$0((CTHeaderFooter) obj);
                    }
                };
                break;
            case FOOTER:
                fun = new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return XSLFPlaceholderDetails.lambda$setVisible$1((CTHeaderFooter) obj);
                    }
                };
                break;
            case HEADER:
                fun = new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return XSLFPlaceholderDetails.lambda$setVisible$2((CTHeaderFooter) obj);
                    }
                };
                break;
            case SLIDE_NUMBER:
                fun = new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return XSLFPlaceholderDetails.lambda$setVisible$3((CTHeaderFooter) obj);
                    }
                };
                break;
            default:
                return;
        }
        CTHeaderFooter hf = getHeaderFooter(true);
        if (hf == null) {
            return;
        }
        fun.apply(hf).accept(Boolean.valueOf(isVisible));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Consumer lambda$setVisible$0(final CTHeaderFooter hf) {
        hf.getClass();
        return new Consumer() { // from class: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTHeaderFooter.this.setDt(((Boolean) obj).booleanValue());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Consumer lambda$setVisible$1(final CTHeaderFooter hf) {
        hf.getClass();
        return new Consumer() { // from class: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTHeaderFooter.this.setFtr(((Boolean) obj).booleanValue());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Consumer lambda$setVisible$2(final CTHeaderFooter hf) {
        hf.getClass();
        return new Consumer() { // from class: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTHeaderFooter.this.setHdr(((Boolean) obj).booleanValue());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Consumer lambda$setVisible$3(final CTHeaderFooter hf) {
        hf.getClass();
        return new Consumer() { // from class: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CTHeaderFooter.this.setSldNum(((Boolean) obj).booleanValue());
            }
        };
    }

    @Override // org.apache.poi.sl.usermodel.PlaceholderDetails
    public PlaceholderDetails.PlaceholderSize getSize() {
        CTPlaceholder ph = getCTPlaceholder(false);
        if (ph == null || !ph.isSetSz()) {
            return null;
        }
        switch (ph.getSz().intValue()) {
            case 1:
                return PlaceholderDetails.PlaceholderSize.full;
            case 2:
                return PlaceholderDetails.PlaceholderSize.half;
            case 3:
                return PlaceholderDetails.PlaceholderSize.quarter;
            default:
                return null;
        }
    }

    @Override // org.apache.poi.sl.usermodel.PlaceholderDetails
    public void setSize(PlaceholderDetails.PlaceholderSize size) {
        CTPlaceholder ph = getCTPlaceholder(false);
        if (ph == null) {
            return;
        }
        if (size == null) {
            ph.unsetSz();
            return;
        }
        switch (size) {
            case full:
                ph.setSz(STPlaceholderSize.FULL);
                return;
            case half:
                ph.setSz(STPlaceholderSize.HALF);
                return;
            case quarter:
                ph.setSz(STPlaceholderSize.QUARTER);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CTPlaceholder getCTPlaceholder(boolean create) {
        if (this._ph != null) {
            return this._ph;
        }
        CTApplicationNonVisualDrawingProps nv = getNvProps();
        if (nv == null) {
            return null;
        }
        this._ph = (nv.isSetPh() || !create) ? nv.getPh() : nv.addNewPh();
        return this._ph;
    }

    private CTApplicationNonVisualDrawingProps getNvProps() {
        try {
            return (CTApplicationNonVisualDrawingProps) XPathHelper.selectProperty(this.shape.getXmlObject(), CTApplicationNonVisualDrawingProps.class, null, NV_CONTAINER, NV_PROPS);
        } catch (XmlException e) {
            return null;
        }
    }

    private CTHeaderFooter getHeaderFooter(boolean create) {
        Sheet<XSLFShape, XSLFTextParagraph> sheet = this.shape.getSheet();
        Sheet<XSLFShape, XSLFTextParagraph> sheet2 = (!(sheet instanceof MasterSheet) || (sheet instanceof XSLFSlideLayout)) ? (XSLFSheet) sheet.getMasterSheet() : sheet;
        if (sheet2 instanceof XSLFSlideMaster) {
            CTSlideMaster ct = ((XSLFSlideMaster) sheet2).getXmlObject();
            return (ct.isSetHf() || !create) ? ct.getHf() : ct.addNewHf();
        }
        if (sheet2 instanceof XSLFNotesMaster) {
            CTNotesMaster ct2 = ((XSLFNotesMaster) sheet2).getXmlObject();
            return (ct2.isSetHf() || !create) ? ct2.getHf() : ct2.addNewHf();
        }
        return null;
    }

    @Override // org.apache.poi.sl.usermodel.PlaceholderDetails
    public String getText() {
        return null;
    }

    @Override // org.apache.poi.sl.usermodel.PlaceholderDetails
    public void setText(String text) {
    }
}
