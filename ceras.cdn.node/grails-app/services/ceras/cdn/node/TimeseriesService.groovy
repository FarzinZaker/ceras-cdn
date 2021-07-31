package ceras.cdn.node

import com.influxdb.client.InfluxDBClient
import com.influxdb.client.InfluxDBClientFactory
import com.influxdb.client.WriteApi
import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.write.Point
import grails.gorm.transactions.Transactional

import java.time.Instant

@Transactional
class TimeseriesService {

    def configService

    def logProcessingTimes(String file, long localExecutionTime, long remoteExecutionTime) {
        println "LOCAL: ${localExecutionTime}, REMOTE: ${remoteExecutionTime}"

        InfluxDBClient client = InfluxDBClientFactory.create("http://${configService.influxDBHost}:${configService.influxDBPort}", configService.influxDBToken.toCharArray());

        Point point = Point
                .measurement("execution_time")
                .addTag("name", configService.name)
                .addField("local", localExecutionTime)
                .addField("remote", remoteExecutionTime)
                .addField("total", localExecutionTime + remoteExecutionTime)
                .time(Instant.now(), WritePrecision.NS);

        try {
            WriteApi writeApi = client.getWriteApi()
            writeApi.writePoint(configService.influxDBBucket, configService.influxDBOrg, point);
        } catch (ignore) {
        }
    }
}
