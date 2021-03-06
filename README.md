# kamon-logback <img align="right" src="https://rawgit.com/kamon-io/Kamon/master/kamon-logo.svg" height="150px" style="padding-left: 20px"/> 
[![Build Status](https://travis-ci.org/kamon-io/kamon-logback.svg?branch=master)](https://travis-ci.org/kamon-io/kamon-logback)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/kamon-io/Kamon?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.kamon/kamon-logback_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.kamon/kamon-logback_2.12)


The <b>kamon-logback</b> module require you to start your application using the AspectJ Weaver Agent.


### Getting Started

Kamon Logback is currently available for Scala 2.10, 2.11 and 2.12.

Supported releases and dependencies are shown below.

| kamon  | status | jdk  | scala            
|:------:|:------:|:----:|------------------
|  1.0.0 | stable | 1.8+ | 2.10, 2.11, 2.12

To get started with SBT, simply add the following to your `build.sbt` or `pom.xml`
file:

```scala
libraryDependencies += "io.kamon" %% "kamon-logback" % "1.0.0"
```

```xml
<dependency>
    <groupId>io.kamon</groupId>
    <artifactId>kamon-logback_2.12</artifactId>
    <version>1.0.0</version>
</dependency>
```

Logging TraceID
---------------

Inserting a `conversionRule` allows you to incorporate the trace ID for a request into your [Logback layout](https://logback.qos.ch/manual/layouts.html). Here is a simple example `logback.xml` configuration that does this:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="false" debug="false">
  <conversionRule conversionWord="traceID" converterClass="kamon.logback.LogbackTraceIDConverter" />

  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss} | %-5level | %traceID | %c{0} -> %m%n</pattern>
    </encoder>
  </appender>

  <root level="DEBUG">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```

Propagating TraceID to AsyncAppender
------------------------------------

If you choose to use [`AsyncAppender`](https://logback.qos.ch/manual/appenders.html#AsyncAppender), your trace ID will automatically be propagated to the thread where the log is actually published. No configuration needed. 
