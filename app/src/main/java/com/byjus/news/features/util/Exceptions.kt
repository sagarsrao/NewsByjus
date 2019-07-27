package com.byjus.news.features.util

sealed class Exceptions {
    open class NetworkException(error: Throwable) : RuntimeException(error)

    class NoNetworkException(error: Throwable) : NetworkException(error)

    class ServerUnreachableException(error: Throwable) : NetworkException(error)

    class HttpCallFailureException(error: Throwable) : NetworkException(error)
}