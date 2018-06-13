package com.example.dell.controldelabos

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService()
{
    val TAG="service"
    override fun onMessageReceived(msg:RemoteMessage)
    {
        if (msg.notification !=null)
        {

            showNotification(msg)
        }
    }
    fun showNotification(remoteMessage: RemoteMessage)
    {
        val intent:Intent= Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent:PendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)
        val notification:NotificationCompat.Builder=
                NotificationCompat.Builder(this)
                        .setContentTitle("ControlDeLabos")
                        .setContentText(remoteMessage.notification?.body)
                        .setSmallIcon(R.drawable.ic_warning_blank_24dp)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)

        val notif=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notif.notify(0,notification.build())

    }
}