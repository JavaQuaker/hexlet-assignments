# Custom annotations

Часто в разработке возникает задача проверить, какой метод был вызван и с какими аргументами. Для этого используется логгирование. Чтобы не писать каждый раз логгирование вручную, создадим свою аннотацию, которой можно будет отметить класс. При вызове методов аннотированного класса в консоль будет записываться лог, который содержит имя метода и аргументы, с которым он был вызван.

## Ссылки

* [Интерфейс BeanPostProcessor – позволяет модифицировать бины в процессе создания](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanPostProcessor.html)
* [Класс Class – содержит полезные методы для работы с рефлексией](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/Class.html)
* [Класс Proxy – позволяет создать прокси, который оборачивает исходный класс и позволяет настраивать вызовы методов](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/reflect/Proxy.html)
* [Аннотация @Retention – указывает, как долго аннотации должны храниться](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/annotation/Retention.html)
* [Аннотация @Target – указывает, к чему может быть применена отмеченная аннотация](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/annotation/Target.html)

## src/main/java/exercise/Inspect.java

## Задача

* Создайте аннотацию `Inspect`, которая отмечает класс. Аннотация будет указывать, что при вызове методов отмеченного класса в консоль будет логгироваться имя метода и аргументы, с которыми он был вызван. Аннотация должна быть доступна в рантайме. Аннотация должна принимать один аргумент `level`, который будет настраивать уровень логгирования. Условимся, что параметр может иметь только два значения: "info" и "debug". По умолчанию он равен "debug". Пример использования аннотации:

  ```java
  @Inspect(level = "info")
  class SomeClass {
      // Методы класса
  }
  ```

## src/main/java/exercise/calculator

Для проверки работы аннотации создан простой интерфейс `Calculator` и класс `CalculatorImpl`, который реализует этот интерфейс

## Задача

* Изучите файлы в директории. Обратите внимание, что класс `CalculatorImpl` отмечен созданной аннотацией `@Inspect`.

## src/main/java/exercise/CustomBeanPostProcessor.java

Просто создать аннотацию недостаточно, нужно подсказать фреймворку, как с ней работать. Для этого будем использовать интерфейс `BeanPostProcessor`, который позволяет нам управлять жизненным циклом бина. Для работы нам понадобится рефлексия и прокси – специальный объект, который позволяет оборачивать исходный и управлять вызовом его методов.

## Задача

* Создайте класс `CustomBeanPostProcessor`, который реализует интерфейс `BeanPostProcessor`. Не забудьте отметить класс нужной аннотацией, чтобы Spring создал его экземпляр при старте приложения.

* В классе создайте два метода: `postProcessBeforeInitialization()` и `postProcessAfterInitialization()`. Сигнатуру методов можно посмотреть в [документации](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanPostProcessor.html).

* Метод `postProcessBeforeInitialization()` выполняется перед инициализацией каждого бина. В этом методе нужно проверить, отмечен ли бин аннотацией `@Inspect`. Если отмечен, нужно запомнить этот бин и нужный уровень логгирования.

* Метод `postProcessAfterInitialization()` выполняется после инициализации каждого бина. В этом методе нужно обернуть отмеченные аннотацией `@Inspect` бины в прокси. Вызов методов аннотированного класса должен выводить в консоль лог с нужным уровнем, который содержит имя метода и параметры, с которым метод был вызван. Пример сообщения: "Was called method: sum() with arguments: [3, 4]"

  ```java
  // Класс
  @Inspect(level = "info")
  public class CalculatorImpl implements Calculator {
      // Методы класса
  }

  // Где-то в контроллере
  Calculator calc = context.getBean(Calculator.class);
  System.out.println(calc.sum(5, 4));
  ```

  Вывод лога в консоли:

  ```bash
  # Выводится лог с уровнем INFO
  Was called method: sum() with arguments: [5, 4]
  9
  ```

* Запустите приложение и отправьте несколько GET запросов на адреса */sum* и */mult*. Параметры для сложения и умножения передаются в строке запроса. Например, GET запрос на */sum?a=5&b=7* должен вернуть 12. Убедитесь, что в консоль выводится лог с нужным уровнем. Попробуйте изменить уровень логгирования в аннотации.

## Подсказки

* Чтобы узнать, отмечен ли класс аннотацией, используйте рефлексию.

* Чтобы получить значение параметра, переданного в аннотацию, нужно вызвать у аннотации одноименный метод. Его вызов вернет значение параметра.

* Для работы с прокси изучите документацию и код в директории *Example*

* Чтобы увидеть в консоли логи уровня DEBUG, измените уровень логгирования на DEBUG в файле *application.yml*
