package org.bpsbits.kt.rest.prototypes.security

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ResourceInfo
import org.bpsbits.kt.rest.annotations.ExclusiveAccess

/**
 * Interface for Gatekeeper filters.
 * Provides the ability to easily apply custom access control filters to the JAX-RS resources.
 */
@RegisterForReflection
fun interface GateKeeper {

    /**
     * Method is executed by [org.bpsbits.kt.rest.prototypes.filters.SimpleAccessFilter].
     * User has to implement this method to approve or reject the request.
     * To reject the request, [org.bpsbits.kt.rest.exceptions.ForbiddenException] exception must be thrown.
     * @param requestContext [ContainerRequestContext]
     * @param resourceInfo [ResourceInfo]
     * @param exclusiveAccess [ExclusiveAccess]
     * @throws [org.bpsbits.kt.rest.exceptions.ForbiddenException]
     * @sample org.bpsbits.kt.rest.dummies.DummyGatekeeper
     */
    fun approve(
        requestContext: ContainerRequestContext,
        resourceInfo: ResourceInfo,
        exclusiveAccess: ExclusiveAccess
    )

}