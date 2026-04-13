package org.apache.commons.lang3;

import org.apache.commons.lang3.math.NumberUtils;

/* loaded from: classes9.dex */
public enum JavaVersion {
    JAVA_0_9(1.5f, "0.9"),
    JAVA_1_1(1.1f, "1.1"),
    JAVA_1_2(1.2f, "1.2"),
    JAVA_1_3(1.3f, "1.3"),
    JAVA_1_4(1.4f, "1.4"),
    JAVA_1_5(1.5f, "1.5"),
    JAVA_1_6(1.6f, "1.6"),
    JAVA_1_7(1.7f, "1.7"),
    JAVA_1_8(1.8f, "1.8"),
    JAVA_1_9(9.0f, "9"),
    JAVA_9(9.0f, "9"),
    JAVA_10(10.0f, "10"),
    JAVA_11(11.0f, "11"),
    JAVA_12(12.0f, "12"),
    JAVA_13(13.0f, "13"),
    JAVA_14(14.0f, "14"),
    JAVA_15(15.0f, "15"),
    JAVA_16(16.0f, "16"),
    JAVA_17(17.0f, "17"),
    JAVA_18(18.0f, "18"),
    JAVA_19(19.0f, "19"),
    JAVA_20(20.0f, "20"),
    JAVA_21(21.0f, "21"),
    JAVA_22(22.0f, "22"),
    JAVA_23(23.0f, "23"),
    JAVA_24(24.0f, "24"),
    JAVA_RECENT(maxVersion(), Float.toString(maxVersion()));

    private static final String VERSION_SPLIT_REGEX = "\\.";
    private final String name;
    private final float value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static JavaVersion get(String versionStr) {
        char c;
        if (versionStr == null) {
            return null;
        }
        switch (versionStr.hashCode()) {
            case 57:
                if (versionStr.equals("9")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1567:
                if (versionStr.equals("10")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1568:
                if (versionStr.equals("11")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1569:
                if (versionStr.equals("12")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1570:
                if (versionStr.equals("13")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1571:
                if (versionStr.equals("14")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1572:
                if (versionStr.equals("15")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1573:
                if (versionStr.equals("16")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1574:
                if (versionStr.equals("17")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 1575:
                if (versionStr.equals("18")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1576:
                if (versionStr.equals("19")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 1598:
                if (versionStr.equals("20")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1599:
                if (versionStr.equals("21")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 1600:
                if (versionStr.equals("22")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 1601:
                if (versionStr.equals("23")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 1602:
                if (versionStr.equals("24")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 47611:
                if (versionStr.equals("0.9")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 48564:
                if (versionStr.equals("1.1")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 48565:
                if (versionStr.equals("1.2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 48566:
                if (versionStr.equals("1.3")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 48567:
                if (versionStr.equals("1.4")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 48568:
                if (versionStr.equals("1.5")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 48569:
                if (versionStr.equals("1.6")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 48570:
                if (versionStr.equals("1.7")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 48571:
                if (versionStr.equals("1.8")) {
                    c = '\b';
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
                return JAVA_0_9;
            case 1:
                return JAVA_1_1;
            case 2:
                return JAVA_1_2;
            case 3:
                return JAVA_1_3;
            case 4:
                return JAVA_1_4;
            case 5:
                return JAVA_1_5;
            case 6:
                return JAVA_1_6;
            case 7:
                return JAVA_1_7;
            case '\b':
                return JAVA_1_8;
            case '\t':
                return JAVA_9;
            case '\n':
                return JAVA_10;
            case 11:
                return JAVA_11;
            case '\f':
                return JAVA_12;
            case '\r':
                return JAVA_13;
            case 14:
                return JAVA_14;
            case 15:
                return JAVA_15;
            case 16:
                return JAVA_16;
            case 17:
                return JAVA_17;
            case 18:
                return JAVA_18;
            case 19:
                return JAVA_19;
            case 20:
                return JAVA_20;
            case 21:
                return JAVA_21;
            case 22:
                return JAVA_22;
            case 23:
                return JAVA_23;
            case 24:
                return JAVA_24;
            default:
                float v = toFloatVersion(versionStr);
                if (v - 1.0d < 1.0d) {
                    int firstComma = Math.max(versionStr.indexOf(46), versionStr.indexOf(44));
                    int end = Math.max(versionStr.length(), versionStr.indexOf(44, firstComma));
                    if (Float.parseFloat(versionStr.substring(firstComma + 1, end)) > 0.9f) {
                        return JAVA_RECENT;
                    }
                } else if (v > 10.0f) {
                    return JAVA_RECENT;
                }
                return null;
        }
    }

    static JavaVersion getJavaVersion(String versionStr) {
        return get(versionStr);
    }

    private static float maxVersion() {
        float v = toFloatVersion(SystemProperties.getJavaSpecificationVersion("99.0"));
        if (v > 0.0f) {
            return v;
        }
        return 99.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] split(String value) {
        return value.split(VERSION_SPLIT_REGEX);
    }

    private static float toFloatVersion(String value) {
        if (!value.contains(".")) {
            return NumberUtils.toFloat(value, -1.0f);
        }
        String[] toParse = split(value);
        if (toParse.length >= 2) {
            return NumberUtils.toFloat(toParse[0] + '.' + toParse[1], -1.0f);
        }
        return -1.0f;
    }

    JavaVersion(float value, String name) {
        this.value = value;
        this.name = name;
    }

    public boolean atLeast(JavaVersion requiredVersion) {
        return this.value >= requiredVersion.value;
    }

    public boolean atMost(JavaVersion requiredVersion) {
        return this.value <= requiredVersion.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
