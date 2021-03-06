# Practicas_Android
## Propiedad de Juan Pablo Monsalve Navarro
## Asistente tecnico Omar David Vargas Bonett
### Repositorio creado para almacenar los proyectos de programación de aplicaciones móviles en Android

## Práctica # 1
### Interfaces de Usuario
1. (1.0) Implementar un formulario de registro con los siguientes datos:
- Nombre - Usar TextInputLayout
- Correo electrónico - Usar TextInputLayout
- Password - Usar TextInputLayout
- Repetir Password - Usar TextInputLayout
- Sexo Femenino Masculino - Usar RadioButton (hay uno seleccionado por defecto)
- Hobbies: Usar CheckBox (4 hobbies a su gusto)
- Fecha de Nacimiento - Usar un DatePicker
- Lugar de nacimiento: Usar un Spinner (max 5 ciudades a su gusto)
- Botón Guardar
- Al dar click en Guardar se debe verificar que ningún campo esté vacío y que los dos password ingresados sean iguales, toda la información se debe visualizar en la parte inferior en un TextView
2. (2.0) Desarrolle una aplicación para realizar conversión de monedas, mínimo 3 tipos de moneda, la interfaz la puede implementar a su gusto
3. (2.0) Desarrollar una aplicación que permita calcular el valor de una resistencia teniendo en cuenta el código de colores, diseñar la interfaz a su gusto

## Práctica # 2
### Sistema de Login
Desarrollar el login de su proyecto final, el diseño es a su gusto y será evaluado en la
práctica. Tener en cuenta las siguientes instrucciones:
1. Realizar una aplicación que tenga 4 actividades:
a. Splash (SplashActivity.kt): con logotipo de su aplicación, se cargará máximo 2 segundos, al final de esta guía está el código de cómo se programa esta actividad.
b. Login (LoginActivity.kt): El login será con correo electrónico, una contraseña de mínimo 6 dígitos y una opción de ir a la actividad de registro
c. Actividad de Registro (RegistroActivity.kt): Se utiliza la actividad de registro de la práctica 1 con las validaciones necesarias y los campos que usted considere necesitará en su proyecto
d. Actividad Principal (MainActivity.kt): En esta actividad se visualiza un textview con el correo electrónico de registro, un menú de overflow con la opción "Cerrar Sesión"

2. Tener en cuenta los siguientes aspectos para definir la navegación:
- Al abrir la aplicación se abre el Splash por máximo 2 segundos, al cerrarse se abre el Login
- En Login inicialmente no hay datos cargados por lo que es necesario que el usuario vaya a Registro (Click en Registro)
- En Registro se digitan los datos y al dar click en Registrar enviará el correo y la contraseña al Login
- En el Login se digitan correo y contraseña, estos valores se deben comparar con los que recibió de la actividad de Registro, si son correctos entra a  Actividad principal sino debe informar al usuario que hay un error en correo o contraseña.
- En Actividad principal se puede visualizar a su gusto el correo electrónico del usuario, el menú de overflow tiene una opción de “Cerrar sesión”, al presionarlo debe volver a la interfaz de login
- Al Cerrar sesión el usuario debe poder loguearse con los datos entrados inicialmente
- Verifique que el funcionamiento del onBackPressed sea el adecuado
- No se puede utilizar SQLite, ROOM, ni Preferencias Compartidas

## Práctica # 3
### Fragments y Material Design (App Final V2.0)
Desarrollar la versión 2.0 de su aplicación, para esto tener en cuenta las siguientes
instrucciones:
- (1.5) La versión 1.0 de su app debe funcionar en su totalidad, (adicional: una vez
el usuario esté logueado (MainActivity) si se presiona el boton BackPressed se
debe cerrar la aplicacion)
- (3.5) Implementar una versión inicial de la aplicación de su proyecto final
utilizando los componente de Material Design para navegación, definir cuál
estructura utilizará (Bottom Navigation, Navigation Drawer ó Swipe Tabs)
Crear los fragments necesarios según su aplicación y colocar imagen o un
textview informando la función de ese fragment.
Observaciones
- Se calificará 80% funcionalidad y 20% Diseño
- Enviar el .apk de la aplicación

## Práctica # 5

### (40%) Diseño base de datos:

-Realizar el diseño de la base de datos de su aplicación, tener en cuenta el ejemplo dado en clase. 
-La aplicación debe tener mínimo 3 tablas: Una para usuarios y dos según necesidad de su aplicación, si implementan más no hay problema
-Subir a classroom un pdf sencillo con el formato que ustedes deseen donde se especifiquen las tablas, el nombre de cada una, sus atributos y que tipo de dato almacenará.
-Describir brevemente cuál es el uso de la tabla
-Se sugiere antes de la fecha de entrega solicitar asesoría al profesor para no tener que hacer correcciones ni reprocesos que los hagan perder tiempo.

### (60%) Aplicación Deudor APP

-Crear un Splash, Login y Registro a la aplicación desarrollada en clase de ROOM.
-El registro debe tener mínimo correo y contraseña y se van a guardar todos los campos digitados en base de datos (incluyendo contraseña) la navegación debe ser igual a la de la práctica 2
-Los datos de registro se deben almacenar en una base de datos con ROOM
En el login al digitar correo y contraseña debe hacer una búsqueda del usuario en base de datos, si lo encuentra inicia sesión y va al MainActivity, sino muestra mensaje de error


