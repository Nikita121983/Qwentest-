package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.section.geometry.ArcTo;
import org.apache.poi.xdgf.usermodel.section.geometry.Ellipse;
import org.apache.poi.xdgf.usermodel.section.geometry.EllipticalArcTo;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;
import org.apache.poi.xdgf.usermodel.section.geometry.InfiniteLine;
import org.apache.poi.xdgf.usermodel.section.geometry.LineTo;
import org.apache.poi.xdgf.usermodel.section.geometry.MoveTo;
import org.apache.poi.xdgf.usermodel.section.geometry.NURBSTo;
import org.apache.poi.xdgf.usermodel.section.geometry.PolyLineTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelCubBezTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelEllipticalArcTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelLineTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelMoveTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelQuadBezTo;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineKnot;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineStart;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public enum GeometryRowTypes {
    ARC_TO("ArcTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda0
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new ArcTo((RowType) obj);
        }
    }),
    ELLIPSE("Ellipse", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda13
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new Ellipse((RowType) obj);
        }
    }),
    ELLIPTICAL_ARC_TO("EllipticalArcTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda14
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new EllipticalArcTo((RowType) obj);
        }
    }),
    INFINITE_LINE("InfiniteLine", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda15
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new InfiniteLine((RowType) obj);
        }
    }),
    LINE_TO("LineTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda1
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new LineTo((RowType) obj);
        }
    }),
    MOVE_TO("MoveTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda2
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new MoveTo((RowType) obj);
        }
    }),
    NURBS_TO("NURBSTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda3
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new NURBSTo((RowType) obj);
        }
    }),
    POLYLINE_TO("PolylineTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda4
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new PolyLineTo((RowType) obj);
        }
    }),
    REL_CUB_BEZ_TO("RelCubBezTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda5
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new RelCubBezTo((RowType) obj);
        }
    }),
    REL_ELLIPTICAL_ARC_TO("RelEllipticalArcTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda6
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new RelEllipticalArcTo((RowType) obj);
        }
    }),
    REL_LINE_TO("RelLineTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda7
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new RelLineTo((RowType) obj);
        }
    }),
    REL_MOVE_TO("RelMoveTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda8
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new RelMoveTo((RowType) obj);
        }
    }),
    REL_QUAD_BEZ_TO("RelQuadBezTo", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda9
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new RelQuadBezTo((RowType) obj);
        }
    }),
    SPLINE_KNOT("SplineKnot", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda10
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new SplineKnot((RowType) obj);
        }
    }),
    SPLINE_START("SplineStart", new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda11
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return new SplineStart((RowType) obj);
        }
    });

    private static final Map<String, GeometryRowTypes> LOOKUP = Collections.unmodifiableMap((Map) Stream.of((Object[]) values()).collect(Collectors.toMap(new Function() { // from class: org.apache.poi.xdgf.usermodel.section.GeometryRowTypes$$ExternalSyntheticLambda12
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((GeometryRowTypes) obj).getRowType();
        }
    }, Function.identity())));
    private final Function<RowType, ? extends GeometryRow> constructor;
    private final String rowType;

    GeometryRowTypes(String rowType, Function function) {
        this.rowType = rowType;
        this.constructor = function;
    }

    public String getRowType() {
        return this.rowType;
    }

    public static GeometryRow load(RowType row) {
        String name = row.getT();
        GeometryRowTypes l = LOOKUP.get(name);
        if (l == null) {
            String typeName = row.schemaType().getName().getLocalPart();
            throw new POIXMLException("Invalid '" + typeName + "' name '" + name + "'");
        }
        return l.constructor.apply(row);
    }
}
