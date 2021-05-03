@ECHO OFF
REM changer l'encodage de la console en utf-8 (pour les charactère spéciaux : source : https://superuser.com/questions/269818/change-default-code-page-of-windows-console-to-utf-8
CHCP 1252
CLS
java -classpath .\bin esieaa.jmusic.main.AppMain
pause