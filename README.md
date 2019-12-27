# tools-plus-plus
Minecraft mod to extend vanilla tools subtly

https://cubicoder.github.io/tutorials/1-12-2/tutorials/
https://mcforge.readthedocs.io/en/latest/

Installed OpenJDK Java 8, `brew cask install adoptopenjdk8`, then as I had Java 13 installed `brew cask install java` so removed it as it conflicted `brew cask remove java` with Java 8.

To create the Java package, this is done at the top of the sidebar browser NOT manually by folder as the class isn't registered in the class map (like composer dump autoload class map), so at the top of the sidebar there are Java package containers that you can right click on and create a new package and name it something like com.othyn.mymodname and then create a class within that new package
