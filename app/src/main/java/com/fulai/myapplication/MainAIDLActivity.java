package com.fulai.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fulai.myapplication.data.MessageModel;
import com.fulai.myapplication.service.MessageService;

/**
 * AIDL使用过程，包括远程回调
 */
public class MainAIDLActivity extends AppCompatActivity {
    public static final String TAG = "MainAIDLActivity";
    private MessageSender messageSender;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aidl);
        setupService();
    }

    private void setupService() {
        intent = new Intent(this, MessageService.class);
        bindService(intent, serviceConnection, Context.BIND_ABOVE_CLIENT);
        startService(intent);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            messageSender = MessageSender.Stub.asInterface(service);
            try {
                //linkToDeath -> 设置死亡代理 DeathRecipient 对象
                messageSender.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {

        @Override
        public void binderDied() {
            Log.d(TAG, "binderDied");
            if (messageSender != null) {
                //unlinkToDeath -> Binder死亡的情况下，解除该代理
                messageSender.asBinder().unlinkToDeath(this, 0);
                messageSender = null;
            }
            setupService();
        }
    };

    @Override
    protected void onDestroy() {
        //Binder中的isBinderAlive也可以判断Binder是否死亡
        if (messageSender != null && messageSender.asBinder().isBinderAlive()) {
            try {
                messageSender.unregisterReceiveListener(messageReceiver);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
        stopService(intent);
        super.onDestroy();
    }

    private MessageReceiver messageReceiver = new MessageReceiver.Stub() {

        @Override
        public void onMessageReceived(MessageModel receiverMessage) throws RemoteException {
            Log.d(TAG, "messageReceiver");
            Log.d(TAG, receiverMessage.toString());
        }
    };

    public void onClick(View view) {
        MessageModel messageModel = new MessageModel();
        messageModel.setFrom("client user id");
        messageModel.setTo("receiver user id");
        messageModel.setContent("this is message content");
        try {
            messageSender.registerReceiveListener(messageReceiver);
            messageSender.sendMessage(messageModel);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
