package org.apache.poi.poifs.crypt.dsig;

import com.google.android.material.card.MaterialCardViewHelper;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTImageData;
import com.microsoft.schemas.vml.CTShape;
import com.microsoft.schemas.vml.STExt;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

/* loaded from: classes10.dex */
public abstract class SignatureLine {
    private static final String MS_OFFICE_URN = "urn:schemas-microsoft-com:office:office";
    protected static final QName QNAME_SIGNATURE_LINE = new QName(MS_OFFICE_URN, "signatureline");
    private Boolean allowComments;
    private String caption;
    private String contentType;
    private byte[] plainSignature;
    private ClassID setupId;
    private CTShape signatureShape;
    private String suggestedSigner;
    private String suggestedSigner2;
    private String suggestedSignerEmail;
    private String signingInstructions = "Before signing the document, verify that the content you are signing is correct.";
    private String invalidStamp = "invalid";

    /* loaded from: classes10.dex */
    protected interface AddPictureData {
        String addPictureData(byte[] bArr, PictureType pictureType) throws InvalidFormatException;
    }

    protected abstract void setRelationId(CTImageData cTImageData, String str);

    public ClassID getSetupId() {
        return this.setupId;
    }

    public void setSetupId(ClassID setupId) {
        this.setupId = setupId;
    }

    public Boolean getAllowComments() {
        return this.allowComments;
    }

    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
    }

    public String getSigningInstructions() {
        return this.signingInstructions;
    }

    public void setSigningInstructions(String signingInstructions) {
        this.signingInstructions = signingInstructions;
    }

    public String getSuggestedSigner() {
        return this.suggestedSigner;
    }

    public void setSuggestedSigner(String suggestedSigner) {
        this.suggestedSigner = suggestedSigner;
    }

    public String getSuggestedSigner2() {
        return this.suggestedSigner2;
    }

    public void setSuggestedSigner2(String suggestedSigner2) {
        this.suggestedSigner2 = suggestedSigner2;
    }

    public String getSuggestedSignerEmail() {
        return this.suggestedSignerEmail;
    }

    public void setSuggestedSignerEmail(String suggestedSignerEmail) {
        this.suggestedSignerEmail = suggestedSignerEmail;
    }

    public String getDefaultCaption() {
        return this.suggestedSigner + StringUtils.LF + this.suggestedSigner2 + StringUtils.LF + this.suggestedSignerEmail;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getInvalidStamp() {
        return this.invalidStamp;
    }

    public void setInvalidStamp(String invalidStamp) {
        this.invalidStamp = invalidStamp;
    }

    public byte[] getPlainSignature() {
        return this.plainSignature;
    }

    public void setPlainSignature(byte[] plainSignature) {
        this.plainSignature = plainSignature;
        this.contentType = null;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public CTShape getSignatureShape() {
        return this.signatureShape;
    }

    public void setSignatureShape(CTShape signatureShape) {
        this.signatureShape = signatureShape;
    }

    public void setSignatureShape(CTSignatureLine signatureLine) {
        XmlCursor cur = signatureLine.newCursor();
        try {
            cur.toParent();
            this.signatureShape = (CTShape) cur.getObject();
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void updateSignatureConfig(SignatureConfig config) throws IOException {
        if (this.plainSignature == null) {
            throw new IllegalStateException("Plain signature not initialized");
        }
        if (this.contentType == null) {
            determineContentType();
        }
        byte[] signValid = generateImage(true, false);
        byte[] signInvalid = generateImage(true, true);
        config.setSignatureImageSetupId(getSetupId());
        config.setSignatureImage(plainPng());
        config.setSignatureImageValid(signValid);
        config.setSignatureImageInvalid(signInvalid);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void parse() {
        if (this.signatureShape == null) {
            return;
        }
        CTSignatureLine signatureLine = this.signatureShape.getSignaturelineArray(0);
        setSetupId(new ClassID(signatureLine.getId()));
        setAllowComments(signatureLine.isSetAllowcomments() ? Boolean.valueOf(STTrueFalse.TRUE.equals(signatureLine.getAllowcomments())) : null);
        setSuggestedSigner(signatureLine.getSuggestedsigner());
        setSuggestedSigner2(signatureLine.getSuggestedsigner2());
        setSuggestedSignerEmail(signatureLine.getSuggestedsigneremail());
        XmlCursor cur = signatureLine.newCursor();
        try {
            setSigningInstructions(cur.getAttributeText(new QName(MS_OFFICE_URN, "signinginstructions")));
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void add(XmlObject signatureContainer, AddPictureData addPictureData) {
        try {
            byte[] inputImage = generateImage(false, false);
            CTGroup grp = CTGroup.Factory.newInstance();
            grp.addNewShape();
            XmlCursor contCur = signatureContainer.newCursor();
            try {
                contCur.toEndToken();
                XmlCursor otherC = grp.newCursor();
                try {
                    otherC.copyXmlContents(contCur);
                    if (otherC != null) {
                        otherC.close();
                    }
                    contCur.toPrevSibling();
                    this.signatureShape = (CTShape) contCur.getObject();
                    if (contCur != null) {
                        contCur.close();
                    }
                    this.signatureShape.setAlt("Microsoft Office Signature Line...");
                    this.signatureShape.setStyle("width:191.95pt;height:96.05pt");
                    this.signatureShape.setType("rect");
                    String relationId = addPictureData.addPictureData(inputImage, PictureType.PNG);
                    CTImageData imgData = this.signatureShape.addNewImagedata();
                    setRelationId(imgData, relationId);
                    imgData.setTitle("");
                    CTSignatureLine xsl = this.signatureShape.addNewSignatureline();
                    if (this.suggestedSigner != null) {
                        xsl.setSuggestedsigner(this.suggestedSigner);
                    }
                    if (this.suggestedSigner2 != null) {
                        xsl.setSuggestedsigner2(this.suggestedSigner2);
                    }
                    if (this.suggestedSignerEmail != null) {
                        xsl.setSuggestedsigneremail(this.suggestedSignerEmail);
                    }
                    if (this.setupId == null) {
                        this.setupId = new ClassID(VectorFormat.DEFAULT_PREFIX + UUID.randomUUID() + VectorFormat.DEFAULT_SUFFIX);
                    }
                    xsl.setId(this.setupId.toString());
                    xsl.setAllowcomments(STTrueFalse.T);
                    xsl.setIssignatureline(STTrueFalse.T);
                    xsl.setProvid("{00000000-0000-0000-0000-000000000000}");
                    xsl.setExt(STExt.EDIT);
                    xsl.setSigninginstructionsset(STTrueFalse.T);
                    XmlCursor cur = xsl.newCursor();
                    try {
                        cur.setAttributeText(new QName(MS_OFFICE_URN, "signinginstructions"), this.signingInstructions);
                        if (cur != null) {
                            cur.close();
                        }
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException | InvalidFormatException e) {
            throw new POIXMLException("Can't generate signature line image", e);
        }
    }

    protected void update() {
    }

    protected byte[] plainPng() throws IOException {
        byte[] plain = getPlainSignature();
        PictureType pictureType = PictureType.valueOf(FileMagic.valueOf(plain));
        if (pictureType == PictureType.UNKNOWN) {
            throw new IllegalArgumentException("Unsupported picture format");
        }
        ImageRenderer rnd = DrawPictureShape.getImageRenderer(null, pictureType.contentType);
        if (rnd == null) {
            throw new UnsupportedOperationException(pictureType + " can't be rendered - did you provide poi-scratchpad and its dependencies (batik et al.)");
        }
        rnd.loadImage(getPlainSignature(), pictureType.contentType);
        Dimension2D dim = rnd.getDimension();
        int defaultHeight = (int) ((MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION * dim.getHeight()) / dim.getWidth());
        BufferedImage bi = new BufferedImage(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, defaultHeight, 2);
        Graphics2D gfx = bi.createGraphics();
        gfx.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        gfx.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gfx.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        rnd.drawImage(gfx, new Rectangle2D.Double(0.0d, 0.0d, MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, defaultHeight));
        gfx.dispose();
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        ImageIO.write(bi, "PNG", bos);
        return bos.toByteArray();
    }

    protected byte[] generateImage(boolean showSignature, boolean showInvalidStamp) throws IOException {
        BufferedImage bi = new BufferedImage(FontHeader.REGULAR_WEIGHT, 150, 2);
        Graphics2D gfx = bi.createGraphics();
        gfx.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        gfx.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gfx.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        String markX = "X\n";
        String lineX = new String(new char[500]).replace("\u0000", StringUtils.SPACE) + StringUtils.LF;
        String cap = getCaption() == null ? getDefaultCaption() : getCaption();
        String text = "X\n" + lineX + cap.replaceAll("(?m)^", "    ");
        AttributedString as = new AttributedString(text);
        as.addAttribute(TextAttribute.FAMILY, "SansSerif");
        int i = 10;
        as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, "X\n".length(), text.indexOf(10, "X\n".length()));
        as.addAttribute(TextAttribute.SIZE, 15, 0, "X\n".length());
        as.addAttribute(TextAttribute.SIZE, 12, "X\n".length(), text.length());
        gfx.setColor(Color.BLACK);
        AttributedCharacterIterator chIter = as.getIterator();
        FontRenderContext frc = gfx.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(chIter, frc);
        float y = 80.0f;
        int lineNr = 0;
        while (measurer.getPosition() < chIter.getEndIndex()) {
            int mpos = measurer.getPosition();
            int limit = text.indexOf(i, mpos);
            String markX2 = markX;
            TextLayout textLayout = measurer.nextLayout(bi.getWidth() - 10.0f, limit == -1 ? text.length() : limit + 1, false);
            if (lineNr != 1) {
                y += textLayout.getAscent();
            }
            float y2 = y;
            textLayout.draw(gfx, 5.0f, y2);
            y = y2 + textLayout.getDescent() + textLayout.getLeading();
            lineNr++;
            markX = markX2;
            i = 10;
        }
        if (showSignature && this.plainSignature != null && this.contentType != null) {
            ImageRenderer renderer = DrawPictureShape.getImageRenderer(gfx, this.contentType);
            renderer.loadImage(this.plainSignature, this.contentType);
            double targetWidth = bi.getWidth() - 10.0d;
            double targetHeight = 100.0d - 5.0d;
            Dimension2D dim = renderer.getDimension();
            double scale = Math.min(targetWidth / dim.getWidth(), targetHeight / dim.getHeight());
            double effWidth = dim.getWidth() * scale;
            double effHeight = dim.getHeight() * scale;
            renderer.drawImage(gfx, new Rectangle2D.Double(10.0d + ((bi.getWidth() - effWidth) / 2.0d), 100.0d - effHeight, effWidth, effHeight));
        }
        if (showInvalidStamp && this.invalidStamp != null && !this.invalidStamp.isEmpty()) {
            gfx.setFont(new Font("Lucida Bright", 2, 60));
            gfx.rotate(Math.toRadians(-15.0d), bi.getWidth() / 2.0d, bi.getHeight() / 2.0d);
            TextLayout tl = new TextLayout(this.invalidStamp, gfx.getFont(), gfx.getFontRenderContext());
            Rectangle2D bounds = tl.getBounds();
            float x = (float) (((bi.getWidth() - bounds.getWidth()) / 2.0d) - bounds.getX());
            float y3 = (float) (((bi.getHeight() - bounds.getHeight()) / 2.0d) - bounds.getY());
            Shape outline = tl.getOutline(AffineTransform.getTranslateInstance(2.0f + x, 1.0f + y3));
            gfx.setComposite(AlphaComposite.getInstance(3, 0.3f));
            gfx.setPaint(Color.RED);
            gfx.draw(outline);
            gfx.setPaint(new GradientPaint(0.0f, 0.0f, Color.RED, 30.0f, 20.0f, new Color(128, 128, 255), true));
            tl.draw(gfx, x, y3);
        }
        gfx.dispose();
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        ImageIO.write(bi, "PNG", bos);
        return bos.toByteArray();
    }

    private void determineContentType() {
        FileMagic fm = FileMagic.valueOf(this.plainSignature);
        PictureType type = PictureType.valueOf(fm);
        if (type == PictureType.UNKNOWN) {
            throw new IllegalArgumentException("unknown image type");
        }
        this.contentType = type.contentType;
    }
}
