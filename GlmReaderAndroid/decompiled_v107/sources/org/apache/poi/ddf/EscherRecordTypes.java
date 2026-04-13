package org.apache.poi.ddf;

import androidx.core.os.EnvironmentCompat;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* loaded from: classes10.dex */
public enum EscherRecordTypes {
    DGG_CONTAINER(61440, "DggContainer", null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    BSTORE_CONTAINER(61441, "BStoreContainer", null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    DG_CONTAINER(61442, "DgContainer", null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    SPGR_CONTAINER(61443, "SpgrContainer", null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    SP_CONTAINER(61444, "SpContainer", null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    SOLVER_CONTAINER(61445, "SolverContainer", null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    DGG(61446, "Dgg", "MsofbtDgg", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda2
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherDggRecord();
        }
    }),
    BSE(61447, "BSE", "MsofbtBSE", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda3
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherBSERecord();
        }
    }),
    DG(61448, "Dg", "MsofbtDg", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda4
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherDgRecord();
        }
    }),
    SPGR(61449, "Spgr", "MsofbtSpgr", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda5
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherSpgrRecord();
        }
    }),
    SP(61450, "Sp", "MsofbtSp", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda6
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherSpRecord();
        }
    }),
    OPT(61451, "Opt", "msofbtOPT", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda7
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherOptRecord();
        }
    }),
    TEXTBOX(61452, null, null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda8
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherTextboxRecord();
        }
    }),
    CLIENT_TEXTBOX(61453, "ClientTextbox", "msofbtClientTextbox", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda8
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherTextboxRecord();
        }
    }),
    ANCHOR(61454, null, null, null),
    CHILD_ANCHOR(61455, "ChildAnchor", "MsofbtChildAnchor", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda9
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherChildAnchorRecord();
        }
    }),
    CLIENT_ANCHOR(61456, "ClientAnchor", "MsofbtClientAnchor", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda10
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherClientAnchorRecord();
        }
    }),
    CLIENT_DATA(61457, "ClientData", "MsofbtClientData", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda11
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherClientDataRecord();
        }
    }),
    CONNECTOR_RULE(61458, null, null, null),
    ALIGN_RULE(61459, null, null, null),
    ARC_RULE(61460, null, null, null),
    CLIENT_RULE(61461, null, null, null),
    CLSID(61462, null, null, null),
    CALLOUT_RULE(61463, null, null, null),
    BLIP_START(61464, "Blip", "msofbtBlip", null),
    BLIP_EMF(61466, "BlipEmf", null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda12
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherMetafileBlip();
        }
    }),
    BLIP_WMF(61467, "BlipWmf", null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda12
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherMetafileBlip();
        }
    }),
    BLIP_PICT(61468, "BlipPict", null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda12
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherMetafileBlip();
        }
    }),
    BLIP_JPEG(61469, "BlipJpeg", null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda13
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherBitmapBlip();
        }
    }),
    BLIP_PNG(61470, "BlipPng", null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda13
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherBitmapBlip();
        }
    }),
    BLIP_DIB(61471, "BlipDib", null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda13
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherBitmapBlip();
        }
    }),
    BLIP_TIFF(61481, "BlipTiff", null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda13
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherBitmapBlip();
        }
    }),
    BLIP_END(61719, "Blip", "msofbtBlip", null),
    REGROUP_ITEMS(61720, null, null, null),
    SELECTION(61721, null, null, null),
    COLOR_MRU(61722, null, null, null),
    DELETED_PSPL(61725, null, null, null),
    SPLIT_MENU_COLORS(61726, "SplitMenuColors", "MsofbtSplitMenuColors", new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda14
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherSplitMenuColorsRecord();
        }
    }),
    OLE_OBJECT(61727, null, null, null),
    COLOR_SCHEME(61728, null, null, null),
    USER_DEFINED(61730, "TertiaryOpt", null, new Supplier() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return new EscherTertiaryOptRecord();
        }
    }),
    UNKNOWN(65535, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, new DefaultEscherRecordFactory$$ExternalSyntheticLambda2());

    private static final Map<Short, EscherRecordTypes> LOOKUP = Collections.unmodifiableMap((Map) Stream.of((Object[]) values()).collect(Collectors.toMap(new Function() { // from class: org.apache.poi.ddf.EscherRecordTypes$$ExternalSyntheticLambda1
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            Short typeId;
            typeId = ((EscherRecordTypes) obj).getTypeId();
            return typeId;
        }
    }, Function.identity())));
    public final Supplier<? extends EscherRecord> constructor;
    public final String description;
    public final String recordName;
    public final short typeID;

    EscherRecordTypes(int typeID, String recordName, String description, Supplier supplier) {
        this.typeID = (short) typeID;
        this.recordName = recordName;
        this.description = description;
        this.constructor = supplier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Short getTypeId() {
        return Short.valueOf(this.typeID);
    }

    public static EscherRecordTypes forTypeID(int typeID) {
        if (typeID == 61482) {
            return BLIP_JPEG;
        }
        EscherRecordTypes rt = LOOKUP.get(Short.valueOf((short) typeID));
        return rt != null ? rt : UNKNOWN;
    }
}
