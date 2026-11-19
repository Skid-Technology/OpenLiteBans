<p align="center">
  <img src="assets/ss.png" alt="Skid Solutions Banner" width="350" />
  <br>
  <font size="6"><b>OpenLiteBans</b></font>
  <br>
  <font size="3">by Skid Solutions</font>
</p>

---

<p align="center">
  <a href="https://discord.gg/AEwatvnvSf">https://discord.gg/AEwatvnvSf</a>
</p>

<p align="center">
  <img src="assets/jak.jpg" alt="jak" height="150" />
  <img src="assets/hack.jpg" alt="hack" height="150" />
  <img src="assets/deobf2.png" alt="deobf2" height="150" />
  <img src="assets/deobf.jpg" alt="deobf" height="150" />
</p>

---

## About

**Databases:** H2 · MySQL · MariaDB · PostgreSQL
**Platform:** Paper / Spigot 1.21.x (Bukkit)
**Java:** 21

---

## How to build

### Prerequisites
- **JDK 21**

### Build

Run from this folder:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"

.\gradlew.bat clean build
```

Output in `build/libs/`:
- `litebans-2.19.0.jar`

Drop the jar into your server's `plugins/` folder and start the server.
