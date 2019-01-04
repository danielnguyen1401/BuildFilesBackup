package com.xgame.donutpuzzlee;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.firebase.jobdispatcher.Job;

public class StickyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.w("MainApplication", "Start reminder service");
        Job job3days = NotificationScheduler.get3DaysJob();
        Job job7days = NotificationScheduler.get7DaysJob();
        MainApplication.getInstance().getDispatcher().mustSchedule(job3days);
        MainApplication.getInstance().getDispatcher().mustSchedule(job7days);
    }
}