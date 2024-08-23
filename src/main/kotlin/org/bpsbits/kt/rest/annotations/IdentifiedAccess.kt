package org.bpsbits.kt.rest.annotations

import io.quarkus.runtime.annotations.RegisterForReflection
import org.bpsbits.kt.rest.dummies.DummyIdentityInspector
import org.bpsbits.kt.rest.prototypes.security.IdentityInspector
import kotlin.reflect.KClass

/**
 * Marks resource to be accessible only for identified clients.
 */
@Suppress("unused")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@RegisterForReflection
annotation class IdentifiedAccess(
    /**
     * A possible message to be sent to the client in the case that the identification fails.
     */
    val message: String = "Unauthorized. The server failed to identify the user.",
    /**
     * Class of the custom user function which check if the user is authenticated.
     * Default inspector is [DummyIdentityInspector], which always throws an exception.
     * You have to implement the [IdentityInspector] interface yourself.
     */
    val authorizer: KClass<out IdentityInspector> = DummyIdentityInspector::class,
)
