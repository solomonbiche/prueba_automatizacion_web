# Framework Selenium Java 
### Versión Spring Boot Cucumber



## Descripción

---

Este framework recoge pruebas automatizadas **E2E** 

El framework está basado en **Spring Boot**, ya que permite una integración
muy sencilla y potente entre las diferentes partes de un framework. Principalmente
es interesante como permite crear un **WebDriver** por cada **Scenario** y *compartirlo*
entre **PageObjects y sus Steps**.

La librería escogida de pruebas es **Cucumber y JUnit 5 (Jupiter)**, *aunque JUnit solo
se usa de soporte*. Los test se definen en **Features** de Cucumber, y entre estas
dos librerías lo que me pareció interesante es que se pueden ejecutar los
Scenarios de manera paralela. El paralelismo se puede filtrar con **@Tags** en
las features, incluso se pueden crear tags **cerrojos de lectura/lectura-escritura.**

Por último, como ya he adelantado: el framework al margen de librerías sigue el **patrón
Page Objects** que se basa en crear un objeto java por cada servicio/vista dentro
de la web. El PageObject contiene los **WebElement** y los *métodos* sobre ellos.



##Tabla de contenidos

---

1. ##Como instalar y ejecutar el Framework

   - Instalación con maven: Despues de tener el maven instalado,descargar el framework (carpeta src y archivo pom.xml)
   y hacer una ejecución de maven desde la carpeta del proyecto:
   
   >mvn -Dtest="auto.framework.selenium.cucumber.RunCucumberTest" test
   - Instalación con IntellIJ: descargar el framework, instalarle una JDK, **Open(abrir)** el proyecto y ejecutar
   el archivo `RunCucumberTest.java` que está en la ruta `src\test\java\auto\framework\selenium\cucumber\RunCucumberTest.java`
   pulsando botón derecho. IntellIJ tiene maven integrado y es capaz de montarlo todo.

---

2. ##Cómo usar el proyecto
   1. ###Estructura del proyecto
      1. ###Page Objects
      Se ubican en `src\test\java\auto\framework\selenium\cucumber\pages`. Utilizan la clase abstracta `BasePage` la cual
      contiene:
         - ***Autowire* del WebDriver y de otras utilidades**. Este tag hace que reciba el driver según
         el scenario que se esté ejecutando.
         - **Métodos base para las funciones de selenium**: es **obligatorio** usar estos métodos en lugar de
         los `.metodos()` del objeto *WebElement*. Esto es por que interactuan con el WebElement que devuelve
         la función `waitElement()`, que espera a que el elemento se materialice en el **DOM**.
      >Nota: los PageObjects pueden tener Autowired otros PageObject para devolverlos si el contexto es el indicado, ejemplo:
      Si desde `Sidebar` pulsas el botón para hacer *logout* el método en lugar de devolver `Sidebar` devuelve `Logout`.
      Esto permite que en los Steps se aniden los métodos de diferentes PageObjects favoreciendo el `init()` de `PageBase`.
      
      2. ###Steps
      Se ubican en `src\test\java\auto\framework\selenium\cucumber\steps`. Hay un archivo *Step* por cada archivo *Page*
      y contienen los pasos usados en las Features. Es necesario que tengan las tag `@Given, @When, @Then, @And y @But` seguidas
      del paso en formato **Gherkin**. Los pasos `@Then` es necesario que tengan ***Asserts*** ya que semánticamente son los que *compruebna* cosas.
      Eso no quita que otros pasos puedan tenerlo.      

      3. ###Features
      Se ubican en `src\test\java\auto\framework\selenium\cucumber\features`. Se crea un archivo *Feature* por cada unidad semántica que se quiera probar, ejemplo:
      El login, el logout, el módulo X, etc. Se componen de los pasos de los archivos *Step*.
      
      4. ###Resto de archivos
         El resto de archivos son o de configuración o de utilidades y permiten el correcto funcionamiento del proyecto. Hablaré de los más interesantes:
            1. **RunCucumberTest.java**: Es el archivo que al ejecutarse monta el ecosistema de pruebas del proyecto y lanza las Features.
            2. **@ElapsedTime y @TakeScreenshot**: Son dos anotaciones de utilidad que usadas en Steps y PageObjects indica al método
            que mida el tiempo de ejecución en milisegundos o haga una captura de pantalla y la guarde en `src/main/resource/screenshot`. En un futuro se pueden seguir creando
            utilidades de esta índole.
   2. ###Añadir cobertura
      Para añadir cobertura se puede hacer de varias maneras y las comentaré en orden de alto a bajo nivel. ***Importante comentar que los niveles bajos han de estar acompañados de elementos de nivel superior, y pueden reutilizar elementos de nivel inferior***.
      1. ***Añadir Features***: Para añadir features simplemente es necesario añadir un Scenario en un feature existente o crear uno nuevo. Se podrán utilizar los steps creados ya.
      2. ***Añadir Steps***: Para crear Steps se crean métodos en archivos de steps de la ruta nombrada anteriormente o en uno nuevo. Para ser utilizados es necesario referenciarlos en features, si no no tienen uso.
      3. ***Añadir PageObjects***: Para crear un PageObject primero hay que identificar el servicio o vista que se ha añadido en la web y no en este framework.
        Después se crea el archivo java ordenado en la jerarquia de PageObjects. Es necesario que extienda de PageBase y se ponga el nombre del pageobject nuevo entre <>, ejemplo: `PageObjectAux extends Pagebase<PageObjectAux>`. Después es simplemente añadir WebElements y métodos sobre ellos de la misma manera que los PageObjects ya creados. Si El pageobject no tiene Steps que le utilicen y Features que llamen a estos Steps no sirve de mucho.
---
3. ##Créditos
- [spring initializr](https://start.spring.io/)
- [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Boot starter parent](https://www.baeldung.com/spring-boot-starter-parent)
- [Spring annotations](https://www.baeldung.com/tag/spring-annotations)
- [Gherkin reference](https://cucumber.io/docs/gherkin/reference/)
- [Selenium 4.6.0 onwards, WebDriverManager incorporated!](https://www.selenium.dev/blog/2022/selenium-4-6-0-released/)
- [Documentación de formato MARKDOWN para README.md](https://www.markdownguide.org/cheat-sheet/)
- [Guía de creación de un buen documento README](https://www.freecodecamp.org/news/how-to-write-a-good-readme-file/)
