# Spotify Queue Simulation - Programación III

## Descripción General

Este proyecto consiste en el diseño e implementación de una simulación tipo Spotify utilizando una estructura de datos Cola (FIFO) desarrollada completamente desde cero en Java.

Se trabajó con arquitectura modular usando Maven, separando la estructura de datos en una librería propia y consumiéndola desde un segundo proyecto.

El sistema simula la reproducción realista de canciones desde consola, integrando:

-Estructura de datos manual
-Sistema de prioridad
-Logs detallados
-Simulación segundo a segundo
-Ejecución desde consola

## Estructura del Repositorio
/umg.edu.gt.data-structure.queue
/queueHandler
/evidencias
README.md 
-----
# Parte A — Librería de Cola

Proyecto: umg.edu.gt.data-structure.queue

Se implementó una cola genérica basada en nodos enlazados.

## Características:

-Implementación genérica QueueLinked<T>
-Clase Node<T>
-Referencias privadas head y tail
-Variable interna size
-Encapsulamiento correcto
-No se exponen nodos internos

## Complejidad:

-enqueue() → O(1)
-dequeue() → O(1)
-No se recorre la lista para insertar

## Manejo de errores:

Si se intenta hacer dequeue() o peek() en una cola vacía, se lanza:

IllegalStateException("Queue is empty.")

## Cómo compilar la librería

Desde la carpeta:

cd umg.edu.gt.data-structure.queue

Ejecutar:

mvn clean install

Esto instala la librería en el repositorio local de Maven.

-----
# Parte B — Simulación de Reproducción

Proyecto: queueHandler

Este proyecto consume la librería creada anteriormente mediante dependencia Maven.

Cada canción contiene:

-title
-artist
-duration (entre 5 y 30 segundos)
-priority (1 = alta, 2 = normal)

Las duraciones son variables para simular un comportamiento realista.

## Cómo compilar el handler

Desde la carpeta:

cd queueHandler

Ejecutar:

mvn clean package

## Cómo ejecutar desde consola

Ubicado dentro de la carpeta queueHandler, ejecutar:

mvn exec:java -Dexec.mainClass="umg.edu.gt.handler.Main"

Este comando permite ejecutar la clase principal incluyendo automáticamente las dependencias instaladas en el repositorio local de Maven.

-----
# Parte C — Sistema de Prioridad

Para implementar la prioridad sin romper el orden FIFO interno, se utilizaron dos instancias de la cola personalizada:

-Cola de prioridad alta (priority = 1)
-Cola de prioridad normal (priority = 2)

## Reglas aplicadas:

-Siempre se reproducen primero las canciones de prioridad alta.
-Dentro de cada prioridad se respeta el orden FIFO.
-No se utilizó PriorityQueue.

## Ejemplo de funcionamiento:

Alta: A1, A2
Normal: N1, N2

Salida esperada:

A1 → A2 → N1 → N2

-----
# Parte D — Extensión de Complejidad

Se implementaron las siguientes mejoras adicionales:

## 1. Contador total de canciones reproducidas

Se agregó una variable que incrementa cada vez que una canción finaliza su reproducción.

Al terminar la playlist se muestra:

[LOG] Total songs played: 4

## 2. Tiempo total acumulado reproducido

Se lleva un acumulado del tiempo total de todas las canciones reproducidas.

Al finalizar la ejecución se muestra:

[LOG] Total time played: 36 seconds

## 3. Barra de progreso visual

Durante la reproducción se muestra una barra dinámica que representa el avance de la canción segundo a segundo:

[#####-----] 5s / 10s

Esto permite visualizar gráficamente el progreso en tiempo real sin utilizar librerías externas.

# Explicación del Diseño

El sistema fue dividido en dos módulos independientes:

1. Librería de estructura de datos (cola manual).
2. Proyecto consumidor que simula la reproducción de canciones.

La cola fue implementada manualmente utilizando nodos enlazados para garantizar eficiencia O(1) en las operaciones principales.

El handler utiliza dos colas internas para gestionar prioridad sin romper el orden FIFO.

## Cómo manejé la simulación de duración

Cada canción tiene una duración variable entre 5 y 30 segundos.

La simulación se realiza usando:

Thread.sleep(1000);

Esto permite:

-Simular reproducción real segundo a segundo.
-Mostrar progreso en consola.
-Imprimir logs detallados.
-Mostrar barra de progreso visual.

Ejemplo de log:

[LOG] Playing: Song A | 3s / 10s [###-------]

Al finalizar cada canción se muestra:

[LOG] Finished: Song A

Y al terminar la playlist:

[LOG] Playlist finished.
Carpeta evidencias

# La carpeta /evidencias contiene capturas reales de:

-Ejecución de mvn clean install en la librería
-Ejecución de mvn clean package en el handler
-Ejecución del programa desde consola
-Logs mostrando reproducción segundo a segundo
-Evidencia del sistema de prioridad funcionando correctamente
