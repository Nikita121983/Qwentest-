package org.apache.poi.sl.draw.geom;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.util.Internal;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public class PresetParser {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) PresetParser.class);
    private CustomGeometry customGeometry;
    private final Map<String, CustomGeometry> geom = new HashMap();
    private Mode mode;
    private Path path;
    private boolean useAdjustValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public enum Mode {
        FILE(new Handler() { // from class: org.apache.poi.sl.draw.geom.PresetParser$Mode$$ExternalSyntheticLambda0
            @Override // org.apache.poi.sl.draw.geom.PresetParser.Mode.Handler
            public final void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) {
                presetParser.updateFile(xMLStreamReader);
            }
        }),
        SHAPE_LST(new Handler() { // from class: org.apache.poi.sl.draw.geom.PresetParser$Mode$$ExternalSyntheticLambda1
            @Override // org.apache.poi.sl.draw.geom.PresetParser.Mode.Handler
            public final void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) {
                presetParser.updateShapeList(xMLStreamReader);
            }
        }),
        SHAPE(new Handler() { // from class: org.apache.poi.sl.draw.geom.PresetParser$Mode$$ExternalSyntheticLambda2
            @Override // org.apache.poi.sl.draw.geom.PresetParser.Mode.Handler
            public final void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) {
                presetParser.updateShape(xMLStreamReader);
            }
        }),
        GUIDE_LST(new Handler() { // from class: org.apache.poi.sl.draw.geom.PresetParser$Mode$$ExternalSyntheticLambda3
            @Override // org.apache.poi.sl.draw.geom.PresetParser.Mode.Handler
            public final void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) {
                presetParser.updateGuideList(xMLStreamReader);
            }
        }),
        AH_LST(new Handler() { // from class: org.apache.poi.sl.draw.geom.PresetParser$Mode$$ExternalSyntheticLambda4
            @Override // org.apache.poi.sl.draw.geom.PresetParser.Mode.Handler
            public final void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) {
                presetParser.updateAhList(xMLStreamReader);
            }
        }),
        CXN_LST(new Handler() { // from class: org.apache.poi.sl.draw.geom.PresetParser$Mode$$ExternalSyntheticLambda5
            @Override // org.apache.poi.sl.draw.geom.PresetParser.Mode.Handler
            public final void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) {
                presetParser.updateCxnList(xMLStreamReader);
            }
        }),
        PATH_LST(new Handler() { // from class: org.apache.poi.sl.draw.geom.PresetParser$Mode$$ExternalSyntheticLambda6
            @Override // org.apache.poi.sl.draw.geom.PresetParser.Mode.Handler
            public final void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) {
                presetParser.updatePathLst(xMLStreamReader);
            }
        }),
        PATH(new Handler() { // from class: org.apache.poi.sl.draw.geom.PresetParser$Mode$$ExternalSyntheticLambda7
            @Override // org.apache.poi.sl.draw.geom.PresetParser.Mode.Handler
            public final void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) {
                presetParser.updatePath(xMLStreamReader);
            }
        });

        final Handler handler;

        /* loaded from: classes10.dex */
        interface Handler {
            void update(PresetParser presetParser, XMLStreamReader xMLStreamReader) throws XMLStreamException;
        }

        Mode(Handler handler) {
            this.handler = handler;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PresetParser(Mode mode) {
        this.mode = mode;
        if (mode == Mode.SHAPE) {
            this.customGeometry = new CustomGeometry();
            this.geom.put("custom", this.customGeometry);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void parse(XMLStreamReader sr) throws XMLStreamException {
        while (sr.hasNext()) {
            switch (sr.next()) {
                case 1:
                    this.mode.handler.update(this, sr);
                    break;
                case 2:
                    endContext();
                    break;
                case 8:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, CustomGeometry> getGeom() {
        return this.geom;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFile(XMLStreamReader sr) {
        String name = sr.getLocalName();
        if (!"presetShapeDefinitons".equals(name)) {
            throw new AssertionError();
        }
        this.mode = Mode.SHAPE_LST;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShapeList(XMLStreamReader sr) {
        String name = sr.getLocalName();
        this.customGeometry = new CustomGeometry();
        if (this.geom.containsKey(name)) {
            LOG.atWarn().log("Duplicate definition of {}", name);
        }
        this.geom.put(name, this.customGeometry);
        this.mode = Mode.SHAPE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void updateShape(XMLStreamReader sr) throws XMLStreamException {
        char c;
        String name = sr.getLocalName();
        switch (name.hashCode()) {
            case -1346505100:
                if (name.equals("cxnLst")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -791471768:
                if (name.equals("pathLst")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3496420:
                if (name.equals("rect")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 92756518:
                if (name.equals("ahLst")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 93173592:
                if (name.equals("avLst")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 98178480:
                if (name.equals("gdLst")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.useAdjustValue = true;
                this.mode = Mode.GUIDE_LST;
                return;
            case 1:
                this.useAdjustValue = false;
                this.mode = Mode.GUIDE_LST;
                return;
            case 2:
                this.mode = Mode.AH_LST;
                return;
            case 3:
                this.mode = Mode.CXN_LST;
                return;
            case 4:
                addRectangle(sr);
                return;
            case 5:
                this.mode = Mode.PATH_LST;
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGuideList(XMLStreamReader sr) throws XMLStreamException {
        final Guide gd;
        String name = sr.getLocalName();
        if (!"gd".equals(name)) {
            throw new AssertionError();
        }
        if (this.useAdjustValue) {
            CustomGeometry customGeometry = this.customGeometry;
            AdjustValue adjustValue = new AdjustValue();
            gd = adjustValue;
            customGeometry.addAdjustGuide(adjustValue);
        } else {
            CustomGeometry customGeometry2 = this.customGeometry;
            Guide guide = new Guide();
            gd = guide;
            customGeometry2.addGeomGuide(guide);
        }
        parseAttributes(sr, new BiConsumer() { // from class: org.apache.poi.sl.draw.geom.PresetParser$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PresetParser.lambda$updateGuideList$0(Guide.this, (String) obj, (String) obj2);
            }
        });
        int tag = nextTag(sr);
        if (tag != 2) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$updateGuideList$0(Guide gd, String key, String val) {
        char c;
        switch (key.hashCode()) {
            case 3146876:
                if (key.equals("fmla")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3373707:
                if (key.equals("name")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                gd.setName(collapseString(val));
                return;
            case 1:
                gd.setFmla(val);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void updateAhList(XMLStreamReader sr) throws XMLStreamException {
        char c;
        String name = sr.getLocalName();
        switch (name.hashCode()) {
            case -1051729065:
                if (name.equals("ahPolar")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2992488:
                if (name.equals("ahXY")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                addXY(sr);
                return;
            case 1:
                addPolar(sr);
                return;
            default:
                return;
        }
    }

    private void addXY(XMLStreamReader sr) throws XMLStreamException {
        final XYAdjustHandle ahXY = new XYAdjustHandle();
        this.customGeometry.addAdjustHandle(ahXY);
        parseAttributes(sr, new BiConsumer() { // from class: org.apache.poi.sl.draw.geom.PresetParser$$ExternalSyntheticLambda6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PresetParser.lambda$addXY$1(XYAdjustHandle.this, (String) obj, (String) obj2);
            }
        });
        ahXY.setPos(parsePosPoint(sr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$addXY$1(XYAdjustHandle ahXY, String key, String val) {
        char c;
        switch (key.hashCode()) {
            case -1251269470:
                if (key.equals("gdRefX")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1251269469:
                if (key.equals("gdRefY")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3344244:
                if (key.equals("maxX")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3344245:
                if (key.equals("maxY")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3351622:
                if (key.equals("minX")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3351623:
                if (key.equals("minY")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ahXY.setGdRefX(collapseString(val));
                return;
            case 1:
                ahXY.setMinX(val);
                return;
            case 2:
                ahXY.setMaxX(val);
                return;
            case 3:
                ahXY.setGdRefY(collapseString(val));
                return;
            case 4:
                ahXY.setMinY(val);
                return;
            case 5:
                ahXY.setMaxY(val);
                return;
            default:
                return;
        }
    }

    private void addPolar(XMLStreamReader sr) throws XMLStreamException {
        final PolarAdjustHandle ahPolar = new PolarAdjustHandle();
        this.customGeometry.addAdjustHandle(ahPolar);
        parseAttributes(sr, new BiConsumer() { // from class: org.apache.poi.sl.draw.geom.PresetParser$$ExternalSyntheticLambda2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PresetParser.lambda$addPolar$2(PolarAdjustHandle.this, (String) obj, (String) obj2);
            }
        });
        ahPolar.setPos(parsePosPoint(sr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$addPolar$2(PolarAdjustHandle ahPolar, String key, String val) {
        char c;
        switch (key.hashCode()) {
            case -1251269476:
                if (key.equals("gdRefR")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1081167402:
                if (key.equals("maxAng")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1074077144:
                if (key.equals("minAng")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3344238:
                if (key.equals("maxR")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3351616:
                if (key.equals("minR")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 120863620:
                if (key.equals("gdRefAng")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ahPolar.setGdRefR(collapseString(val));
                return;
            case 1:
                ahPolar.setMinR(val);
                return;
            case 2:
                ahPolar.setMaxR(val);
                return;
            case 3:
                ahPolar.setGdRefAng(collapseString(val));
                return;
            case 4:
                ahPolar.setMinAng(val);
                return;
            case 5:
                ahPolar.setMaxAng(val);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCxnList(XMLStreamReader sr) throws XMLStreamException {
        String name = sr.getLocalName();
        if (!"cxn".equals(name)) {
            throw new AssertionError();
        }
        final ConnectionSite cxn = new ConnectionSite();
        this.customGeometry.addConnectionSite(cxn);
        parseAttributes(sr, new BiConsumer() { // from class: org.apache.poi.sl.draw.geom.PresetParser$$ExternalSyntheticLambda5
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PresetParser.lambda$updateCxnList$3(ConnectionSite.this, (String) obj, (String) obj2);
            }
        });
        cxn.setPos(parsePosPoint(sr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateCxnList$3(ConnectionSite cxn, String key, String val) {
        if ("ang".equals(key)) {
            cxn.setAng(val);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePathLst(XMLStreamReader sr) {
        String name = sr.getLocalName();
        if (!"path".equals(name)) {
            throw new AssertionError();
        }
        this.path = new Path();
        this.customGeometry.addPath(this.path);
        parseAttributes(sr, new BiConsumer() { // from class: org.apache.poi.sl.draw.geom.PresetParser$$ExternalSyntheticLambda7
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PresetParser.this.m2512lambda$updatePathLst$4$orgapachepoisldrawgeomPresetParser((String) obj, (String) obj2);
            }
        });
        this.mode = Mode.PATH;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: lambda$updatePathLst$4$org-apache-poi-sl-draw-geom-PresetParser, reason: not valid java name */
    public /* synthetic */ void m2512lambda$updatePathLst$4$orgapachepoisldrawgeomPresetParser(String key, String val) {
        char c;
        switch (key.hashCode()) {
            case -1453197163:
                if (key.equals("extrusionOk")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -891980232:
                if (key.equals("stroke")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 104:
                if (key.equals("h")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 119:
                if (key.equals("w")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3143043:
                if (key.equals("fill")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.path.setW(Long.parseLong(val));
                return;
            case 1:
                this.path.setH(Long.parseLong(val));
                return;
            case 2:
                this.path.setFill(mapFill(val));
                return;
            case 3:
                this.path.setStroke(Boolean.parseBoolean(val));
                return;
            case 4:
                this.path.setExtrusionOk(Boolean.parseBoolean(val));
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static PaintStyle.PaintModifier mapFill(String fill) {
        char c;
        switch (fill.hashCode()) {
            case -2005126536:
                if (fill.equals("lightenLess")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1414880040:
                if (fill.equals("darkenLess")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1338968417:
                if (fill.equals("darken")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3387192:
                if (fill.equals("none")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3387324:
                if (fill.equals("norm")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 170546239:
                if (fill.equals("lighten")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 2:
                return PaintStyle.PaintModifier.NORM;
            case 3:
                return PaintStyle.PaintModifier.LIGHTEN;
            case 4:
                return PaintStyle.PaintModifier.LIGHTEN_LESS;
            case 5:
                return PaintStyle.PaintModifier.DARKEN;
            case 6:
                return PaintStyle.PaintModifier.DARKEN_LESS;
            default:
                return PaintStyle.PaintModifier.NONE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void updatePath(XMLStreamReader sr) throws XMLStreamException {
        char c;
        String name = sr.getLocalName();
        switch (name.hashCode()) {
            case -1513004949:
                if (name.equals("quadBezTo")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1068263892:
                if (name.equals("moveTo")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3325853:
                if (name.equals("lnTo")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 93075565:
                if (name.equals("arcTo")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 94756344:
                if (name.equals("close")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1594918984:
                if (name.equals("cubicBezTo")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                closePath(sr);
                return;
            case 1:
                moveTo(sr);
                return;
            case 2:
                lineTo(sr);
                return;
            case 3:
                arcTo(sr);
                return;
            case 4:
                quadBezTo(sr);
                return;
            case 5:
                cubicBezTo(sr);
                return;
            default:
                return;
        }
    }

    private void closePath(XMLStreamReader sr) throws XMLStreamException {
        this.path.addCommand(new ClosePathCommand());
        int tag = nextTag(sr);
        if (tag != 2) {
            throw new AssertionError();
        }
    }

    private void moveTo(XMLStreamReader sr) throws XMLStreamException {
        MoveToCommand cmd = new MoveToCommand();
        this.path.addCommand(cmd);
        AdjustPoint pt = parsePtPoint(sr, true);
        if (pt == null) {
            throw new AssertionError();
        }
        cmd.setPt(pt);
    }

    private void lineTo(XMLStreamReader sr) throws XMLStreamException {
        LineToCommand cmd = new LineToCommand();
        this.path.addCommand(cmd);
        AdjustPoint pt = parsePtPoint(sr, true);
        if (pt == null) {
            throw new AssertionError();
        }
        cmd.setPt(pt);
    }

    private void arcTo(XMLStreamReader sr) throws XMLStreamException {
        final ArcToCommand cmd = new ArcToCommand();
        this.path.addCommand(cmd);
        parseAttributes(sr, new BiConsumer() { // from class: org.apache.poi.sl.draw.geom.PresetParser$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PresetParser.lambda$arcTo$5(ArcToCommand.this, (String) obj, (String) obj2);
            }
        });
        int tag = nextTag(sr);
        if (tag != 2) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$arcTo$5(ArcToCommand cmd, String key, String val) {
        char c;
        switch (key.hashCode()) {
            case 3306:
                if (key.equals("hR")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3771:
                if (key.equals("wR")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 109726649:
                if (key.equals("stAng")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 109816022:
                if (key.equals("swAng")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                cmd.setWR(val);
                return;
            case 1:
                cmd.setHR(val);
                return;
            case 2:
                cmd.setStAng(val);
                return;
            case 3:
                cmd.setSwAng(val);
                return;
            default:
                return;
        }
    }

    private void quadBezTo(XMLStreamReader sr) throws XMLStreamException {
        QuadToCommand cmd = new QuadToCommand();
        this.path.addCommand(cmd);
        AdjustPoint pt1 = parsePtPoint(sr, false);
        AdjustPoint pt2 = parsePtPoint(sr, true);
        if (pt1 == null || pt2 == null) {
            throw new AssertionError();
        }
        cmd.setPt1(pt1);
        cmd.setPt2(pt2);
    }

    private void cubicBezTo(XMLStreamReader sr) throws XMLStreamException {
        CurveToCommand cmd = new CurveToCommand();
        this.path.addCommand(cmd);
        AdjustPoint pt1 = parsePtPoint(sr, false);
        AdjustPoint pt2 = parsePtPoint(sr, false);
        AdjustPoint pt3 = parsePtPoint(sr, true);
        if (pt1 == null || pt2 == null || pt3 == null) {
            throw new AssertionError();
        }
        cmd.setPt1(pt1);
        cmd.setPt2(pt2);
        cmd.setPt3(pt3);
    }

    private void addRectangle(XMLStreamReader sr) throws XMLStreamException {
        final String[] ltrb = new String[4];
        parseAttributes(sr, new BiConsumer() { // from class: org.apache.poi.sl.draw.geom.PresetParser$$ExternalSyntheticLambda4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PresetParser.lambda$addRectangle$6(ltrb, (String) obj, (String) obj2);
            }
        });
        this.customGeometry.setTextBounds(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);
        int tag = nextTag(sr);
        if (tag != 2) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$addRectangle$6(String[] ltrb, String key, String val) {
        char c;
        switch (key.hashCode()) {
            case 98:
                if (key.equals("b")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 108:
                if (key.equals("l")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 114:
                if (key.equals("r")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 116:
                if (key.equals("t")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ltrb[0] = val;
                return;
            case 1:
                ltrb[1] = val;
                return;
            case 2:
                ltrb[2] = val;
                return;
            case 3:
                ltrb[3] = val;
                return;
            default:
                return;
        }
    }

    private void endContext() {
        switch (this.mode) {
            case FILE:
            case SHAPE_LST:
                this.mode = Mode.FILE;
                return;
            case SHAPE:
                this.mode = Mode.SHAPE_LST;
                return;
            case CXN_LST:
            case AH_LST:
            case GUIDE_LST:
            case PATH_LST:
                this.useAdjustValue = false;
                this.path = null;
                this.mode = Mode.SHAPE;
                return;
            case PATH:
                this.path = null;
                this.mode = Mode.PATH_LST;
                return;
            default:
                return;
        }
    }

    private AdjustPoint parsePosPoint(XMLStreamReader sr) throws XMLStreamException {
        return parseAdjPoint(sr, true, "pos");
    }

    private AdjustPoint parsePtPoint(XMLStreamReader sr, boolean closeOuter) throws XMLStreamException {
        return parseAdjPoint(sr, closeOuter, "pt");
    }

    private AdjustPoint parseAdjPoint(XMLStreamReader sr, boolean closeOuter, String name) throws XMLStreamException {
        int tag = nextTag(sr);
        if (tag == 2) {
            return null;
        }
        if (!name.equals(sr.getLocalName())) {
            throw new AssertionError();
        }
        final AdjustPoint pos = new AdjustPoint();
        parseAttributes(sr, new BiConsumer() { // from class: org.apache.poi.sl.draw.geom.PresetParser$$ExternalSyntheticLambda3
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PresetParser.lambda$parseAdjPoint$7(AdjustPoint.this, (String) obj, (String) obj2);
            }
        });
        int tag2 = nextTag(sr);
        if (tag2 != 2) {
            throw new AssertionError();
        }
        if (closeOuter) {
            int tag3 = nextTag(sr);
            if (tag3 != 2) {
                throw new AssertionError();
            }
        }
        return pos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$parseAdjPoint$7(AdjustPoint pos, String key, String val) {
        char c;
        switch (key.hashCode()) {
            case 120:
                if (key.equals("x")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 121:
                if (key.equals("y")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                pos.setX(val);
                return;
            case 1:
                pos.setY(val);
                return;
            default:
                return;
        }
    }

    private void parseAttributes(XMLStreamReader sr, BiConsumer<String, String> c) {
        for (int i = 0; i < sr.getAttributeCount(); i++) {
            c.accept(sr.getAttributeLocalName(i), sr.getAttributeValue(i));
        }
    }

    private static int nextTag(XMLStreamReader sr) throws XMLStreamException {
        int tag;
        do {
            tag = sr.next();
            if (tag == 1 || tag == 2) {
                break;
            }
        } while (tag != 8);
        return tag;
    }

    private static String collapseString(String text) {
        if (text == null) {
            return null;
        }
        int len = text.length();
        int s = 0;
        while (s < len && !isWhiteSpace(text.charAt(s))) {
            s++;
        }
        if (s == len) {
            return text;
        }
        StringBuilder result = new StringBuilder(len);
        if (s != 0) {
            for (int i = 0; i < s; i++) {
                result.append(text.charAt(i));
            }
            result.append(Chars.SPACE);
        }
        boolean inStripMode = true;
        for (int i2 = s + 1; i2 < len; i2++) {
            char ch = text.charAt(i2);
            boolean b = isWhiteSpace(ch);
            if (!inStripMode || !b) {
                inStripMode = b;
                result.append(inStripMode ? ' ' : ch);
            }
        }
        int len2 = result.length();
        if (len2 > 0 && result.charAt(len2 - 1) == ' ') {
            result.setLength(len2 - 1);
        }
        return result.toString();
    }

    private static boolean isWhiteSpace(char ch) {
        return ch == '\t' || ch == '\n' || ch == '\r' || ch == ' ';
    }
}
