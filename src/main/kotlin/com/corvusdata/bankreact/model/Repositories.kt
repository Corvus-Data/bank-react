package com.corvusdata.bankreact.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface TipoReactivoRepository : JpaRepository<TipoReactivo, Int> {
}

@Repository
interface ReactivoRepository : JpaRepository<Reactivo, Int> {
}

@Repository
interface OpcionMultipleRepository : JpaRepository<OpcionMultiple, Int> {
}

@Repository
interface ReactivoOpcionMultipleRepository : JpaRepository<ReactivoOpcionMultiple, ReactivoOpcionMultipleId> {
}