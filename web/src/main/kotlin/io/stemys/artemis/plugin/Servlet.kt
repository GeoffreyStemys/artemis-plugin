package io.stemys.artemis.plugin


import io.micrometer.core.instrument.Metrics.globalRegistry
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND
import jakarta.servlet.http.HttpServletResponse.SC_OK
import mu.KotlinLogging

class Servlet : HttpServlet() {
  
  private val logger = KotlinLogging.logger {}
  
  private var registry: PrometheusMeterRegistry? = null
  
  init {
    getRegistry()
  }
  
  private fun getRegistry(): PrometheusMeterRegistry? = run {
    registry ?: findRegistry()
    registry
  }
  
  private fun findRegistry(): PrometheusMeterRegistry? = globalRegistry
    .registries.toMutableList()
    .firstOrNull { it is PrometheusMeterRegistry }
    ?.let { it as PrometheusMeterRegistry }
  
  override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) = doGet(req, resp)
  
  override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) =
    when (val registry = getRegistry()) {
      null -> {
        val msg = "Prometheus registry is null"
        logger.error { msg }
        resp.sendError(SC_NOT_FOUND, msg)
      }
      
      else -> {
        logger.error { "scraping metrics ..." }
        resp.status = SC_OK
        resp.writer.use {
          it.write(registry.scrape())
          it.flush()
        }
      }
    }
  
}