package org.codemindstudio.classnetworking.modals.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
 * Copyright (c) $today.year Codemind Studio.
 *
 * All rights reserved.
 *
 * This software and associated documentation files (the "Software") are
 * the confidential and proprietary information of Codemind Studio.
 *
 * Unauthorized copying, modification, distribution, reverse engineering,
 * sublicensing, or any form of commercial or non-commercial use, in whole
 * or in part, is strictly prohibited without a valid written license
 * agreement from Codemind Studio.
 *
 * This Software is protected under applicable intellectual property and
 * copyright laws. All copies must retain this notice and all other
 * proprietary markings of Codemind Studio.
 *
 * Disclaimer:
 * This Software is provided "AS IS," without warranty of any kind,
 * express or implied, including but not limited to warranties of
 * merchantability, fitness for a particular purpose, or non-infringement.
 * In no event shall Codemind Studio be liable for any claim, damages,
 * or other liability, whether in an action of contract, tort, or otherwise,
 * arising from, out of, or in connection with the Software or the use
 * or other dealings in the Software.
 *
 * Private Product – Not for public release.
 */

@Serializable
data class AddProductResponse(
    @SerialName("data")
    val `data`: Data = Data(),
    @SerialName("name")
    val name: String = "",
    val id: String = "",
    val createdAt: String = ""
)
