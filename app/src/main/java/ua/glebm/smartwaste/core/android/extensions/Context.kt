@file:Suppress("unused")

package ua.glebm.smartwaste.core.android.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 7/25/2023
 */

/**
 * Extension method to show toast for Context.
 */

fun Context.toast(
    message: CharSequence,
    duration: Int = Toast.LENGTH_SHORT,
) {
    Toast.makeText(this, message, duration).show()
}
