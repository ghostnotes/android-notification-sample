package co.ghostnotes.sample.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.annotation.StringRes
import android.support.annotation.VisibleForTesting
import android.support.v4.app.NotificationCompat
import co.ghostnotes.sample.notification.util.AndroidVersionUtils

class NotificationHelper(
    private val context: Context,
    private val notificationId: NotificationID,
    private val androidVersionUtils: AndroidVersionUtils
) {

    companion object {
        private const val BASE_NOTIFICATION_CHANNEL_ID = "co.ghostnotes.sample.notification.ui.main"
        private const val NOTIFICATION_CHANNEL_ID_DEFAULT = "$BASE_NOTIFICATION_CHANNEL_ID.NOTIFICATION_CHANNEL_ID_DEFAULT"
        private const val NOTIFICATION_CHANNEL_ID_MIN = "$BASE_NOTIFICATION_CHANNEL_ID.NOTIFICATION_CHANNEL_ID_MIN"
        private const val NOTIFICATION_CHANNEL_ID_LOW = "$BASE_NOTIFICATION_CHANNEL_ID.NOTIFICATION_CHANNEL_ID_LOW"
        private const val NOTIFICATION_CHANNEL_ID_HIGH = "$BASE_NOTIFICATION_CHANNEL_ID.NOTIFICATION_CHANNEL_ID_HIGH"
        private const val NOTIFICATION_CHANNEL_ID_MAX = "$BASE_NOTIFICATION_CHANNEL_ID.NOTIFICATION_CHANNEL_ID_MAX"
    }

    private val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        initialize()
    }

    private fun initialize() {
        if (androidVersionUtils.isOreoOrUp()) {
            addNotificationChannels()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addNotificationChannels() {
        val channels = mutableListOf<NotificationChannel>()

        // Default
        channels.add(
            createNotificationChannel(
                NOTIFICATION_CHANNEL_ID_DEFAULT,
                R.string.notification_channel_name_default,
                NotificationManager.IMPORTANCE_DEFAULT,
                R.string.notification_channel_description_default
            )
        )
        // MIN
        channels.add(
            createNotificationChannel(
                NOTIFICATION_CHANNEL_ID_MIN,
                R.string.notification_channel_name_min,
                NotificationManager.IMPORTANCE_MIN,
                R.string.notification_channel_description_min
            )
        )
        // LOW
        channels.add(
            createNotificationChannel(
                NOTIFICATION_CHANNEL_ID_LOW,
                R.string.notification_channel_name_low,
                NotificationManager.IMPORTANCE_LOW,
                R.string.notification_channel_description_low
            )
        )
        // HIGH
        channels.add(
            createNotificationChannel(
                NOTIFICATION_CHANNEL_ID_HIGH,
                R.string.notification_channel_name_high,
                NotificationManager.IMPORTANCE_HIGH,
                R.string.notification_channel_description_high
            )
        )
        // MAX
        channels.add(
            createNotificationChannel(
                NOTIFICATION_CHANNEL_ID_MAX,
                R.string.notification_channel_name_max,
                NotificationManager.IMPORTANCE_MAX,
                R.string.notification_channel_description_max
            )
        )

        notificationManager.createNotificationChannels(channels)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, @StringRes channelNameResId: Int, importance: Int): NotificationChannel =
        createNotificationChannel(channelId, channelNameResId, importance, null)

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, @StringRes channelNameResId: Int, importance: Int, @StringRes descriptionResId: Int?): NotificationChannel {
        val channel = NotificationChannel(channelId, context.getString(channelNameResId), importance)
        descriptionResId?.let { channel.description = context.getString(descriptionResId) }
        return channel
    }

    fun notifyDefault() {
        notifyInternal(
            NotificationInfo(
                NOTIFICATION_CHANNEL_ID_DEFAULT,
                R.string.notification_title_default,
                R.string.notification_text_default,
                R.drawable.ic_audiotrack_black_24dp
            )
        )
    }

    fun notifyMin() {
        notifyInternal(
            NotificationInfo(
                NOTIFICATION_CHANNEL_ID_MIN,
                R.string.notification_title_min,
                R.string.notification_text_min,
                R.drawable.ic_audiotrack_black_24dp
            )
        )
    }

    fun notifyLow() {
        notifyInternal(
            NotificationInfo(
                NOTIFICATION_CHANNEL_ID_LOW,
                R.string.notification_title_low,
                R.string.notification_text_low,
                R.drawable.ic_audiotrack_black_24dp
            )
        )
    }
    fun notifyHigh() {
        notifyInternal(
            NotificationInfo(
                NOTIFICATION_CHANNEL_ID_HIGH,
                R.string.notification_title_high,
                R.string.notification_text_high,
                R.drawable.ic_audiotrack_black_24dp
            )
        )
    }

    fun notifyMax() {
        notifyInternal(
            NotificationInfo(
                NOTIFICATION_CHANNEL_ID_MAX,
                R.string.notification_title_max,
                R.string.notification_text_max,
                R.drawable.ic_audiotrack_black_24dp
            )
        )
    }

    private fun notifyInternal(notificationInfo: NotificationInfo) {
        val notification = NotificationCompat.Builder(context, notificationInfo.channelId).apply {
            setContentTitle(context.getString(notificationInfo.contentTitleResId))
            setContentText(context.getString(notificationInfo.contentText))
            setSmallIcon(notificationInfo.smallIconResId)
        }.build()

        notificationManager.notify(notificationId.get(), notification)
    }

    internal data class NotificationInfo(val channelId: String, @StringRes val contentTitleResId: Int, @StringRes val contentText: Int, @DrawableRes val smallIconResId: Int)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun test(): Int = 1

}