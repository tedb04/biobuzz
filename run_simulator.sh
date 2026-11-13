#!/bin/bash
#
# Simulator Launcher for use on Linux.
# Runs the FTC CI virtual-robot-simulator, using your TeamCode sources.
#
# Ensure we run with the bundled Gradle wrapper, from the project root.
if [ ! -x "./gradlew" ]; then
    echo "ERROR: Gradle wrapper (gradlew) not found. Please run from the project root."
    exit 1
fi

SIM_JAR="libs/simulator/virtual-robot-simulator-1.5.0-SNAPSHOT.jar"
# The specific URL you confirmed works
RAW_URL="https://github.com/tedb04/virtual_robot/raw/refs/heads/feature/make_library/repo/com/github/tedb04/virtual-robot-simulator/1.5.0-SNAPSHOT/virtual-robot-simulator-1.5.0-SNAPSHOT.jar"

if [ ! -f "$SIM_JAR" ] || [ ! -s "$SIM_JAR" ]; then
    echo "Simulator JAR not found or is empty. Downloading..."
    mkdir -p libs/simulator
    
    # Use curl with the follow redirects flag as suggested
    if curl -LJO "$RAW_URL"; then
        mv virtual-robot-simulator-1.5.0-SNAPSHOT.jar "$SIM_JAR"
        echo "Successfully downloaded simulator JAR using curl -L -O."
    else
        echo "curl failed, trying wget..."
        if ! wget -O "$SIM_JAR" "$RAW_URL"; then
            echo "ERROR: Failed to download the simulator JAR using wget."
            exit 1
        fi
    fi
fi

# Final check: ensure we didn't just download an HTML error page (usually < 500KB)
# The expected JAR is much larger.
FILE_SIZE=$(stat -c%s "$SIM_JAR" 2>/dev/null || stat -f%z "$SIM_JAR" 2>/dev/null || echo 0)
if [ "$FILE_SIZE" -lt 500000 ]; then
    echo "ERROR: Downloaded file is suspiciously small (${FILE_SIZE} bytes). It might be an HTML error page."
    exit 1
fi

# Run the simulator task: ./gradlew runSimulator
export OP_MODE_PACKAGE="org.firstinspires.ftc.teamcode"
./gradlew runSimulator
