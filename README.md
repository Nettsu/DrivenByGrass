# DrivenByGrass
Bitwig Studio extension for APC40 mk2. This is a fork of DrivenByMoss by Jürgen Moßgraber, 
I made some changes specific to my Bitwig workflow, which include:
- Changing the ninth column from launching scenes to launching clips of the ninth track
- All faders now go up to 0db, instead of +6db
- The default view is now session view
- Changed colors to match Bitwig more closely, and changed playing clip color to white
- 'Pan' mode is now used to control the first remote control on each track

### Building and Installing the extension

1. Users should download and install the version from
[here](http://netsu.type.pl/files/DrivenByGrass.bwextension).
2. Copy that file in the following location depending on your OS:
- Windows %USERPROFILE%\Documents\Bitwig Studio\Extensions\
- Mac ~/Documents/Bitwig Studio/Extensions/
- Linux ~/Bitwig Studio/Extensions/
3. Start Bitwig Studio and open the Dashboard. Select Settings and Controllers.
4. Select Add controller manually and look for Akai -> APC4BitwigMod (by Netsu).

These directions are for developers to test changes prior to release:
1. Install Maven and dependences, either [from here](https://maven.apache.org/install.html)
or if on Linux, using the distro package manager, e.g. `yum install maven` or
`apt-get install maven`.
2. Run `mvn install` in this repo's root.
3. Follow [installation instructions](https://github.com/git-moss/DrivenByMoss/wiki/Installation)
for further steps.
