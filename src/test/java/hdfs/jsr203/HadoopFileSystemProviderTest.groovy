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

import spock.lang.Specification

/**
 * High level integration-style tests for the entire provider.
 */
class HadoopFileSystemProviderTest extends Specification {
	def "just starting stuff"() {
		given: "the number 5"
			def number = 5
		when: "I multiply it by 10"
			def result = number * 10
		then: "we get 50"
			50 == result
	}
}
