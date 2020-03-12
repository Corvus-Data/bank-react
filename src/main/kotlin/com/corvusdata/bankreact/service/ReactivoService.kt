package com.corvusdata.bankreact.service

import com.corvusdata.bankreact.model.Reactivo
import com.corvusdata.bankreact.model.ReactivoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class ReactivoService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var reactivoRepository: ReactivoRepository

    fun save(reactivo: Reactivo) = reactivoRepository.save(reactivo)
    fun existsById(id: Int) = reactivoRepository.existsById(id)
    fun findAll(): List<Reactivo> = reactivoRepository.findAll()
    fun findById(id: Int) = reactivoRepository.findById(id)
    fun count(): Long = reactivoRepository.count()

    fun findAll(pageNumber: Int, rowPerPage: Int): List<Reactivo> {
        val reactivos = ArrayList<Reactivo>()
        val sortedByLastUpdateDesc: Pageable = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("idReactivo").ascending())
        reactivoRepository.findAll(sortedByLastUpdateDesc).forEach { reactivos.add(it) }

        return reactivos
    }
}