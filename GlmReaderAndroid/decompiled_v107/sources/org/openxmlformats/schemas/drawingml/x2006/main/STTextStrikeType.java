package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STTextStrikeType extends XmlToken {
    public static final int INT_DBL_STRIKE = 3;
    public static final int INT_NO_STRIKE = 1;
    public static final int INT_SNG_STRIKE = 2;
    public static final SimpleTypeFactory<STTextStrikeType> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextstriketype4744type");
    public static final SchemaType type = Factory.getType();
    public static final Enum NO_STRIKE = Enum.forString("noStrike");
    public static final Enum SNG_STRIKE = Enum.forString("sngStrike");
    public static final Enum DBL_STRIKE = Enum.forString("dblStrike");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_DBL_STRIKE = 3;
        static final int INT_NO_STRIKE = 1;
        static final int INT_SNG_STRIKE = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("noStrike", 1), new Enum("sngStrike", 2), new Enum("dblStrike", 3)});

        public static Enum forString(String s) {
            return (Enum) table.forString(s);
        }

        public static Enum forInt(int i) {
            return (Enum) table.forInt(i);
        }

        private Enum(String s, int i) {
            super(s, i);
        }

        private Object readResolve() {
            return forInt(intValue());
        }
    }
}
