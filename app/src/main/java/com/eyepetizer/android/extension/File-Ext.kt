package com.eyepetizer.android.extension

import okio.buffer
import okio.sink
import java.io.File
import java.nio.charset.Charset

/**
 * @Description
 * @Author lux
 * @Date 2021/8/5 10:27 AM
 * @Version
 */

fun File.writeString(string: String) =
    sink().buffer().writeString(string, Charset.forName("utf-8")).close()