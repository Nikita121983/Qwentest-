package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

/* loaded from: classes10.dex */
public class XDDFCustomGeometry2D {
    private CTCustomGeometry2D geometry;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFCustomGeometry2D(CTCustomGeometry2D geometry) {
        this.geometry = geometry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTCustomGeometry2D getXmlObject() {
        return this.geometry;
    }

    public XDDFGeometryRectangle getRectangle() {
        if (this.geometry.isSetRect()) {
            return new XDDFGeometryRectangle(this.geometry.getRect());
        }
        return null;
    }

    public void setRectangle(XDDFGeometryRectangle rectangle) {
        if (rectangle == null) {
            if (this.geometry.isSetRect()) {
                this.geometry.unsetRect();
                return;
            }
            return;
        }
        this.geometry.setRect(rectangle.getXmlObject());
    }

    public XDDFAdjustHandlePolar addPolarAdjustHandle() {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandlePolar(this.geometry.getAhLst().addNewAhPolar());
    }

    public XDDFAdjustHandlePolar insertPolarAdjustHandle(int index) {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandlePolar(this.geometry.getAhLst().insertNewAhPolar(index));
    }

    public void removePolarAdjustHandle(int index) {
        if (this.geometry.isSetAhLst()) {
            this.geometry.getAhLst().removeAhPolar(index);
        }
    }

    public XDDFAdjustHandlePolar getPolarAdjustHandle(int index) {
        if (this.geometry.isSetAhLst()) {
            return new XDDFAdjustHandlePolar(this.geometry.getAhLst().getAhPolarArray(index));
        }
        return null;
    }

    public List<XDDFAdjustHandlePolar> getPolarAdjustHandles() {
        if (this.geometry.isSetAhLst()) {
            return Collections.unmodifiableList((List) this.geometry.getAhLst().getAhPolarList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.XDDFCustomGeometry2D$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XDDFCustomGeometry2D.lambda$getPolarAdjustHandles$0((CTPolarAdjustHandle) obj);
                }
            }).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFAdjustHandlePolar lambda$getPolarAdjustHandles$0(CTPolarAdjustHandle guide) {
        return new XDDFAdjustHandlePolar(guide);
    }

    public XDDFAdjustHandleXY addXYAdjustHandle() {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandleXY(this.geometry.getAhLst().addNewAhXY());
    }

    public XDDFAdjustHandleXY insertXYAdjustHandle(int index) {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandleXY(this.geometry.getAhLst().insertNewAhXY(index));
    }

    public void removeXYAdjustHandle(int index) {
        if (this.geometry.isSetAhLst()) {
            this.geometry.getAhLst().removeAhXY(index);
        }
    }

    public XDDFAdjustHandleXY getXYAdjustHandle(int index) {
        if (this.geometry.isSetAhLst()) {
            return new XDDFAdjustHandleXY(this.geometry.getAhLst().getAhXYArray(index));
        }
        return null;
    }

    public List<XDDFAdjustHandleXY> getXYAdjustHandles() {
        if (this.geometry.isSetAhLst()) {
            return Collections.unmodifiableList((List) this.geometry.getAhLst().getAhXYList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.XDDFCustomGeometry2D$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XDDFCustomGeometry2D.lambda$getXYAdjustHandles$1((CTXYAdjustHandle) obj);
                }
            }).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFAdjustHandleXY lambda$getXYAdjustHandles$1(CTXYAdjustHandle guide) {
        return new XDDFAdjustHandleXY(guide);
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
            return Collections.unmodifiableList((List) this.geometry.getAvLst().getGdList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.XDDFCustomGeometry2D$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XDDFCustomGeometry2D.lambda$getAdjustValues$2((CTGeomGuide) obj);
                }
            }).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFGeometryGuide lambda$getAdjustValues$2(CTGeomGuide guide) {
        return new XDDFGeometryGuide(guide);
    }

    public XDDFConnectionSite addConnectionSite() {
        if (!this.geometry.isSetCxnLst()) {
            this.geometry.addNewCxnLst();
        }
        return new XDDFConnectionSite(this.geometry.getCxnLst().addNewCxn());
    }

    public XDDFConnectionSite insertConnectionSite(int index) {
        if (!this.geometry.isSetCxnLst()) {
            this.geometry.addNewCxnLst();
        }
        return new XDDFConnectionSite(this.geometry.getCxnLst().insertNewCxn(index));
    }

    public void removeConnectionSite(int index) {
        if (this.geometry.isSetCxnLst()) {
            this.geometry.getCxnLst().removeCxn(index);
        }
    }

    public XDDFConnectionSite getConnectionSite(int index) {
        if (this.geometry.isSetCxnLst()) {
            return new XDDFConnectionSite(this.geometry.getCxnLst().getCxnArray(index));
        }
        return null;
    }

    public List<XDDFConnectionSite> getConnectionSites() {
        if (this.geometry.isSetCxnLst()) {
            return Collections.unmodifiableList((List) this.geometry.getCxnLst().getCxnList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.XDDFCustomGeometry2D$$ExternalSyntheticLambda4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XDDFCustomGeometry2D.lambda$getConnectionSites$3((CTConnectionSite) obj);
                }
            }).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFConnectionSite lambda$getConnectionSites$3(CTConnectionSite guide) {
        return new XDDFConnectionSite(guide);
    }

    public XDDFGeometryGuide addGuide() {
        if (!this.geometry.isSetGdLst()) {
            this.geometry.addNewGdLst();
        }
        return new XDDFGeometryGuide(this.geometry.getGdLst().addNewGd());
    }

    public XDDFGeometryGuide insertGuide(int index) {
        if (!this.geometry.isSetGdLst()) {
            this.geometry.addNewGdLst();
        }
        return new XDDFGeometryGuide(this.geometry.getGdLst().insertNewGd(index));
    }

    public void removeGuide(int index) {
        if (this.geometry.isSetGdLst()) {
            this.geometry.getGdLst().removeGd(index);
        }
    }

    public XDDFGeometryGuide getGuide(int index) {
        if (this.geometry.isSetGdLst()) {
            return new XDDFGeometryGuide(this.geometry.getGdLst().getGdArray(index));
        }
        return null;
    }

    public List<XDDFGeometryGuide> getGuides() {
        if (this.geometry.isSetGdLst()) {
            return Collections.unmodifiableList((List) this.geometry.getGdLst().getGdList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.XDDFCustomGeometry2D$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return XDDFCustomGeometry2D.lambda$getGuides$4((CTGeomGuide) obj);
                }
            }).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFGeometryGuide lambda$getGuides$4(CTGeomGuide guide) {
        return new XDDFGeometryGuide(guide);
    }

    public XDDFPath addNewPath() {
        return new XDDFPath(this.geometry.getPathLst().addNewPath());
    }

    public XDDFPath insertNewPath(int index) {
        return new XDDFPath(this.geometry.getPathLst().insertNewPath(index));
    }

    public void removePath(int index) {
        this.geometry.getPathLst().removePath(index);
    }

    public XDDFPath getPath(int index) {
        return new XDDFPath(this.geometry.getPathLst().getPathArray(index));
    }

    public List<XDDFPath> getPaths() {
        return Collections.unmodifiableList((List) this.geometry.getPathLst().getPathList().stream().map(new Function() { // from class: org.apache.poi.xddf.usermodel.XDDFCustomGeometry2D$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XDDFCustomGeometry2D.lambda$getPaths$5((CTPath2D) obj);
            }
        }).collect(Collectors.toList()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XDDFPath lambda$getPaths$5(CTPath2D ds) {
        return new XDDFPath(ds);
    }
}
