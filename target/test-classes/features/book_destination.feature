Feature: Agendar un destino de viaje

  Background:
    Given el usuario está en la página de inicio de Testim.io

  @Smoke @Critical
  Scenario Outline: Filtrar por precio y reservar un destino
    When ingresa los datos del viaje:
      | fecha_partida | fecha_regreso | adultos | ninos |
      | 2026-06-15    | 2026-06-20    | 2       | 1     |
    And presiona el botón "SELECT DESTINATION"
    When cambia el precio del filtro a <precio_maximo>
    And selecciona el destino "<destino>"
    And llena el formulario de pago con los datos:
      | name        | email               | ssn         | phone_number  | ruta                               | codigo_promocional |
      | Jean Carlos | jeancarlos@test.com | 123-45-6789 | +573025123111 | src/test/resources/data/Upload.txt | TEST2026           |
    Then debe ver el mensaje "<mensaje>"

    Examples:
      | precio_maximo | destino | mensaje             |
      | 1200          | Madan   | MADAN TEMPERATURES  |
      | 1100          | Shenji  | SHENJI TEMPERATURES |

  @Smoke @Critical
  Scenario: Validar formato incorrecto de Social Security Number
    When ingresa los datos del viaje:
      | fecha_partida | fecha_regreso | adultos | ninos |
      | 2026-06-23    | 2026-06-30    | 3       | 2     |
    And presiona el botón "SELECT DESTINATION"
    When cambia el precio del filtro a 1500
    And selecciona el destino "Sant Cugat Del Valles"
    And llena los datos básicos del formulario:
      | name        | email               | phone_number  |
      | kean Carlos | keancarlos@test.com | +573053223117 |
    And escribe un SSN inválido "12345"
    Then debe ver el mensaje de error "Enter a valid Social Security number (xxx-xx-xxxx)."


  @Smoke @Critical
  Scenario: Seleccionar destino desde dropdown y reservar Tongli
    When ingresa los datos del viaje:
      | fecha_partida | fecha_regreso | adultos | ninos |
      | 2026-06-28    | 2026-07-12    | 1       | 3     |
    And presiona el botón "SELECT DESTINATION"
    And selecciona el destino "Tongli" desde el dropdown
    And selecciona la tarjeta de destino "Tongli"
    And llena el formulario de pago con los datos:
      | name        | email               | ssn         | phone_number  | ruta                               | codigo_promocional |
      | Puan Carlos | puancarlos@test.com | 623-65-6790 | +573002221119 | src/test/resources/data/Upload.txt | TEST2026           |
    Then debe ver el mensaje "TONGLI"