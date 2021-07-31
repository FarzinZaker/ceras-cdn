package ceras.cdn.feeder

class HostClient extends Thread {

    private String path
    private String host
    private Integer count

    HostClient(String path, String host, Integer count) {
        this.path = path
        this.host = host
        this.count = count
    }

    @Override
    void run() {
        List<FileClient> threads = []
        count.times {
            threads << new FileClient("$host$path")
        }
        threads.each {
            it.start()
        }
        threads.each {
            it.join()
        }
    }
}
