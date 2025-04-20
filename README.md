# Arkanoid Game in Java

Welcome to **Arkanoid**, a classic brick-breaker game implemented in Java as part of the Object-Oriented Programming (OOP) course at Bar-Ilan University.  
This project demonstrates object-oriented principles, animation, level design, and game mechanics using the `biuoop-1.4.jar` package.

---

## Features

- 3 default levels:
  - Level 1: **DirectHit**
  - Level 2: **WideEasy**
  - Level 3: **Green3**
- Extendable level architecture
- Built with [Apache Ant](https://ant.apache.org/)

---

## Requirements

- Java Development Kit (JDK) 8 or above
- Apache Ant 1.10.12+
- `biuoop-1.4.jar` (included in this repo)

---

## Setup & Installation

### 1. Install Apache Ant

> You can skip this if you already have Ant installed.

- [Download Apache Ant 1.10.12 (ZIP)](https://archive.apache.org/dist/ant/binaries/apache-ant-1.10.12-bin.zip)
- Extract it to a folder (e.g., `C:\Users\User\apache-ant-1.10.12-bin`)
- Set environment variables:

**On Windows:**

- `ANT_HOME` → path to Ant folder (e.g., `C:\Users\User\apache-ant-1.10.12-bin`)
- Add `%ANT_HOME%\bin` to your system `PATH`

### 2. Verify Installation

Open a terminal and run:

```bash
ant -version
```

Expected output:

```
Apache Ant(TM) version 1.10.12 compiled on October 13 2021
```

---

## Running the Game

### Compile the project

Run from the project root:

```bash
ant compile
```

### Run the game with default levels

```bash
ant run
```

### Run the game with specific levels

You may choose the order or include only specific levels:

```bash
ant -Dargs="1 2 3" run
```

### Run Checkstyle (optional)

```bash
ant check
```

This will run Checkstyle on all `.java` files in the `src` folder (recursively)
according to the coding conventions specified at `biuoop.xml`. 

---

### Level Argument Mapping

| Level Name | Number |
|------------|--------|
| DirectHit  | 1      |
| WideEasy   | 2      |
| Green3     | 3      |

---

## Project Structure

```
├── src/                    # Java source files
├── biuoop-1.4.jar          # Provided OOP animation and GUI library
├── build.xml               # Ant build configuration
├── biuoop.xml              # Configuration file specifying coding conventions
├── checkstyle-8.44-all.jar # Checkstyle software
└── README.md               # This file
```

---

## License

This project is for educational purposes and part of the OOP curriculum at Bar-Ilan University.