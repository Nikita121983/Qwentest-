package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPageMargins extends XmlObject {
    public static final DocumentFactory<CTPageMargins> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagemarginsb730type");
    public static final SchemaType type = Factory.getType();

    double getB();

    double getFooter();

    double getHeader();

    double getL();

    double getR();

    double getT();

    void setB(double d);

    void setFooter(double d);

    void setHeader(double d);

    void setL(double d);

    void setR(double d);

    void setT(double d);

    XmlDouble xgetB();

    XmlDouble xgetFooter();

    XmlDouble xgetHeader();

    XmlDouble xgetL();

    XmlDouble xgetR();

    XmlDouble xgetT();

    void xsetB(XmlDouble xmlDouble);

    void xsetFooter(XmlDouble xmlDouble);

    void xsetHeader(XmlDouble xmlDouble);

    void xsetL(XmlDouble xmlDouble);

    void xsetR(XmlDouble xmlDouble);

    void xsetT(XmlDouble xmlDouble);
}
