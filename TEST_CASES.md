# Casos de Prueba No Automatizados

## Validaciones de Formulario de Pago
1.  **CP-01 - Formato de Teléfono**: Validar que el sistema rechace números que no comiencen con "+5787" o no tengan exactamente 7 dígitos después del prefijo.
3.  **CP-02 - Código Promocional Inválido**: Ingresar un código inválido y verificar que se muestre un mensaje de error (no solo que no haga nada).

## Interacción con el Filtro de Precio
4.  **CP-03 - Rango de Precio Mínimo**: Verificar que al mover el slider al valor mínimo ($100), solo se muestren destinos con precio menor o igual a ese valor.
5.  **CP-04 - Sin Resultados**: Seleccionar un rango de precio muy bajo y verificar que se muestre un mensaje de "No destinations found".

## Navegación y UI
6.  **CP-05 - Botón LOAD MORE**: [Reportado como Bug] Este es un bug funcional.