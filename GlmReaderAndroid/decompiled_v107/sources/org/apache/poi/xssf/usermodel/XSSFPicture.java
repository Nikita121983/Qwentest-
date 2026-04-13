package org.apache.poi.xssf.usermodel;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualPictureProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPictureNonVisual;

/* loaded from: classes10.dex */
public final class XSSFPicture extends XSSFShape implements Picture {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFPicture.class);
    private static CTPicture prototype;
    private final CTPicture ctPicture;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFPicture(XSSFDrawing drawing, CTPicture ctPicture) {
        this.drawing = drawing;
        this.ctPicture = ctPicture;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static CTPicture prototype() {
        if (prototype == null) {
            CTPicture pic = CTPicture.Factory.newInstance();
            CTPictureNonVisual nvpr = pic.addNewNvPicPr();
            CTNonVisualDrawingProps nvProps = nvpr.addNewCNvPr();
            nvProps.setId(1L);
            nvProps.setName("Picture 1");
            nvProps.setDescr("Picture");
            CTNonVisualPictureProperties nvPicProps = nvpr.addNewCNvPicPr();
            nvPicProps.addNewPicLocks().setNoChangeAspect(true);
            CTBlipFillProperties blip = pic.addNewBlipFill();
            blip.addNewBlip().setEmbed("");
            blip.addNewStretch().addNewFillRect();
            CTShapeProperties sppr = pic.addNewSpPr();
            CTTransform2D t2d = sppr.addNewXfrm();
            CTPositiveSize2D ext = t2d.addNewExt();
            ext.setCx(0L);
            ext.setCy(0L);
            CTPoint2D off = t2d.addNewOff();
            off.setX(0);
            off.setY(0);
            CTPresetGeometry2D prstGeom = sppr.addNewPrstGeom();
            prstGeom.setPrst(STShapeType.RECT);
            prstGeom.addNewAvLst();
            prototype = pic;
        }
        return prototype;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPictureReference(PackageRelationship rel) {
        this.ctPicture.getBlipFill().getBlip().setEmbed(rel.getId());
    }

    @Internal
    public CTPicture getCTPicture() {
        return this.ctPicture;
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public void resize() {
        resize(Double.MAX_VALUE);
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public void resize(double scale) {
        resize(scale, scale);
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public void resize(double scaleX, double scaleY) {
        XSSFClientAnchor anchor = getClientAnchor();
        XSSFClientAnchor pref = getPreferredSize(scaleX, scaleY);
        if (anchor == null || pref == null) {
            LOG.atWarn().log("picture is not anchored via client anchor - ignoring resize call");
            return;
        }
        int row2 = anchor.getRow1() + (pref.getRow2() - pref.getRow1());
        int col2 = anchor.getCol1() + (pref.getCol2() - pref.getCol1());
        anchor.setCol2(col2);
        anchor.setDx2(pref.getDx2());
        anchor.setRow2(row2);
        anchor.setDy2(pref.getDy2());
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public XSSFClientAnchor getPreferredSize() {
        return getPreferredSize(1.0d);
    }

    public XSSFClientAnchor getPreferredSize(double scale) {
        return getPreferredSize(scale, scale);
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public XSSFClientAnchor getPreferredSize(double scaleX, double scaleY) {
        Dimension dim = ImageUtils.setPreferredSize(this, scaleX, scaleY);
        CTPositiveSize2D size2d = this.ctPicture.getSpPr().getXfrm().getExt();
        size2d.setCx((int) dim.getWidth());
        size2d.setCy((int) dim.getHeight());
        return getClientAnchor();
    }

    protected static Dimension getImageDimension(PackagePart part, int type) {
        try {
            InputStream stream = part.getInputStream();
            try {
                Dimension imageDimension = ImageUtils.getImageDimension(stream, type);
                if (stream != null) {
                    stream.close();
                }
                return imageDimension;
            } finally {
            }
        } catch (IOException e) {
            LOG.atWarn().withThrowable(e).log("Failed to read image");
            return new Dimension();
        }
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public Dimension getImageDimension() {
        XSSFPictureData picData = getPictureData();
        return getImageDimension(picData.getPackagePart(), picData.getPictureType());
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public XSSFPictureData getPictureData() {
        String blipId = this.ctPicture.getBlipFill().getBlip().getEmbed();
        return (XSSFPictureData) getDrawing().getRelationById(blipId);
    }

    @Override // org.apache.poi.xssf.usermodel.XSSFShape
    protected CTShapeProperties getShapeProperties() {
        return this.ctPicture.getSpPr();
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public XSSFClientAnchor getClientAnchor() {
        XSSFAnchor a = getAnchor();
        if (a instanceof XSSFClientAnchor) {
            return (XSSFClientAnchor) a;
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Picture
    public XSSFSheet getSheet() {
        return (XSSFSheet) getDrawing().getParent();
    }

    @Override // org.apache.poi.ss.usermodel.Shape
    public String getShapeName() {
        return this.ctPicture.getNvPicPr().getCNvPr().getName();
    }
}
