# Nomad's Delight

<!-- 
<a href="link to modrinth">
  <img src="http://cf.way2muchnoise.eu/full_nomads_delight_downloads.svg" alt="Curseforge Downloads"> 
</a>
<br>
-->
<img src="common/src/main/resources/nomads_delight.png">

## Overview

A [Farmer's Delight](https://modrinth.com/mod/farmers-delight) addon that brings the rich, hearty
culinary traditions of the Central Asian steppe nomads to your Minecraft kitchen.

Embrace nomadic life with food built for the road: traditional dishes, ingredients, and storage solutions inspired by
real steppe cuisine, reimagined for survival worlds. Saddle up, fire up the cooking pot, and taste the spirit of the
steppes - As bolsyn!

## Features

- 50+ new traditional and original dishes & ingredients
- 19 new advancements to chase
- Two new functional blocks: **Curd Bag** (Qaltasha), a portable storage pouch, and **Churn** (
  Qubi), a traditional churn used for processing dairy. Milk churns on its own over five
  minutes, or faster if you work the animated plunger yourself with repeated right-clicks
- Recipes and mechanics that hook directly into Farmer's Delight's cooking and farming systems

## Platforms & Dependencies

Nomad's Delight is built from a single shared codebase for two loaders:

| Loader   | Required mods                                                                                                    |
|----------|------------------------------------------------------------------------------------------------------------------|
| NeoForge | [Farmer's Delight](https://www.curseforge.com/minecraft/mc-mods/farmers-delight)                                   |
| Fabric   | [Fabric API](https://modrinth.com/mod/fabric-api), [Farmer's Delight Refabricated](https://modrinth.com/mod/farmers-delight-refabricated) |

[JEI](https://modrinth.com/mod/jei) is optional on both loaders and adds Churning/Straining recipe categories.

## Building from source

- `common` holds all game logic, assets and data; `neoforge` and `fabric` hold loader glue,
  registration and (on NeoForge) the datagen providers.
- `./gradlew build` produces the loader jars in `neoforge/build/libs` and `fabric/build/libs`.
- `./gradlew :neoforge:runClient` / `./gradlew :fabric:runClient` launch a dev client.
- `./gradlew :neoforge:runData` regenerates the shared data into `common/src/generated/resources`.

### Creators

Developed with love and a taste for nomadic life by:

- [DosyaKitarov](https://github.com/DosyaKitarov)
- [ninsent](https://github.com/ninsent)

### Contributing

Thank you for checking out the addon! If you'd like to contribute, report a bug, or suggest a new feature, feel free to
visit our [Issues page](https://github.com/DosyaKitarov/NomadsDelight/issues).

We are open to feedback. If you spot any mistakes or know a better way to balance the meals, feel free to open an issue
or a Pull Request. Any help is appreciated!
