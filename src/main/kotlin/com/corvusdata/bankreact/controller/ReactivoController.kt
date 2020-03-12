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

@Controller
class ReactivoController {

    private val logger = LoggerFactory.getLogger(javaClass)

    final val ROW_PER_PAGE = 5;

    val modulo = "Reactivo"

    @Autowired
    lateinit var reactivoService: ReactivoService

    @Autowired
    lateinit var tipoReactivoService: TipoReactivoService

    @Value("\${msg.title}")
    lateinit var title: String

    @Value("\${msg.add}")
    lateinit var add: String



    @GetMapping("/", "/index")
    fun index(model:Model): String {
        model.addAttribute("title", title)
        return "index"
    }

    @GetMapping("/reactivos")
    fun getReactivos(model: Model, @RequestParam("page", defaultValue = "1") pageNumber: Int ) : String {
        var reactivosList = reactivoService.findAll(pageNumber, ROW_PER_PAGE)

        var count = reactivoService.count()
        var hasPrev = pageNumber > 1
        var hasNext = (pageNumber * ROW_PER_PAGE) < count
        model.addAttribute("add", add)
        model.addAttribute("modulo", modulo)
        model.addAttribute("reactivos", reactivosList)
        model.addAttribute("hasPrev", hasPrev)
        model.addAttribute("prev", pageNumber - 1)
        model.addAttribute("hasNext", hasNext)
        model.addAttribute("next", pageNumber + 1)
        return "reactivos-list"
    }

    @GetMapping("/reactivo/{idReactivo}")
    fun getReactivoById(model: Model, @PathVariable idReactivo: Int) : String {
        var reactivo : Reactivo

        try {
            model.addAttribute("modulo", modulo)
            logger.debug(" idReactivo ---> {}", idReactivo)
            reactivo = reactivoService.findById(idReactivo)
            logger.debug("Mira lo que tenemos aqui {}", reactivo)
            model.addAttribute("reactivo", reactivo)
        } catch (ex: Exception) {
            logger.error(ex.message)
            model.addAttribute("errorMessage", ex.message)
        }

        return "reactivo"
    }

    @GetMapping("/reactivo/add")
    fun showAddReactivo(model: Model) : String {
        val reactivo = Reactivo()
        val tipoReactivos = tipoReactivoService.findAll()

        model.addAttribute("modulo", modulo)
        model.addAttribute("tipoReactivos", tipoReactivos)
        model.addAttribute("add", true)
        model.addAttribute("reactivo", reactivo)

        return "reactivo-edit"
    }

    @PostMapping("/reactivo/add")
    fun addReactivo(model: Model, @ModelAttribute("reactivo") reactivo: Reactivo): String {

        try {
            return "redirect:/reactivo/" + reactivoService.save(reactivo).idReactivo;
        } catch (ex: Exception){
            logger.error(ex.message)
            model.addAttribute("modulo", modulo)
            model.addAttribute("errorMessage", ex.message)
            model.addAttribute("add", true)
            return "reactivo-edit"
        }
    }

    @GetMapping("/reactivo/{idReactivo}/edit")
    fun showEditReactivo(model: Model, @PathVariable idReactivo: Int) : String {
        var reactivo : Reactivo?

        try {
            reactivo = reactivoService.findById(idReactivo)
            model.addAttribute("reactivo", reactivo)

        } catch (ex: Exception) {
            model.addAttribute("errorMessage", ex.message)
        }

        model.addAttribute("modulo", modulo)
        model.addAttribute("add", false)

        return "reactivo-edit"
    }

    @PostMapping("/reactivo/{idReactivo}/edit")
    fun updateReactivo(model: Model, @PathVariable idReactivo: Int, @ModelAttribute("reactivo") reactivo: Reactivo) : String {

        return "reactivo-edit"
    }
}