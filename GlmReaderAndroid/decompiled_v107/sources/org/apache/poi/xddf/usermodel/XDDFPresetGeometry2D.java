package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;

/* loaded from: classes10.dex */
public class XDDFPresetGeometry2D {
    private CTPresetGeometry2D geometry;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFPresetGeometry2D(CTPresetGeometry2D geometry) {
        this.geometry = geometry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTPresetGeometry2D getXmlObject() {
        return this.geometry;
    }

    public PresetGeometry getGeometry() {
        return PresetGeometry.valueOf(this.geometry.getPrst());
    }

    public void setGeometry(PresetGeometry preset) {
        this.geometry.setPrst(preset.underlying);
    }

    public XDDFGeometryGuide addAdjustValue() {
        if (!this.geometry.isSetAvLst()) {
            this.geometry.addNewAvLst();
        }
        return new XDDFGeometryGuide(this.geometry.getAvLst().addNewGd());
    }

    public XDDFGeometryGuide insertAdjustValue(int index) {
        if (!this.geometry.isSetAvLst()) {
            this.geometry.addNewAvLst();
        }
        return new XDDFGeometryGuide(this.geometry.getAvLst().insertNewGd(index));
    }

    public void removeAdjustValue(int index) {
        if (this.geometry.isSetAvLst()) {
            this.geometry.getAvLst().removeGd(index);
        }
    }

    public XDDFGeometryGuide getAdjustValue(int index) {
        if (this.geometry.isSetAvLst()) {
            return new XDDFGeometryGuide(this.geometry.getAvLst().getGdArray(index));
        }
        return null;
    }

    public List<XDDFGeometryGuide> getAdjustValues() {
        if (this.geometry.isSetAvLst()) {
            return Collections.unmodifiableList((List) this.geometry.getAvLst().getGdList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.XDDFPresetGeometry2D$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XDDFPresetGeometry2D.lambda$getAdjustValues$0((CTGeomGuide) obj);
                }
            }).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFGeometryGuide lambda$getAdjustValues$0(CTGeomGuide guide) {
        return new XDDFGeometryGuide(guide);
    }
}
