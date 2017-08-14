// MessageReceiver.aidl
package com.fulai.myapplication;
import com.fulai.myapplication.data.MessageModel;

interface MessageReceiver {
    void onMessageReceived(in MessageModel receiverMessage);
}
