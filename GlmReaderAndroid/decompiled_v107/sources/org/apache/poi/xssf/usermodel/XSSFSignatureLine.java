package org.apache.poi.xssf.usermodel;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.excel.STObjectType;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.vml.CTImageData;
import com.microsoft.schemas.vml.CTShape;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.poifs.crypt.dsig.SignatureLine;
import org.apache.poi.schemas.vmldrawing.CTXML;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

/* loaded from: classes10.dex */
public class XSSFSignatureLine extends SignatureLine {
    private static final String MS_VML_URN = "urn:schemas-microsoft-com:vml";

    public void parse(XSSFSheet sheet) throws XmlException {
        CTSignatureLine line;
        XSSFVMLDrawing vml = sheet.getVMLDrawing(false);
        if (vml != null && (line = (CTSignatureLine) XPathHelper.selectProperty(vml.getDocument(), CTSignatureLine.class, null, new QName[]{XSSFVMLDrawing.QNAME_VMLDRAWING}, new QName[]{new QName(MS_VML_URN, "shape")}, new QName[]{QNAME_SIGNATURE_LINE})) != null) {
            setSignatureShape(line);
            parse();
        }
    }

    public void add(final XSSFSheet sheet, XSSFClientAnchor anchor) {
        XSSFVMLDrawing vml = sheet.getVMLDrawing(true);
        CTXML root = vml.getDocument().getXml();
        add(root, new SignatureLine.AddPictureData() { // from class: org.apache.poi.xssf.usermodel.XSSFSignatureLine$$ExternalSyntheticLambda0
            @Override // org.apache.poi.poifs.crypt.dsig.SignatureLine.AddPictureData
            public final String addPictureData(byte[] bArr, PictureType pictureType) {
                return XSSFSignatureLine.this.m2585lambda$add$0$orgapachepoixssfusermodelXSSFSignatureLine(sheet, bArr, pictureType);
            }
        });
        CTShape shape = getSignatureShape();
        CTClientData clientData = shape.addNewClientData();
        String anchorStr = ((int) anchor.getCol1()) + ", " + anchor.getDx1() + ", " + anchor.getRow1() + ", " + anchor.getDy1() + ", " + ((int) anchor.getCol2()) + ", " + anchor.getDx2() + ", " + anchor.getRow2() + ", " + anchor.getDy2();
        clientData.addAnchor(anchorStr);
        clientData.setObjectType(STObjectType.PICT);
        clientData.addSizeWithCells(STTrueFalseBlank.X);
        clientData.addCF(ContentTypes.EXTENSION_PICT);
        clientData.addAutoPict(STTrueFalseBlank.X);
    }

    @Override // org.apache.poi.poifs.crypt.dsig.SignatureLine
    protected void setRelationId(CTImageData imageData, String relId) {
        imageData.setRelid(relId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addPicture, reason: merged with bridge method [inline-methods] */
    public String m2585lambda$add$0$orgapachepoixssfusermodelXSSFSignatureLine(byte[] image, PictureType type, XSSFSheet sheet) throws InvalidFormatException {
        XSSFWorkbook wb = sheet.getWorkbook();
        XSSFVMLDrawing vml = sheet.getVMLDrawing(false);
        POIXMLRelation xtype = mapType(type);
        int idx = wb.getNextPartNumber(xtype, -1);
        POIXMLDocumentPart.RelationPart rp = vml.createRelationship(xtype, wb.getXssfFactory(), idx, false);
        POIXMLDocumentPart dp = rp.getDocumentPart();
        try {
            OutputStream out = dp.getPackagePart().getOutputStream();
            try {
                out.write(image);
                if (out != null) {
                    out.close();
                }
                return rp.getRelationship().getId();
            } finally {
            }
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    private static POIXMLRelation mapType(PictureType type) throws InvalidFormatException {
        switch (type) {
            case BMP:
                return XSSFRelation.IMAGE_BMP;
            case DIB:
                return XSSFRelation.IMAGE_DIB;
            case EMF:
                return XSSFRelation.IMAGE_EMF;
            case EPS:
                return XSSFRelation.IMAGE_EPS;
            case GIF:
                return XSSFRelation.IMAGE_GIF;
            case JPEG:
                return XSSFRelation.IMAGE_JPEG;
            case PICT:
                return XSSFRelation.IMAGE_PICT;
            case PNG:
                return XSSFRelation.IMAGE_PNG;
            case TIFF:
                return XSSFRelation.IMAGE_TIFF;
            case WMF:
                return XSSFRelation.IMAGE_WMF;
            case WPG:
                return XSSFRelation.IMAGE_WPG;
            default:
                throw new InvalidFormatException("Unsupported picture format " + type);
        }
    }
}
