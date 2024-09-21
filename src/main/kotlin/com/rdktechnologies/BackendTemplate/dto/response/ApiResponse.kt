package com.rdktechnologies.BackendTemplate.dto.response


data class ApiResponse(
    var error: Boolean? = false,
    var statusCode: Int? = -1,
    var message: String?= null,
    var serverError: String?=null,
    var data: Any? = null
)
