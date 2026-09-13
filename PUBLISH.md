# Publish name100-women on JitPack

## 1. Push to GitHub

```powershell
cd e:\AIweb\nameahundred\jitpack-name100-women
git push -u origin main
git tag 0.1.0
git push origin 0.1.0
```

Repository: https://github.com/ilovejackylee/name100-women

GitHub **Homepage** field: `https://nameahundred.com/women/` (single outbound link).

## 2. Trigger JitPack build

Open:

https://jitpack.io/#ilovejackylee/name100-women

Click **Look up** (or refresh). JitPack builds tag `0.1.0` on first request.

## 3. Verify backlink

Project page: https://jitpack.io/#ilovejackylee/name100-women

- GitHub homepage → `https://nameahundred.com/women/`
- `pom.xml` `<url>` → `https://nameahundred.com/women/`

Dependency coordinate:

```
com.github.ilovejackylee:name100-women:0.1.0
```
