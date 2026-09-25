package com.android.support;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;

public class Launcher extends Service {

    Menu menu;

    private static final String CHANNEL_ID = "launcher_service";

    @Override
    public void onCreate() {
        super.onCreate();

        // Android 8+ notification channel
        createNotificationChannel();

        // Android 14+ foreground service
        startForegroundServiceNotification();

        // Create menu
        menu = new Menu(this);

        // Only show overlay when permission is available
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || Settings.canDrawOverlays(this)) {

            menu.SetWindowManagerWindowService();
            menu.ShowMenu();
        }

        // Create a handler for this Class
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (menu != null) {
                    Thread();
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Launcher Service",
                    NotificationManager.IMPORTANCE_LOW
            );

            channel.setDescription("Launcher foreground service");

            NotificationManager manager =
                    getSystemService(NotificationManager.class);

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private void startForegroundServiceNotification() {

        Notification notification;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notification = new Notification.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_menu_info_details)
                    .setContentTitle("Only Fair Hacks")
                    .setContentText("Service is running")
                    .setOngoing(true)
                    .build();

        } else {

            notification = new Notification.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_menu_info_details)
                    .setContentTitle("Only Fair Hacks")
                    .setContentText("Service is running")
                    .setOngoing(true)
                    .build();
        }

        // Android 14+
        if (Build.VERSION.SDK_INT >= 34) {

            startForeground(
                    1001,
                    notification,
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
            );

        } else {

            startForeground(
                    1001,
                    notification
            );
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private boolean isNotInGame() {

        ActivityManager.RunningAppProcessInfo
                runningAppProcessInfo =
                new ActivityManager.RunningAppProcessInfo();

        ActivityManager.getMyMemoryState(runningAppProcessInfo);

        return runningAppProcessInfo.importance != 100;
    }

    private void Thread() {

        if (menu == null) {
            return;
        }

        if (isNotInGame()) {
            menu.setVisibility(View.INVISIBLE);
        } else {
            menu.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {

        if (menu != null) {
            menu.onDestroy();
        }

        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent intent) {

        super.onTaskRemoved(intent);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int id) {

        return Service.START_NOT_STICKY;
    }
}
