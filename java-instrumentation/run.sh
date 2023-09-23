export OTEL_TRACES_EXPORTER=otlp
export OTEL_METRICS_EXPORTER=otlp
export OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:5555
export OTEL_RESOURCE_ATTRIBUTES=service.name=java-instrumentation,service.version=1.0

java -javaagent:opentelemetry-javaagent.jar -Dotel.service.name=java-instrumentation -jar ./target/api-1.0.jar