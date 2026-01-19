# Quarkus Toolbox

Quarkus Toolbox is a set of miscellaneous Kotlin-based tools for building Quarkus applications by eliminating the "Backend Tax" of traditional REST frameworks.

- [Homepage](https://bpsbits.org/kt-rest/)
- [Documentation](https://bpsbits.org/kt-rest/docs/)
- [About Quarkus Toolbox](./module.md)

## Benefits

**The "Fast-Path" for PostgreSQL REST APIs.**

`kt-rest` is a high-performance Kotlin toolset designed to eliminate the "Backend Tax" of traditional REST frameworks. By bypassing the ORM layer and leveraging PostgreSQL’s native JSON capabilities, `kt-rest` streams data directly from your database to your clients with near-zero CPU and memory overhead.

* 🚀 **Extreme Throughput:** Streams JSON directly from the DB socket to the HTTP response, bypassing reflection and serialization.
* 🛠️ **One-Line Endpoints:** Develop full REST-API responses in a single line using the `pgFunctionResponse` feature.
* ⚡ **Instant Hot-Swapping:** Update business logic in production in milliseconds. No rebuilds, no container restarts, and zero downtime.
* 🛡️ **Hardened Security:** Your API contract lives in Stored Procedures. No direct table access is required for the application user.
* 📉 **Zero-Mapping Overhead:** Eliminate the "Boilerplate Tax" of DTOs, Mappers, and Repository interfaces.
* 🧪 **Atomic Logic:** Business logic is inherently transactional and easily testable using standard SQL tools.
* 🧪 **Clean SQL Testing:** Test your logic where it lives. Decouple data logic from the JVM for faster, more reliable unit tests.

For a more detailed overview, refer to: [About Quarkus Toolbox](./module.md).

## Usage

Add dependency into `pom.xml`.

```xml
<dependency>
    <groupId>org.bpsbits</groupId>
    <artifactId>kt-rest</artifactId>
    <version>3.2.0</version>
</dependency>
```

See [kt-rest in Maven Central](https://central.sonatype.com/artifact/org.bpsbits/kt-rest) for more options.