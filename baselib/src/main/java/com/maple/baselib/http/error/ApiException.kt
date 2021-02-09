package com.maple.baselib.http.error

import java.lang.RuntimeException


class ApiException:RuntimeException {
    constructor():super("api exception")
    constructor(message:String):super(message)
}