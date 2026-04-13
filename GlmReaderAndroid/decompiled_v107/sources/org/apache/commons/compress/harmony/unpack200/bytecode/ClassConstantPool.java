package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import org.apache.commons.compress.harmony.unpack200.Segment;

/* loaded from: classes9.dex */
public class ClassConstantPool {
    protected Map<ClassFileEntry, Integer> indexCache;
    private boolean resolved;
    protected HashSet<ClassFileEntry> entriesContainsSet = new HashSet<>();
    protected HashSet<ClassFileEntry> othersContainsSet = new HashSet<>();
    private final HashSet<ClassFileEntry> mustStartClassPool = new HashSet<>();
    private final List<ClassFileEntry> others = new ArrayList(500);
    private final List<ClassFileEntry> entries = new ArrayList(500);

    public ClassFileEntry add(ClassFileEntry entry) {
        if (entry instanceof ByteCode) {
            return null;
        }
        if (entry instanceof ConstantPoolEntry) {
            if (this.entriesContainsSet.add(entry)) {
                this.entries.add(entry);
            }
        } else if (this.othersContainsSet.add(entry)) {
            this.others.add(entry);
        }
        return entry;
    }

    public void addNestedEntries() {
        boolean isAtStart;
        boolean added = true;
        List<ClassFileEntry> parents = new ArrayList<>(512);
        List<ClassFileEntry> children = new ArrayList<>(512);
        parents.addAll(this.entries);
        parents.addAll(this.others);
        while (true) {
            if (added || parents.size() > 0) {
                children.clear();
                int entriesOriginalSize = this.entries.size();
                int othersOriginalSize = this.others.size();
                int indexParents = 0;
                while (true) {
                    isAtStart = false;
                    if (indexParents >= parents.size()) {
                        break;
                    }
                    ClassFileEntry entry = parents.get(indexParents);
                    ClassFileEntry[] entryChildren = entry.getNestedClassFileEntries();
                    children.addAll(Arrays.asList(entryChildren));
                    if ((entry instanceof ByteCode) && ((ByteCode) entry).nestedMustStartClassPool()) {
                        isAtStart = true;
                    }
                    if (isAtStart) {
                        this.mustStartClassPool.addAll(Arrays.asList(entryChildren));
                    }
                    add(entry);
                    indexParents++;
                }
                if (this.entries.size() != entriesOriginalSize || this.others.size() != othersOriginalSize) {
                    isAtStart = true;
                }
                added = isAtStart;
                parents.clear();
                parents.addAll(children);
            } else {
                return;
            }
        }
    }

    public ClassFileEntry addWithNestedEntries(ClassFileEntry entry) {
        add(entry);
        for (ClassFileEntry nestedEntry : entry.getNestedClassFileEntries()) {
            addWithNestedEntries(nestedEntry);
        }
        return entry;
    }

    public List<ClassFileEntry> entries() {
        return Collections.unmodifiableList(this.entries);
    }

    public ClassFileEntry get(int i) {
        if (!this.resolved) {
            throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
        }
        return this.entries.get(i - 1);
    }

    public int indexOf(ClassFileEntry entry) {
        if (!this.resolved) {
            throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
        }
        if (this.indexCache == null) {
            throw new IllegalStateException("Index cache is not initialized!");
        }
        Integer entryIndex = this.indexCache.get(entry);
        if (entryIndex != null) {
            return entryIndex.intValue() + 1;
        }
        return -1;
    }

    private void initialSort() {
        TreeSet<ClassFileEntry> inCpAll = new TreeSet<>((Comparator<? super ClassFileEntry>) Comparator.comparingInt(new ToIntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool$$ExternalSyntheticLambda2
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int globalIndex;
                globalIndex = ((ConstantPoolEntry) ((ClassFileEntry) obj)).getGlobalIndex();
                return globalIndex;
            }
        }));
        TreeSet<ClassFileEntry> cpUtf8sNotInCpAll = new TreeSet<>((Comparator<? super ClassFileEntry>) Comparator.comparing(new Function() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String underlyingString;
                underlyingString = ((CPUTF8) ((ClassFileEntry) obj)).underlyingString();
                return underlyingString;
            }
        }));
        TreeSet<ClassFileEntry> cpClassesNotInCpAll = new TreeSet<>((Comparator<? super ClassFileEntry>) Comparator.comparing(new Function() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ((CPClass) ((ClassFileEntry) obj)).getName();
                return name;
            }
        }));
        for (ClassFileEntry entry2 : this.entries) {
            ConstantPoolEntry entry = (ConstantPoolEntry) entry2;
            if (entry.getGlobalIndex() == -1) {
                if (entry instanceof CPUTF8) {
                    cpUtf8sNotInCpAll.add(entry);
                } else if (entry instanceof CPClass) {
                    cpClassesNotInCpAll.add(entry);
                } else {
                    throw new Error("error");
                }
            } else {
                inCpAll.add(entry);
            }
        }
        this.entries.clear();
        this.entries.addAll(inCpAll);
        this.entries.addAll(cpUtf8sNotInCpAll);
        this.entries.addAll(cpClassesNotInCpAll);
    }

    public void resolve(Segment segment) {
        initialSort();
        sortClassPool();
        this.resolved = true;
        this.entries.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ClassConstantPool.this.m2089xce19110a((ClassFileEntry) obj);
            }
        });
        this.others.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ClassConstantPool.this.m2090xe88a0a29((ClassFileEntry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$resolve$3$org-apache-commons-compress-harmony-unpack200-bytecode-ClassConstantPool, reason: not valid java name */
    public /* synthetic */ void m2089xce19110a(ClassFileEntry entry) {
        entry.resolve(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$resolve$4$org-apache-commons-compress-harmony-unpack200-bytecode-ClassConstantPool, reason: not valid java name */
    public /* synthetic */ void m2090xe88a0a29(ClassFileEntry entry) {
        entry.resolve(this);
    }

    public int size() {
        return this.entries.size();
    }

    protected void sortClassPool() {
        List<ClassFileEntry> startOfPool = new ArrayList<>(this.entries.size());
        List<ClassFileEntry> finalSort = new ArrayList<>(this.entries.size());
        for (ClassFileEntry entry : this.entries) {
            if (this.mustStartClassPool.contains(entry)) {
                startOfPool.add(entry);
            } else {
                finalSort.add(entry);
            }
        }
        this.indexCache = new HashMap(this.entries.size());
        int index = 0;
        this.entries.clear();
        for (ClassFileEntry entry2 : startOfPool) {
            this.indexCache.put(entry2, Integer.valueOf(index));
            if ((entry2 instanceof CPLong) || (entry2 instanceof CPDouble)) {
                this.entries.add(entry2);
                this.entries.add(entry2);
                index += 2;
            } else {
                this.entries.add(entry2);
                index++;
            }
        }
        for (ClassFileEntry entry3 : finalSort) {
            this.indexCache.put(entry3, Integer.valueOf(index));
            if ((entry3 instanceof CPLong) || (entry3 instanceof CPDouble)) {
                this.entries.add(entry3);
                this.entries.add(entry3);
                index += 2;
            } else {
                this.entries.add(entry3);
                index++;
            }
        }
    }
}
