# Quarkus Toolbox

Quarkus Toolbox is a set of miscellaneous Kotlin-based tools for building Quarkus applications by eliminating the "Backend Tax" of traditional REST frameworks.

- [Homepage](https://bpsbits.org/kt-rest/)
- [Documentation](https://bpsbits.org/kt-rest/docs/)
- [About Quarkus Toolbox](./module.md)

## Benefits

**The "Fast-Path" for PostgreSQL REST APIs.**

`kt-rest` is a high-performance Kotlin toolset designed to eliminate the "Backend Tax" of traditional REST frameworks. By bypassing the ORM layer and leveraging PostgreSQL’s native JSON capabilities, `kt-rest` streams data directly from your database to your clients with near-zero CPU and memory overhead.

* 🚀 **Extreme Performance:** Bypasses ORM reflection and Jackson serialization. Streams JSON directly from the DB socket to the HTTP response.
* 🛡️ **Hardened Security:** Use PostgreSQL Stored Procedures as your API contract. No broad table permissions; just `EXECUTE` rights.
* 📉 **Zero-Mapping Overhead:** Stop writing DTOs, Mappers, and Boilerplate. If your DB can generate JSON, `kt-rest` can serve it.
* 🧪 **Unit-Testable Logic:** Move business logic into database functions that are easily testable and transactionally atomic by nature.
* ⚡ **Quarkus Native:** Built on the Quarkus reactive stack for sub-second startup and tiny memory footprints.

For a more detailed overview, refer to: [About Quarkus Toolbox](./module.md)

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