package org.apache.poi.poifs.crypt.dsig.facets;

import java.util.ArrayList;
import java.util.List;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.w3c.dom.Document;

/* loaded from: classes10.dex */
public class EnvelopedSignatureFacet implements SignatureFacet {
    @Override // org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet
    public void preSign(SignatureInfo signatureInfo, Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        List<Transform> transforms = new ArrayList<>();
        Transform envelopedTransform = SignatureFacetHelper.newTransform(signatureInfo, "http://www.w3.org/2000/09/xmldsig#enveloped-signature");
        transforms.add(envelopedTransform);
        Transform exclusiveTransform = SignatureFacetHelper.newTransform(signatureInfo, "http://www.w3.org/2001/10/xml-exc-c14n#");
        transforms.add(exclusiveTransform);
        Reference reference = SignatureFacetHelper.newReference(signatureInfo, "", transforms, null);
        references.add(reference);
    }
}
