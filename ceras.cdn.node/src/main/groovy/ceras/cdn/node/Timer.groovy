package ceras.cdn.node

class Timer {
    private long startTime = System.currentTimeMillis()

    private long localTime = 0
    private long remoteTime = 0

    void logLocalTime() {
        def duration = System.currentTimeMillis() - startTime
        localTime += duration
        startTime = System.currentTimeMillis()
    }

    void logRemoteTime() {
        def duration = System.currentTimeMillis() - startTime
        remoteTime += duration
        startTime = System.currentTimeMillis()
    }

    Long getLocalTime() {
        localTime
    }

    Long getRemoteTime() {
        remoteTime
    }
}
