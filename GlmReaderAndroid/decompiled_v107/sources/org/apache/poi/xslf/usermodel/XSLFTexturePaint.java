package org.apache.poi.xslf.usermodel;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFDiagram;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;

@Internal
/* loaded from: classes10.dex */
public class XSLFTexturePaint implements PaintStyle.TexturePaint {
    private final CTBlip blip;
    private final CTBlipFillProperties blipFill;
    private final PackagePart parentPart;
    private final CTSchemeColor phClr;
    private final XSLFShape shape;
    private final XSLFSheet sheet;
    private final XSLFTheme theme;

    public XSLFTexturePaint(XSLFShape shape, CTBlipFillProperties blipFill, PackagePart parentPart, CTSchemeColor phClr, XSLFTheme theme, XSLFSheet sheet) {
        this.shape = shape;
        this.blipFill = blipFill;
        this.parentPart = parentPart;
        this.blip = blipFill.getBlip();
        this.phClr = phClr;
        this.theme = theme;
        this.sheet = sheet;
    }

    private PackagePart getPart() throws InvalidFormatException {
        String blipId = this.blip.getEmbed();
        if (this.shape.getParent() != null && (this.shape.getParent() instanceof XSLFDiagram.XSLFDiagramGroupShape)) {
            XSLFDiagram.XSLFDiagramGroupShape diagramGroupShape = (XSLFDiagram.XSLFDiagramGroupShape) this.shape.getParent();
            POIXMLDocumentPart documentPart = diagramGroupShape.getRelationById(blipId);
            if (documentPart != null) {
                return documentPart.getPackagePart();
            }
        }
        PackageRelationship rel = this.parentPart.getRelationship(blipId);
        return this.parentPart.getRelatedPart(rel);
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public InputStream getImageData() {
        try {
            return getPart().getInputStream();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read image data", e);
        }
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public String getContentType() {
        if (this.blip == null || !this.blip.isSetEmbed() || this.blip.getEmbed().isEmpty()) {
            return null;
        }
        try {
            return getPart().getContentType();
        } catch (InvalidFormatException e) {
            throw new IllegalStateException("Failed to read package part", e);
        }
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public int getAlpha() {
        return this.blip.sizeOfAlphaModFixArray() > 0 ? POIXMLUnits.parsePercent(this.blip.getAlphaModFixArray(0).xgetAmt()) : BZip2Constants.BASEBLOCKSIZE;
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public boolean isRotatedWithShape() {
        return !this.blipFill.isSetRotWithShape() || this.blipFill.getRotWithShape();
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public Dimension2D getScale() {
        CTTileInfoProperties tile = this.blipFill.getTile();
        if (tile == null) {
            return null;
        }
        return new Dimension2DDouble(tile.isSetSx() ? POIXMLUnits.parsePercent(tile.xgetSx()) / 100000.0d : 1.0d, tile.isSetSy() ? POIXMLUnits.parsePercent(tile.xgetSy()) / 100000.0d : 1.0d);
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public Point2D getOffset() {
        CTTileInfoProperties tile = this.blipFill.getTile();
        if (tile == null) {
            return null;
        }
        return new Point2D.Double(tile.isSetTx() ? Units.toPoints(POIXMLUnits.parseLength(tile.xgetTx())) : 0.0d, tile.isSetTy() ? Units.toPoints(POIXMLUnits.parseLength(tile.xgetTy())) : 0.0d);
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public PaintStyle.FlipMode getFlipMode() {
        CTTileInfoProperties tile = this.blipFill.getTile();
        switch ((tile == null || tile.getFlip() == null) ? 1 : tile.getFlip().intValue()) {
            case 2:
                return PaintStyle.FlipMode.X;
            case 3:
                return PaintStyle.FlipMode.Y;
            case 4:
                return PaintStyle.FlipMode.XY;
            default:
                return PaintStyle.FlipMode.NONE;
        }
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public PaintStyle.TextureAlignment getAlignment() {
        CTTileInfoProperties tile = this.blipFill.getTile();
        if (tile == null || !tile.isSetAlgn()) {
            return null;
        }
        return PaintStyle.TextureAlignment.fromOoxmlId(tile.getAlgn().toString());
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public Insets2D getInsets() {
        return getRectVal(this.blipFill.getSrcRect());
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public Insets2D getStretch() {
        return getRectVal(this.blipFill.isSetStretch() ? this.blipFill.getStretch().getFillRect() : null);
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public List<ColorStyle> getDuoTone() {
        if (this.blip.sizeOfDuotoneArray() == 0) {
            return null;
        }
        List<ColorStyle> colors = new ArrayList<>();
        CTDuotoneEffect duoEff = this.blip.getDuotoneArray(0);
        for (CTSchemeColor phClrDuo : duoEff.getSchemeClrArray()) {
            colors.add(new XSLFColor(phClrDuo, this.theme, this.phClr, this.sheet).getColorStyle());
        }
        return colors;
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.TexturePaint
    public Shape getShape() {
        return this.shape;
    }

    private static Insets2D getRectVal(final CTRelativeRect rect) {
        if (rect == null) {
            return null;
        }
        rect.getClass();
        Supplier supplier = new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTexturePaint$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTRelativeRect.this.isSetT());
            }
        };
        rect.getClass();
        double rectVal = getRectVal(supplier, new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTexturePaint$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return CTRelativeRect.this.xgetT();
            }
        });
        rect.getClass();
        Supplier supplier2 = new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTexturePaint$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTRelativeRect.this.isSetL());
            }
        };
        rect.getClass();
        double rectVal2 = getRectVal(supplier2, new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTexturePaint$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return CTRelativeRect.this.xgetL();
            }
        });
        rect.getClass();
        Supplier supplier3 = new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTexturePaint$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTRelativeRect.this.isSetB());
            }
        };
        rect.getClass();
        double rectVal3 = getRectVal(supplier3, new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTexturePaint$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return CTRelativeRect.this.xgetB();
            }
        });
        rect.getClass();
        Supplier supplier4 = new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTexturePaint$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CTRelativeRect.this.isSetR());
            }
        };
        rect.getClass();
        return new Insets2D(rectVal, rectVal2, rectVal3, getRectVal(supplier4, new Supplier() { // from class: org.apache.poi.xslf.usermodel.XSLFTexturePaint$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return CTRelativeRect.this.xgetR();
            }
        }));
    }

    private static int getRectVal(Supplier<Boolean> isSet, Supplier<STPercentage> val) {
        if (isSet.get().booleanValue()) {
            return POIXMLUnits.parsePercent(val.get());
        }
        return 0;
    }
}
