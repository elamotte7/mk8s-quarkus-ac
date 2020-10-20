package org.acme.grpc.server

import io.quarkus.test.junit.NativeImageTest

@NativeImageTest
class NativeHelloServiceIT : HelloServiceTest()