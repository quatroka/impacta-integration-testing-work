@run
Feature: CRUD Process

  Background:
    Given user wants save a process
    And and the user informs a process data
      | vara | numero_processo | natureza | partes       | urgente | arbitramento | assistente_social | data_entrada | data_saida | data_agendamento | status     | Observacao |
      | 3    | 4               | Guarda   | Jose X Maria | N       | S            | Gisele            | 20/10/2020   | 20/10/2020 |                  | Aguardando |            |

  Scenario: Create Process - POST
    When user clicks on save button
    Then user should see "Salvo com sucesso" message


  Scenario: Show process detail - GET
    Given user wants see a process with ID
    When user clicks on see button
    Then user should see "Consulta realizada com sucesso" message

  Scenario Outline: Edit process info - PUT
    Given user wants edit a process
    And user informs "vara" equals to "<vara>"
    And user informs "numero_processo" equals to "<numero_processo>"
    And user informs "natureza" equals to "<natureza>"
    And user informs "partes" equals to "<partes>"
    And user informs "urgente" equals to "<urgente>"
    And user informs "arbitramento" equals to "<arbitramento>"
    And user informs "assistente_social" equals to "<assistente_social>"
    And user informs "data_entrada" equals to "<data_entrada>"
    And user informs "data_saida" equals to "<data_saida>"
    And user informs "data_agendamento" equals to "<data_agendamento>"
    And user informs "status" equals to "<status>"
    And user informs "observacao" equals to "<observacao>"
    When user clicks on edit button
    Then user should see "<result_message>" message
    Examples:
      | vara | numero_processo | natureza  | partes            | urgente | arbitramento | assistente_social  | data_entrada | data_saida | data_agendamento | status     | observacao       | result_message               |
      | 10   | 422             | Guarda2   |                   | S       | N            | Gisele da Silva    | 20/10/2022   | 20/10/2021 |                  | Aguardando |                  | Erro ao editar processo      |
      | 10   | 422             | Guarda2   | JOao X Maria      | S       | N            | Gisele da Silva    | 20/10/2022   | 20/10/2021 |                  | Aguardando | Vai ser incrivel | Processo editado com sucesso |
      | 10   | 422             | Guarda2   | Fulatinha         | S       |              | Gisele da Silva    | 20/10/2022   | 20/10/2021 |                  | Aguardando |                  | Erro ao editar processo      |
      | 1    | 1               | Auditoria | Empresa X Empresa | N       | N            | Clayton das couves | 01/02/2018   |            | 01/02/2023       | Em Analise |                  | Processo editado com sucesso |


  Scenario: Delete process - DELETE
    Given user wants delete a process with ID
    When user clicks on delete button
    Then user should see "Deletado com sucesso" message

  @ignore
  Scenario: Edit a process partially - PATCH
    Given user wants edit a process
    When user clicks on edit button
    Then user should see "" message

