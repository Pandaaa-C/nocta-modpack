# Nocta Modpack (NeoForge) — Developer Guide

This repo is a **multi-module NeoForge (MC 1.21.11 / Java 21)** workspace for the Nocta modpack, with custom-built mods under `mods/`.

## Modules

- `mods/nocta-core` — shared systems (registries, common utilities, base items/blocks, configs)
- `mods/nocta-world` — datapack-style world generation (JSON) + biome modifiers

## Prerequisites

- **Java 21** (Temurin / Adoptium recommended)
- **Git**
- **Gradle Wrapper** (included in repo)
- IDE: IntelliJ IDEA recommended (Gradle project import)

> Windows users: run commands in **PowerShell** from the repo root.

## Quick start

```bash
git clone <your-repo-url>
cd nocta-modpack
./gradlew --version
```

### Import into IntelliJ

1. **File → Open** → select the repo root folder
2. When prompted, import as a **Gradle** project
3. Let Gradle sync finish
4. If anything looks missing, use **Gradle → Reload All Gradle Projects**

## Run configurations (Gradle)

All commands are run from the repo root.

### Nocta Core

Run client:
```bash
./gradlew :mods:nocta-core:runClient
```

Run server:
```bash
./gradlew :mods:nocta-core:runServer
```

Run data generation:
```bash
./gradlew :mods:nocta-core:runData
```

### Nocta World

Run client (loads `nocta-world` + depends on `nocta-core`):
```bash
./gradlew :mods:nocta-world:runClient
```

Run data generation:
```bash
./gradlew :mods:nocta-world:runData
```

## Worldgen (how it’s wired)

`nocta-world` uses **datapack JSON** for worldgen.

Paths you’ll work with most:

- `mods/nocta-world/src/main/resources/data/nocta_world/worldgen/configured_feature/`
- `mods/nocta-world/src/main/resources/data/nocta_world/worldgen/placed_feature/`
- `mods/nocta-world/src/main/resources/data/neoforge/neoforge/biome_modifier/`

### Important testing note

Worldgen changes only appear in **new chunks**.
- Create a **new world**, or
- Teleport far away to generate new terrain (`/tp 5000 120 5000`)

## Repo layout

```
.
├─ mods/
│  ├─ nocta-core/
│  └─ nocta-world/
├─ gradlew
├─ gradlew.bat
└─ settings.gradle
```

## Common tasks

### Clean + rebuild everything

```bash
./gradlew clean build
```

### Delete run folders (fresh runs)

If you get odd run-state issues, delete module run dirs:

- `mods/nocta-core/run/`
- `mods/nocta-world/run/`

Then rerun `runClient` or `runData`.

### If you hit Gradle / IDE weirdness

- Re-import Gradle project
- Delete `.gradle/` in the repo root (not your global Gradle cache)
- Re-run: `./gradlew --refresh-dependencies`

## Contributing

### Branching

Use a simple, team-friendly flow:

- `main` — stable, playable
- `dev` — integration branch
- `feat/<short-name>` — features (e.g., `feat/ore-gen`, `feat/machines`)
- `fix/<short-name>` — bugfixes (e.g., `fix/datagen-crash`)

### Commit messages

Keep it short and descriptive:
- `core: add basic item registry`
- `world: add overworld feature injection`
- `build: fix gradle module wiring`

### Pull requests

- Target `dev`
- Include a short description + screenshots/log snippets when relevant
- Mention new worldgen JSON locations if you added any

## Coding & style

- Java 21
- Prefer small, focused commits
- Keep modules independent: `nocta-world` depends on `nocta-core`, not the other way around
- Worldgen should live in JSON unless there’s a strong reason to use code

## License

Add your chosen license file at the repo root (e.g., MIT, Apache-2.0) once decided.
