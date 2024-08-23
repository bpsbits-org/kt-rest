package org.bpsbits.kt.rest.dummies

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ResourceInfo
import org.bpsbits.kt.rest.annotations.ExclusiveAccess
import org.bpsbits.kt.rest.exceptions.ForbiddenException
import org.bpsbits.kt.rest.prototypes.security.GateKeeper

/**
 * Dummy implementation of [GateKeeper].
 * Throws [ForbiddenException] always.
 */
@RegisterForReflection
class DummyGatekeeper : GateKeeper {

    /**
     * @param requestContext [ContainerRequestContext]
     * @param resourceInfo [ResourceInfo]
     * @param exclusiveAccess [ExclusiveAccess]
     * @throws [ForbiddenException]
     */
    override fun approve(
        requestContext: ContainerRequestContext,
        resourceInfo: ResourceInfo,
        exclusiveAccess: ExclusiveAccess
    ) {
        throw ForbiddenException(exclusiveAccess.messageForbidden)
    }

}