package org.bpsbits.kt.rest.prototypes.security

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ResourceInfo

/**
 * Interface for IdentityInspector filters.
 * Provides the ability to easily apply custom access control filters to the JAX-RS resources.
 */
@RegisterForReflection
fun interface IdentityInspector {

    fun approve(
        requestContext: ContainerRequestContext,
        resourceInfo: ResourceInfo,
        message: String
    )

}