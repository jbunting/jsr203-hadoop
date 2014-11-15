/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hdfs.jsr203

import org.apache.hadoop.fs.Path
import org.apache.hadoop.hdfs.MiniDFSCluster
import spock.lang.Shared
import spock.lang.Specification

/**
 * High level integration-style tests for the entire provider.
 */
class HadoopFileSystemProviderTest extends Specification {

	@Shared
	MiniDFSCluster dfsCluster;

	def setupSpec() {
		dfsCluster = ClusterLauncher.launchCluster(HadoopFileSystemProviderTest.class.getName())
	}

	def teardownSpec() {
		dfsCluster.shutdown()
	}

	def "test basic cluster operations"() {
		given: "the filesystem"
			def fs = dfsCluster.getFileSystem()
		expect: "root dir exists"
			fs.exists(new Path("/"))
		and: "/test.txt doesn't exist"
			!fs.exists(new Path("/test.txt"))
		when: "i write a new file"
			fs.create(new Path("/test.txt"), true).withPrintWriter {
				it.printf("Just doing a test.%n")
			}
		then: "the file exists"
			fs.exists(new Path("/test.txt"))
		and: "it contains the proper content"
			print fs.listStatus(new Path("/"))
			"Just doing a test.\n" == fs.open(new Path("/test.txt")).withCloseable {
				return it.getText("UTF-8")
			}
	}
}
