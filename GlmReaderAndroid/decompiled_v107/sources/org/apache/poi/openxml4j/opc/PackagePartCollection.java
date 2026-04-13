package org.apache.poi.openxml4j.opc;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

/* loaded from: classes10.dex */
public final class PackagePartCollection implements Serializable {
    private static final long serialVersionUID = 2515031135957635517L;
    private final Set<String> registerPartNameStr = new HashSet();
    private final TreeMap<String, PackagePart> packagePartLookup = new TreeMap<>(new Comparator() { // from class: org.apache.poi.openxml4j.opc.PackagePartCollection$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return PackagePartName.compare((String) obj, (String) obj2);
        }
    });

    /* renamed from: $r8$lambda$pfkoovNdWMaDDkm0ILr5-ARkfn4, reason: not valid java name */
    public static /* synthetic */ SparseBitSet m2494$r8$lambda$pfkoovNdWMaDDkm0ILr5ARkfn4() {
        return new SparseBitSet();
    }

    public PackagePart put(PackagePartName partName, PackagePart part) {
        String ppName = partName.getName();
        StringBuilder concatSeg = new StringBuilder();
        for (String seg : ppName.split("(?=[/])")) {
            concatSeg.append(seg);
            if (this.registerPartNameStr.contains(concatSeg.toString())) {
                throw new InvalidOperationException("You can't add a part with a part name derived from another part ! [M1.11]");
            }
        }
        this.registerPartNameStr.add(ppName);
        return this.packagePartLookup.put(ppName, part);
    }

    public PackagePart remove(PackagePartName key) {
        if (key == null) {
            return null;
        }
        String ppName = key.getName();
        PackagePart pp = this.packagePartLookup.remove(ppName);
        if (pp != null) {
            this.registerPartNameStr.remove(ppName);
        }
        return pp;
    }

    public Collection<PackagePart> sortedValues() {
        return Collections.unmodifiableCollection(this.packagePartLookup.values());
    }

    public boolean containsKey(PackagePartName partName) {
        return partName != null && this.packagePartLookup.containsKey(partName.getName());
    }

    public PackagePart get(PackagePartName partName) {
        if (partName == null) {
            return null;
        }
        return this.packagePartLookup.get(partName.getName());
    }

    public int size() {
        return this.packagePartLookup.size();
    }

    public int getUnusedPartIndex(String nameTemplate) throws InvalidFormatException {
        if (nameTemplate == null || !nameTemplate.contains("#")) {
            throw new InvalidFormatException("name template must not be null and contain an index char (#)");
        }
        final Pattern pattern = Pattern.compile(nameTemplate.replace("#", "([0-9]+)"));
        ToIntFunction<String> indexFromName = new ToIntFunction() { // from class: org.apache.poi.openxml4j.opc.PackagePartCollection$$ExternalSyntheticLambda1
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return PackagePartCollection.lambda$getUnusedPartIndex$0(pattern, (String) obj);
            }
        };
        return ((SparseBitSet) this.packagePartLookup.keySet().stream().mapToInt(indexFromName).collect(new Supplier() { // from class: org.apache.poi.openxml4j.opc.PackagePartCollection$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return PackagePartCollection.m2494$r8$lambda$pfkoovNdWMaDDkm0ILr5ARkfn4();
            }
        }, new ObjIntConsumer() { // from class: org.apache.poi.openxml4j.opc.PackagePartCollection$$ExternalSyntheticLambda3
            @Override // java.util.function.ObjIntConsumer
            public final void accept(Object obj, int i) {
                ((SparseBitSet) obj).set(i);
            }
        }, new BiConsumer() { // from class: org.apache.poi.openxml4j.opc.PackagePartCollection$$ExternalSyntheticLambda4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((SparseBitSet) obj).or((SparseBitSet) obj2);
            }
        })).nextClearBit(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getUnusedPartIndex$0(Pattern pattern, String name) {
        Matcher m = pattern.matcher(name);
        if (m.matches()) {
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeParts() {
        for (PackagePart part : this.packagePartLookup.values()) {
            part.close();
        }
    }
}
