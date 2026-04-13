package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import javax.xml.namespace.QName;

/* loaded from: classes11.dex */
public class FactorImports {
    public static void printUsage() {
        System.out.println("Refactors a directory of XSD files to remove name conflicts.");
        System.out.println("Usage: sfactor [-import common.xsd] [-out outputdir] inputdir");
        System.out.println("    -import common.xsd - The XSD file to contain redundant ");
        System.out.println("                         definitions for importing.");
        System.out.println("    -out outputdir - The directory into which to place XSD ");
        System.out.println("                     files resulting from refactoring, ");
        System.out.println("                     plus a commonly imported common.xsd.");
        System.out.println("    inputdir - The directory containing the XSD files with");
        System.out.println("               redundant definitions.");
        System.out.println("    -license - Print license information.");
        System.out.println();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:(3:233|234|(50:236|52|(2:54|55)|56|57|58|(6:60|61|62|63|64|65)|81|82|83|84|85|86|87|88|(3:90|91|92)|96|97|98|(6:100|101|102|103|104|105)|116|117|118|119|120|121|122|123|(6:125|126|127|128|129|130)|141|142|143|144|145|146|147|148|(6:150|151|152|153|154|155)|166|167|168|169|170|171|172|(5:174|175|176|177|178)|182|183|184|71))|143|144|145|146|147|148|(0)|166|167|168|169|170|171|172|(0)|182|183|184|71) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:(20:(3:233|234|(50:236|52|(2:54|55)|56|57|58|(6:60|61|62|63|64|65)|81|82|83|84|85|86|87|88|(3:90|91|92)|96|97|98|(6:100|101|102|103|104|105)|116|117|118|119|120|121|122|123|(6:125|126|127|128|129|130)|141|142|143|144|145|146|147|148|(6:150|151|152|153|154|155)|166|167|168|169|170|171|172|(5:174|175|176|177|178)|182|183|184|71))|143|144|145|146|147|148|(0)|166|167|168|169|170|171|172|(0)|182|183|184|71)|83|84|85|86|87|88|(0)|96|97|98|(0)|116|117|118|119|120|121|122|123|(0)|141|142) */
    /* JADX WARN: Can't wrap try/catch for region: R(59:41|(2:42|43)|(3:233|234|(50:236|52|(2:54|55)|56|57|58|(6:60|61|62|63|64|65)|81|82|83|84|85|86|87|88|(3:90|91|92)|96|97|98|(6:100|101|102|103|104|105)|116|117|118|119|120|121|122|123|(6:125|126|127|128|129|130)|141|142|143|144|145|146|147|148|(6:150|151|152|153|154|155)|166|167|168|169|170|171|172|(5:174|175|176|177|178)|182|183|184|71))|45|46|47|48|49|50|51|52|(0)|56|57|58|(0)|81|82|83|84|85|86|87|88|(0)|96|97|98|(0)|116|117|118|119|120|121|122|123|(0)|141|142|143|144|145|146|147|148|(0)|166|167|168|169|170|171|172|(0)|182|183|184|71) */
    /* JADX WARN: Can't wrap try/catch for region: R(60:41|42|43|(3:233|234|(50:236|52|(2:54|55)|56|57|58|(6:60|61|62|63|64|65)|81|82|83|84|85|86|87|88|(3:90|91|92)|96|97|98|(6:100|101|102|103|104|105)|116|117|118|119|120|121|122|123|(6:125|126|127|128|129|130)|141|142|143|144|145|146|147|148|(6:150|151|152|153|154|155)|166|167|168|169|170|171|172|(5:174|175|176|177|178)|182|183|184|71))|45|46|47|48|49|50|51|52|(0)|56|57|58|(0)|81|82|83|84|85|86|87|88|(0)|96|97|98|(0)|116|117|118|119|120|121|122|123|(0)|141|142|143|144|145|146|147|148|(0)|166|167|168|169|170|171|172|(0)|182|183|184|71) */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x04ce, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x04d7, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x04d8, code lost:
    
        r13 = r17;
        r17 = r12;
        r12 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x04e0, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x04eb, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x04ec, code lost:
    
        r21 = r13;
        r13 = r17;
        r17 = r12;
        r12 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x04f6, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x04f7, code lost:
    
        r12 = r16;
        r16 = r9;
        r9 = r21;
        r21 = r13;
        r13 = r17;
        r17 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0524, code lost:
    
        r12 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0515, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0516, code lost:
    
        r19 = r16;
        r16 = r9;
        r9 = r21;
        r21 = r13;
        r13 = r17;
        r17 = r19;
        r19 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0528, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x053b, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x053c, code lost:
    
        r11 = r16;
        r16 = r9;
        r9 = r21;
        r21 = r13;
        r13 = r17;
        r17 = r11;
        r11 = r19;
        r19 = r12;
        r12 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0550, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0565, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0566, code lost:
    
        r18 = r16;
        r16 = r9;
        r9 = r21;
        r21 = r13;
        r13 = r17;
        r17 = r18;
        r18 = r11;
        r11 = r19;
        r19 = r12;
        r12 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x057c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x057d, code lost:
    
        r10 = r16;
        r16 = r9;
        r9 = r21;
        r21 = r13;
        r13 = r17;
        r17 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x05b0, code lost:
    
        r10 = r18;
        r18 = r11;
        r11 = r19;
        r19 = r12;
        r12 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x05a1, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x05a2, code lost:
    
        r31 = r16;
        r16 = r9;
        r9 = r21;
        r21 = r13;
        r13 = r17;
        r17 = r31;
        r31 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x05bc, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x05df, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x05e0, code lost:
    
        r4 = r20;
        r20 = r6;
        r6 = r4;
        r4 = r23;
        r23 = r8;
        r8 = r4;
        r4 = r16;
        r16 = r9;
        r9 = r21;
        r21 = r13;
        r13 = r17;
        r17 = r4;
        r4 = r40;
        r31 = r10;
        r10 = r18;
        r18 = r11;
        r11 = r19;
        r19 = r12;
        r12 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x0606, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x062a, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x062b, code lost:
    
        r29 = r20;
        r20 = r6;
        r6 = r29;
        r29 = r23;
        r23 = r8;
        r8 = r29;
        r29 = r16;
        r16 = r9;
        r9 = r21;
        r21 = r13;
        r13 = r17;
        r17 = r29;
        r29 = r4;
        r31 = r10;
        r10 = r18;
        r4 = r40;
        r18 = r11;
        r11 = r19;
        r19 = r12;
        r12 = r22;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0339 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03be A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0433 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x04a0 A[Catch: IOException -> 0x04ce, XmlException -> 0x04d7, TRY_LEAVE, TryCatch #28 {IOException -> 0x04ce, XmlException -> 0x04d7, blocks: (B:172:0x0499, B:174:0x04a0), top: B:171:0x0499 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x023c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0303 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 18 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 20 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r40) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 2866
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.FactorImports.main(java.lang.String[]):void");
    }

    private static File outputFileFor(File file, File baseDir, File outdir) {
        URI base = baseDir.getAbsoluteFile().toURI();
        URI abs = file.getAbsoluteFile().toURI();
        URI rel = base.relativize(abs);
        if (rel.isAbsolute()) {
            System.out.println("Cannot relativize " + file);
            return null;
        }
        URI outbase = outdir.toURI();
        URI out = CodeGenUtil.resolve(outbase, rel);
        return new File(out);
    }

    private static URI commonAncestor(URI first, URI second) {
        String firstStr = first.toString();
        String secondStr = second.toString();
        int len = firstStr.length();
        if (secondStr.length() < len) {
            len = secondStr.length();
        }
        int i = 0;
        while (i < len && firstStr.charAt(i) == secondStr.charAt(i)) {
            i++;
        }
        int i2 = i - 1;
        if (i2 >= 0) {
            i2 = firstStr.lastIndexOf(47, i2);
        }
        if (i2 < 0) {
            return null;
        }
        try {
            return new URI(firstStr.substring(0, i2));
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static String relativeURIFor(File source, File target) {
        int i;
        URI base = source.getAbsoluteFile().toURI();
        URI abs = target.getAbsoluteFile().toURI();
        URI commonBase = commonAncestor(base, abs);
        if (commonBase == null) {
            return abs.toString();
        }
        URI baserel = commonBase.relativize(base);
        URI targetrel = commonBase.relativize(abs);
        if (baserel.isAbsolute() || targetrel.isAbsolute()) {
            String prefix = abs.toString();
            return prefix;
        }
        String prefix2 = "";
        String sourceRel = baserel.toString();
        int i2 = 0;
        while (i2 < sourceRel.length() && (i = sourceRel.indexOf(47, i2)) >= 0) {
            prefix2 = prefix2 + "../";
            i2 = i + 1;
        }
        return prefix2 + targetrel.toString();
    }

    private static File commonFileFor(String commonName, String namespace, int i, File outdir) {
        String name = commonName;
        if (i > 0) {
            int index = commonName.lastIndexOf(46);
            if (index < 0) {
                index = commonName.length();
            }
            name = commonName.substring(0, index) + i + commonName.substring(index);
        }
        return new File(outdir, name);
    }

    private static void noteName(String name, String targetNamespace, Set<QName> seen, Set<QName> dupes, Set<String> dupeNamespaces) {
        if (name == null) {
            return;
        }
        QName qName = new QName(targetNamespace, name);
        if (seen.contains(qName)) {
            dupes.add(qName);
            dupeNamespaces.add(targetNamespace);
        } else {
            seen.add(qName);
        }
    }

    private static boolean isFirstDuplicate(String name, String targetNamespace, Set<QName> notseen, Set<QName> dupes) {
        if (name == null) {
            return false;
        }
        QName qName = new QName(targetNamespace, name);
        if (!dupes.contains(qName) || !notseen.contains(qName)) {
            return false;
        }
        notseen.remove(qName);
        return true;
    }

    private static boolean isDuplicate(String name, String targetNamespace, Set<QName> dupes) {
        if (name == null) {
            return false;
        }
        QName qName = new QName(targetNamespace, name);
        return dupes.contains(qName);
    }
}
