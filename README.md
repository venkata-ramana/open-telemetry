# open-telemetry

Run docker composer file to setup open telemetry collector in your local. This docker compose also contains prometheus, grafana.

```
docker compose up -d
```

After setting up, your collector will be running on 5555 port

```
export OTEL_TRACES_EXPORTER=otlp
export OTEL_METRICS_EXPORTER=otlp
export OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:5555
export OTEL_RESOURCE_ATTRIBUTES=service.name=<serviceName>,service.version=<serviceVersion>
```
