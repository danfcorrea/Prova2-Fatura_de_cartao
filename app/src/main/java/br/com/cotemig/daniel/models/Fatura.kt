package br.com.cotemig.daniel.models

class Fatura {
    var limiteDisponivel: Double = 0.0
    var valor: Double = 0.0
    var despesas: List<ItensFatura> = emptyList()
}