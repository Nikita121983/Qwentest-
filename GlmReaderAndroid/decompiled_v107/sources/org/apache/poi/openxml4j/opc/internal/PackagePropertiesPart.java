package org.apache.poi.openxml4j.opc.internal;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageProperties;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public final class PackagePropertiesPart extends PackagePart implements PackageProperties {
    private static final String[] DATE_FORMATS = {"yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", "yyyy-MM-dd"};
    private static final String DEFAULT_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String NAMESPACE_CP_URI = "http://schemas.openxmlformats.org/package/2006/metadata/core-properties";
    public static final String NAMESPACE_DCTERMS_URI = "http://purl.org/dc/terms/";
    public static final String NAMESPACE_DC_URI = "http://purl.org/dc/elements/1.1/";
    private final Pattern TIME_ZONE_PAT;
    private final String[] TZ_DATE_FORMATS;
    protected Optional<String> category;
    protected Optional<String> contentStatus;
    protected Optional<String> contentType;
    protected Optional<Date> created;
    protected Optional<String> creator;
    protected Optional<String> description;
    protected Optional<String> identifier;
    protected Optional<String> keywords;
    protected Optional<String> language;
    protected Optional<String> lastModifiedBy;
    protected Optional<Date> lastPrinted;
    protected Optional<Date> modified;
    protected Optional<String> revision;
    protected Optional<String> subject;
    protected Optional<String> title;
    protected Optional<String> version;

    public PackagePropertiesPart(OPCPackage pack, PackagePartName partName) throws InvalidFormatException {
        super(pack, partName, ContentTypes.CORE_PROPERTIES_PART);
        this.TZ_DATE_FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ss.Sz", "yyyy-MM-dd'T'HH:mm:ss.SSz", "yyyy-MM-dd'T'HH:mm:ss.SSSz"};
        this.TIME_ZONE_PAT = Pattern.compile("([-+]\\d\\d):?(\\d\\d)");
        this.category = Optional.empty();
        this.contentStatus = Optional.empty();
        this.contentType = Optional.empty();
        this.created = Optional.empty();
        this.creator = Optional.empty();
        this.description = Optional.empty();
        this.identifier = Optional.empty();
        this.keywords = Optional.empty();
        this.language = Optional.empty();
        this.lastModifiedBy = Optional.empty();
        this.lastPrinted = Optional.empty();
        this.modified = Optional.empty();
        this.revision = Optional.empty();
        this.subject = Optional.empty();
        this.title = Optional.empty();
        this.version = Optional.empty();
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getCategoryProperty() {
        return this.category;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getContentStatusProperty() {
        return this.contentStatus;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getContentTypeProperty() {
        return this.contentType;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<Date> getCreatedProperty() {
        return this.created;
    }

    public String getCreatedPropertyString() {
        return getDateValue(this.created);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getCreatorProperty() {
        return this.creator;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getDescriptionProperty() {
        return this.description;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getIdentifierProperty() {
        return this.identifier;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getKeywordsProperty() {
        return this.keywords;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getLanguageProperty() {
        return this.language;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getLastModifiedByProperty() {
        return this.lastModifiedBy;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<Date> getLastPrintedProperty() {
        return this.lastPrinted;
    }

    public String getLastPrintedPropertyString() {
        return getDateValue(this.lastPrinted);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<Date> getModifiedProperty() {
        return this.modified;
    }

    public String getModifiedPropertyString() {
        if (this.modified.isPresent()) {
            return getDateValue(this.modified);
        }
        return getDateValue((Optional<Date>) Optional.of(new Date()));
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getRevisionProperty() {
        return this.revision;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getSubjectProperty() {
        return this.subject;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getTitleProperty() {
        return this.title;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public Optional<String> getVersionProperty() {
        return this.version;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setCategoryProperty(String category) {
        this.category = parseStringValue(category);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setCategoryProperty(Optional<String> category) {
        this.category = category;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setContentStatusProperty(String contentStatus) {
        this.contentStatus = parseStringValue(contentStatus);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setContentStatusProperty(Optional<String> contentStatus) {
        this.contentStatus = contentStatus;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setContentTypeProperty(String contentType) {
        this.contentType = parseStringValue(contentType);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setContentTypeProperty(Optional<String> contentType) {
        this.contentType = contentType;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setCreatedProperty(String created) throws InvalidFormatException {
        this.created = parseDateValue(created);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setCreatedProperty(Optional<Date> created) {
        this.created = created;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setCreatorProperty(String creator) {
        this.creator = parseStringValue(creator);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setCreatorProperty(Optional<String> creator) {
        this.creator = creator;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setDescriptionProperty(String description) {
        this.description = parseStringValue(description);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setDescriptionProperty(Optional<String> description) {
        this.description = description;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setIdentifierProperty(String identifier) {
        this.identifier = parseStringValue(identifier);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setIdentifierProperty(Optional<String> identifier) {
        this.identifier = identifier;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setKeywordsProperty(String keywords) {
        this.keywords = parseStringValue(keywords);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setKeywordsProperty(Optional<String> keywords) {
        this.keywords = keywords;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setLanguageProperty(String language) {
        this.language = parseStringValue(language);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setLanguageProperty(Optional<String> language) {
        this.language = language;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setLastModifiedByProperty(String lastModifiedBy) {
        this.lastModifiedBy = parseStringValue(lastModifiedBy);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setLastModifiedByProperty(Optional<String> lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setLastPrintedProperty(String lastPrinted) throws InvalidFormatException {
        this.lastPrinted = parseDateValue(lastPrinted);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setLastPrintedProperty(Optional<Date> lastPrinted) {
        this.lastPrinted = lastPrinted;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setModifiedProperty(String modified) throws InvalidFormatException {
        this.modified = parseDateValue(modified);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setModifiedProperty(Optional<Date> modified) {
        this.modified = modified;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setRevisionProperty(Optional<String> revision) {
        this.revision = revision;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setRevisionProperty(String revision) {
        this.revision = parseStringValue(revision);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setSubjectProperty(String subject) {
        this.subject = parseStringValue(subject);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setSubjectProperty(Optional<String> subject) {
        this.subject = subject;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setTitleProperty(String title) {
        this.title = parseStringValue(title);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setTitleProperty(Optional<String> title) {
        this.title = title;
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setVersionProperty(String version) {
        this.version = parseStringValue(version);
    }

    @Override // org.apache.poi.openxml4j.opc.PackageProperties
    public void setVersionProperty(Optional<String> version) {
        this.version = version;
    }

    private Optional<String> parseStringValue(String s) {
        if (s == null || s.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(s);
    }

    private Optional<Date> parseDateValue(String dateStr) throws InvalidFormatException {
        if (dateStr == null || dateStr.isEmpty()) {
            return Optional.empty();
        }
        Matcher m = this.TIME_ZONE_PAT.matcher(dateStr);
        Date d = null;
        if (m.find()) {
            String dateTzStr = dateStr.substring(0, m.start()) + m.group(1) + m.group(2);
            d = parseDateFormat(this.TZ_DATE_FORMATS, dateTzStr);
        }
        if (d == null) {
            String dateTzStr2 = dateStr.endsWith("Z") ? dateStr : dateStr + "Z";
            d = parseDateFormat(DATE_FORMATS, dateTzStr2);
        }
        if (d != null) {
            return Optional.of(d);
        }
        String allFormats = (String) Stream.of((Object[]) new String[][]{this.TZ_DATE_FORMATS, DATE_FORMATS}).flatMap(new Function() { // from class: org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream of;
                of = Stream.of((Object[]) obj);
                return of;
            }
        }).collect(Collectors.joining(", "));
        throw new InvalidFormatException("Date " + dateStr + " not well formatted, expected format in: " + allFormats);
    }

    private static Date parseDateFormat(String[] formats, String dateTzStr) {
        for (String fStr : formats) {
            SimpleDateFormat df = new SimpleDateFormat(fStr, Locale.ROOT);
            df.setTimeZone(LocaleUtil.TIMEZONE_UTC);
            Date d = df.parse(dateTzStr, new ParsePosition(0));
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    private static String getDateValue(Optional<Date> d) {
        return (String) d.map(new Function() { // from class: org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String dateValue;
                dateValue = PackagePropertiesPart.getDateValue((Date) obj);
                return dateValue;
            }
        }).orElse("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getDateValue(Date d) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
        df.setTimeZone(LocaleUtil.TIMEZONE_UTC);
        return df.format(d);
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    protected InputStream getInputStreamImpl() {
        throw new InvalidOperationException("Operation not authorized. This part may only be manipulated using the getters and setters on PackagePropertiesPart");
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    protected OutputStream getOutputStreamImpl() {
        throw new InvalidOperationException("Can't use output stream to set properties !");
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public boolean save(OutputStream zos) {
        throw new InvalidOperationException("Operation not authorized. This part may only be manipulated using the getters and setters on PackagePropertiesPart");
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public boolean load(InputStream ios) {
        throw new InvalidOperationException("Operation not authorized. This part may only be manipulated using the getters and setters on PackagePropertiesPart");
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void close() {
    }

    @Override // org.apache.poi.openxml4j.opc.PackagePart
    public void flush() {
    }
}
