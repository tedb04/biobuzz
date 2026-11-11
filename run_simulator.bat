@echo off
REM --- Simulator Launcher for use on Windows.
REM Runs the FTC CI virtual-robot-simulator, using your TeamCode sources.
REM
REM Ensure we run with the bundled Gradle wrapper, from the project root.
echo DEBUG: Checking gradlew.bat
if not exist "gradlew.bat" (
    echo ERROR: Gradle wrapper (gradlew.bat) not found. Please run from the project root.
    pause
    exit /b 1
)

SET SIM_JAR=libs\simulator\virtual-robot-simulator-1.5.0-SNAPSHOT.jar
SET RAW_URL=https://github.com/tedb04/virtual_robot/raw/refs/heads/feature/make_library/repo/com/github/tedb04/virtual_robot_simulator/1.5.0-SNAPSHOT/virtual-robot-simulator-1.5.0-SNAPSHOT.jar

echo DEBUG: Checking SIM_JAR: %SIM_JAR%
if not exist "%SIM_JAR%" (
    echo Simulator JAR not found or is empty. Downloading...
    echo DEBUG: Checking libs\simulator
if not exist "libs\simulator" mkdir "libs\simulator"
    
    echo Downloading using wget...
    wget -O "%SIM_JAR%" "%RAW_URL%"
    if errorlevel 1 (
        echo wget failed, trying curl...
        curl -L -o "%SIM_JAR%" "%RAW_URL%" -H "User-Agent: curl/7.68.0" -H "Accept: */*"
    )
)

echo Building TeamCode module...
call gradlew.bat :TeamCode:assembleDebug
if %errorlevel% neq 0 (
    echo Build failed. Please check for errors.
    pause
    exit /b 1
)

echo Running the simulator task: gradlew.bat runSimulator
call gradlew.bat runSimulator