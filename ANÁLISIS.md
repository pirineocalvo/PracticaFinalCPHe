# ANÁLISIS PrácticaFinal: Gestión gastos concesionario

## Información

### User
- name
- password 
- UUID

### Car
- brand
- plate
- yearProduction
- UUID (relación con User)

### Outlay
- type
- kilometers
- date
- finalCost
- optionalDescription
- plate (relación con Car)

## Acciones

### Register
- Usuario se registra en el sistema con nombre (único), contraseña y tras su validación, se le asigna automáticamente un UUID. La contraseña se guarda encriptada para mejorar la seguridad.

### Log in
- Usuario accede al sistema introduciendo su nombre y contraseña.

### Create car
- Usuario crea un coche y se convierte en propietario. Sistema antes de finalizar la creación, debe preguntar si tiene algún otro propietario.

### Show cars
- Usuario puede ver la información de los coches de los que es propietario y a los que se le ha añadido.

### Edit cars
- Usuario puede modificar la información de sus coches.

### Delete cars 
- Usuario puede eliminar sus coches.

### Add outlay
- Usuario escoge tipo de gasto (gasolina, revisión ITV, cambio de aceito u otros) de una lista. Luego selecciona kilometraje, fecha  e importe. Por último descripción.

### Show outlay
- Usuario puede ver en un tabla los gastos de cada coche. También, tiene que poder filtrar por año, fecha y kilometraje. Por defecto, esta opción está en "Todos".


### Importante
- Todas las acciones tienen que tener un control de errores.
