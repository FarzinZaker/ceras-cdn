package ceras.cdn.feeder

class FileClient extends Thread {

    private String url

    FileClient(String url) {
        this.url = url
    }

    @Override
    void run() {
        try {
            new URL(url).bytes
        } catch (ignore) {
        }
    }
}
