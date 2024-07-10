package com.ayush.campuseaze.presentation.components

import android.content.Context
import android.widget.Toast

fun Toast(ctx: Context, msg: String) {
    Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
}