@echo off
adb disconnect
adb tcpip 5555
echo ��ȴ�����Adb��ɺ󣬰����κμ��Լ���
pause>nul
adb shell ifconfig wlan0 > tmp.txt
for /f "delims=" %%i in ('findStr "\<inet\>" tmp.txt') do (set addr=%%i)
del tmp.txt
:intercept
if "%addr:~0,1%"==" " set "addr=%addr:~1%"&goto intercept
set "addr=%addr:~10,15%"
:intercept1
if "%addr:~-1%"=="B" set "addr=%addr:~0,-1%"&goto intercept1
echo %addr%
adb connect %addr%