# Ejercicio 1

## Descripción
Sistema para registrar participantes en un concurso.

## Estructura del Proyecto

```
ejercicio1/
├── controller/
│   └── ParticipanteController.java
├── db/
│   └── JDBCRegistroParticipante.java
├── main/
│   ├── Main.java
│   └── SetUpDatabase.java
├── models/
│   ├── Concurso.java
│   ├── Participante.java
│   └── RegistrarParticipante.java
├── ui/
│   └── VentanaPrincipal.java
└── README.md (este archivo)
```

---

## Anteiormente: Arquitectura sin Controller

### Estructura Original
```
ejercicio1/
├── db/
│   └── JDBCRegistroParticipante.java
├── main/
│   ├── Main.java
│   └── SetUpDatabase.java
├── models/
│   ├── Concurso.java
│   ├── Participante.java
│   └── RegistrarParticipante.java
└── ui/
    └── VentanaPrincipal.java
```

### Flujo de Datos
```
Main
  └─> VentanaPrincipal(Concurso)  ← La UI recibe el Concurso directamente
      └─> Usuario ingresa datos
          └─> botonCargar.addActionListener() → {
              1. Extrae datos de campos (Ask)
              2. Crea Participante (Ask + construcción)
              3. Llama concurso.registrarParticipante()
          }
```

### Código de la UI
```java
public class VentanaPrincipal extends JFrame {
    private Concurso concurso;
    
    public VentanaPrincipal(Concurso concurso) {
        this.concurso = concurso;  // ← Recibe la lógica de negocio
    }
    
    private void setUpUIComponents() {
        // ...
        botonCargar.addActionListener(e -> {
            try {
                // ← La UI pregunta (Ask) por datos
                // ← La UI construye el objeto (Ask + Tell)
                concurso.registrarParticipante(new Participante(
                        nombre.getText(),      // ← Pregunta: ¿cuál es el nombre?
                        telefono.getText(),    // ← Pregunta: ¿cuál es el teléfono?
                        region.getText()       // ← Pregunta: ¿cuál es la región?
                ));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
```

### Identifique:

#### 1. **Violación de "Tell, Don't Ask"**
- La UI **pregunta** (Ask) por los datos de los campos de texto
- La UI **construye** el objeto `Participante`
- La UI sabía demasiado sobre cómo crear y registrar participantes
- Acoplamiento entre UI y lógica de negocio

#### 2. **Violación del SRP (Single Responsibility Principle)**
- `VentanaPrincipal` tenía múltiples responsabilidades:
  - Presentación de interfaz gráfica
  - Captura de datos de entrada
  - Validación y construcción de `Participante`
  - Orquestación del registro

#### 3. **Difícil de Testear**
- No se podía testear la lógica de registro sin la UI
- Dependencia fuerte entre UI y modelo

#### 4. **Difícil de Mantener**
- Cambios en la lógica de registro requerían modificar la UI
- Reutilización de lógica de negocio limitada

---

## Luego apliqué un MVC (Modelo-Vista-Controlador)

### La Estructura Refactorizada
```
ejercicio1/
├── controller/
│   └── ParticipanteController.java  ← ✨ Nuevo
├── db/
│   └── JDBCRegistroParticipante.java
├── main/
│   ├── Main.java
│   └── SetUpDatabase.java
├── models/
│   ├── Concurso.java
│   ├── Participante.java
│   └── RegistrarParticipante.java
└── ui/
    └── VentanaPrincipal.java
```

### Flujo de Datos (Versión MVC)
```
Main
  ├─> SetUpDatabase → Inicializa BD
  ├─> JDBCRegistroParticipante → Implementa persistencia
  ├─> Concurso → Modelo de negocio
  ├─> ParticipanteController ← ✨ CONTROLADOR
  │   └─> Encapsula lógica de registro
  └─> VentanaPrincipal(ParticipanteController)  ← Recibe el controlador
      └─> Usuario ingresa datos
          └─> botonCargar.addActionListener() → {
              controller.registrarParticipante(nombre, teléfono, región)
          }
```

### Código de la UI (Después)
```java
public class VentanaPrincipal extends JFrame {
    private ParticipanteController controller;  // ← Recibe el controlador
    
    public VentanaPrincipal(ParticipanteController controller) {
        this.controller = controller;
    }
    
    private void setUpUIComponents() {
        // ...
        botonCargar.addActionListener(e -> {
            try {
                // ← La UI dice (Tell) al controlador qué hacer
                controller.registrarParticipante(
                        nombre.getText(),
                        telefono.getText(),
                        region.getText()
                );
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
```

### ParticipanteController
```java
public class ParticipanteController {
    private final Concurso concurso;
    
    public ParticipanteController(Concurso concurso) {
        this.concurso = concurso;
    }
    
    // ← El controlador encapsula la lógica de negocio
    public void registrarParticipante(String nombre, String telefono, String region) {
        Participante participante = new Participante(nombre, telefono, region);
        concurso.registrarParticipante(participante);
    }
}
```

### Ahora las Mejoras:

#### 1. **Cumple "Tell, Don't Ask"**
- La UI **dice** (Tell) al controlador qué hacer
- La UI pasa solo datos primitivos (strings)
- La UI no construye objetos ni sabe detalles de la lógica
- **Antes:** `concurso.registrarParticipante(new Participante(...))`
- **Después:** `controller.registrarParticipante(...)`

#### 2. **Cumple SRP**
- `VentanaPrincipal`: Solo responsable de capturar datos y presentar la UI
- `ParticipanteController`: Responsable de orquestar la lógica de registro
- `Participante`: Responsable de representar los datos del participante
- `Concurso`: Responsable de la lógica de negocio del concurso
- `JDBCRegistroParticipante`: Responsable de la persistencia

#### 3. **Más Fácil de Testear**
- Se puede testear `ParticipanteController` de forma aislada
- Se puede testear la UI sin la lógica de negocio
- Se pueden mockear dependencias

**Ejemplo de test:**
```java
@Test
public void testRegistrarParticipante() {
    RegistrarParticipante mockRegistry = mock(RegistrarParticipante.class);
    Concurso concurso = new Concurso(mockRegistry);
    ParticipanteController controller = new ParticipanteController(concurso);
    
    controller.registrarParticipante("Juan", "1234-567890", "China");
    
    verify(mockRegistry).guardar(any(Participante.class));
}
```
---

## Diagrama de Dependencias

### Antes (Acoplado)
```
VentanaPrincipal ──────┐
                       ├──> Concurso
JDBCRegistroParticipante ──>│

(La UI accede directamente al modelo de negocio)
```

### Después (Desacoplado con MVC)
```
VentanaPrincipal ──> ParticipanteController ──> Concurso
                                               ↓
                                    JDBCRegistroParticipante

(La UI accede solo al controlador, que orquesta la lógica)
```

---
### Análisis de Dependencias

**Output esperado:**
```
ejercicio1.controller -> ejercicio1.models
ejercicio1.db -> ejercicio1.models
ejercicio1.main -> ejercicio1.controller
ejercicio1.main -> ejercicio1.db
ejercicio1.main -> ejercicio1.models
ejercicio1.main -> ejercicio1.ui
ejercicio1.ui -> ejercicio1.controller
```

El layering es correcto: UI depende de Controller, Controller de Models, sin ciclos.

---

## Mejoras Futuras Posibles:

**Abstraer la Capa de Persistencia**
   - Crear `ParticipanteRepository` para facilitar cambios de BD
   - Implementaciones para PostgreSQL, MongoDB, etc.
