package org.apache.poi.ddf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public final class EscherPropertyFactory {
    public List<EscherProperty> createProperties(byte[] data, int offset, short numProperties) {
        BiFunction<Short, Integer, EscherProperty> con;
        ArrayList<EscherProperty> arrayList = new ArrayList();
        int pos = offset;
        for (int i = 0; i < numProperties; i++) {
            short propId = LittleEndian.getShort(data, pos);
            int propData = LittleEndian.getInt(data, pos + 2);
            boolean isComplex = (32768 & propId) != 0;
            EscherPropertyTypes propertyType = EscherPropertyTypes.forPropertyID(propId);
            switch (propertyType.holder) {
                case BOOLEAN:
                    con = new BiFunction() { // from class: org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda0
                        @Override // java.util.function.BiFunction
                        public final Object apply(Object obj, Object obj2) {
                            return new EscherBoolProperty(((Short) obj).shortValue(), ((Integer) obj2).intValue());
                        }
                    };
                    break;
                case RGB:
                    con = new BiFunction() { // from class: org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda1
                        @Override // java.util.function.BiFunction
                        public final Object apply(Object obj, Object obj2) {
                            return new EscherRGBProperty(((Short) obj).shortValue(), ((Integer) obj2).intValue());
                        }
                    };
                    break;
                case SHAPE_PATH:
                    con = new BiFunction() { // from class: org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda2
                        @Override // java.util.function.BiFunction
                        public final Object apply(Object obj, Object obj2) {
                            return new EscherShapePathProperty(((Short) obj).shortValue(), ((Integer) obj2).intValue());
                        }
                    };
                    break;
                default:
                    if (isComplex) {
                        if (propertyType.holder == EscherPropertyTypesHolder.ARRAY) {
                            con = new BiFunction() { // from class: org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda3
                                @Override // java.util.function.BiFunction
                                public final Object apply(Object obj, Object obj2) {
                                    return new EscherArrayProperty(((Short) obj).shortValue(), ((Integer) obj2).intValue());
                                }
                            };
                            break;
                        } else {
                            con = new BiFunction() { // from class: org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda4
                                @Override // java.util.function.BiFunction
                                public final Object apply(Object obj, Object obj2) {
                                    return new EscherComplexProperty(((Short) obj).shortValue(), ((Integer) obj2).intValue());
                                }
                            };
                            break;
                        }
                    } else {
                        con = new BiFunction() { // from class: org.apache.poi.ddf.EscherPropertyFactory$$ExternalSyntheticLambda5
                            @Override // java.util.function.BiFunction
                            public final Object apply(Object obj, Object obj2) {
                                return new EscherSimpleProperty(((Short) obj).shortValue(), ((Integer) obj2).intValue());
                            }
                        };
                        break;
                    }
            }
            arrayList.add(con.apply(Short.valueOf(propId), Integer.valueOf(propData)));
            pos += 6;
        }
        for (EscherProperty p : arrayList) {
            if (p instanceof EscherArrayProperty) {
                EscherArrayProperty eap = (EscherArrayProperty) p;
                pos += eap.setArrayData(data, pos);
            } else if (p instanceof EscherComplexProperty) {
                EscherComplexProperty ecp = (EscherComplexProperty) p;
                int cdLen = ecp.getComplexData().length;
                int leftover = data.length - pos;
                if (leftover < cdLen) {
                    throw new IllegalStateException("Could not read complex escher property, length was " + cdLen + ", but had only " + leftover + " bytes left");
                }
                pos += ecp.setComplexData(data, pos);
            } else {
                continue;
            }
        }
        return arrayList;
    }
}
