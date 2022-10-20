# AlfaTest

Создан maven-проект со следующей структурой

src/test/java/AlfaTest содержит пакеты:
* tests – тестовая логика;
* PO – классы PageObject (описание элементов страниц и методы работы с ними);
* utilities – конфигурационный файл Android.

src/main/resources содержит скомпилированный apk-файл тестируемого приложения.

> Требуется установить и настроить следующие инструменты:
> - Node & Watchman
> - Java Development Kit (JDK)
> - Android SDK (Настроить переменную окружения ANDROID_HOME)
> - Android SDK Platform
> - Android Virtual Device
> - Android Studio

## Инструкция по установке
### 1. Установить Appium
Для настройки локальной тестовой среды необходимо установить:

- [appium-doctor](https://github.com/appium/appium-doctor) через `npm install appium-doctor -g`
- [Appium](https://github.com/appium/appium) через `npm install appium -g`
- [appium-desktop](https://github.com/appium/appium-desktop). Данную версию можно скачать [здесь](https://github.com/appium/appium-desktop/releases)

Для проверки правильной настройки окружение запустите `appium-doctor --android`

>Если есть предупреждения, то необходимо исправить то, что выделено красным цветом.

### 2. Запустить эмулятор Android Studio

### 3. Откройте проект AlfaTest и обновите следующие DesiredCapabilities:
> Файл находится по следующему пути - “\src\test\java\AlfaTest\utilities\AndroidDriverConfig.java”

- “platformVersion” - версия операционной системы эмулятора;
- “deviceName” - список подключенных устройств можно получить с помощью команды:

```shell
$ adb devices
```

### 4. Запустите Appium с помощью команды:

```shell
$ appium
```

Если установлен Appium Desktop, то запустите приложение и нажмите кнопку «Запустить сервер».
### 5. Перейдите в проект и запустите класс AuthorizationTest в директории "tests"
> Java-класс находится по пути - “src/test/java/AlfaTest/tests/AuthorizationTest.java”
