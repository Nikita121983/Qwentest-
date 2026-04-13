package org.apache.poi.common.usermodel.fonts;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianInputStream;

/* loaded from: classes10.dex */
public class FontHeader implements FontInfo, GenericRecord {
    private static final int[] FLAGS_MASKS = {1, 4, 16, 32, 64, 128, 268435456};
    private static final String[] FLAGS_NAMES = {"SUBSET", "TTCOMPRESSED", "FAILIFVARIATIONSIMULATED", "EMBEDEUDC", "VALIDATIONTESTS", "WEBOBJECT", "XORENCRYPTDATA"};
    private static final int[] FSTYPE_MASKS = {0, 2, 4, 8, 256, 512};
    private static final String[] FSTYPE_NAMES = {"INSTALLABLE_EMBEDDING", "RESTRICTED_LICENSE_EMBEDDING", "PREVIEW_PRINT_EMBEDDING", "EDITABLE_EMBEDDING", "NO_SUBSETTING", "BITMAP_EMBEDDING_ONLY"};
    public static final int REGULAR_WEIGHT = 400;
    private byte charset;
    private int checkSumAdjustment;
    private int codePageRange1;
    private int codePageRange2;
    private int eotSize;
    private String familyName;
    private int flags;
    private int fontDataSize;
    private int fsType;
    private String fullName;
    private byte italic;
    private int magic;
    private final byte[] panose = new byte[10];
    private String styleName;
    private int unicodeRange1;
    private int unicodeRange2;
    private int unicodeRange3;
    private int unicodeRange4;
    private int version;
    private String versionName;
    private int weight;

    /* loaded from: classes10.dex */
    public enum PanoseArmStyle {
        ANY,
        NO_FIT,
        STRAIGHT_ARMS_HORZ,
        STRAIGHT_ARMS_WEDGE,
        STRAIGHT_ARMS_VERT,
        STRAIGHT_ARMS_SINGLE_SERIF,
        STRAIGHT_ARMS_DOUBLE_SERIF,
        BENT_ARMS_HORZ,
        BENT_ARMS_WEDGE,
        BENT_ARMS_VERT,
        BENT_ARMS_SINGLE_SERIF,
        BENT_ARMS_DOUBLE_SERIF
    }

    /* loaded from: classes10.dex */
    public enum PanoseContrast {
        ANY,
        NO_FIT,
        NONE,
        VERY_LOW,
        LOW,
        MEDIUM_LOW,
        MEDIUM,
        MEDIUM_HIGH,
        HIGH,
        VERY_HIGH
    }

    /* loaded from: classes10.dex */
    public enum PanoseFamily {
        ANY,
        NO_FIT,
        TEXT_DISPLAY,
        SCRIPT,
        DECORATIVE,
        PICTORIAL
    }

    /* loaded from: classes10.dex */
    public enum PanoseLetterForm {
        ANY,
        NO_FIT,
        NORMAL_CONTACT,
        NORMAL_WEIGHTED,
        NORMAL_BOXED,
        NORMAL_FLATTENED,
        NORMAL_ROUNDED,
        NORMAL_OFF_CENTER,
        NORMAL_SQUARE,
        OBLIQUE_CONTACT,
        OBLIQUE_WEIGHTED,
        OBLIQUE_BOXED,
        OBLIQUE_FLATTENED,
        OBLIQUE_ROUNDED,
        OBLIQUE_OFF_CENTER,
        OBLIQUE_SQUARE
    }

    /* loaded from: classes10.dex */
    public enum PanoseMidLine {
        ANY,
        NO_FIT,
        STANDARD_TRIMMED,
        STANDARD_POINTED,
        STANDARD_SERIFED,
        HIGH_TRIMMED,
        HIGH_POINTED,
        HIGH_SERIFED,
        CONSTANT_TRIMMED,
        CONSTANT_POINTED,
        CONSTANT_SERIFED,
        LOW_TRIMMED,
        LOW_POINTED,
        LOW_SERIFED
    }

    /* loaded from: classes10.dex */
    public enum PanoseProportion {
        ANY,
        NO_FIT,
        OLD_STYLE,
        MODERN,
        EVEN_WIDTH,
        EXPANDED,
        CONDENSED,
        VERY_EXPANDED,
        VERY_CONDENSED,
        MONOSPACED
    }

    /* loaded from: classes10.dex */
    public enum PanoseSerif {
        ANY,
        NO_FIT,
        COVE,
        OBTUSE_COVE,
        SQUARE_COVE,
        OBTUSE_SQUARE_COVE,
        SQUARE,
        THIN,
        BONE,
        EXAGGERATED,
        TRIANGLE,
        NORMAL_SANS,
        OBTUSE_SANS,
        PERP_SANS,
        FLARED,
        ROUNDED
    }

    /* loaded from: classes10.dex */
    public enum PanoseStroke {
        ANY,
        NO_FIT,
        GRADUAL_DIAG,
        GRADUAL_TRAN,
        GRADUAL_VERT,
        GRADUAL_HORZ,
        RAPID_VERT,
        RAPID_HORZ,
        INSTANT_VERT
    }

    /* loaded from: classes10.dex */
    public enum PanoseWeight {
        ANY,
        NO_FIT,
        VERY_LIGHT,
        LIGHT,
        THIN,
        BOOK,
        MEDIUM,
        DEMI,
        BOLD,
        HEAVY,
        BLACK,
        NORD
    }

    /* loaded from: classes10.dex */
    public enum PanoseXHeight {
        ANY,
        NO_FIT,
        CONSTANT_SMALL,
        CONSTANT_STD,
        CONSTANT_LARGE,
        DUCKING_SMALL,
        DUCKING_STD,
        DUCKING_LARGE
    }

    public void init(byte[] source, int offset, int length) {
        init(new LittleEndianByteArrayInputStream(source, offset, length));
    }

    public void init(LittleEndianInput leis) {
        this.eotSize = leis.readInt();
        this.fontDataSize = leis.readInt();
        this.version = leis.readInt();
        if (this.version != 65536 && this.version != 131073 && this.version != 131074) {
            throw new IllegalStateException("not a EOT font data stream");
        }
        this.flags = leis.readInt();
        leis.readFully(this.panose);
        this.charset = leis.readByte();
        this.italic = leis.readByte();
        this.weight = leis.readInt();
        this.fsType = leis.readUShort();
        this.magic = leis.readUShort();
        if (this.magic != 20556) {
            throw new IllegalStateException("not a EOT font data stream");
        }
        this.unicodeRange1 = leis.readInt();
        this.unicodeRange2 = leis.readInt();
        this.unicodeRange3 = leis.readInt();
        this.unicodeRange4 = leis.readInt();
        this.codePageRange1 = leis.readInt();
        this.codePageRange2 = leis.readInt();
        this.checkSumAdjustment = leis.readInt();
        leis.readInt();
        leis.readInt();
        leis.readInt();
        leis.readInt();
        this.familyName = readName(leis);
        this.styleName = readName(leis);
        this.versionName = readName(leis);
        this.fullName = readName(leis);
    }

    public InputStream bufferInit(InputStream fontStream) throws IOException {
        LittleEndianInputStream is = new LittleEndianInputStream(fontStream);
        is.mark(1000);
        init(is);
        is.reset();
        return is;
    }

    private String readName(LittleEndianInput leis) {
        leis.readShort();
        int nameSize = leis.readUShort();
        byte[] nameBuf = IOUtils.safelyAllocate(nameSize, 1000);
        leis.readFully(nameBuf);
        return new String(nameBuf, 0, nameSize, StandardCharsets.UTF_16LE).trim();
    }

    public boolean isItalic() {
        return this.italic != 0;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean isBold() {
        return getWeight() > 400;
    }

    public byte getCharsetByte() {
        return this.charset;
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public FontCharset getCharset() {
        return FontCharset.valueOf(getCharsetByte());
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public FontPitch getPitch() {
        switch (getPanoseFamily()) {
            case TEXT_DISPLAY:
            case DECORATIVE:
                return getPanoseProportion() == PanoseProportion.MONOSPACED ? FontPitch.FIXED : FontPitch.VARIABLE;
            case SCRIPT:
            case PICTORIAL:
                return getPanoseProportion() == PanoseProportion.MODERN ? FontPitch.FIXED : FontPitch.VARIABLE;
            default:
                return FontPitch.VARIABLE;
        }
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public FontFamily getFamily() {
        switch (getPanoseFamily()) {
            case ANY:
            case NO_FIT:
                return FontFamily.FF_DONTCARE;
            case TEXT_DISPLAY:
                switch (getPanoseSerif()) {
                    case TRIANGLE:
                    case NORMAL_SANS:
                    case OBTUSE_SANS:
                    case PERP_SANS:
                    case FLARED:
                    case ROUNDED:
                        return FontFamily.FF_SWISS;
                    default:
                        return FontFamily.FF_ROMAN;
                }
            case DECORATIVE:
            default:
                return FontFamily.FF_DECORATIVE;
            case SCRIPT:
                return FontFamily.FF_SCRIPT;
            case PICTORIAL:
                return FontFamily.FF_MODERN;
        }
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getStyleName() {
        return this.styleName;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String getFullName() {
        return this.fullName;
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public byte[] getPanose() {
        return this.panose;
    }

    @Override // org.apache.poi.common.usermodel.fonts.FontInfo
    public String getTypeface() {
        return getFamilyName();
    }

    public int getFlags() {
        return this.flags;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("eotSize", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2210x33357764();
            }
        });
        m.put("fontDataSize", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda13
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2211x24df1d83();
            }
        });
        m.put("version", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda24
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2213x1688c3a2();
            }
        });
        m.put("flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda25
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(FontHeader.this.getFlags());
            }
        }, FLAGS_MASKS, FLAGS_NAMES));
        m.put("panose.familyType", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda26
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseFamily();
            }
        });
        m.put("panose.serifType", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda27
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseSerif();
            }
        });
        m.put("panose.weight", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda28
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseWeight();
            }
        });
        m.put("panose.proportion", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda29
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseProportion();
            }
        });
        m.put("panose.contrast", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda30
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseContrast();
            }
        });
        m.put("panose.stroke", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda31
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseStroke();
            }
        });
        m.put("panose.armStyle", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseArmStyle();
            }
        });
        m.put("panose.letterForm", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseLetterForm();
            }
        });
        m.put("panose.midLine", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseMidLine();
            }
        });
        m.put("panose.xHeight", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getPanoseXHeight();
            }
        });
        m.put("charset", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getCharset();
            }
        });
        m.put("italic", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(FontHeader.this.isItalic());
            }
        });
        m.put("weight", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(FontHeader.this.getWeight());
            }
        });
        m.put("fsType", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2214x83269c1();
            }
        }, FSTYPE_MASKS, FSTYPE_NAMES));
        m.put("unicodeRange1", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2215xf9dc0fe0();
            }
        });
        m.put("unicodeRange2", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda12
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2216xeb85b5ff();
            }
        });
        m.put("unicodeRange3", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda14
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2217xdd2f5c1e();
            }
        });
        m.put("unicodeRange4", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda15
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2218xced9023d();
            }
        });
        m.put("codePageRange1", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda16
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2219xc082a85c();
            }
        });
        m.put("codePageRange2", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda17
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2220xb22c4e7b();
            }
        });
        m.put("checkSumAdjustment", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda18
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2212xcf0fa18f();
            }
        });
        m.put("familyName", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda19
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getFamilyName();
            }
        });
        m.put("styleName", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda20
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getStyleName();
            }
        });
        m.put("versionName", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda21
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getVersionName();
            }
        });
        m.put("fullName", new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda23
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.getFullName();
            }
        });
        return Collections.unmodifiableMap(m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2210x33357764() {
        return Integer.valueOf(this.eotSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2211x24df1d83() {
        return Integer.valueOf(this.fontDataSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2213x1688c3a2() {
        return Integer.valueOf(this.version);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2214x83269c1() {
        return Integer.valueOf(this.fsType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2215xf9dc0fe0() {
        return Integer.valueOf(this.unicodeRange1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2216xeb85b5ff() {
        return Integer.valueOf(this.unicodeRange2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$6$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2217xdd2f5c1e() {
        return Integer.valueOf(this.unicodeRange3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$7$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2218xced9023d() {
        return Integer.valueOf(this.unicodeRange4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$8$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2219xc082a85c() {
        return Integer.valueOf(this.codePageRange1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$9$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2220xb22c4e7b() {
        return Integer.valueOf(this.codePageRange2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$10$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Object m2212xcf0fa18f() {
        return Integer.valueOf(this.checkSumAdjustment);
    }

    public PanoseFamily getPanoseFamily() {
        return (PanoseFamily) GenericRecordUtil.safeEnum(PanoseFamily.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda37
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2223xf74efe9e();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseFamily$11$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2223xf74efe9e() {
        return Byte.valueOf(this.panose[0]);
    }

    public PanoseSerif getPanoseSerif() {
        return (PanoseSerif) GenericRecordUtil.safeEnum(PanoseSerif.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda35
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2227x2db0f1bc();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseSerif$12$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2227x2db0f1bc() {
        return Byte.valueOf(this.panose[1]);
    }

    public PanoseWeight getPanoseWeight() {
        return (PanoseWeight) GenericRecordUtil.safeEnum(PanoseWeight.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda33
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2229x4a9b8ff0();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseWeight$13$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2229x4a9b8ff0() {
        return Byte.valueOf(this.panose[2]);
    }

    public PanoseProportion getPanoseProportion() {
        return (PanoseProportion) GenericRecordUtil.safeEnum(PanoseProportion.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda38
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2226xf94b84b1();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseProportion$14$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2226xf94b84b1() {
        return Byte.valueOf(this.panose[3]);
    }

    public PanoseContrast getPanoseContrast() {
        return (PanoseContrast) GenericRecordUtil.safeEnum(PanoseContrast.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda32
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2222x372cf078();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseContrast$15$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2222x372cf078() {
        return Byte.valueOf(this.panose[4]);
    }

    public PanoseStroke getPanoseStroke() {
        return (PanoseStroke) GenericRecordUtil.safeEnum(PanoseStroke.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2228x7cfe560d();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseStroke$16$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2228x7cfe560d() {
        return Byte.valueOf(this.panose[5]);
    }

    public PanoseArmStyle getPanoseArmStyle() {
        return (PanoseArmStyle) GenericRecordUtil.safeEnum(PanoseArmStyle.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda34
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2221xc0bb92e9();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseArmStyle$17$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2221xc0bb92e9() {
        return Byte.valueOf(this.panose[6]);
    }

    public PanoseLetterForm getPanoseLetterForm() {
        return (PanoseLetterForm) GenericRecordUtil.safeEnum(PanoseLetterForm.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2224x57f5629d();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseLetterForm$18$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2224x57f5629d() {
        return Byte.valueOf(this.panose[7]);
    }

    public PanoseMidLine getPanoseMidLine() {
        return (PanoseMidLine) GenericRecordUtil.safeEnum(PanoseMidLine.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda22
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2225x1d542334();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseMidLine$19$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2225x1d542334() {
        return Byte.valueOf(this.panose[8]);
    }

    public PanoseXHeight getPanoseXHeight() {
        return (PanoseXHeight) GenericRecordUtil.safeEnum(PanoseXHeight.values(), new Supplier() { // from class: org.apache.poi.common.usermodel.fonts.FontHeader$$ExternalSyntheticLambda36
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontHeader.this.m2230xf066bc1();
            }
        }).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPanoseXHeight$20$org-apache-poi-common-usermodel-fonts-FontHeader, reason: not valid java name */
    public /* synthetic */ Number m2230xf066bc1() {
        return Byte.valueOf(this.panose[9]);
    }
}
