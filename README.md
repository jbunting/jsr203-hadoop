jsr203-hadoop
=============

JSR 203 Implementation for Hadoop Distributed File System


[![Build Status](https://travis-ci.org/damiencarol/jsr203-hadoop.svg?branch=master)](https://travis-ci.org/damiencarol/jsr203-hadoop)

Prerequisites
=============

jsr203-hadoop requires git, maven (3.0.4 or later), and JDK 1.7 or later.

Download and build
==================

```bash
$ git clone git://github.com/damiencarol/jsr203-hadoop.git
$ cd jsr203-hadoop
$ mvn install
```

Use `mvn -DskipTests` if you do not want to execute the tests.

Example
=======

jsr203-hadoop provide NIO 2 access to your HDFS cluster. For
example, you can build URI starts with `hdfs://<namebode>:<port>` and use standard NIO API :

```java
URI uri = new URI("hdfs://" + host + ":" + port + "/somefile");
Path file = Paths.get(uri);
System.out.println("File [" + file.toString() + "] exists = '" + Files.exists(file) + "'");
```


## Status

This project is still a beta.

The following features are complete.

* Basic Path support (build from string/URI, resolve)
* Directory stream (provide ability to list files in directory, get status, ...)
* Create/delete file/directory
* Basic Input/Output
* Basic attribute views ("basic", "posix", "hadoop" for hadoop specific attributes)

More feature will come :

* Security and permissions

For more details, see the <a href="REFERENCE.md">Reference guide</a>.

## More information

* License: <a href="LICENSE">Apache License, Version 2.0.</a>
* Author: <a href="https://github.com/damiencarol">Damien Carol</a>
* Source code:<a href="http://github.com/damiencarol/jsr203-hadoop">http://github.com/damiencarol/jsr203-hadoop</a>

### Ressources

* <a href="https://jcp.org/en/jsr/detail?id=203">JSR 203 NIO 2 spec</a>
* <a href="http://docs.oracle.com/javase/tutorial/essential/io/fileio.html">Tutorial about NIO 2 from Oracle</a>
