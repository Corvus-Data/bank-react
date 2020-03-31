package com.corvusdata.bankreact.controller

import com.corvusdata.bankreact.service.ReactivoService
import com.corvusdata.bankreact.service.TipoReactivoService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReactivoAPIController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var reactivoService: ReactivoService

    @Autowired
    lateinit var tipoReactivoService: TipoReactivoService


    @GetMapping("/api/reactivos")
    fun getReactivos() = reactivoService.findAll()

    
}