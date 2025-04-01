package gaur.himanshu.common.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.ui.platform.LocalContext


sealed class UiText {
    data class RemoteString(val message: String) : UiText()
    class LocalString(@StringRes val res: Int, vararg val args: Any) : UiText()
    data object Idle : UiText()

    fun getString(context: Context): String {
        return when (this) {
            is RemoteString -> message
            is LocalString -> context.getString(res, *args)
            Idle -> ""
        }
    }
}

@Composable
fun UiText.getString(): String {
    val context = LocalContext.current
    return when (this) {
        is UiText.RemoteString -> message
        is UiText.LocalString -> context.getString(res, *args)
        UiText.Idle -> ""
    }
}
