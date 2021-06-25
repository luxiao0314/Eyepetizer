/*
 * Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eyepetizer.android.extension

import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.eyepetizer.android.R

/**
 * 加载图片，可以指定圆角弧度。
 *
 * @param url 图片地址
 */
fun ImageView.load(url: String, topLeft: Float = 0f, bottomLeft: Float = 0f, topRight: Float = 0f, bottomRight: Float = 0f) {
    load(url) {
        crossfade(true)
        placeholder(R.drawable.shape_album_loading_bg)
        transformations(RoundedCornersTransformation(topLeft,topRight, bottomLeft, bottomRight))
    }
}

/**
 * 加载图片，可以指定圆角弧度。
 *
 * @param url 图片地址
 * @param round 圆角，单位dp
 */
fun ImageView.load(url: String, round: Float = 0f) {
    load(url) {
        crossfade(true)
        placeholder(R.drawable.shape_album_loading_bg)
        transformations(RoundedCornersTransformation(round))
    }
}