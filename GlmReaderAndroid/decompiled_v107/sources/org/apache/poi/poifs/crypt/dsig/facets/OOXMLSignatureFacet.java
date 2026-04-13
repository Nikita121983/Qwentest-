package org.apache.poi.poifs.crypt.dsig.facets;

import com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1;
import com.microsoft.schemas.office.x2006.digsig.SignatureInfoV1Document;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.Manifest;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureProperties;
import javax.xml.crypto.dsig.SignatureProperty;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.services.RelationshipTransformService;
import org.apache.poi.ss.util.CellUtil;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.SignatureTimeDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: classes10.dex */
public class OOXMLSignatureFacet implements SignatureFacet {
    private static final String ID_PACKAGE_OBJECT = "idPackageObject";
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) OOXMLSignatureFacet.class);
    private static final Set<String> signed = (Set) Stream.of((Object[]) new String[]{"activeXControlBinary", "aFChunk", "attachedTemplate", "attachedToolbars", "audio", "calcChain", "chart", "chartColorStyle", "chartLayout", "chartsheet", "chartStyle", "chartUserShapes", "classificationlabels", "commentAuthors", "comments", "connections", "connectorXml", "control", "ctrlProp", "customData", "customData", "customProperty", "customXml", "diagram", "diagramColors", "diagramColorsHeader", "diagramData", "diagramDrawing", "diagramLayout", "diagramLayoutHeader", "diagramQuickStyle", "diagramQuickStyleHeader", "dialogsheet", "dictionary", "documentParts", "downRev", "drawing", "endnotes", "externalLink", "externalLinkPath", CellUtil.FONT, "fontTable", "footer", "footnotes", "functionPrototypes", "glossaryDocument", "graphicFrameDoc", "groupShapeXml", "handoutMaster", "hdphoto", "header", "hyperlink", "image", "ink", "inkXml", "keyMapCustomizations", "legacyDiagramText", "legacyDocTextInfo", "mailMergeHeaderSource", "mailMergeRecipientData", "mailMergeSource", "media", "notesMaster", "notesSlide", "numbering", "officeDocument", "officeDocument", "oleObject", "package", "pictureXml", "pivotCacheDefinition", "pivotCacheRecords", "pivotTable", "powerPivotData", "presProps", "printerSettings", "queryTable", "recipientData", "settings", "shapeXml", "sharedStrings", "sheetMetadata", "slicer", "slicer", "slicerCache", "slicerCache", "slide", "slideLayout", "slideMaster", "slideUpdateInfo", "slideUpdateUrl", "smartTags", "styles", "stylesWithEffects", "table", "tableSingleCells", "tableStyles", "tags", "theme", "themeOverride", "timeline", "timelineCache", "transform", "ui/altText", "ui/buttonSize", "ui/controlID", "ui/description", "ui/enabled", "ui/extensibility", "ui/extensibility", "ui/helperText", "ui/imageID", "ui/imageMso", "ui/keyTip", "ui/label", "ui/lcid", "ui/loud", "ui/pressed", "ui/progID", "ui/ribbonID", "ui/showImage", "ui/showLabel", "ui/supertip", "ui/target", "ui/text", "ui/title", "ui/tooltip", "ui/userCustomization", "ui/visible", "userXmlData", "vbaProject", "video", "viewProps", "vmlDrawing", "volatileDependencies", "webSettings", "wordVbaData", "worksheet", "wsSortMap", "xlBinaryIndex", "xlExternalLinkPath/xlAlternateStartup", "xlExternalLinkPath/xlLibrary", "xlExternalLinkPath/xlPathMissing", "xlExternalLinkPath/xlStartup", "xlIntlMacrosheet", "xlMacrosheet", "xmlMaps"}).collect(Collectors.toSet());

    @Override // org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet
    public void preSign(SignatureInfo signatureInfo, Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        LOG.atDebug().log("pre sign");
        addManifestObject(signatureInfo, document, references, objects);
        addSignatureInfo(signatureInfo, document, references, objects);
    }

    protected void addManifestObject(SignatureInfo signatureInfo, Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        XMLSignatureFactory sigFac = signatureInfo.getSignatureFactory();
        List<Reference> manifestReferences = new ArrayList<>();
        addManifestReferences(signatureInfo, manifestReferences);
        Manifest manifest = sigFac.newManifest(manifestReferences);
        List<XMLStructure> objectContent = new ArrayList<>();
        objectContent.add(manifest);
        addSignatureTime(signatureInfo, document, objectContent);
        XMLObject xo = sigFac.newXMLObject(objectContent, ID_PACKAGE_OBJECT, (String) null, (String) null);
        objects.add(xo);
        Reference reference = SignatureFacetHelper.newReference(signatureInfo, "#idPackageObject", null, "http://www.w3.org/2000/09/xmldsig#Object");
        references.add(reference);
    }

    protected void addManifestReferences(SignatureInfo signatureInfo, List<Reference> manifestReferences) throws XMLSignatureException {
        OPCPackage opcPackage = signatureInfo.getOpcPackage();
        List<PackagePart> relsEntryNames = opcPackage.getPartsByContentType(ContentTypes.RELATIONSHIPS_PART);
        Set<String> digestedPartNames = new HashSet<>();
        Iterator<PackagePart> it = relsEntryNames.iterator();
        while (it.hasNext()) {
            PackagePart pp = it.next();
            String baseUri = pp.getPartName().getName().replaceFirst("(.*)/_rels/.*", "$1");
            try {
                PackageRelationshipCollection prc = new PackageRelationshipCollection(opcPackage);
                prc.parseRelationshipsPart(pp);
                RelationshipTransformService.RelationshipTransformParameterSpec parameterSpec = new RelationshipTransformService.RelationshipTransformParameterSpec();
                Iterator<PackageRelationship> it2 = prc.iterator();
                while (it2.hasNext()) {
                    PackageRelationship relationship = it2.next();
                    String relationshipType = relationship.getRelationshipType();
                    if (TargetMode.EXTERNAL == relationship.getTargetMode()) {
                        parameterSpec.addRelationshipReference(relationship.getId());
                    } else if (isSignedRelationship(relationshipType)) {
                        parameterSpec.addRelationshipReference(relationship.getId());
                        String partName = normalizePartName(relationship.getTargetURI(), baseUri);
                        if (digestedPartNames.contains(partName)) {
                            continue;
                        } else {
                            digestedPartNames.add(partName);
                            try {
                                PackagePartName relName = PackagingURIHelper.createPartName(partName);
                                PackagePart pp2 = opcPackage.getPart(relName);
                                if (pp2 == null) {
                                    throw new XMLSignatureException("Failed to find part " + relName);
                                }
                                String contentType = pp2.getContentType();
                                if (!relationshipType.endsWith("customXml") || contentType.equals("inkml+xml") || contentType.equals(ContentTypes.XML)) {
                                    Iterator<PackagePart> it3 = it;
                                    String uri = partName + "?ContentType=" + contentType;
                                    Reference reference = SignatureFacetHelper.newReference(signatureInfo, uri, null, null);
                                    manifestReferences.add(reference);
                                    it = it3;
                                } else {
                                    LOG.atDebug().log("skipping customXml with content type: {}", contentType);
                                    it = it;
                                }
                            } catch (InvalidFormatException e) {
                                throw new XMLSignatureException(e);
                            }
                        }
                    } else {
                        continue;
                    }
                }
                Iterator<PackagePart> it4 = it;
                if (parameterSpec.hasSourceIds()) {
                    List<Transform> transforms = new ArrayList<>();
                    transforms.add(SignatureFacetHelper.newTransform(signatureInfo, RelationshipTransformService.TRANSFORM_URI, parameterSpec));
                    transforms.add(SignatureFacetHelper.newTransform(signatureInfo, "http://www.w3.org/TR/2001/REC-xml-c14n-20010315"));
                    String uri2 = normalizePartName(pp.getPartName().getURI(), baseUri) + "?ContentType=application/vnd.openxmlformats-package.relationships+xml";
                    Reference reference2 = SignatureFacetHelper.newReference(signatureInfo, uri2, transforms, null);
                    manifestReferences.add(reference2);
                }
                it = it4;
            } catch (InvalidFormatException e2) {
                throw new XMLSignatureException("Invalid relationship descriptor: " + pp.getPartName().getName(), e2);
            }
        }
        manifestReferences.sort(Comparator.comparing(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.facets.OOXMLSignatureFacet$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Reference) obj).getURI();
            }
        }));
    }

    private static String normalizePartName(URI partName, String baseUri) throws XMLSignatureException {
        String pn = partName.toASCIIString();
        if (!pn.startsWith(baseUri)) {
            pn = baseUri + pn;
        }
        try {
            String pn2 = new URI(pn).normalize().getPath().replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
            LOG.atDebug().log("part name: {}", pn2);
            return pn2;
        } catch (URISyntaxException e) {
            throw new XMLSignatureException(e);
        }
    }

    protected void addSignatureTime(SignatureInfo signatureInfo, Document document, List<XMLStructure> objectContent) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        XMLSignatureFactory sigFac = signatureInfo.getSignatureFactory();
        SignatureTimeDocument sigTime = SignatureTimeDocument.Factory.newInstance();
        CTSignatureTime ctTime = sigTime.addNewSignatureTime();
        ctTime.setFormat("YYYY-MM-DDThh:mm:ssTZD");
        ctTime.setValue(signatureConfig.formatExecutionTime());
        LOG.atDebug().log("execution time: {}", ctTime.getValue());
        Element n = (Element) document.importNode(ctTime.getDomNode(), true);
        List<XMLStructure> signatureTimeContent = new ArrayList<>();
        signatureTimeContent.add(new DOMStructure(n));
        SignatureProperty signatureTimeSignatureProperty = sigFac.newSignatureProperty(signatureTimeContent, "#" + signatureConfig.getPackageSignatureId(), "idSignatureTime");
        List<SignatureProperty> signaturePropertyContent = new ArrayList<>();
        signaturePropertyContent.add(signatureTimeSignatureProperty);
        SignatureProperties signatureProperties = sigFac.newSignatureProperties(signaturePropertyContent, (String) null);
        objectContent.add(signatureProperties);
    }

    protected void addSignatureInfo(SignatureInfo signatureInfo, Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        Reference reference;
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        XMLSignatureFactory sigFac = signatureInfo.getSignatureFactory();
        List<XMLStructure> objectContent = new ArrayList<>();
        SignatureInfoV1Document sigV1 = createSignatureInfoV1(signatureInfo);
        Element n = (Element) document.importNode(sigV1.getSignatureInfoV1().getDomNode(), true);
        List<XMLStructure> signatureInfoContent = new ArrayList<>();
        signatureInfoContent.add(new DOMStructure(n));
        SignatureProperty signatureInfoSignatureProperty = sigFac.newSignatureProperty(signatureInfoContent, "#" + signatureConfig.getPackageSignatureId(), "idOfficeV1Details");
        List<SignatureProperty> signaturePropertyContent = new ArrayList<>();
        signaturePropertyContent.add(signatureInfoSignatureProperty);
        SignatureProperties signatureProperties = sigFac.newSignatureProperties(signaturePropertyContent, (String) null);
        objectContent.add(signatureProperties);
        objects.add(sigFac.newXMLObject(objectContent, "idOfficeObject", (String) null, (String) null));
        Reference reference2 = SignatureFacetHelper.newReference(signatureInfo, "#idOfficeObject", null, "http://www.w3.org/2000/09/xmldsig#Object");
        references.add(reference2);
        Base64.Encoder enc = Base64.getEncoder();
        byte[] imageValid = signatureConfig.getSignatureImageValid();
        if (imageValid == null) {
            reference = reference2;
        } else {
            DOMStructure tn = new DOMStructure(document.createTextNode(enc.encodeToString(imageValid)));
            objects.add(sigFac.newXMLObject(Collections.singletonList(tn), "idValidSigLnImg", (String) null, (String) null));
            Reference reference3 = SignatureFacetHelper.newReference(signatureInfo, "#idValidSigLnImg", null, "http://www.w3.org/2000/09/xmldsig#Object");
            references.add(reference3);
            reference = reference3;
        }
        byte[] imageInvalid = signatureConfig.getSignatureImageInvalid();
        if (imageInvalid != null) {
            DOMStructure tn2 = new DOMStructure(document.createTextNode(enc.encodeToString(imageInvalid)));
            objects.add(sigFac.newXMLObject(Collections.singletonList(tn2), "idInvalidSigLnImg", (String) null, (String) null));
            references.add(SignatureFacetHelper.newReference(signatureInfo, "#idInvalidSigLnImg", null, "http://www.w3.org/2000/09/xmldsig#Object"));
        }
    }

    protected SignatureInfoV1Document createSignatureInfoV1(SignatureInfo signatureInfo) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        SignatureInfoV1Document sigV1 = SignatureInfoV1Document.Factory.newInstance();
        CTSignatureInfoV1 ctSigV1 = sigV1.addNewSignatureInfoV1();
        if (signatureConfig.getDigestAlgo() != HashAlgorithm.sha1) {
            ctSigV1.setManifestHashAlgorithm(signatureConfig.getDigestMethodUri());
        }
        String desc = signatureConfig.getSignatureDescription();
        if (desc != null) {
            ctSigV1.setSignatureComments(desc);
        }
        byte[] image = signatureConfig.getSignatureImage();
        if (image == null) {
            ctSigV1.setSignatureType(1);
        } else {
            ctSigV1.setSetupID(signatureConfig.getSignatureImageSetupId().toString());
            ctSigV1.setSignatureImage(image);
            ctSigV1.setSignatureType(2);
        }
        return sigV1;
    }

    protected static String getRelationshipReferenceURI(String zipEntryName) {
        return PackagingURIHelper.FORWARD_SLASH_STRING + zipEntryName + "?ContentType=application/vnd.openxmlformats-package.relationships+xml";
    }

    protected static String getResourceReferenceURI(String resourceName, String contentType) {
        return PackagingURIHelper.FORWARD_SLASH_STRING + resourceName + "?ContentType=" + contentType;
    }

    protected static boolean isSignedRelationship(String relationshipType) {
        LOG.atDebug().log("relationship type: {}", relationshipType);
        String rt = relationshipType.replaceFirst(".*/relationships/", "");
        return signed.contains(rt) || rt.endsWith("customXml");
    }
}
