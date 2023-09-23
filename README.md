# Open-Telemetry

Run docker composer file to setup open telemetry collector in your local. This docker compose also contains prometheus, grafana.

```
cd otel-collector && docker compose up -d
```

After setting up, your collector will be running on 5555 port

```
export OTEL_TRACES_EXPORTER=otlp
export OTEL_METRICS_EXPORTER=otlp
export OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:5555
export OTEL_RESOURCE_ATTRIBUTES=service.name=<serviceName>,service.version=<serviceVersion>
```

logs will be collected by otel collector then kibana tempo utilizes the volumns to show the traces.

Here is the official collector [documentation](https://opentelemetry.io/docs/collector/)

## Otel Collector 

Data becomes truly valuable when it can be harnessed to extract insights and inform decisions. This principle holds just as true for telemetry data. Without the means to export and utilize telemetry data within an observability backend, its generation serves little purpose. OpenTelemetry, however, equips you with the tools to seamlessly gather, process, and transmit this valuable data to observability backends through its collector.

![otel-collector](https://opentelemetry.io/docs/collector/img/otel-collector.svg)

One might wonder, "Why not simply send telemetry data directly to the observability backend?" While this is feasible in cases where the backend supports OpenTelemetry's native protocol, OTLP, it's often considered a best practice to employ a collector. Here's why:
#### Data Reliability: 
By placing the collector alongside your application, you reduce the risk of data loss during network transmission. This is particularly valuable in edge-based scenarios like IoT or when the observability backend is located far from the application.
#### Buffering Capabilities: 
The collector can buffer data before forwarding it to the observability backend. This buffering mechanism is crucial for high-volume telemetry data generation, safeguarding the backend from sudden bursts that could overload it.
#### Centralized Gateway: 
Use the collector as a central gateway for multiple applications. It can efficiently gather and transmit data from various sources to the observability backend, streamlining the process.
#### Flexibility and Resilience: 
The collector acts as a flexible intermediary layer between your application and the observability backend. This layer allows you to make changes, upgrades, or even switch out the backend without causing downtime for your applications.
In essence, the collector serves as a versatile bridge, optimizing the way your telemetry data flows from your applications to the observability backend while enhancing reliability and adaptability.

Using the collector is straightforward. All you need to do is to create a configuration file that describes how the processing pipeline should work. A processing pipeline comprises three components: one or more receivers, optional processors, and one or more exporters.

### Otel Configuration
```yaml
receivers:
  otlp:
    protocols:
      grpc:
        endpoint: 0.0.0.0:5555

processors:
  batch:
    timeout: 1s
    send_batch_size: 1024

exporters:
  logging:
    loglevel: debug
  otlp:
    endpoint: tempo:4317
    tls:
      insecure: true

service:
  pipelines:
    metrics:
      receivers: [otlp]
      processors: [batch]
      exporters: [logging]
    traces:
      receivers: [otlp]
      processors: [batch]
      exporters: [logging, otlp]
```
