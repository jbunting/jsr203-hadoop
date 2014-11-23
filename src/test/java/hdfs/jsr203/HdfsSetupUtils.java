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
package hdfs.jsr203;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.MiniDFSCluster;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This exists because the imports for direct-hdfs setup and jsr203 testing tend to conflict with each other. So we
 * have put some useful "create the environment" type methods in here.
 */
class HdfsSetupUtils {
    private final MiniDFSCluster cluster;

    HdfsSetupUtils(MiniDFSCluster cluster) {
        this.cluster = cluster;
    }

    OutputStream writePath(String pathString) throws IOException {
        final DistributedFileSystem fs = cluster.getFileSystem();
        final Path path = new Path(pathString);
        return new FilterOutputStream(fs.create(path, true)) {
            @Override
            public void close() throws IOException {
                super.close();
                System.out.println("Wrote content to path " + fs.resolvePath(path));
            }
        };
    }
}
