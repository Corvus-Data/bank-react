package com.corvusdata.bankreact

import com.corvusdata.bankreact.model.TipoReactivo
import com.corvusdata.bankreact.model.TipoReactivoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BankReactApplication : CommandLineRunner {

	private val logger = LoggerFactory.getLogger(javaClass)


	override fun run(args: Array<String>) {
		logger.info("----------------------------------------")
		logger.info("----- Bank React Application Started ---")
		logger.info("----------------------------------------")
	}

	@Bean
	fun initDatabase(tipoReactivoRepository: TipoReactivoRepository) = ApplicationRunner {
		logger.info("Init or Update Database")

		var tr = TipoReactivo(1, "Opción Múltiple", "Tipo de reactivo que será de " +
				"opción múltiple.")
		tipoReactivoRepository.save(tr)

		tr = TipoReactivo(2, "Pregunta abierta", "Tipo de reactivo donde se " +
				"contestará la pregunta de forma abierta.")

		tipoReactivoRepository.save(tr)
	}
}

fun main(args: Array<String>) {
	runApplication<BankReactApplication>(*args)
}
