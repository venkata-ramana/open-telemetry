env OTEL_TRACES_EXPORTER=otlp OTEL_EXPORTER_OTLP_TRACES_ENDPOINT=http://localhost:5555 OTEL_RESOURCE_ATTRIBUTES=service.name=node-app,service.version=1.0
node --require @opentelemetry/auto-instrumentations-node/register app.js
