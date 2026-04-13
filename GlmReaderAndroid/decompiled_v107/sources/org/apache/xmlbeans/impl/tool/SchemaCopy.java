package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument;
import org.apache.xmlbeans.impl.xb.substwsdl.TImport;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

/* loaded from: classes11.dex */
public class SchemaCopy {
    private static final XmlOptions loadOptions = new XmlOptions().setLoadSubstituteNamespaces(Collections.singletonMap("http://schemas.xmlsoap.org/wsdl/", "http://www.apache.org/internal/xmlbeans/wsdlsubst"));

    public static void printUsage() {
        System.out.println("Copies the XML schema at the specified URL to the specified file.");
        System.out.println("Usage: scopy sourceurl [targetfile]");
        System.out.println("    sourceurl - The URL at which the schema is located.");
        System.out.println("    targetfile - The file to which the schema should be copied.");
        System.out.println();
    }

    public static void main(String[] args) {
        URI target;
        URI target2;
        if (args.length < 1 || args.length > 2) {
            printUsage();
            return;
        }
        try {
            if (args[0].compareToIgnoreCase("-usage") == 0) {
                printUsage();
                return;
            }
            URI source = new URI(args[0]);
            source.toURL();
            if (args.length < 2) {
                try {
                    URI dir = new File(".").getCanonicalFile().toURI();
                    String lastPart = source.getPath();
                    target = CodeGenUtil.resolve(dir, URI.create(lastPart.substring(lastPart.lastIndexOf(47) + 1)));
                } catch (Exception e) {
                    System.err.println("Cannot canonicalize current directory");
                    return;
                }
            } else {
                try {
                    target2 = new URI(args[1]);
                    if (!target2.isAbsolute()) {
                        target2 = null;
                    } else if (!target2.getScheme().equals("file")) {
                        target2 = null;
                    }
                } catch (Exception e2) {
                    target2 = null;
                }
                if (target2 != null) {
                    target = target2;
                } else {
                    try {
                        target = Paths.get("", new String[0]).toAbsolutePath().toUri();
                    } catch (Exception e3) {
                        System.err.println("Cannot canonicalize current directory");
                        return;
                    }
                }
            }
            Map<URI, URI> thingsToCopy = findAllRelative(source, target);
            copyAll(thingsToCopy, true);
        } catch (Exception e4) {
            System.err.println("Badly formed URL " + ((Object) null));
        }
    }

    private static void copyAll(Map<URI, URI> uriMap, boolean stdout) {
        for (URI source : uriMap.keySet()) {
            URI target = uriMap.get(source);
            try {
                IOUtil.copyCompletely(source, target);
                if (stdout) {
                    System.out.println("Copied " + source + " -> " + target);
                }
            } catch (Exception e) {
                if (stdout) {
                    System.out.println("Could not copy " + source + " -> " + target);
                }
            }
        }
    }

    public static Map<URI, URI> findAllRelative(URI source, URI target) {
        Map<URI, URI> result = new LinkedHashMap<>();
        result.put(source, target);
        LinkedList<URI> process = new LinkedList<>();
        process.add(source);
        while (!process.isEmpty()) {
            URI nextSource = process.removeFirst();
            URI nextTarget = result.get(nextSource);
            Map<URI, URI> nextResults = findRelativeInOne(nextSource, nextTarget);
            for (URI newSource : nextResults.keySet()) {
                if (!result.containsKey(newSource)) {
                    result.put(newSource, nextResults.get(newSource));
                    process.add(newSource);
                }
            }
        }
        return result;
    }

    private static Map<URI, URI> findRelativeInOne(URI source, URI target) {
        try {
            URL sourceURL = source.toURL();
            XmlObject xobj = XmlObject.Factory.parse(sourceURL, loadOptions);
            Map<URI, URI> result = new LinkedHashMap<>();
            if (xobj instanceof SchemaDocument) {
                putMappingsFromSchema(result, source, target, ((SchemaDocument) xobj).getSchema());
            } else if (xobj instanceof DefinitionsDocument) {
                putMappingsFromWsdl(result, source, target, ((DefinitionsDocument) xobj).getDefinitions());
            }
            return result;
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    private static void putNewMapping(Map<URI, URI> result, URI origSource, URI origTarget, String literalURI) {
        if (literalURI == null) {
            return;
        }
        try {
            URI newRelative = new URI(literalURI);
            if (newRelative.isAbsolute()) {
                return;
            }
            URI newSource = CodeGenUtil.resolve(origSource, newRelative);
            URI newTarget = CodeGenUtil.resolve(origTarget, newRelative);
            result.put(newSource, newTarget);
        } catch (URISyntaxException e) {
        }
    }

    private static void putMappingsFromSchema(Map<URI, URI> result, URI source, URI target, SchemaDocument.Schema schema) {
        for (ImportDocument.Import anImport : schema.getImportArray()) {
            putNewMapping(result, source, target, anImport.getSchemaLocation());
        }
        for (IncludeDocument.Include include : schema.getIncludeArray()) {
            putNewMapping(result, source, target, include.getSchemaLocation());
        }
    }

    private static void putMappingsFromWsdl(Map<URI, URI> result, URI source, URI target, DefinitionsDocument.Definitions wdoc) {
        for (XmlObject type : wdoc.getTypesArray()) {
            SchemaDocument.Schema[] schemas = (SchemaDocument.Schema[]) type.selectPath("declare namespace xs='http://www.w3.org/2001/XMLSchema' xs:schema");
            for (SchemaDocument.Schema schema : schemas) {
                putMappingsFromSchema(result, source, target, schema);
            }
        }
        for (TImport anImport : wdoc.getImportArray()) {
            putNewMapping(result, source, target, anImport.getLocation());
        }
    }
}
