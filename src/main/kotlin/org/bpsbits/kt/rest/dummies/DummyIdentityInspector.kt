package org.bpsbits.kt.rest.dummies

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ResourceInfo
import org.bpsbits.kt.rest.exceptions.UnauthorizedException
import org.bpsbits.kt.rest.prototypes.security.IdentityInspector

/**
 * Dummy implementation of [IdentityInspector].
 * Throws [UnauthorizedException] always
 */
@RegisterForReflection
class DummyIdentityInspector : IdentityInspector {

    override fun approve(
        requestContext: ContainerRequestContext,
        resourceInfo: ResourceInfo,
        message: String
    ) {
        throw UnauthorizedException(message)
    }

}