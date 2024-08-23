# Package org.bpsbits.kt.rest.exceptions

Helps to specify more accurately the cause of problem instead of using generic `WebApplicationException`.

These types of exception mappers are quite useful when dealing with exceptions from an `ExceptionMapper`. You may examine the instance type of an object and make decisions based on the type of exception.

All of these exceptions are descendants of the `WebApplicationException` class, which means they are compatible with both the `ExceptionMapper` and `ThrowableInfo`.