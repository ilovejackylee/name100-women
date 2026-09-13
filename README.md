# name100-women

Terminal companion for the **Name 100 Women** recall challenge on Name a Hundred.

Play the full browser game (live Wikidata checks, shareable score):

**https://nameahundred.com/women/**

## JitPack

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.ilovejackylee:name100-women:0.1.0'
}
```

## CLI

Build locally:

```bash
mvn -q package
java -jar target/name100-women-0.1.0.jar --print-url
```

Offline free-recall practice (timer + your own list — no answer key):

```bash
java -jar target/name100-women-0.1.0.jar
```

Not affiliated with Wikidata or any Twitch streamer.
