package org.bpsbits.kt.rest.annotations

import io.quarkus.runtime.annotations.RegisterForReflection
import org.bpsbits.kt.rest.dummies.DummyGatekeeper
import org.bpsbits.kt.rest.dummies.DummyIdentityInspector
import org.bpsbits.kt.rest.prototypes.security.GateKeeper
import org.bpsbits.kt.rest.prototypes.security.IdentityInspector
import kotlin.reflect.KClass

/**
 * Marks resource to be accessible only for authorized clients.
 * Simplifies usage of RBAC-A or other patterns for controlling access to resources.
 */
@Suppress("unused")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@RegisterForReflection
annotation class ExclusiveAccess(
    /**
     * Possible message to be given to the client if they do not possess the required permissions to access the resource.
     */
    val messageForbidden: String = "Forbidden. Lack of required rights to access or interact with the given resource.",
    /**
     * A possible message to be sent to the client in the case that the identification fails.
     */
    val messageUnauthorized: String = "Unauthorized. The server failed to identify the user.",
    /**
     * Class of the custom user function which check if the user is identified.
     * Default inspector is [DummyIdentityInspector], which always throws an exception.
     * You have to implement the [IdentityInspector] interface yourself.
     */
    val identityInspector: KClass<out IdentityInspector> = DummyIdentityInspector::class,
    /**
     * Class of the custom user function which check if the user
     * is allowed to access or interact with the given resource.
     * Default gatekeeper is [DummyGatekeeper], which always throws an exception.
     * You have to implement the [GateKeeper] interface yourself.
     */
    val gatekeeper: KClass<out GateKeeper> = DummyGatekeeper::class,
    /**
     * Additional instructions for gatekeeper
     */
    val tags: Array<String> = [],
)
