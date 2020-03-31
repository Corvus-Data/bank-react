package com.corvusdata.bankreact.controller

import com.corvusdata.bankreact.model.Reactivo
import com.corvusdata.bankreact.model.TipoReactivo
import com.corvusdata.bankreact.service.ReactivoService
import com.corvusdata.bankreact.service.TipoReactivoService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
class ReactivoController {

    private val logger = LoggerFactory.getLogger(javaClass)

    final val ROW_PER_PAGE = 5

    val modulo = "Reactivo"

    @Autowired
    lateinit var reactivoService: ReactivoService

    @Autowired
    lateinit var tipoReactivoService: TipoReactivoService

    @Value("\${msg.title}")
    lateinit var title: String

    @Value("\${msg.add}")
    lateinit var add: String

    @GetMapping("/gui/reactivos")
    fun getReactivos(model: Model, @RequestParam("page", defaultValue = "1") pageNumber: Int ) : String {
        val reactivosList = reactivoService.findAll(pageNumber, ROW_PER_PAGE)
        logger.debug("Recibo el parametro de el Numero de pagina")
        val count = reactivoService.count()
        val hasPrev = pageNumber > 1
        val hasNext = (pageNumber * ROW_PER_PAGE) < count
        model.addAttribute("add", add)
        model.addAttribute("modulo", modulo)
        model.addAttribute("reactivos", reactivosList)
        model.addAttribute("hasPrev", hasPrev)
        model.addAttribute("prev", pageNumber - 1)
        model.addAttribute("hasNext", hasNext)
        model.addAttribute("next", pageNumber + 1)
        return "reactivos-list"
    }

    @GetMapping("/gui/reactivo/{idReactivo}")
    fun getReactivoById(model: Model, @PathVariable idReactivo: Int) : String {
        val reactivo : Reactivo

        try {
            model.addAttribute("modulo", modulo)
            reactivo = reactivoService.findById(idReactivo).get()
            model.addAttribute("reactivo", reactivo)
        } catch (ex: Exception) {
            logger.error(ex.message)
            model.addAttribute("errorMessage", ex.message)
        }

        return "reactivo"
    }

    @GetMapping("/gui/reactivo/add")
    fun showAddReactivo(model: Model) : String {
        val reactivo = Reactivo()
        val tipoReactivos = tipoReactivoService.findAll()

        model.addAttribute("modulo", modulo)
        model.addAttribute("tipoReactivos", tipoReactivos)
        model.addAttribute("add", true)
        model.addAttribute("reactivo", reactivo)

        return "reactivo-edit"
    }

    @PostMapping("/gui/reactivo/add")
    fun addReactivo(model: Model, @ModelAttribute("reactivo") reactivo: Reactivo): String {

        try {
            return "redirect:/reactivo/" + reactivoService.save(reactivo).idReactivo
        } catch (ex: Exception){
            logger.error(ex.message)
            model.addAttribute("modulo", modulo)
            model.addAttribute("errorMessage", ex.message)
            model.addAttribute("add", true)
            return "reactivo-edit"
        }
    }

    @GetMapping("/gui/reactivo/{idReactivo}/edit")
    fun showEditReactivo(model: Model, @PathVariable idReactivo: Int) : String {
        val reactivo : Reactivo
        val tipoReactivos = tipoReactivoService.findAll()

        try {
            reactivo = reactivoService.findById(idReactivo).get()
            model.addAttribute("reactivo", reactivo)
        } catch (ex: Exception) {
            model.addAttribute("errorMessage", ex.message)
        }

        model.addAttribute("modulo", modulo)
        model.addAttribute("tipoReactivos", tipoReactivos)
        model.addAttribute("add", false)

        return "reactivo-edit"
    }

    @PostMapping("/gui/reactivo/{idReactivo}/edit")
    fun updateReactivo(model: Model, @PathVariable idReactivo: Int, @ModelAttribute("reactivo") reactivo: Reactivo) : String {
        return "reactivo-edit"
    }
}