# Tools++

Minecraft mod to subtly extend vanilla tools.

## Excellent Resources

- [Forge Docs](https://mcforge.readthedocs.io/en/latest/)
- [Minecraft written mod tutorial](https://cubicoder.github.io/tutorials/1-12-2/tutorials/)
- [Reference repo for the mod tutorial](https://github.com/cubicoder/tutorialmod)
- [Reference repo for multi-block breaking and achievements, SparksHammers](https://github.com/thebrightspark/SparksHammers)
- [Reference repo for ore generation, CompactOres](https://github.com/DoubleNegation/CompactOres)

## Setup

See the [Minecraft written mod tutorial](https://cubicoder.github.io/tutorials/1-12-2/tutorials/), specifically "[Setting Up the Development Environment](https://cubicoder.github.io/tutorials/1-12-2/2018-06-19-setting-up-the-development-environment/)". Or, if that asset goes down, the mirrored asset in the project:

```sh
./docs/C - Setting Up the Development Environment.pdf
```

## Building & Releasing

Edit the following line in `./mods/build.gradle`:

```gradle
version = "1.12.2-X.Y" // This line, update the version
group = "com.othyn.toolsplusplus"
archivesBaseName = "toolsplusplus"
```

Simply run the Gradle build command within the root project directory:

```sh
./mods/gradlew build
```

The mod will then be built to the `/build/libs/` directory as `toolsplusplus-1.12.2-X.Y`.

## Issues

Documentation of the issues encountered during the development process, given that this is my first exploration into Java and Minecraft modding.

### Java

Installed OpenJDK Java 8, `brew cask install adoptopenjdk8`. Then as I had Java 13 installed, `brew cask install java`, so removed it as it conflicted `brew cask remove java` with Java 8.

### IDE (Eclipse) & Java Namespacing

To create the Java package, this is done at the top of the sidebar browser NOT manually by folder as the class isn't registered in the class map (like composer dump autoload class map), so at the top of the sidebar there are Java package containers that you can right click on and create a new package and name it something like com.othyn.mymodname and then create a class within that new package

### Item Assets

Original item model assets found in `~/Library/Application Support/minecraft/versions/1.12.2/1.12.2/assets/minecraft/textures/items` after first unpacking the base `1.12.2.jar` with an unarchiver.
