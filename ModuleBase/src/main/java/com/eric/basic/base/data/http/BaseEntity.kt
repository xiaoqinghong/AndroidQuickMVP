package com.eric.basic.base.data.http

/**
 * Author: Eric
 * Date: Created in 2019/1/25 3:28 PM
 * Description： TODO
 */
class BaseEntity<T> (
        var error: Int = 0,
        var code: Int = 0,
        var message: String? ="",
        var data: T? = null
)