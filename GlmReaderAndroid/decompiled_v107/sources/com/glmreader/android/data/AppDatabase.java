package com.glmreader.android.data;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.glmreader.android.data.dao.GroupDao;
import com.glmreader.android.data.dao.MeasurementDao;
import com.glmreader.android.data.dao.NoteDao;
import com.glmreader.android.data.dao.ObjectDao;
import com.glmreader.android.data.dao.ProjectDao;
import com.glmreader.android.data.dao.SettingsDao;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppDatabase.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0011"}, d2 = {"Lcom/glmreader/android/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "measurementDao", "Lcom/glmreader/android/data/dao/MeasurementDao;", "noteDao", "Lcom/glmreader/android/data/dao/NoteDao;", "settingsDao", "Lcom/glmreader/android/data/dao/SettingsDao;", "projectDao", "Lcom/glmreader/android/data/dao/ProjectDao;", "groupDao", "Lcom/glmreader/android/data/dao/GroupDao;", "objectDao", "Lcom/glmreader/android/data/dao/ObjectDao;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class AppDatabase extends RoomDatabase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile AppDatabase INSTANCE;

    public abstract GroupDao groupDao();

    public abstract MeasurementDao measurementDao();

    public abstract NoteDao noteDao();

    public abstract ObjectDao objectDao();

    public abstract ProjectDao projectDao();

    public abstract SettingsDao settingsDao();

    /* compiled from: AppDatabase.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/glmreader/android/data/AppDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/glmreader/android/data/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AppDatabase getDatabase(Context context) {
            AppDatabase appDatabase;
            Intrinsics.checkNotNullParameter(context, "context");
            AppDatabase appDatabase2 = AppDatabase.INSTANCE;
            if (appDatabase2 != null) {
                return appDatabase2;
            }
            synchronized (this) {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                appDatabase = (AppDatabase) Room.databaseBuilder(applicationContext, AppDatabase.class, "glm_reader_database").fallbackToDestructiveMigration().build();
                Companion companion = AppDatabase.INSTANCE;
                AppDatabase.INSTANCE = appDatabase;
            }
            return appDatabase;
        }
    }
}
