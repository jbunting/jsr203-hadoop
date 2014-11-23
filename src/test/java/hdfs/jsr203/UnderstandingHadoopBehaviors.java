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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * TODO: Document this class
 */
public class UnderstandingHadoopBehaviors {
    private static MiniDFSCluster cluster;
    private static URI clusterUri;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        cluster = startMini(TestFileSystem.class.getName());
        clusterUri = cluster.getFileSystem().getUri();
    }

    @AfterClass
    public static void teardownClass() throws Exception {
        if (cluster != null)
        {
            cluster.shutdown();
        }
    }

    private static MiniDFSCluster startMini(String testName) throws IOException {
        File baseDir = new File("./target/hdfs/" + testName).getAbsoluteFile();
        FileUtil.fullyDelete(baseDir);
        Configuration conf = new Configuration();
        conf.set(MiniDFSCluster.HDFS_MINIDFS_BASEDIR, baseDir.getAbsolutePath());
        MiniDFSCluster.Builder builder = new MiniDFSCluster.Builder(conf);
        MiniDFSCluster hdfsCluster = builder.clusterId(testName).build();
        hdfsCluster.waitActive();
        return hdfsCluster;
    }

    @Test
    public void testHDFSBehaviors() throws IOException {
        FileSystem hdfs = FileSystem.get(cluster.getConfiguration(0));
        System.out.println(hdfs.getUri());

        Path path = new Path("/myRoot");
        System.out.println(path);
        try (FSDataOutputStream out = hdfs.create(path, true)) {
            out.writeBytes("hadoop");
        }
        System.out.println(hdfs.resolvePath(path));

        Path relative = new Path("myRelative");
        System.out.println(relative);
        try (FSDataOutputStream out = hdfs.create(relative, true)) {
            out.writeBytes("hadoop");
        }
        System.out.println(hdfs.resolvePath(relative));

        hdfs.setWorkingDirectory(new Path("/home"));

        System.out.println(relative);
        try (FSDataOutputStream out = hdfs.create(relative, true)) {
            out.writeBytes("hadoop");
        }
        System.out.println(hdfs.resolvePath(relative));

        Path empty = new Path("");
        System.out.println(empty);
        try (FSDataOutputStream out = hdfs.create(relative, true)) {
            out.writeBytes("hadoop");
        }
        System.out.println(hdfs.resolvePath(empty));
    }
}
