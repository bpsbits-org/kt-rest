package org.bpsbits.kt.rest.prototypes.filters

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.ResourceInfo
import org.bpsbits.kt.rest.annotations.ExclusiveAccess
import org.bpsbits.kt.rest.annotations.IdentifiedAccess
import kotlin.reflect.full.createInstance

/**
 * Basic access control filter.
 * Provides the ability to easily apply custom access control filters to the JAX-RS resources.
 */
@Suppress("unused")
@RegisterForReflection
interface SimpleAccessFilter : ContainerRequestFilter {

    @Suppress("MemberVisibilityCanBePrivate")
    var resourceInfo: ResourceInfo

    override fun filter(requestContext: ContainerRequestContext) {
        val restrictedAccess = resourceInfo.resourceMethod
            .annotations.any { it is ExclusiveAccess }
        // Check that the client is authorized to access the resource
        if (restrictedAccess) {
            println("AccessFilter: Limited access")
            val gateKeeperInfo = resourceInfo.resourceMethod.annotations
                .find { it is ExclusiveAccess } as ExclusiveAccess
            val authorizer = gateKeeperInfo.identityInspector.createInstance()
            authorizer.approve(requestContext, resourceInfo, gateKeeperInfo.messageUnauthorized)
            val gateKeeper = gateKeeperInfo.gatekeeper.createInstance()
            gateKeeper.approve(requestContext, resourceInfo, gateKeeperInfo)
            return
        }
        // Check that the client is authenticated
        val authenticatedOnly = resourceInfo.resourceMethod
            .annotations.any { it is IdentifiedAccess }
        if (authenticatedOnly) {
            println("AccessFilter: Authenticated client only")
            val authorizerInfo = resourceInfo.resourceMethod.annotations
                .find { it is IdentifiedAccess } as IdentifiedAccess
            val authorizer = authorizerInfo.authorizer.createInstance()
            authorizer.approve(requestContext, resourceInfo, authorizerInfo.message)
        }
    }

}