package org.bpsbits.kt.rest.prototypes.filters

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.HttpHeaders
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import org.bpsbits.kt.rest.commons.QuarkusApp
import org.bpsbits.kt.rest.exceptions.InvalidFileInfoInputException
import org.bpsbits.kt.rest.exceptions.RequestedFileNotFoundException
import org.bpsbits.kt.rest.exceptions.meta.ThrowableInfo
import java.io.FileNotFoundException

@Suppress("unused")
@RegisterForReflection
interface SimpleThrowableMapper : ExceptionMapper<Throwable> {

    @Produces(MediaType.APPLICATION_JSON)
    override fun toResponse(exception: Throwable): Response {
        val info = ThrowableInfo.fromException(exception)
        val directoryPath = QuarkusApp.incidentLogsDirPath
        if (directoryPath.isNotBlank()) {
            info.saveInto(directoryPath)
        }
        // If file not found or invalid input for file request
        if (
            exception is FileNotFoundException ||
            exception is RequestedFileNotFoundException ||
            exception is InvalidFileInfoInputException
        ) {
            return Response.status(info.code)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .entity(exception.message)
                .type(MediaType.TEXT_PLAIN).build()
        }
        return Response
            .status(info.code)
            .entity(info.toJson(QuarkusApp.hideThrowableStackTraces))
            .build()
    }

}