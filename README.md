# Planejamento do Projeto Prático: Angular + Spring Boot

**Duração Total:** 12 Horas (6 encontros de 2 horas)  
**Nível:** Ensino Técnico  
**Foco:** Backend Spring Boot (1 Entidade JPA + Regras de Negócio) e Frontend Angular (Interface CRUD + Integração REST)  

---

## 📅 Cronograma de Entregas por Aula (2h por Encontro)

### Aula 1 — 20/10: Modelagem do Backend e Configuração Inicial
* **Objetivo:** Criar o projeto Spring Boot, configurar o banco H2 e criar a Entidade principal com JPA.
* **Entregáveis (Backend):**
  * Projeto gerado no *Spring Initializr* (`Web`, `JPA`, `H2`, `Lombok`).
  * Classe `@Entity` mapeada com seus atributos e anotações `@Id`, `@GeneratedValue`.
  * Interface `Repository` estendendo `JpaRepository`.
  * Aplicação executando com banco H2 ativo e tabela gerada.

### Aula 2 — 27/10: Implementação das Regras de Negócio e Endpoints REST
* **Objetivo:** Escrever a lógica de negócio na camada Service e expor a API REST.
* **Entregáveis (Backend):**
  * Classe `@Service` contendo a regra de negócio central da entidade.
  * Classe `@RestController` com os métodos `GET`, `POST`, `PUT`, `DELETE` e os métodos customizados da regra de negócio.
  * Testes locais dos endpoints via Postman, Bruno ou Swagger.

### Aula 3 — 03/11: Inicialização do Frontend Angular e Serviço REST
* **Objetivo:** Criar o projeto Angular, configurar rotas e implementar o serviço HTTP.
* **Entregáveis (Frontend):**
  * Projeto Angular criado e limpo com módulo de rotas ativado.
  * Configuração do `HttpClient` / `provideHttpClient()`.
  * Criação da interface TypeScript (model) idêntica ao DTO/Entity Java.
  * Criação do `@Injectable Service` com métodos para consumir todos os endpoints do Spring Boot.

### Aula 4 — 10/11: Interface Gráfica — Listagem e Operações Rápidas
* **Objetivo:** Desenvolver o componente principal de exibição e ação de regra de negócio.
* **Entregáveis (Frontend):**
  * Componente de Listagem com tabela ou cards renderizados dinamicamente via `*ngFor` / `@for`.
  * Integração com o Service para carregar dados do Backend (`GET`).
  * Botões de ação direta (ex: botão "Concluir", "Votar", "Reservar" ou "Debitar") consumindo os endpoints customizados.
  * Exclusão (`DELETE`) integrada na tabela.

### Aula 5 — 17/11: Interface Gráfica — Formulário e Validações
* **Objetivo:** Criar tela ou modal para cadastro e edição de registros.
* **Entregáveis (Frontend):**
  * Componente de Formulário utilizando `ReactiveFormsModule`.
  * Validações de campos no front (campos obrigatórios, valores mínimos, etc.).
  * Integração do formulário com as rotas `POST` (Criação) e `PUT` (Edição).
  * Feedback visual ao usuário (mensagens de sucesso/erro ou alertas).

### Aula 6 — 24/11: Polimento, Tratamento de Erros e Apresentação Final
* **Objetivo:** Ajustes finais, integração `CORS`, tratamento de exceções e entrega final.
* **Entregáveis (Geral):**
  * Ajuste de `CORS` no Spring Boot (`@CrossOrigin`).
  * Tratamento básico de erros no Angular (exibir mensagem quando a regra de negócio do Java bloquear algo).
  * Teste do fluxo completo (End-to-End): Cadastrar $\rightarrow$ Listar $\rightarrow$ Executar Regra $\rightarrow$ Editar $\rightarrow$ Excluir.
  * Apresentação/Avaliação do projeto finalizado.

---

## 🚀 Agendamento de Equipamentos / Armários
* **Entidade:** `ReservaArmario` (`id`, `numeroArmario`, `nomeAluno`, `dataInicio`, `dataFim`, `ativo`)
* **Regra de Negócio:** O backend verifica se já existe uma reserva ativa para o mesmo `numeroArmario` cujo intervalo de datas coincida com o novo agendamento, impedindo conflitos.
* **Endpoints:**
  * `GET /api/armarios` — Lista todas as reservas.
  * `GET /api/armarios/{id}` — Consulta detalhes da reserva.
  * `POST /api/armarios` — Cria reserva (valida se o armário está livre no período).
  * `PUT /api/armarios/{id}` — Edita dados ou datas da reserva.
  * `DELETE /api/armarios/{id}` — Exclui/cancela a reserva.
  * `PATCH /api/armarios/{id}/liberar` — Marca a reserva como encerrada/inativa.

* **Pseudocódigo da Regra de Negócio (Validação de Conflito de Horário):**
```text
Função criarReserva(novaReserva):
    reservasExistentes = repository.buscarAtivasPorArmario(novaReserva.numeroArmario)
    
    Para Cada reserva Em reservasExistentes Faça
        Se (novaReserva.dataInicio < reserva.dataFim) E (novaReserva.dataFim > reserva.dataInicio) Então
            LançarErro("O armário já possui uma reserva ativa para o período informado.")
        FimSe
    FimPara
    
    novaReserva.ativo = Verdadeiro
    Retornar repository.salvar(novaReserva)
FimFuncao
```
