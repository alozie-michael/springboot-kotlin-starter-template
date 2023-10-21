package com.base.exceptionHandler

import com.base.exception.ResourceNotFoundException
import mu.KLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler() {

    companion object : KLogging()
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors = ex.bindingResult.allErrors
            .map { error -> error.defaultMessage!! }
            .sorted()
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error(errors.joinToString(", ") { it }))
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourcesNotFound(ex: ResourceNotFoundException, request: WebRequest) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error(ex.message))
    }

    @ExceptionHandler(Exception::class)
    fun handleAllException (ex: Exception, request: WebRequest): ResponseEntity<Any> {
        logger.error("Exception observed ${ex.message}", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error("Server error, please try again later."))
    }
}