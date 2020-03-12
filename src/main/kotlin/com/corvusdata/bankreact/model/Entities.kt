package com.corvusdata.bankreact.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tipo_reactivo")
class TipoReactivo (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id_tipo_reactivo")
        var idTipoReactivo: Int,
        var nombre: String,
        var descripcion: String
) {
        constructor() : this(0, "", "")
}

@Entity
@Table(name = "reactivo")
class Reactivo(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id_reactivo")
        var idReactivo: Int?,
        var descripcion: String?,
        @ManyToOne
        var tipoReactivo: TipoReactivo?
) {
        constructor() : this(null, null, null)
}

@Entity
@Table(name = "opcion_multiple")
class OpcionMultiple (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id_opcion_multiple")
        var idOpcionMultiple: Int,
        var descripcion: String
)

class ReactivoOpcionMultipleId(
        @Column(name="id_reactivo")
        var idReactivo: Int,
        @Column(name="id_opcion_multiple")
        var idOpcionMultiple: Int
) : Serializable


@Entity
@IdClass(ReactivoOpcionMultipleId::class)
data class ReactivoOpcionMultiple(
        @Id
        @Column(name="id_reactivo")
        var idReactivo: Int,
        @Id
        @Column(name="id_opcion_multiple")
        var idOpcionMultiple: Int,
        var respuesta_correcta: Boolean)