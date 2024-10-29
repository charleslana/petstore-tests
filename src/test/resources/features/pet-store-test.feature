@petStore
Feature: Teste da funcionalidade pet store

    @postOrder
    Scenario: Criar um pedido de pet
        Given Eu faço uma solicitação POST para "/store/order"
        Then o código de status de resposta deve ser 200
        And a resposta deve conter status "PROCESSING"

    @example
    Scenario: Criar um pedido de pet inexistente
        Given Eu faço uma solicitação POST para "/store/order"
        Then o código de status de resposta deve ser 400
        And a resposta deve conter status "PROCESSING"
