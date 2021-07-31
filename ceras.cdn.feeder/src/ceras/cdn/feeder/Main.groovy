package ceras.cdn.feeder

class Main {
    static void main(String[] args) {
        def load = Generator.generateRandomLoad()
        Config.STEPS.times { step ->
            List<PathClient> clients = []
            load.keySet().each { path ->
                clients << new PathClient(path, load[path][step])
            }
            clients.each { it.start() }
            println "STEP $step STARTED"
            clients.each { it.join() }
            println "STEP $step COMPLETED"
        }
    }
}
