# Tools++

Minecraft mod to subtly extend vanilla tools.

## Excellent Resources

- [TurtyWurty - 1.16 Modding Series](https://www.youtube.com/playlist?list=PLaevjqy3Xufavi5mWXvWnGmwRylL-QZy7)
- [Jorrit Tyberghein - 1.16 Modding Series](https://www.youtube.com/playlist?list=PLmaTwVFUUXiDRjPTbDmBgJZN4MeQ5uYby)
- [Cy4's Tutorials - 1.16 Modding Series](https://www.youtube.com/playlist?list=PLlbkaeFHn13HQlW5Pb7Gf-xLJoAlfVbNU)
- [TechnoVision - 1.16 Modding Series](https://www.youtube.com/playlist?list=PLDhiRTZ_vnoVKPsdTLORpcRnQ50fRdb-S)

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

## Development

To view reference Minecraft files; assets, classes, etc. see the `Java Dependencies` window in the VSCode explorer sidebar. Then under `mods > Project and External Dependencies > forgeSrc-1.16.5-14.23.5.2847.jar` (or whatever Forge decomiled Minecraft to whilst running the gradle task to decompile it). Then within that Java package, there will be all of the reference structure and data of the game in its OG form. E.g. `<forge>/assets/minecraft` matches the structure of `mods/src/main/resources/assets/toolsplusplus`.

Once you are ready to test in the Minecraft client, simply run the Gradle task `mods > forgegradle > runClient`. Sometimes I've found VSCode bugs out and gets stuck binding the run arrow to a specific gradle task, regardless of which one you are hovering over and when you click it. If it does this, right click on the gradle task and click "Run Task" instead of clicking the rows run button.

## Building & Releasing

Edit the following line in `./mods/build.gradle`:

```gradle
version = "1.16.5-X.Y" // This line, update the version
group = "com.othyn.toolsplusplus"
archivesBaseName = "toolsplusplus"
```

Simply run the Gradle task `mods > build > jar`:

```sh
./mods/gradlew build
```

The mod will then be built to:

```sh
./mods/build/libs/toolsplusplus-1.16.5-X.Y.jar
```

Which can then just be placed into the Minecraft Forge mods directory and away you go!

If you do do a new build, please add it to the `builds` directory in the root of the project:

```sh
./builds/toolsplusplus-1.16.5-X.Y.jar
```

## Issues

Documentation of the issues encountered during the development process, given that this is my first exploration into Java and Minecraft modding.

### Java

Installed OpenJDK Java 8, `brew cask install adoptopenjdk8`. Then as I had Java 13 installed, `brew cask install java`, so removed it as it conflicted `brew cask remove java` with Java 8.

### IDE (Eclipse) & Java Namespacing

To create the Java package, this is done at the top of the sidebar browser NOT manually by folder as the class isn't registered in the class map (like composer dump autoload class map), so at the top of the sidebar there are Java package containers that you can right click on and create a new package and name it something like com.othyn.mymodname and then create a class within that new package

### Item Assets

Original item model assets found in `~/Library/Application Support/minecraft/versions/1.16.5/1.16.5/assets/minecraft/textures/items` after first unpacking the base `1.16.5.jar` with an unarchiver.

I then extract the iron colours from the iron bucket and used a sub-set of those to make a new pallet, which I've bundled with `./mods/src/main/resources/assets/toolsplusplus/textures/items/iron_stick.afpalette`. Then using Affinity Photo's ❤️ pixel tool, got to work transforming the tools into their new handle colours!

## Todo

- The `Area of Effect` logic, the horrible smelling duplicate code, needs abstracting out of the `ItemDiamond*Plus` classes and placing into an abstract class. Although need to think about logistics of that as the base class for each item extends differently. There may be a base shared class, such as `ItemTool` that I can base it off, but then the issue becomes then further extending for the type which is impossible... hmmm...

- Add achievements.
