package ceras.cdn.feeder

class PathClient extends Thread {

    private String path
    private Map<String, Integer> countMap

    PathClient(String path, Map<String, Integer> countMap) {
        this.path = path
        this.countMap = countMap
    }

    @Override
    void run() {
        List<HostClient> threads = []
        countMap.keySet().each { host ->
            threads << new HostClient(path, host, countMap[host])
        }
        threads.each {
            it.start()
        }
        threads.each {
            it.join()
        }
    }
}
