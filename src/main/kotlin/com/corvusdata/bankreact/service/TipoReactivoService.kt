package com.corvusdata.bankreact.service

import com.corvusdata.bankreact.model.Reactivo
import com.corvusdata.bankreact.model.ReactivoRepository
import com.corvusdata.bankreact.model.TipoReactivo
import com.corvusdata.bankreact.model.TipoReactivoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
public class TipoReactivoService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var tipoReactivoRepository: TipoReactivoRepository

    fun findAll(): List<TipoReactivo> = tipoReactivoRepository.findAll()

}