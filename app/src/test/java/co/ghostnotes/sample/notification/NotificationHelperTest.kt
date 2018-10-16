package co.ghostnotes.sample.notification

import android.app.NotificationManager
import android.content.Context
import co.ghostnotes.sample.notification.util.AndroidVersionUtils
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class NotificationHelperTest {

    private lateinit var notificationHelper: NotificationHelper

    @Mock
    private lateinit var mockContext: Context
    @Mock
    private lateinit var mockNotificationManager: NotificationManager
    @Mock
    private lateinit var mockNotificationId: NotificationID
    @Mock
    private lateinit var mockAndroidVersionUtils: AndroidVersionUtils

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(mockContext.getSystemService(Context.NOTIFICATION_SERVICE)).thenReturn(mockNotificationManager)
    }

    @Test
    fun initialize_oreo() {
        // Prepare
        `when`(mockAndroidVersionUtils.isOreoOrUp()).thenReturn(true)

        // Call
        notificationHelper = NotificationHelper(mockContext, mockNotificationId, mockAndroidVersionUtils)

        // Test
        verify(mockNotificationManager, times(1)).createNotificationChannels(ArgumentMatchers.any())
    }

    @Test
    fun initialize_nougat() {
        // Prepare
        `when`(mockAndroidVersionUtils.isOreoOrUp()).thenReturn(false)

        // Call
        notificationHelper = NotificationHelper(mockContext, mockNotificationId, mockAndroidVersionUtils)

        // Test
        verify(mockNotificationManager, never()).createNotificationChannels(ArgumentMatchers.any())
    }

    @Test
    fun test() {
        // Prepare
        notificationHelper = NotificationHelper(mockContext, mockNotificationId, mockAndroidVersionUtils)
        val expected = 1

        // Call
        val actual = notificationHelper.test()

        // Test
        assertThat(actual, `is`(expected))
    }

}