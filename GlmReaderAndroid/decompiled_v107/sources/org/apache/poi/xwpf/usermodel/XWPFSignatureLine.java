package org.apache.poi.xwpf.usermodel;

import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.vml.CTImageData;
import javax.xml.namespace.QName;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.poifs.crypt.dsig.SignatureLine;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;

/* loaded from: classes10.dex */
public class XWPFSignatureLine extends SignatureLine {
    private static final String MS_VML_URN = "urn:schemas-microsoft-com:vml";
    private CTSignatureLine line;

    public void parse(XWPFDocument doc) throws XmlException {
        this.line = (CTSignatureLine) XPathHelper.selectProperty(doc.getDocument(), CTSignatureLine.class, null, new QName[]{new QName(XSSFRelation.NS_WORDPROCESSINGML, "body")}, new QName[]{new QName(XSSFRelation.NS_WORDPROCESSINGML, "p")}, new QName[]{new QName(XSSFRelation.NS_WORDPROCESSINGML, "r")}, new QName[]{new QName(XSSFRelation.NS_WORDPROCESSINGML, ContentTypes.EXTENSION_PICT)}, new QName[]{new QName(MS_VML_URN, "shape")}, new QName[]{QNAME_SIGNATURE_LINE});
        if (this.line != null) {
            setSignatureShape(this.line);
            parse();
        }
    }

    public void add(final XWPFParagraph paragraph) {
        XWPFRun r = paragraph.createRun();
        CTPicture pict = r.getCTR().addNewPict();
        add(pict, new SignatureLine.AddPictureData() { // from class: org.apache.poi.xwpf.usermodel.XWPFSignatureLine$$ExternalSyntheticLambda0
            @Override // org.apache.poi.poifs.crypt.dsig.SignatureLine.AddPictureData
            public final String addPictureData(byte[] bArr, PictureType pictureType) {
                String addPictureData;
                addPictureData = XWPFParagraph.this.getDocument().addPictureData(bArr, XWPFSignatureLine.mapType(pictureType));
                return addPictureData;
            }
        });
    }

    @Override // org.apache.poi.poifs.crypt.dsig.SignatureLine
    protected void setRelationId(CTImageData imageData, String relId) {
        imageData.setId2(relId);
    }

    private static PictureType mapType(PictureType type) throws InvalidFormatException {
        switch (type) {
            case BMP:
                return PictureType.BMP;
            case DIB:
                return PictureType.DIB;
            case EMF:
                return PictureType.EMF;
            case EPS:
                return PictureType.EPS;
            case GIF:
                return PictureType.GIF;
            case JPEG:
                return PictureType.JPEG;
            case PICT:
                return PictureType.PICT;
            case PNG:
                return PictureType.PNG;
            case TIFF:
                return PictureType.TIFF;
            case WMF:
                return PictureType.WMF;
            case WPG:
                return PictureType.WPG;
            case WDP:
                return PictureType.WDP;
            default:
                throw new InvalidFormatException("Unsupported picture format " + type);
        }
    }
}
