package com.example.a.myapplicationt15_b15;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 문자는 pdu 라는 단위로 관리된다 (프로토콜 데이터 유닛)
        //
        SmsMessage[] msgs = null;
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        msgs = new SmsMessage[pdus.length];
        String str = "";
        for(int i=0; i<msgs.length; i++){
            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            str += "SMS from : "+msgs[i].getOriginatingAddress();
            str += " : "+msgs[i].getMessageBody();
            
        }
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
