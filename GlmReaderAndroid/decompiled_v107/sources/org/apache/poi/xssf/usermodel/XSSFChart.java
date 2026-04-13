package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageMargins;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/* loaded from: classes10.dex */
public final class XSSFChart extends XDDFChart {
    private XSSFGraphicFrame frame;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFChart() {
        createChart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFChart(PackagePart part) throws IOException, XmlException {
        super(part);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLRelation getChartRelation() {
        return null;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLRelation getChartWorkbookRelation() {
        return null;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLFactory getChartFactory() {
        return null;
    }

    private void createChart() {
        CTPlotArea plotArea = getCTPlotArea();
        plotArea.addNewLayout();
        getCTChart().addNewPlotVisOnly().setVal(true);
        CTPrintSettings printSettings = this.chartSpace.addNewPrintSettings();
        printSettings.addNewHeaderFooter();
        CTPageMargins pageMargins = printSettings.addNewPageMargins();
        pageMargins.setB(0.75d);
        pageMargins.setL(0.7d);
        pageMargins.setR(0.7d);
        pageMargins.setT(0.75d);
        pageMargins.setHeader(0.3d);
        pageMargins.setFooter(0.3d);
        printSettings.addNewPageSetup();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart, org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTChartSpace.type.getName().getNamespaceURI(), "chartSpace", "c"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this.chartSpace.save(out, xmlOptions);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public XSSFGraphicFrame getGraphicFrame() {
        return this.frame;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setGraphicFrame(XSSFGraphicFrame frame) {
        this.frame = frame;
    }

    public XSSFRichTextString getTitleText() {
        if (!getCTChart().isSetTitle()) {
            return null;
        }
        CTTitle title = getCTChart().getTitle();
        StringBuilder text = new StringBuilder(64);
        XmlObject[] t = title.selectPath("declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main' .//a:t");
        for (XmlObject element : t) {
            NodeList kids = element.getDomNode().getChildNodes();
            int count = kids.getLength();
            for (int n = 0; n < count; n++) {
                Node kid = kids.item(n);
                if (kid instanceof Text) {
                    text.append(kid.getNodeValue());
                }
            }
        }
        return new XSSFRichTextString(text.toString());
    }

    public String getTitleFormula() {
        if (!getCTChart().isSetTitle()) {
            return null;
        }
        CTTitle title = getCTChart().getTitle();
        if (!title.isSetTx()) {
            return null;
        }
        CTTx tx = title.getTx();
        if (tx.isSetStrRef()) {
            return tx.getStrRef().getF();
        }
        return null;
    }

    public void setTitleFormula(String formula) {
        CTTitle ctTitle;
        CTTx tx;
        CTStrRef strRef;
        if (getCTChart().isSetTitle()) {
            ctTitle = getCTChart().getTitle();
        } else {
            ctTitle = getCTChart().addNewTitle();
        }
        if (ctTitle.isSetTx()) {
            tx = ctTitle.getTx();
        } else {
            tx = ctTitle.addNewTx();
        }
        if (tx.isSetRich()) {
            tx.unsetRich();
        }
        if (tx.isSetStrRef()) {
            strRef = tx.getStrRef();
        } else {
            strRef = tx.addNewStrRef();
        }
        strRef.setF(formula);
    }
}
