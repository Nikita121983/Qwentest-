package org.apache.commons.compress.harmony.pack200;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.objectweb.asm.Attribute;

/* loaded from: classes9.dex */
public class PackingOptions {
    private static final Attribute[] EMPTY_ATTRIBUTE_ARRAY = new Attribute[0];
    public static final String ERROR = "error";
    public static final String KEEP = "keep";
    public static final String PASS = "pass";
    public static final long SEGMENT_LIMIT = 1000000;
    public static final String STRIP = "strip";
    private String logFile;
    private boolean stripDebug;
    private Attribute[] unknownAttributeTypes;
    private boolean verbose;
    private boolean gzip = true;
    private boolean keepFileOrder = true;
    private long segmentLimit = SEGMENT_LIMIT;
    private int effort = 5;
    private String deflateHint = "keep";
    private String modificationTime = "keep";
    private final List<String> passFiles = new ArrayList();
    private String unknownAttributeAction = "pass";
    private final Map<String, String> classAttributeActions = new HashMap();
    private final Map<String, String> fieldAttributeActions = new HashMap();
    private final Map<String, String> methodAttributeActions = new HashMap();
    private final Map<String, String> codeAttributeActions = new HashMap();

    public void addClassAttributeAction(String attributeName, String action) {
        this.classAttributeActions.put(attributeName, action);
    }

    public void addCodeAttributeAction(String attributeName, String action) {
        this.codeAttributeActions.put(attributeName, action);
    }

    public void addFieldAttributeAction(String attributeName, String action) {
        this.fieldAttributeActions.put(attributeName, action);
    }

    public void addMethodAttributeAction(String attributeName, String action) {
        this.methodAttributeActions.put(attributeName, action);
    }

    private void addOrUpdateAttributeActions(List<Attribute> prototypes, Map<String, String> attributeActions, int tag) {
        char c;
        NewAttribute newAttribute;
        if (attributeActions != null && attributeActions.size() > 0) {
            for (Map.Entry<String, String> entry : attributeActions.entrySet()) {
                String name = entry.getKey();
                String action = entry.getValue();
                boolean prototypeExists = false;
                Iterator<Attribute> it = prototypes.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object prototype = it.next();
                        NewAttribute newAttribute2 = (NewAttribute) prototype;
                        if (newAttribute2.type.equals(name)) {
                            newAttribute2.addContext(tag);
                            prototypeExists = true;
                        }
                    }
                }
                if (!prototypeExists) {
                    switch (action.hashCode()) {
                        case 3433489:
                            if (action.equals("pass")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 96784904:
                            if (action.equals("error")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 109773592:
                            if (action.equals("strip")) {
                                c = 1;
                                break;
                            }
                            break;
                    }
                    c = 65535;
                    switch (c) {
                        case 0:
                            newAttribute = new NewAttribute.ErrorAttribute(name, tag);
                            break;
                        case 1:
                            newAttribute = new NewAttribute.StripAttribute(name, tag);
                            break;
                        case 2:
                            newAttribute = new NewAttribute.PassAttribute(name, tag);
                            break;
                        default:
                            newAttribute = new NewAttribute(name, action, tag);
                            break;
                    }
                    prototypes.add(newAttribute);
                }
            }
        }
    }

    public void addPassFile(String passFileName) {
        String fileSeparator = FileSystems.getDefault().getSeparator();
        if (fileSeparator.equals("\\")) {
            fileSeparator = fileSeparator + "\\";
        }
        this.passFiles.add(passFileName.replaceAll(fileSeparator, PackagingURIHelper.FORWARD_SLASH_STRING));
    }

    public String getDeflateHint() {
        return this.deflateHint;
    }

    public int getEffort() {
        return this.effort;
    }

    public String getLogFile() {
        return this.logFile;
    }

    public String getModificationTime() {
        return this.modificationTime;
    }

    private String getOrDefault(Map<String, String> map, String type, String defaultValue) {
        return map == null ? defaultValue : map.getOrDefault(type, defaultValue);
    }

    public long getSegmentLimit() {
        return this.segmentLimit;
    }

    public String getUnknownAttributeAction() {
        return this.unknownAttributeAction;
    }

    public Attribute[] getUnknownAttributePrototypes() {
        if (this.unknownAttributeTypes == null) {
            List<Attribute> prototypes = new ArrayList<>();
            addOrUpdateAttributeActions(prototypes, this.classAttributeActions, 0);
            addOrUpdateAttributeActions(prototypes, this.methodAttributeActions, 2);
            addOrUpdateAttributeActions(prototypes, this.fieldAttributeActions, 1);
            addOrUpdateAttributeActions(prototypes, this.codeAttributeActions, 3);
            this.unknownAttributeTypes = (Attribute[]) prototypes.toArray(EMPTY_ATTRIBUTE_ARRAY);
        }
        return this.unknownAttributeTypes;
    }

    public String getUnknownClassAttributeAction(String type) {
        return getOrDefault(this.classAttributeActions, type, this.unknownAttributeAction);
    }

    public String getUnknownCodeAttributeAction(String type) {
        return getOrDefault(this.codeAttributeActions, type, this.unknownAttributeAction);
    }

    public String getUnknownFieldAttributeAction(String type) {
        return getOrDefault(this.fieldAttributeActions, type, this.unknownAttributeAction);
    }

    public String getUnknownMethodAttributeAction(String type) {
        return getOrDefault(this.methodAttributeActions, type, this.unknownAttributeAction);
    }

    public boolean isGzip() {
        return this.gzip;
    }

    public boolean isKeepDeflateHint() {
        return "keep".equals(this.deflateHint);
    }

    public boolean isKeepFileOrder() {
        return this.keepFileOrder;
    }

    public boolean isPassFile(String passFileName) {
        Iterator<String> it = this.passFiles.iterator();
        while (it.hasNext()) {
            String pass = it.next();
            if (passFileName.equals(pass)) {
                return true;
            }
            if (!pass.endsWith(".class")) {
                if (!pass.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                    pass = pass + PackagingURIHelper.FORWARD_SLASH_STRING;
                }
                return passFileName.startsWith(pass);
            }
        }
        return false;
    }

    public boolean isStripDebug() {
        return this.stripDebug;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void removePassFile(String passFileName) {
        this.passFiles.remove(passFileName);
    }

    public void setDeflateHint(String deflateHint) {
        if (!"keep".equals(deflateHint) && !"true".equals(deflateHint) && !"false".equals(deflateHint)) {
            throw new IllegalArgumentException("Bad argument: -H " + deflateHint + " ? deflate hint should be either true, false or keep (default)");
        }
        this.deflateHint = deflateHint;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public void setGzip(boolean gzip) {
        this.gzip = gzip;
    }

    public void setKeepFileOrder(boolean keepFileOrder) {
        this.keepFileOrder = keepFileOrder;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public void setModificationTime(String modificationTime) {
        if (!"keep".equals(modificationTime) && !Pack200.Packer.LATEST.equals(modificationTime)) {
            throw new IllegalArgumentException("Bad argument: -m " + modificationTime + " ? transmit modtimes should be either latest or keep (default)");
        }
        this.modificationTime = modificationTime;
    }

    public void setQuiet(boolean quiet) {
        this.verbose = !quiet;
    }

    public void setSegmentLimit(long segmentLimit) {
        this.segmentLimit = segmentLimit;
    }

    public void setStripDebug(boolean stripDebug) {
        this.stripDebug = stripDebug;
    }

    public void setUnknownAttributeAction(String unknownAttributeAction) {
        this.unknownAttributeAction = unknownAttributeAction;
        if (!"pass".equals(unknownAttributeAction) && !"error".equals(unknownAttributeAction) && !"strip".equals(unknownAttributeAction)) {
            throw new IllegalArgumentException("Incorrect option for -U, " + unknownAttributeAction);
        }
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
