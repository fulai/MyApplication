// MessageSender.aidl
package com.fulai.myapplication;
import com.fulai.myapplication.data.MessageModel;
import com.fulai.myapplication.MessageReceiver;


interface MessageSender {
    void sendMessage(in MessageModel messageModel);
    void registerReceiveListener(MessageReceiver messageReceiver);
    void unregisterReceiveListener(MessageReceiver messageReceiver);
}
