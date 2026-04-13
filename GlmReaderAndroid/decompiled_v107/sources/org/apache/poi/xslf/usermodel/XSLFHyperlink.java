package org.apache.poi.xslf.usermodel;

import androidx.core.net.MailTo;
import java.net.URI;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;

/* loaded from: classes10.dex */
public class XSLFHyperlink implements Hyperlink<XSLFShape, XSLFTextParagraph> {
    private final CTHyperlink _link;
    private final XSLFSheet _sheet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFHyperlink(CTHyperlink link, XSLFSheet sheet) {
        this._sheet = sheet;
        this._link = link;
    }

    @Internal
    public CTHyperlink getXmlObject() {
        return this._link;
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public void setAddress(String address) {
        linkToUrl(address);
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public String getAddress() {
        URI targetURI;
        String id = this._link.getId();
        if (id == null || id.isEmpty()) {
            return this._link.getAction();
        }
        PackageRelationship rel = this._sheet.getPackagePart().getRelationship(id);
        if (rel == null || (targetURI = rel.getTargetURI()) == null) {
            return null;
        }
        return targetURI.toASCIIString();
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public String getLabel() {
        return this._link.getTooltip();
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public void setLabel(String label) {
        this._link.setTooltip(label);
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public HyperlinkType getType() {
        String action = this._link.getAction();
        if (action == null) {
            action = "";
        }
        if (action.equals("ppaction://hlinksldjump") || action.startsWith("ppaction://hlinkshowjump")) {
            return HyperlinkType.DOCUMENT;
        }
        String address = getAddress();
        if (address == null) {
            address = "";
        }
        if (address.startsWith(MailTo.MAILTO_SCHEME)) {
            return HyperlinkType.EMAIL;
        }
        return HyperlinkType.URL;
    }

    @Override // org.apache.poi.sl.usermodel.Hyperlink
    public void linkToEmail(String emailAddress) {
        linkToExternal(MailTo.MAILTO_SCHEME + emailAddress);
        setLabel(emailAddress);
    }

    @Override // org.apache.poi.sl.usermodel.Hyperlink
    public void linkToUrl(String url) {
        linkToExternal(url);
        setLabel(url);
    }

    private void linkToExternal(String url) {
        PackagePart thisPP = this._sheet.getPackagePart();
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            thisPP.removeRelationship(this._link.getId());
        }
        PackageRelationship rel = thisPP.addExternalRelationship(url, XSLFRelation.HYPERLINK.getRelation());
        this._link.setId(rel.getId());
        if (this._link.isSetAction()) {
            this._link.unsetAction();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.poi.sl.usermodel.Hyperlink
    public void linkToSlide(Slide<XSLFShape, XSLFTextParagraph> slide) {
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            this._sheet.getPackagePart().removeRelationship(this._link.getId());
        }
        POIXMLDocumentPart.RelationPart rp = this._sheet.addRelation(null, XSLFRelation.SLIDE, (XSLFSheet) slide);
        this._link.setId(rp.getRelationship().getId());
        this._link.setAction("ppaction://hlinksldjump");
    }

    @Override // org.apache.poi.sl.usermodel.Hyperlink
    public void linkToNextSlide() {
        linkToRelativeSlide("nextslide");
    }

    @Override // org.apache.poi.sl.usermodel.Hyperlink
    public void linkToPreviousSlide() {
        linkToRelativeSlide("previousslide");
    }

    @Override // org.apache.poi.sl.usermodel.Hyperlink
    public void linkToFirstSlide() {
        linkToRelativeSlide("firstslide");
    }

    @Override // org.apache.poi.sl.usermodel.Hyperlink
    public void linkToLastSlide() {
        linkToRelativeSlide("lastslide");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void copy(XSLFHyperlink src) {
        switch (src.getType()) {
            case EMAIL:
            case URL:
                linkToExternal(src.getAddress());
                break;
            case DOCUMENT:
                String idSrc = src._link.getId();
                if (idSrc == null || idSrc.isEmpty()) {
                    linkToRelativeSlide(src.getAddress());
                    break;
                } else {
                    POIXMLDocumentPart pp = src._sheet.getRelationById(idSrc);
                    if (pp != null) {
                        POIXMLDocumentPart.RelationPart rp = this._sheet.addRelation(null, XSLFRelation.SLIDE, pp);
                        this._link.setId(rp.getRelationship().getId());
                        this._link.setAction(src._link.getAction());
                        break;
                    }
                }
                break;
            default:
                return;
        }
        setLabel(src.getLabel());
    }

    private void linkToRelativeSlide(String jump) {
        PackagePart thisPP = this._sheet.getPackagePart();
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            thisPP.removeRelationship(this._link.getId());
        }
        this._link.setId("");
        this._link.setAction((jump.startsWith("ppaction") ? "" : "ppaction://hlinkshowjump?jump=") + jump);
    }
}
