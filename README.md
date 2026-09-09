# 🔐 Generador de Contraseñas Seguras — Java Swing

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)
![GUI](https://img.shields.io/badge/GUI-Java%20Swing-blue)
![License](https://img.shields.io/badge/License-MIT-green)

Aplicación de escritorio interactiva para la generación de contraseñas criptográficamente seguras desarrollada en Java con interfaz gráfica Swing. 

---

## 🚀 Características Principales

* **Generación Criptográfica Segura:** Utiliza `SecureRandom` para garantizar una alta entropía y aleatoriedad no predecible.
* **Evaluador de Fuerza Multicriterio:** Sistema de puntuación dinámico (0 a 6 puntos) que evalúa longitud, mayúsculas, minúsculas, números y caracteres especiales.
* **Interfaz Limpia y Centrada:** Maquetada mediante la anidación de `BoxLayout` (eje Y) y `FlowLayout`, con márgenes e interlineados controlados (`Box.createVerticalStrut`).
* **Copiado al Portapapeles en 1 Clic:** Integración nativa con la API de sistema `java.awt.datatransfer.Clipboard`.
* **Control Completo de Errores:** Captura de excepciones (`NumberFormatException`, `IllegalArgumentException`) previniendo caídas mediante alertas emergentes informativas (`JOptionPane`).
* **Campos Protegidos:** Caja de resultado configurada como solo lectura (`setEditable(false)`).

---

## 🛠️ Arquitectura del Proyecto

El código aplica el patrón de separación de responsabilidades en tres clases principales:

```text
src/
├── Main.java               # Punto de entrada y gestión del hilo de la interfaz (SwingUtilities)
├── PasswordGenerator.java  # Motor criptográfico, diccionarios y algoritmo de evaluación
└── GeneradorUI.java        # Ventana Swing, maquetación, componentes y gestión de eventos
```

## 📊 Criterios de Evaluación de Fuerza

El evaluador analiza cada clave generada asignando **1 punto** por cada criterio cumplido, alcanzando un máximo de **6 puntos**:

| Criterio | Condición | Puntuación |
| :--- | :--- | :---: |
| **Longitud Mínima** | Longitud $\ge 8$ caracteres | +1 pto |
| **Longitud Óptima** | Longitud $\ge 12$ caracteres | +1 pto |
| **Minúsculas** | Contiene letras minúsculas `[a-z]` | +1 pto |
| **Mayúsculas** | Contiene letras mayúsculas `[A-Z]` | +1 pto |
| **Números** | Contiene dígitos numéricos `[0-9]` | +1 pto |
| **Símbolos** | Contiene caracteres especiales `@#$%&*` | +1 pto |

### Nivel de Seguridad
* 🔴 **0 – 2 puntos:** `DÉBIL`
* 🟠 **3 – 4 puntos:** `MEDIA`
* 🟢 **5 – 6 puntos:** `FUERTE`

---

## 🔧 Requisitos e Instalación

### Requisitos Previos
* **Java Development Kit (JDK):** Versión 17 o superior.
* **Git:** Opcional (para clonar el repositorio).

---

## 📜 Licencia

Este proyecto está distribuido bajo la licencia **MIT**. Puedes usarlo, modificarlo y distribuirlo libremente para fines educativos o personales.

---

## 👤 Autor y Contacto

* **Estudiante de DAM** — *Desarrollo de Aplicaciones Multiplataforma*
* **GitHub:** [@ShinobiDev79](https://github.com/ShinobiDev79)
* **Proyecto:** Práctica de desarrollo e interfaces gráficas con Java Swing.
