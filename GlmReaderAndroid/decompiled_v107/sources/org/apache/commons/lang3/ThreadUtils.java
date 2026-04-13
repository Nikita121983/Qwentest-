package org.apache.commons.lang3;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.function.Predicates;
import org.apache.commons.lang3.time.DurationUtils;

/* loaded from: classes9.dex */
public class ThreadUtils {

    @Deprecated
    public static final AlwaysTruePredicate ALWAYS_TRUE_PREDICATE = new AlwaysTruePredicate();

    @FunctionalInterface
    @Deprecated
    /* loaded from: classes9.dex */
    public interface ThreadGroupPredicate {
        boolean test(ThreadGroup threadGroup);
    }

    @FunctionalInterface
    @Deprecated
    /* loaded from: classes9.dex */
    public interface ThreadPredicate {
        boolean test(Thread thread);
    }

    @Deprecated
    /* loaded from: classes9.dex */
    private static final class AlwaysTruePredicate implements ThreadPredicate, ThreadGroupPredicate {
        private AlwaysTruePredicate() {
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return true;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadGroupPredicate
        public boolean test(ThreadGroup threadGroup) {
            return true;
        }
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public static class NamePredicate implements ThreadPredicate, ThreadGroupPredicate {
        private final String name;

        public NamePredicate(String name) {
            Objects.requireNonNull(name, "name");
            this.name = name;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return thread != null && thread.getName().equals(this.name);
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadGroupPredicate
        public boolean test(ThreadGroup threadGroup) {
            return threadGroup != null && threadGroup.getName().equals(this.name);
        }
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public static class ThreadIdPredicate implements ThreadPredicate {
        private final long threadId;

        public ThreadIdPredicate(long threadId) {
            if (threadId <= 0) {
                throw new IllegalArgumentException("The thread id must be greater than zero");
            }
            this.threadId = threadId;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return thread != null && thread.getId() == this.threadId;
        }
    }

    public static Thread findThreadById(final long threadId) {
        if (threadId <= 0) {
            throw new IllegalArgumentException("The thread id must be greater than zero");
        }
        Collection<Thread> result = findThreads((Predicate<Thread>) new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThreadUtils.lambda$findThreadById$0(threadId, (Thread) obj);
            }
        });
        if (result.isEmpty()) {
            return null;
        }
        return result.iterator().next();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$findThreadById$0(long threadId, Thread t) {
        return t != null && t.getId() == threadId;
    }

    public static Thread findThreadById(long threadId, String threadGroupName) {
        Objects.requireNonNull(threadGroupName, "threadGroupName");
        Thread thread = findThreadById(threadId);
        if (thread != null && thread.getThreadGroup() != null && thread.getThreadGroup().getName().equals(threadGroupName)) {
            return thread;
        }
        return null;
    }

    public static Thread findThreadById(long threadId, ThreadGroup threadGroup) {
        Objects.requireNonNull(threadGroup, "threadGroup");
        Thread thread = findThreadById(threadId);
        if (thread != null && threadGroup.equals(thread.getThreadGroup())) {
            return thread;
        }
        return null;
    }

    public static Collection<ThreadGroup> findThreadGroups(Predicate<ThreadGroup> predicate) {
        return findThreadGroups(getSystemThreadGroup(), true, predicate);
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroup threadGroup, boolean recurse, Predicate<ThreadGroup> predicate) {
        ThreadGroup[] threadGroups;
        Objects.requireNonNull(threadGroup, "threadGroup");
        Objects.requireNonNull(predicate, "predicate");
        int count = threadGroup.activeGroupCount();
        do {
            threadGroups = new ThreadGroup[(count / 2) + count + 1];
            count = threadGroup.enumerate(threadGroups, recurse);
        } while (count >= threadGroups.length);
        return Collections.unmodifiableCollection((Collection) Stream.of((Object[]) threadGroups).limit(count).filter(predicate).collect(Collectors.toList()));
    }

    @Deprecated
    public static Collection<ThreadGroup> findThreadGroups(ThreadGroup threadGroup, boolean recurse, final ThreadGroupPredicate predicate) {
        Objects.requireNonNull(predicate);
        return findThreadGroups(threadGroup, recurse, (Predicate<ThreadGroup>) new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThreadUtils.ThreadGroupPredicate.this.test((ThreadGroup) obj);
            }
        });
    }

    @Deprecated
    public static Collection<ThreadGroup> findThreadGroups(ThreadGroupPredicate predicate) {
        return findThreadGroups(getSystemThreadGroup(), true, predicate);
    }

    public static Collection<ThreadGroup> findThreadGroupsByName(String threadGroupName) {
        return findThreadGroups(predicateThreadGroup(threadGroupName));
    }

    public static Collection<Thread> findThreads(Predicate<Thread> predicate) {
        return findThreads(getSystemThreadGroup(), true, predicate);
    }

    public static Collection<Thread> findThreads(ThreadGroup threadGroup, boolean recurse, Predicate<Thread> predicate) {
        Thread[] threads;
        Objects.requireNonNull(threadGroup, "The group must not be null");
        Objects.requireNonNull(predicate, "The predicate must not be null");
        int count = threadGroup.activeCount();
        do {
            threads = new Thread[(count / 2) + count + 1];
            count = threadGroup.enumerate(threads, recurse);
        } while (count >= threads.length);
        return Collections.unmodifiableCollection((Collection) Stream.of((Object[]) threads).limit(count).filter(predicate).collect(Collectors.toList()));
    }

    @Deprecated
    public static Collection<Thread> findThreads(ThreadGroup threadGroup, boolean recurse, final ThreadPredicate predicate) {
        Objects.requireNonNull(predicate);
        return findThreads(threadGroup, recurse, (Predicate<Thread>) new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThreadUtils.ThreadPredicate.this.test((Thread) obj);
            }
        });
    }

    @Deprecated
    public static Collection<Thread> findThreads(ThreadPredicate predicate) {
        return findThreads(getSystemThreadGroup(), true, predicate);
    }

    public static Collection<Thread> findThreadsByName(String threadName) {
        return findThreads(predicateThread(threadName));
    }

    public static Collection<Thread> findThreadsByName(final String threadName, String threadGroupName) {
        Objects.requireNonNull(threadName, "threadName");
        Objects.requireNonNull(threadGroupName, "threadGroupName");
        return Collections.unmodifiableCollection((Collection) findThreadGroups(predicateThreadGroup(threadGroupName)).stream().flatMap(new Function() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream stream;
                stream = ThreadUtils.findThreads((ThreadGroup) obj, false, ThreadUtils.predicateThread(threadName)).stream();
                return stream;
            }
        }).collect(Collectors.toList()));
    }

    public static Collection<Thread> findThreadsByName(String threadName, ThreadGroup threadGroup) {
        return findThreads(threadGroup, false, predicateThread(threadName));
    }

    public static Collection<ThreadGroup> getAllThreadGroups() {
        return findThreadGroups((Predicate<ThreadGroup>) Predicates.truePredicate());
    }

    public static Collection<Thread> getAllThreads() {
        return findThreads((Predicate<Thread>) Predicates.truePredicate());
    }

    public static ThreadGroup getSystemThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup != null && threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        return threadGroup;
    }

    public static void join(final Thread thread, Duration duration) throws InterruptedException {
        Objects.requireNonNull(thread);
        DurationUtils.accept(new FailableBiConsumer() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda2
            @Override // org.apache.commons.lang3.function.FailableBiConsumer
            public final void accept(Object obj, Object obj2) {
                thread.join(((Long) obj).longValue(), ((Integer) obj2).intValue());
            }
        }, duration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$namePredicate$2(Function nameGetter, String name, Object t) {
        return t != null && Objects.equals(nameGetter.apply(t), Objects.requireNonNull(name));
    }

    private static <T> Predicate<T> namePredicate(final String name, final Function<T, String> nameGetter) {
        return new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThreadUtils.lambda$namePredicate$2(nameGetter, name, obj);
            }
        };
    }

    private static Predicate<Thread> predicateThread(String threadName) {
        return namePredicate(threadName, new Function() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ((Thread) obj).getName();
                return name;
            }
        });
    }

    private static Predicate<ThreadGroup> predicateThreadGroup(String threadGroupName) {
        return namePredicate(threadGroupName, new Function() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ((ThreadGroup) obj).getName();
                return name;
            }
        });
    }

    public static void sleep(Duration duration) throws InterruptedException {
        DurationUtils.accept(new FailableBiConsumer() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableBiConsumer
            public final void accept(Object obj, Object obj2) {
                Thread.sleep(((Long) obj).longValue(), ((Integer) obj2).intValue());
            }
        }, duration);
    }

    public static void sleepQuietly(Duration duration) {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
        }
    }

    @Deprecated
    public ThreadUtils() {
    }
}
