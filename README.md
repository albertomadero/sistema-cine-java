# Sistema Gestionador de Cinema

## Descripción
Gestion el flujo completo de un cinema, el sistema puede gestionar:
- Peliculas
- Funciones
- Salas
- Reservas
- Usuarios

## Tecnologías
- Lenguaje: Java 22
- Gestor de dependencias: Maven

## Cómo ejecutar el proyecto
1. Clona el repositorio: git clone https://github.com/albertomadero/sistema-cine-java
2. Ábrelo en tu IDE y presiona el botón Run.
3. El sistema se maneja mediante un menú por línea de comandos.

## Persistencia de datos
El sistema utiliza archivos CSV para persistir la información (usuarios, asientos,
salas, películas, funciones y reservas). En la primera ejecución no habrá datos
existentes, ya que los archivos se generan conforme se usa el sistema.