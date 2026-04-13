package org.apache.xmlbeans;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.store.Saaj;
import org.xml.sax.EntityResolver;
import org.xml.sax.XMLReader;

/* loaded from: classes.dex */
public class XmlOptions implements Serializable {
    public static final int DEFAULT_ENTITY_EXPANSION_LIMIT = 2048;
    private static final XmlOptions EMPTY_OPTIONS = new XmlOptions();
    private static final long serialVersionUID = 1;
    private Map<XmlOptionsKeys, Object> _map = new HashMap();

    /* loaded from: classes.dex */
    public enum BeanMethod {
        GET,
        XGET,
        IS_SET,
        IS_NIL,
        IS_NIL_IDX,
        SET,
        SET_NIL,
        SET_NIL_IDX,
        XSET,
        UNSET,
        GET_ARRAY,
        XGET_ARRAY,
        GET_IDX,
        XGET_IDX,
        XSET_ARRAY,
        XSET_IDX,
        SIZE_OF_ARRAY,
        SET_ARRAY,
        SET_IDX,
        INSERT_IDX,
        INSERT_NEW_IDX,
        ADD,
        ADD_NEW,
        REMOVE_IDX,
        GET_LIST,
        XGET_LIST,
        SET_LIST,
        INSTANCE_TYPE
    }

    /* loaded from: classes.dex */
    public enum XmlOptionsKeys {
        SAVE_NAMESPACES_FIRST,
        SAVE_SYNTHETIC_DOCUMENT_ELEMENT,
        SAVE_PRETTY_PRINT,
        SAVE_PRETTY_PRINT_INDENT,
        SAVE_PRETTY_PRINT_OFFSET,
        SAVE_AGGRESSIVE_NAMESPACES,
        SAVE_USE_DEFAULT_NAMESPACE,
        SAVE_IMPLICIT_NAMESPACES,
        SAVE_SUGGESTED_PREFIXES,
        SAVE_FILTER_PROCINST,
        SAVE_USE_OPEN_FRAGMENT,
        SAVE_OUTER,
        SAVE_INNER,
        SAVE_NO_XML_DECL,
        SAVE_SUBSTITUTE_CHARACTERS,
        SAVE_OPTIMIZE_FOR_SPEED,
        SAVE_CDATA_LENGTH_THRESHOLD,
        SAVE_CDATA_ENTITY_COUNT_THRESHOLD,
        SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES,
        LOAD_REPLACE_DOCUMENT_ELEMENT,
        LOAD_STRIP_WHITESPACE,
        LOAD_STRIP_COMMENTS,
        LOAD_STRIP_PROCINSTS,
        LOAD_LINE_NUMBERS,
        LOAD_LINE_NUMBERS_END_ELEMENT,
        LOAD_SAVE_CDATA_BOOKMARKS,
        LOAD_SUBSTITUTE_NAMESPACES,
        LOAD_TRIM_TEXT_BUFFER,
        LOAD_ADDITIONAL_NAMESPACES,
        LOAD_MESSAGE_DIGEST,
        LOAD_USE_DEFAULT_RESOLVER,
        LOAD_USE_XMLREADER,
        XQUERY_CURRENT_NODE_VAR,
        XQUERY_VARIABLE_MAP,
        CHARACTER_ENCODING,
        ERROR_LISTENER,
        DOCUMENT_TYPE,
        DOCUMENT_SOURCE_NAME,
        COMPILE_SUBSTITUTE_NAMES,
        COMPILE_NO_VALIDATION,
        COMPILE_NO_UPA_RULE,
        COMPILE_NO_PVR_RULE,
        COMPILE_NO_ANNOTATIONS,
        COMPILE_DOWNLOAD_URLS,
        COMPILE_MDEF_NAMESPACES,
        COMPILE_PARTIAL_TYPESYSTEM,
        COMPILE_PARTIAL_METHODS,
        COMPILE_ANNOTATION_JAVADOC,
        VALIDATE_ON_SET,
        VALIDATE_TREAT_LAX_AS_SKIP,
        VALIDATE_STRICT,
        VALIDATE_TEXT_ONLY,
        UNSYNCHRONIZED,
        ENTITY_RESOLVER,
        BASE_URI,
        SCHEMA_CODE_PRINTER,
        GENERATE_JAVA_VERSION,
        USE_SAME_LOCALE,
        COPY_USE_NEW_SYNC_DOMAIN,
        LOAD_ENTITY_BYTES_LIMIT,
        ENTITY_EXPANSION_LIMIT,
        LOAD_DTD_GRAMMAR,
        LOAD_EXTERNAL_DTD,
        DISALLOW_DOCTYPE_DECLARATION,
        SAAJ_IMPL,
        LOAD_USE_LOCALE_CHAR_UTIL,
        XPATH_USE_SAXON,
        XPATH_USE_XMLBEANS,
        ATTRIBUTE_VALIDATION_COMPAT_MODE
    }

    static {
        EMPTY_OPTIONS._map = Collections.unmodifiableMap(EMPTY_OPTIONS._map);
    }

    public XmlOptions() {
    }

    public XmlOptions(XmlOptions other) {
        if (other != null) {
            this._map.putAll(other._map);
        }
    }

    public XmlOptions setSaveNamespacesFirst() {
        return setSaveNamespacesFirst(true);
    }

    public XmlOptions setSaveNamespacesFirst(boolean b) {
        return set(XmlOptionsKeys.SAVE_NAMESPACES_FIRST, b);
    }

    public boolean isSaveNamespacesFirst() {
        return hasOption(XmlOptionsKeys.SAVE_NAMESPACES_FIRST);
    }

    public XmlOptions setSavePrettyPrint() {
        return setSavePrettyPrint(true);
    }

    public XmlOptions setSavePrettyPrint(boolean b) {
        return set(XmlOptionsKeys.SAVE_PRETTY_PRINT, b);
    }

    public boolean isSavePrettyPrint() {
        return hasOption(XmlOptionsKeys.SAVE_PRETTY_PRINT);
    }

    public XmlOptions setSavePrettyPrintIndent(int indent) {
        return set(XmlOptionsKeys.SAVE_PRETTY_PRINT_INDENT, indent);
    }

    public Integer getSavePrettyPrintIndent() {
        return (Integer) get(XmlOptionsKeys.SAVE_PRETTY_PRINT_INDENT);
    }

    public XmlOptions setSavePrettyPrintOffset(int offset) {
        return set(XmlOptionsKeys.SAVE_PRETTY_PRINT_OFFSET, offset);
    }

    public Integer getSavePrettyPrintOffset() {
        return (Integer) get(XmlOptionsKeys.SAVE_PRETTY_PRINT_OFFSET);
    }

    public XmlOptions setCharacterEncoding(String encoding) {
        return set(XmlOptionsKeys.CHARACTER_ENCODING, encoding);
    }

    public String getCharacterEncoding() {
        return (String) get(XmlOptionsKeys.CHARACTER_ENCODING);
    }

    public XmlOptions setDocumentType(SchemaType type) {
        return set(XmlOptionsKeys.DOCUMENT_TYPE, type);
    }

    public SchemaType getDocumentType() {
        return (SchemaType) get(XmlOptionsKeys.DOCUMENT_TYPE);
    }

    public XmlOptions setErrorListener(Collection<XmlError> c) {
        return set(XmlOptionsKeys.ERROR_LISTENER, c);
    }

    public Collection<XmlError> getErrorListener() {
        return (Collection) get(XmlOptionsKeys.ERROR_LISTENER);
    }

    public XmlOptions setSaveAggressiveNamespaces() {
        return setSaveAggressiveNamespaces(true);
    }

    public XmlOptions setSaveAggressiveNamespaces(boolean b) {
        return set(XmlOptionsKeys.SAVE_AGGRESSIVE_NAMESPACES, b);
    }

    public boolean isSaveAggressiveNamespaces() {
        return hasOption(XmlOptionsKeys.SAVE_AGGRESSIVE_NAMESPACES);
    }

    public XmlOptions setSaveSyntheticDocumentElement(QName name) {
        return set(XmlOptionsKeys.SAVE_SYNTHETIC_DOCUMENT_ELEMENT, name);
    }

    public QName getSaveSyntheticDocumentElement() {
        return (QName) get(XmlOptionsKeys.SAVE_SYNTHETIC_DOCUMENT_ELEMENT);
    }

    public XmlOptions setUseDefaultNamespace() {
        return setUseDefaultNamespace(true);
    }

    public XmlOptions setUseDefaultNamespace(boolean b) {
        return set(XmlOptionsKeys.SAVE_USE_DEFAULT_NAMESPACE, b);
    }

    public boolean isUseDefaultNamespace() {
        return hasOption(XmlOptionsKeys.SAVE_USE_DEFAULT_NAMESPACE);
    }

    public XmlOptions setSaveImplicitNamespaces(Map<String, String> implicitNamespaces) {
        return set(XmlOptionsKeys.SAVE_IMPLICIT_NAMESPACES, implicitNamespaces);
    }

    public Map<String, String> getSaveImplicitNamespaces() {
        return (Map) get(XmlOptionsKeys.SAVE_IMPLICIT_NAMESPACES);
    }

    public XmlOptions setSaveSuggestedPrefixes(Map<String, String> suggestedPrefixes) {
        return set(XmlOptionsKeys.SAVE_SUGGESTED_PREFIXES, suggestedPrefixes);
    }

    public Map<String, String> getSaveSuggestedPrefixes() {
        return (Map) get(XmlOptionsKeys.SAVE_SUGGESTED_PREFIXES);
    }

    public XmlOptions setSaveFilterProcinst(String filterProcinst) {
        return set(XmlOptionsKeys.SAVE_FILTER_PROCINST, filterProcinst);
    }

    public String getSaveFilterProcinst() {
        return (String) get(XmlOptionsKeys.SAVE_FILTER_PROCINST);
    }

    public XmlOptions setSaveSubstituteCharacters(XmlOptionCharEscapeMap characterReplacementMap) {
        return set(XmlOptionsKeys.SAVE_SUBSTITUTE_CHARACTERS, characterReplacementMap);
    }

    public XmlOptionCharEscapeMap getSaveSubstituteCharacters() {
        return (XmlOptionCharEscapeMap) get(XmlOptionsKeys.SAVE_SUBSTITUTE_CHARACTERS);
    }

    public XmlOptions setSaveUseOpenFrag() {
        return setSaveUseOpenFrag(true);
    }

    public XmlOptions setSaveUseOpenFrag(boolean b) {
        return set(XmlOptionsKeys.SAVE_USE_OPEN_FRAGMENT, b);
    }

    public boolean isSaveUseOpenFrag() {
        return hasOption(XmlOptionsKeys.SAVE_USE_OPEN_FRAGMENT);
    }

    public XmlOptions setSaveOuter() {
        return setSaveOuter(true);
    }

    public XmlOptions setSaveOuter(boolean b) {
        return set(XmlOptionsKeys.SAVE_OUTER, b);
    }

    public boolean isSaveOuter() {
        return hasOption(XmlOptionsKeys.SAVE_OUTER);
    }

    public XmlOptions setSaveInner() {
        return setSaveInner(true);
    }

    public XmlOptions setSaveInner(boolean b) {
        return set(XmlOptionsKeys.SAVE_INNER, b);
    }

    public boolean isSaveInner() {
        return hasOption(XmlOptionsKeys.SAVE_INNER);
    }

    public XmlOptions setSaveNoXmlDecl() {
        return setSaveNoXmlDecl(true);
    }

    public XmlOptions setSaveNoXmlDecl(boolean b) {
        return set(XmlOptionsKeys.SAVE_NO_XML_DECL, b);
    }

    public boolean isSaveNoXmlDecl() {
        return hasOption(XmlOptionsKeys.SAVE_NO_XML_DECL);
    }

    public XmlOptions setSaveCDataLengthThreshold(int cdataLengthThreshold) {
        return set(XmlOptionsKeys.SAVE_CDATA_LENGTH_THRESHOLD, cdataLengthThreshold);
    }

    public Integer getSaveCDataLengthThreshold() {
        return (Integer) get(XmlOptionsKeys.SAVE_CDATA_LENGTH_THRESHOLD);
    }

    public XmlOptions setSaveCDataEntityCountThreshold(int cdataEntityCountThreshold) {
        return set(XmlOptionsKeys.SAVE_CDATA_ENTITY_COUNT_THRESHOLD, cdataEntityCountThreshold);
    }

    public Integer getSaveCDataEntityCountThreshold() {
        return (Integer) get(XmlOptionsKeys.SAVE_CDATA_ENTITY_COUNT_THRESHOLD);
    }

    public XmlOptions setUseCDataBookmarks() {
        return set(XmlOptionsKeys.LOAD_SAVE_CDATA_BOOKMARKS);
    }

    public boolean isUseCDataBookmarks() {
        return hasOption(XmlOptionsKeys.LOAD_SAVE_CDATA_BOOKMARKS);
    }

    public XmlOptions setSaveSaxNoNSDeclsInAttributes() {
        return setSaveSaxNoNSDeclsInAttributes(true);
    }

    public XmlOptions setSaveSaxNoNSDeclsInAttributes(boolean b) {
        return set(XmlOptionsKeys.SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES, b);
    }

    public boolean isSaveSaxNoNSDeclsInAttributes() {
        return hasOption(XmlOptionsKeys.SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES);
    }

    public XmlOptions setLoadReplaceDocumentElement(QName replacement) {
        return set(XmlOptionsKeys.LOAD_REPLACE_DOCUMENT_ELEMENT, replacement);
    }

    public QName getLoadReplaceDocumentElement() {
        return (QName) get(XmlOptionsKeys.LOAD_REPLACE_DOCUMENT_ELEMENT);
    }

    public XmlOptions setLoadStripWhitespace() {
        return setLoadStripWhitespace(true);
    }

    public XmlOptions setLoadStripWhitespace(boolean b) {
        return set(XmlOptionsKeys.LOAD_STRIP_WHITESPACE, b);
    }

    public boolean isSetLoadStripWhitespace() {
        return hasOption(XmlOptionsKeys.LOAD_STRIP_WHITESPACE);
    }

    public XmlOptions setLoadStripComments() {
        return setLoadStripComments(true);
    }

    public XmlOptions setLoadStripComments(boolean b) {
        return set(XmlOptionsKeys.LOAD_STRIP_COMMENTS, b);
    }

    public boolean isLoadStripComments() {
        return hasOption(XmlOptionsKeys.LOAD_STRIP_COMMENTS);
    }

    public XmlOptions setLoadStripProcinsts() {
        return setLoadStripProcinsts(true);
    }

    public XmlOptions setLoadStripProcinsts(boolean b) {
        return set(XmlOptionsKeys.LOAD_STRIP_PROCINSTS, b);
    }

    public boolean isLoadStripProcinsts() {
        return hasOption(XmlOptionsKeys.LOAD_STRIP_PROCINSTS);
    }

    public XmlOptions setLoadLineNumbers() {
        return setLoadLineNumbers(true);
    }

    public XmlOptions setLoadLineNumbers(boolean b) {
        return set(XmlOptionsKeys.LOAD_LINE_NUMBERS, b);
    }

    public boolean isLoadLineNumbers() {
        return hasOption(XmlOptionsKeys.LOAD_LINE_NUMBERS);
    }

    public XmlOptions setLoadLineNumbersEndElement() {
        return setLoadLineNumbersEndElement(true);
    }

    public XmlOptions setLoadLineNumbersEndElement(boolean b) {
        setLoadLineNumbers(true);
        return set(XmlOptionsKeys.LOAD_LINE_NUMBERS_END_ELEMENT, b);
    }

    public boolean isLoadLineNumbersEndElement() {
        return hasOption(XmlOptionsKeys.LOAD_LINE_NUMBERS_END_ELEMENT);
    }

    public XmlOptions setLoadSubstituteNamespaces(Map<String, String> substNamespaces) {
        return set(XmlOptionsKeys.LOAD_SUBSTITUTE_NAMESPACES, substNamespaces);
    }

    public Map<String, String> getLoadSubstituteNamespaces() {
        return (Map) get(XmlOptionsKeys.LOAD_SUBSTITUTE_NAMESPACES);
    }

    public XmlOptions setLoadTrimTextBuffer() {
        return setLoadTrimTextBuffer(true);
    }

    public XmlOptions setLoadTrimTextBuffer(boolean b) {
        return set(XmlOptionsKeys.LOAD_TRIM_TEXT_BUFFER, b);
    }

    public boolean isLoadTrimTextBuffer() {
        return hasOption(XmlOptionsKeys.LOAD_TRIM_TEXT_BUFFER);
    }

    public XmlOptions setLoadAdditionalNamespaces(Map<String, String> nses) {
        return set(XmlOptionsKeys.LOAD_ADDITIONAL_NAMESPACES, nses);
    }

    public Map<String, String> getLoadAdditionalNamespaces() {
        return (Map) get(XmlOptionsKeys.LOAD_ADDITIONAL_NAMESPACES);
    }

    public XmlOptions setLoadMessageDigest() {
        return setLoadMessageDigest(true);
    }

    public XmlOptions setLoadMessageDigest(boolean b) {
        return set(XmlOptionsKeys.LOAD_MESSAGE_DIGEST, b);
    }

    public boolean isLoadMessageDigest() {
        return hasOption(XmlOptionsKeys.LOAD_MESSAGE_DIGEST);
    }

    public XmlOptions setLoadUseDefaultResolver() {
        return setLoadUseDefaultResolver(true);
    }

    public XmlOptions setLoadUseDefaultResolver(boolean b) {
        return set(XmlOptionsKeys.LOAD_USE_DEFAULT_RESOLVER, b);
    }

    public boolean isLoadUseDefaultResolver() {
        return hasOption(XmlOptionsKeys.LOAD_USE_DEFAULT_RESOLVER);
    }

    public XmlOptions setLoadUseXMLReader(XMLReader xmlReader) {
        return set(XmlOptionsKeys.LOAD_USE_XMLREADER, xmlReader);
    }

    public XMLReader getLoadUseXMLReader() {
        return (XMLReader) get(XmlOptionsKeys.LOAD_USE_XMLREADER);
    }

    public XmlOptions setXqueryCurrentNodeVar(String varName) {
        return set(XmlOptionsKeys.XQUERY_CURRENT_NODE_VAR, varName);
    }

    public String getXqueryCurrentNodeVar() {
        return (String) get(XmlOptionsKeys.XQUERY_CURRENT_NODE_VAR);
    }

    public XmlOptions setXqueryVariables(Map<String, Object> varMap) {
        return set(XmlOptionsKeys.XQUERY_VARIABLE_MAP, varMap);
    }

    public Map<String, Object> getXqueryVariables() {
        return (Map) get(XmlOptionsKeys.XQUERY_VARIABLE_MAP);
    }

    public XmlOptions setDocumentSourceName(String documentSourceName) {
        return set(XmlOptionsKeys.DOCUMENT_SOURCE_NAME, documentSourceName);
    }

    public String getDocumentSourceName() {
        return (String) get(XmlOptionsKeys.DOCUMENT_SOURCE_NAME);
    }

    public XmlOptions setCompileSubstituteNames(Map<QName, QName> nameMap) {
        return set(XmlOptionsKeys.COMPILE_SUBSTITUTE_NAMES, nameMap);
    }

    public Map<QName, QName> getCompileSubstituteNames() {
        return (Map) get(XmlOptionsKeys.COMPILE_SUBSTITUTE_NAMES);
    }

    public XmlOptions setCompileNoValidation() {
        return set(XmlOptionsKeys.COMPILE_NO_VALIDATION);
    }

    public boolean isCompileNoValidation() {
        return hasOption(XmlOptionsKeys.COMPILE_NO_VALIDATION);
    }

    public XmlOptions setCompileNoUpaRule() {
        return setCompileNoUpaRule(true);
    }

    public XmlOptions setCompileNoUpaRule(boolean b) {
        return set(XmlOptionsKeys.COMPILE_NO_UPA_RULE, b);
    }

    public boolean isCompileNoUpaRule() {
        return hasOption(XmlOptionsKeys.COMPILE_NO_UPA_RULE);
    }

    public XmlOptions setCompileNoPvrRule() {
        return setCompileNoPvrRule(true);
    }

    public XmlOptions setCompileNoPvrRule(boolean b) {
        return set(XmlOptionsKeys.COMPILE_NO_PVR_RULE, b);
    }

    public boolean isCompileNoPvrRule() {
        return hasOption(XmlOptionsKeys.COMPILE_NO_PVR_RULE);
    }

    public XmlOptions setCompileNoAnnotations() {
        return setCompileNoAnnotations(true);
    }

    public XmlOptions setCompileNoAnnotations(boolean b) {
        return set(XmlOptionsKeys.COMPILE_NO_ANNOTATIONS, b);
    }

    public boolean isCompileNoAnnotations() {
        return hasOption(XmlOptionsKeys.COMPILE_NO_ANNOTATIONS);
    }

    public XmlOptions setCompileDownloadUrls() {
        return setCompileDownloadUrls(true);
    }

    public XmlOptions setCompileDownloadUrls(boolean b) {
        return set(XmlOptionsKeys.COMPILE_DOWNLOAD_URLS, b);
    }

    public boolean isCompileDownloadUrls() {
        return hasOption(XmlOptionsKeys.COMPILE_DOWNLOAD_URLS);
    }

    public XmlOptions setCompileMdefNamespaces(Set<String> mdefNamespaces) {
        return set(XmlOptionsKeys.COMPILE_MDEF_NAMESPACES, mdefNamespaces);
    }

    public Set<String> getCompileMdefNamespaces() {
        return (Set) get(XmlOptionsKeys.COMPILE_MDEF_NAMESPACES);
    }

    public XmlOptions setCompilePartialTypesystem() {
        return setCompilePartialTypesystem(true);
    }

    public XmlOptions setCompilePartialTypesystem(boolean compilePartialTypesystem) {
        return set(XmlOptionsKeys.COMPILE_PARTIAL_TYPESYSTEM, compilePartialTypesystem);
    }

    public boolean isCompilePartialTypesystem() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.COMPILE_PARTIAL_TYPESYSTEM);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setValidateOnSet() {
        return setValidateOnSet(true);
    }

    public XmlOptions setValidateOnSet(boolean b) {
        return set(XmlOptionsKeys.VALIDATE_ON_SET, b);
    }

    public boolean isValidateOnSet() {
        return hasOption(XmlOptionsKeys.VALIDATE_ON_SET);
    }

    public XmlOptions setValidateTreatLaxAsSkip() {
        return setValidateTreatLaxAsSkip(true);
    }

    public XmlOptions setValidateTreatLaxAsSkip(boolean b) {
        return set(XmlOptionsKeys.VALIDATE_TREAT_LAX_AS_SKIP, b);
    }

    public boolean isValidateTreatLaxAsSkip() {
        return hasOption(XmlOptionsKeys.VALIDATE_TREAT_LAX_AS_SKIP);
    }

    public XmlOptions setValidateStrict() {
        return setValidateStrict(true);
    }

    public XmlOptions setValidateStrict(boolean b) {
        return set(XmlOptionsKeys.VALIDATE_STRICT, b);
    }

    public boolean isValidateStrict() {
        return hasOption(XmlOptionsKeys.VALIDATE_STRICT);
    }

    public XmlOptions setValidateTextOnly() {
        return setValidateTextOnly(true);
    }

    public XmlOptions setValidateTextOnly(boolean b) {
        return set(XmlOptionsKeys.VALIDATE_TEXT_ONLY, b);
    }

    public boolean isValidateTextOnly() {
        return hasOption(XmlOptionsKeys.VALIDATE_TEXT_ONLY);
    }

    public XmlOptions setUnsynchronized() {
        return setUnsynchronized(true);
    }

    public XmlOptions setUnsynchronized(boolean b) {
        return set(XmlOptionsKeys.UNSYNCHRONIZED, b);
    }

    public boolean isUnsynchronized() {
        return hasOption(XmlOptionsKeys.UNSYNCHRONIZED);
    }

    public XmlOptions setEntityResolver(EntityResolver resolver) {
        return set(XmlOptionsKeys.ENTITY_RESOLVER, resolver);
    }

    public EntityResolver getEntityResolver() {
        return (EntityResolver) get(XmlOptionsKeys.ENTITY_RESOLVER);
    }

    public XmlOptions setBaseURI(URI baseURI) {
        return set(XmlOptionsKeys.BASE_URI, baseURI);
    }

    public URI getBaseURI() {
        return (URI) get(XmlOptionsKeys.BASE_URI);
    }

    public XmlOptions setSchemaCodePrinter(SchemaCodePrinter printer) {
        return set(XmlOptionsKeys.SCHEMA_CODE_PRINTER, printer);
    }

    public SchemaCodePrinter getSchemaCodePrinter() {
        return (SchemaCodePrinter) get(XmlOptionsKeys.SCHEMA_CODE_PRINTER);
    }

    public XmlOptions setCopyUseNewSynchronizationDomain(boolean useNewSyncDomain) {
        return set(XmlOptionsKeys.COPY_USE_NEW_SYNC_DOMAIN, useNewSyncDomain);
    }

    public boolean isCopyUseNewSynchronizationDomain() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.COPY_USE_NEW_SYNC_DOMAIN);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setUseSameLocale(Object localeOrXmlTokenSource) {
        return set(XmlOptionsKeys.USE_SAME_LOCALE, localeOrXmlTokenSource);
    }

    public Object getUseSameLocale() {
        return get(XmlOptionsKeys.USE_SAME_LOCALE);
    }

    public XmlOptions setLoadEntityBytesLimit(int entityBytesLimit) {
        return set(XmlOptionsKeys.LOAD_ENTITY_BYTES_LIMIT, entityBytesLimit);
    }

    public Integer getLoadEntityBytesLimit() {
        return (Integer) get(XmlOptionsKeys.LOAD_ENTITY_BYTES_LIMIT);
    }

    public XmlOptions setEntityExpansionLimit(int entityExpansionLimit) {
        return set(XmlOptionsKeys.ENTITY_EXPANSION_LIMIT, entityExpansionLimit);
    }

    public int getEntityExpansionLimit() {
        Integer limit = (Integer) get(XmlOptionsKeys.ENTITY_EXPANSION_LIMIT);
        if (limit == null) {
            return 2048;
        }
        return limit.intValue();
    }

    public XmlOptions setLoadDTDGrammar(boolean loadDTDGrammar) {
        return set(XmlOptionsKeys.LOAD_DTD_GRAMMAR, loadDTDGrammar);
    }

    public boolean isLoadDTDGrammar() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.LOAD_DTD_GRAMMAR);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setLoadExternalDTD(boolean loadExternalDTD) {
        return set(XmlOptionsKeys.LOAD_EXTERNAL_DTD, loadExternalDTD);
    }

    public boolean isLoadExternalDTD() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.LOAD_EXTERNAL_DTD);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setDisallowDocTypeDeclaration(boolean disallowDocTypeDeclaration) {
        return set(XmlOptionsKeys.DISALLOW_DOCTYPE_DECLARATION, disallowDocTypeDeclaration);
    }

    public boolean disallowDocTypeDeclaration() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.DISALLOW_DOCTYPE_DECLARATION);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setSaveOptimizeForSpeed(boolean saveOptimizeForSpeed) {
        return set(XmlOptionsKeys.SAVE_OPTIMIZE_FOR_SPEED, saveOptimizeForSpeed);
    }

    public boolean isSaveOptimizeForSpeed() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.SAVE_OPTIMIZE_FOR_SPEED);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setSaaj(Saaj saaj) {
        return set(XmlOptionsKeys.SAAJ_IMPL, saaj);
    }

    public Saaj getSaaj() {
        return (Saaj) get(XmlOptionsKeys.SAAJ_IMPL);
    }

    public XmlOptions setLoadUseLocaleCharUtil(boolean useCharUtil) {
        return set(XmlOptionsKeys.LOAD_USE_LOCALE_CHAR_UTIL, useCharUtil);
    }

    public boolean isLoadUseLocaleCharUtil() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.LOAD_USE_LOCALE_CHAR_UTIL);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setXPathUseSaxon() {
        return setXPathUseSaxon(true);
    }

    public XmlOptions setXPathUseSaxon(boolean xpathUseSaxon) {
        return set(XmlOptionsKeys.XPATH_USE_SAXON, xpathUseSaxon);
    }

    public boolean isXPathUseSaxon() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.XPATH_USE_SAXON);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setXPathUseXmlBeans() {
        return setXPathUseSaxon(true);
    }

    public XmlOptions setXPathUseXmlBeans(boolean xpathUseXmlBeans) {
        return set(XmlOptionsKeys.XPATH_USE_XMLBEANS, xpathUseXmlBeans);
    }

    public boolean isXPathUseXmlBeans() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.XPATH_USE_XMLBEANS);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setCompileAnnotationAsJavadoc() {
        return setCompileAnnotationAsJavadoc(true);
    }

    public XmlOptions setCompileAnnotationAsJavadoc(boolean useAnnotationAsJavadoc) {
        return set(XmlOptionsKeys.COMPILE_ANNOTATION_JAVADOC, useAnnotationAsJavadoc);
    }

    public boolean isCompileAnnotationAsJavadoc() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.COMPILE_ANNOTATION_JAVADOC);
        return flag != null && flag.booleanValue();
    }

    public XmlOptions setAttributeValidationCompatMode(boolean attributeValidationCompatMode) {
        return set(XmlOptionsKeys.ATTRIBUTE_VALIDATION_COMPAT_MODE, attributeValidationCompatMode);
    }

    public boolean isAttributeValidationCompatMode() {
        Boolean flag = (Boolean) get(XmlOptionsKeys.ATTRIBUTE_VALIDATION_COMPAT_MODE);
        return flag != null && flag.booleanValue();
    }

    public Set<BeanMethod> getCompilePartialMethod() {
        return (Set) get(XmlOptionsKeys.COMPILE_PARTIAL_METHODS);
    }

    public void setCompilePartialMethod(Set<BeanMethod> list) {
        if (list == null || list.isEmpty()) {
            remove(XmlOptionsKeys.COMPILE_PARTIAL_METHODS);
        } else {
            set(XmlOptionsKeys.COMPILE_PARTIAL_METHODS, list);
        }
    }

    public static XmlOptions maskNull(XmlOptions o) {
        return o == null ? EMPTY_OPTIONS : o;
    }

    private XmlOptions set(XmlOptionsKeys option) {
        return set(option, true);
    }

    private XmlOptions set(XmlOptionsKeys option, Object value) {
        this._map.put(option, value);
        return this;
    }

    private XmlOptions set(XmlOptionsKeys option, int value) {
        return set(option, Integer.valueOf(value));
    }

    private XmlOptions set(XmlOptionsKeys option, boolean value) {
        if (value) {
            set(option, Boolean.TRUE);
        } else {
            remove(option);
        }
        return this;
    }

    public boolean hasOption(XmlOptionsKeys option) {
        return this._map.containsKey(option);
    }

    public Object get(XmlOptionsKeys option) {
        return this._map.get(option);
    }

    public void remove(XmlOptionsKeys option) {
        this._map.remove(option);
    }
}
