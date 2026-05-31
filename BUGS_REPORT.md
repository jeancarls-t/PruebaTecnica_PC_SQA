# Reporte de Bugs - Prueba Técnica PC

## Bug 1: Botón LOAD MORE no funciona
- **Severidad:** Alta
- **Descripción:** Al hacer clic en el botón "LOAD MORE", la página no carga más destinos. Solo muestra los destinos iniciales.
- **Pasos para reproducir:**
  1. Completar datos de viaje
  2. Hacer clic en "SELECT DESTINATION"
  3. Hacer clic en "LOAD MORE"
- **Resultado esperado:** Deben mostrarse más destinos disponibles.
- **Resultado actual:** La página no muestra nuevos destinos.
- **Impacto:** No se pueden ver todos los destinos disponibles.

## Bug 2: Botón PAY NOW no se habilita
- **Severidad:** Alta
- **Descripción:** Después de llenar todos los campos del formulario de pago y aplicar código promocional, el botón "Pay Now" permanece deshabilitado o no aparece.
- **Pasos para reproducir:**
  1. Completar todo el flujo de reserva
  2. Llenar nombre, email, SSN, teléfono
  3. Cargar archivo
  4. Aplicar código promocional "TEST2026"
  5. Aceptar términos y condiciones
- **Resultado esperado:** El botón "Pay Now" debe habilitarse y permitir finalizar la compra.
- **Resultado actual:** El botón no se habilita, impidiendo completar la reserva.
- **Evidencia:** El test automatizado falla al intentar hacer clic en "Pay Now".
- **Ambiente:** Chrome 148, Windows 11, URL: https://demo.testim.io/