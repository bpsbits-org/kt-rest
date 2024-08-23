# Package org.bpsbits.kt.rest.annotations

**Annotations** for marking elements in order to trigger specific actions while the
application is running.

## Annotations

When you attach a annotation to a class, interface, method, or field in
Kotlin/Java, you're essentially adding metadata that the Java compiler and JVM
may utilise.

Annotations from this package can be applied to assets to initiate relevant
actions while the application runs.

### Security annotations

- [IdentifiedAccess] - Marks resource to be accessible only for **identified** clients.
- [ExclusiveAccess] - Marks resource to be accessible only for **authorised** clients.

## Authentication vs authorization

Authentication and authorisation are two critical ideas for safeguarding application data and resources, but they represent two distinct aspects of security.

### Authentication

Authentication is the process of determining the identification of a user, device, or system. It is about determining "Who is the user?"

Attaching the annotation [IdentifiedAccess] to a function runs the operation that determines if the user is **identified**. If the user is recognised, the function can be executed.

Please keep in mind that [IdentifiedAccess]  operation  *does not authenticate the client; rather, it detects if the client has already been recognised*.

### Authorisation

Authorisation, on the other hand, takes place after successful authentication and involves allowing or refusing access to specified resources or rights. It is about addressing the question, "*What is the user allowed to do?*"

When the annotation [ExclusiveAccess] is attached to a function, the method that determines if the user is recognised is called during runtime. If a user is recognised, another procedure is initiated, which concludes if the user is permitted to interact with the resource under certain conditions.

The benefit of [ExclusiveAccess] is that you may use RBAC, RBAC-A, or other access control patterns to safeguard the resource. 

#### Possible use scenarios

For example, you may develop a standardised mechanism to verify that the user has signed the service agreement before accessing a certain resource.

For example, in a B2B application with various business customers and users, you may grant or refuse access to users for data from specified organisations exclusively.
