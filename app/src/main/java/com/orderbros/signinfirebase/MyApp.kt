package com.orderbros.signinfirebase
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MyApp : Application() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "notification"
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.messaging.token.addOnCompleteListener {
            if (!it.isSuccessful) {
                println("Token Generation")
                return@addOnCompleteListener
            }
            val token = it.result
            println("Token> $token")
        }
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notification",
                NotificationManager.IMPORTANCE_HIGH,
            )
            channel.description = "Notification App"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}