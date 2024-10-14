package io.stemys.artemis.plugin

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.prometheusmetrics.PrometheusConfig.DEFAULT
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.apache.activemq.artemis.core.server.metrics.ActiveMQMetricsPlugin

class Metrics : ActiveMQMetricsPlugin {
  
  private val prometheusRegistry: MeterRegistry = PrometheusMeterRegistry(DEFAULT)
  
  override fun init(options: Map<String?, String?>?): ActiveMQMetricsPlugin = this
  
  override fun getRegistry(): MeterRegistry = prometheusRegistry

}
