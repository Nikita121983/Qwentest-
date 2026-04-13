package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

/* loaded from: classes11.dex */
public class TypeHierarchyPrinter {
    public static void printUsage() {
        System.out.println("Prints the inheritance hierarchy of types defined in a schema.\n");
        System.out.println("Usage: xsdtree [-noanon] [-nopvr] [-noupa] [-partial] [-license] schemafile.xsd*");
        System.out.println("    -noanon - Don't include anonymous types in the tree.");
        System.out.println("    -noupa - do not enforce the unique particle attribution rule");
        System.out.println("    -nopvr - do not enforce the particle valid (restriction) rule");
        System.out.println("    -partial - Print only part of the hierarchy.");
        System.out.println("    -license - prints license information");
        System.out.println("    schemafile.xsd - File containing the schema for which to print a tree.");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        SchemaTypeLoader linkTo;
        SchemaTypeSystem typeSystem;
        List<SchemaType> allSeenTypes;
        boolean noupa;
        boolean partial;
        int i;
        boolean nopvr;
        Set<String> flags = new HashSet<>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        flags.add("noanon");
        flags.add("noupr");
        flags.add("noupa");
        flags.add("partial");
        CommandLine cl = new CommandLine(args, flags, Collections.EMPTY_SET);
        if (cl.getOpt("h") == null && cl.getOpt("help") == null) {
            if (cl.getOpt("usage") == null) {
                String[] badopts = cl.getBadOpts();
                if (badopts.length > 0) {
                    for (String str : badopts) {
                        System.out.println("Unrecognized option: " + str);
                    }
                    printUsage();
                    System.exit(0);
                    return;
                }
                if (cl.getOpt("license") != null) {
                    CommandLine.printLicense();
                    System.exit(0);
                    return;
                }
                if (cl.getOpt("version") != null) {
                    CommandLine.printVersion();
                    System.exit(0);
                    return;
                }
                if (cl.args().length == 0) {
                    printUsage();
                    return;
                }
                boolean noanon = cl.getOpt("noanon") != null;
                boolean nopvr2 = cl.getOpt("nopvr") != null;
                boolean noupa2 = cl.getOpt("noupa") != null;
                boolean partial2 = cl.getOpt("partial") != null;
                File[] schemaFiles = cl.filesEndingWith(".xsd");
                File[] jarFiles = cl.filesEndingWith(".jar");
                List<XmlObject> sdocs = new ArrayList<>();
                for (int i2 = 0; i2 < schemaFiles.length; i2++) {
                    try {
                        try {
                            sdocs.add(SchemaDocument.Factory.parse(schemaFiles[i2], new XmlOptions().setLoadLineNumbers()));
                        } catch (Exception e) {
                            e = e;
                            System.err.println(schemaFiles[i2] + " not loadable: " + e);
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                XmlObject[] schemas = (XmlObject[]) sdocs.toArray(new XmlObject[0]);
                Collection<XmlError> compErrors = new ArrayList<>();
                XmlOptions schemaOptions = new XmlOptions();
                schemaOptions.setErrorListener(compErrors);
                schemaOptions.setCompileDownloadUrls();
                if (nopvr2) {
                    schemaOptions.setCompileNoPvrRule();
                }
                if (noupa2) {
                    schemaOptions.setCompileNoUpaRule();
                }
                if (partial2) {
                    schemaOptions.setCompilePartialTypesystem();
                }
                if (jarFiles != null && jarFiles.length > 0) {
                    SchemaTypeLoader linkTo2 = XmlBeans.typeLoaderForResource(XmlBeans.resourceLoaderForPath(jarFiles));
                    linkTo = linkTo2;
                } else {
                    linkTo = null;
                }
                try {
                    SchemaTypeSystem typeSystem2 = XmlBeans.compileXsd(schemas, linkTo, schemaOptions);
                    if (!partial2 || compErrors.isEmpty()) {
                        typeSystem = typeSystem2;
                    } else {
                        typeSystem = typeSystem2;
                        System.out.println("Schema invalid: partial schema type system recovered");
                        Iterator<XmlError> i3 = compErrors.iterator();
                        while (i3.hasNext()) {
                            Iterator<XmlError> i4 = i3;
                            System.out.println(i4.next());
                            i3 = i4;
                        }
                    }
                    Map<String, String> prefixes = new HashMap<>();
                    prefixes.put("http://www.w3.org/XML/1998/namespace", "xml");
                    prefixes.put("http://www.w3.org/2001/XMLSchema", "xs");
                    System.out.println("xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"");
                    Map<SchemaType, Collection<SchemaType>> childTypes = new HashMap<>();
                    List<SchemaType> allSeenTypes2 = new ArrayList<>();
                    allSeenTypes2.addAll(Arrays.asList(typeSystem.documentTypes()));
                    allSeenTypes2.addAll(Arrays.asList(typeSystem.attributeTypes()));
                    allSeenTypes2.addAll(Arrays.asList(typeSystem.globalTypes()));
                    int i5 = 0;
                    while (true) {
                        boolean noanon2 = noanon;
                        if (i5 >= allSeenTypes2.size()) {
                            break;
                        }
                        SchemaType sType = allSeenTypes2.get(i5);
                        if (noanon2) {
                            i = i5;
                        } else {
                            i = i5;
                            allSeenTypes2.addAll(Arrays.asList(sType.getAnonymousTypes()));
                        }
                        if (sType.isDocumentType() || sType.isAttributeType()) {
                            nopvr = nopvr2;
                        } else if (sType == XmlObject.type) {
                            nopvr = nopvr2;
                        } else {
                            noteNamespace(prefixes, sType);
                            Collection<SchemaType> children = childTypes.get(sType.getBaseType());
                            if (children != null) {
                                nopvr = nopvr2;
                            } else {
                                children = new ArrayList<>();
                                nopvr = nopvr2;
                                childTypes.put(sType.getBaseType(), children);
                                if (sType.getBaseType().isBuiltinType()) {
                                    allSeenTypes2.add(sType.getBaseType());
                                }
                            }
                            children.add(sType);
                        }
                        i5 = i + 1;
                        noanon = noanon2;
                        nopvr2 = nopvr;
                    }
                    List typesToPrint = new ArrayList();
                    typesToPrint.add(XmlObject.type);
                    StringBuilder spaces = new StringBuilder();
                    while (!typesToPrint.isEmpty()) {
                        SchemaType sType2 = (SchemaType) typesToPrint.remove(typesToPrint.size() - 1);
                        if (sType2 == null) {
                            allSeenTypes = allSeenTypes2;
                            noupa = noupa2;
                            spaces.setLength(Math.max(0, spaces.length() - 2));
                            partial = partial2;
                        } else {
                            allSeenTypes = allSeenTypes2;
                            noupa = noupa2;
                            partial = partial2;
                            System.out.println(((Object) spaces) + "+-" + QNameHelper.readable(sType2, prefixes) + notes(sType2));
                            Collection<SchemaType> children2 = childTypes.get(sType2);
                            if (children2 != null && !children2.isEmpty()) {
                                spaces.append((typesToPrint.isEmpty() || typesToPrint.get(typesToPrint.size() + (-1)) == null) ? "  " : "| ");
                                typesToPrint.add(null);
                                typesToPrint.addAll(children2);
                            }
                        }
                        noupa2 = noupa;
                        allSeenTypes2 = allSeenTypes;
                        partial2 = partial;
                    }
                    return;
                } catch (XmlException e3) {
                    System.out.println("Schema invalid:" + (partial2 ? " couldn't recover from errors" : ""));
                    if (compErrors.isEmpty()) {
                        System.out.println(e3.getMessage());
                        return;
                    }
                    Iterator<XmlError> i6 = compErrors.iterator();
                    while (i6.hasNext()) {
                        System.out.println(i6.next());
                    }
                    return;
                }
            }
        }
        printUsage();
        System.exit(0);
    }

    private static String notes(SchemaType sType) {
        if (sType.isBuiltinType()) {
            return " (builtin)";
        }
        if (sType.isSimpleType()) {
            switch (sType.getSimpleVariety()) {
                case 2:
                    return " (union)";
                case 3:
                    return " (list)";
                default:
                    return sType.getEnumerationValues() != null ? " (enumeration)" : "";
            }
        }
        switch (sType.getContentType()) {
            case 2:
                return " (complex)";
            case 3:
            default:
                return "";
            case 4:
                return " (mixed)";
        }
    }

    private static void noteNamespace(Map prefixes, SchemaType sType) {
        String namespace = QNameHelper.namespace(sType);
        if (namespace.equals("") || prefixes.containsKey(namespace)) {
            return;
        }
        String base = QNameHelper.suggestPrefix(namespace);
        String result = base;
        int n = 0;
        while (prefixes.containsValue(result)) {
            result = base + n;
            n++;
        }
        prefixes.put(namespace, result);
        System.out.println(Sax2Dom.XMLNS_STRING + result + "=\"" + namespace + "\"");
    }
}
