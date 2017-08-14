package com.fulai.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.fulai.myapplication.MessageReceiver;
import com.fulai.myapplication.MessageSender;
import com.fulai.myapplication.data.MessageModel;

import java.util.concurrent.atomic.AtomicBoolean;

public class MessageService extends Service {
    public static final String TAG = "MessageService";
    private AtomicBoolean serviceStop = new AtomicBoolean(false);
    private RemoteCallbackList<MessageReceiver> listenerList = new RemoteCallbackList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        new Thread(new FakeTcpTask()).start();
    }

    public MessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        if (checkCallingOrSelfPermission("com.fulai.myapplication.permisson.REMOTE_SERVICE_PERMISSION") ==
                PackageManager.PERMISSION_DENIED) {
            return null;
        }
        return messageSender;
    }

    IBinder messageSender = new MessageSender.Stub() {

        @Override
        public void sendMessage(MessageModel messageModel) throws RemoteException {
            Log.d(TAG, "messageModel" + messageModel.toString());
        }

        @Override
        public void registerReceiveListener(MessageReceiver messageReceiver) throws RemoteException {
            listenerList.register(messageReceiver);
        }

        @Override
        public void unregisterReceiveListener(MessageReceiver messageReceiver) throws RemoteException {
            listenerList.unregister(messageReceiver);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (packageName == null && !packageName.startsWith("com.fulai.myapplication")) {
                Log.d(TAG, "拒绝调用");
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }
    };


    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.d(TAG, "unbindService");
    }

    private class FakeTcpTask implements Runnable {

        @Override
        public void run() {
            while (!serviceStop.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MessageModel messageModel = new MessageModel();
                messageModel.setFrom("Service");
                messageModel.setTo("Client");
                messageModel.setContent(String.valueOf(System.currentTimeMillis()));

                int listenerCount = listenerList.beginBroadcast();
                for (int i = 0; i < listenerCount; i++) {
                    MessageReceiver messageReceiver = listenerList.getBroadcastItem(i);
                    if (messageReceiver != null) {
                        try {
                            messageReceiver.onMessageReceived(messageModel);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
                listenerList.finishBroadcast();
            }
        }
    }
}
