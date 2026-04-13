package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;

/* loaded from: classes10.dex */
public class XWPFPicture {
    private final CTPicture ctPic;
    private final String description;
    private final XWPFRun run;

    public XWPFPicture(CTPicture ctPic, XWPFRun run) {
        this.run = run;
        this.ctPic = ctPic;
        if (ctPic == null || ctPic.getNvPicPr() == null || ctPic.getNvPicPr().getCNvPr() == null) {
            throw new IllegalArgumentException("Found missing data while reading picture data from " + ctPic);
        }
        this.description = ctPic.getNvPicPr().getCNvPr().getDescr();
    }

    public void setPictureReference(PackageRelationship rel) {
        this.ctPic.getBlipFill().getBlip().setEmbed(rel.getId());
    }

    public CTPicture getCTPicture() {
        return this.ctPic;
    }

    public XWPFPictureData getPictureData() {
        CTBlipFillProperties blipProps = this.ctPic.getBlipFill();
        if (blipProps == null || !blipProps.isSetBlip()) {
            return null;
        }
        String blipId = blipProps.getBlip().getEmbed();
        POIXMLDocumentPart part = this.run.getParent().getPart();
        if (part != null) {
            POIXMLDocumentPart relatedPart = part.getRelationById(blipId);
            if (relatedPart instanceof XWPFPictureData) {
                return (XWPFPictureData) relatedPart;
            }
        }
        return null;
    }

    public double getWidth() {
        return Units.toPoints(this.ctPic.getSpPr().getXfrm().getExt().getCx());
    }

    public double getDepth() {
        return Units.toPoints(this.ctPic.getSpPr().getXfrm().getExt().getCy());
    }

    public String getDescription() {
        return this.description;
    }
}
