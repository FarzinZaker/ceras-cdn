package ceras.cdn.feeder

import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.DirectoryFileFilter
import org.apache.commons.io.filefilter.RegexFileFilter


class Generator {
    static void main(String[] args) {
        def load = generateRandomLoad()
        load.each { file ->
            println file
        }
    }

    private static Collection<String> getAllFiles() {
        def root = '/opt/cdn/data/000/files'
        FileUtils.listFiles(
                new File(root),
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        ).findAll { it.name != '.DS_Store' }.subList(0,50).collect { it.absolutePath.replace(root, '') }
    }

    static Map<String, List<Map<String, Integer>>> generateRandomLoad() {
        def load = [:]
        def random = new Random()
        allFiles.each { file ->
            load.put(file, [])
            Config.STEPS.times { step ->
                def map = [:]
                Config.NODES.each { node ->
                    map.put("http://${node.value.host}:${node.value.port}"?.toString(), random.nextInt(Config.MAX + 1))
                }
                load[file] << map
            }
        }
        load
    }
}
