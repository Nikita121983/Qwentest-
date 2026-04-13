package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda5;

/* loaded from: classes11.dex */
public class SchemaDependencies {
    private final Map<String, List<String>> _contributions = new HashMap();
    private final Map<String, Set<String>> _dependencies = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Set lambda$registerDependency$0(String k) {
        return new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerDependency(String source, String target) {
        this._dependencies.computeIfAbsent(target, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaDependencies$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SchemaDependencies.lambda$registerDependency$0((String) obj);
            }
        }).add(source);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> computeTransitiveClosure(List<String> modifiedNamespaces) {
        List<String> nsList = new ArrayList<>(modifiedNamespaces);
        Set<String> result = new HashSet<>(modifiedNamespaces);
        for (int i = 0; i < nsList.size(); i++) {
            Set<String> deps = this._dependencies.get(nsList.get(i));
            if (deps != null) {
                for (String ns : deps) {
                    if (!result.contains(ns)) {
                        nsList.add(ns);
                        result.add(ns);
                    }
                }
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaDependencies() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaDependencies(SchemaDependencies base, Set<String> updatedNs) {
        for (String target : base._dependencies.keySet()) {
            if (!updatedNs.contains(target)) {
                Set<String> depSet = new HashSet<>();
                this._dependencies.put(target, depSet);
                Set<String> baseDepSet = base._dependencies.get(target);
                for (String source : baseDepSet) {
                    if (!updatedNs.contains(source)) {
                        depSet.add(source);
                    }
                }
            }
        }
        for (String ns : base._contributions.keySet()) {
            if (!updatedNs.contains(ns)) {
                List<String> fileList = new ArrayList<>();
                this._contributions.put(ns, fileList);
                fileList.addAll(base._contributions.get(ns));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$registerContribution$1(String k) {
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerContribution(String ns, String fileURL) {
        this._contributions.computeIfAbsent(ns, new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaDependencies$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SchemaDependencies.lambda$registerContribution$1((String) obj);
            }
        }).add(fileURL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFileRepresented(final String fileURL) {
        return this._contributions.values().stream().anyMatch(new Predicate() { // from class: org.apache.xmlbeans.impl.schema.SchemaDependencies$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean contains;
                contains = ((List) obj).contains(fileURL);
                return contains;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getFilesTouched(Set<String> updatedNs) {
        Stream<String> stream = updatedNs.stream();
        final Map<String, List<String>> map = this._contributions;
        map.getClass();
        return (List) stream.map(new Function() { // from class: org.apache.xmlbeans.impl.schema.SchemaDependencies$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (List) map.get((String) obj);
            }
        }).filter(new Predicate() { // from class: org.apache.xmlbeans.impl.schema.SchemaDependencies$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((List) obj);
                return nonNull;
            }
        }).flatMap(new SchemaDependencies$$ExternalSyntheticLambda3()).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getNamespacesTouched(final Set<String> modifiedFiles) {
        return (List) this._contributions.entrySet().stream().filter(new Predicate() { // from class: org.apache.xmlbeans.impl.schema.SchemaDependencies$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SchemaDependencies.lambda$getNamespacesTouched$3(modifiedFiles, (Map.Entry) obj);
            }
        }).map(new ClassUtils$$ExternalSyntheticLambda5()).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getNamespacesTouched$3(final Set modifiedFiles, Map.Entry e) {
        Stream stream = ((List) e.getValue()).stream();
        modifiedFiles.getClass();
        return stream.anyMatch(new Predicate() { // from class: org.apache.xmlbeans.impl.schema.SchemaDependencies$$ExternalSyntheticLambda7
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return modifiedFiles.contains((String) obj);
            }
        });
    }
}
