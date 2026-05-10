#!/bin/bash
# RE-WILDER — Gradle Wrapper Bootstrap Script
# Run this ONCE in Termux after cloning the repo.
# It generates the gradle/wrapper/gradle-wrapper.jar that GitHub Actions needs.
#
# Usage: bash bootstrap_wrapper.sh

set -e

echo "Installing Gradle in Termux..."
pkg install gradle -y

echo "Generating Gradle wrapper..."
gradle wrapper --gradle-version 8.5 --distribution-type bin

echo "Done. gradle/wrapper/gradle-wrapper.jar is now present."
echo "Add it to git:"
echo "  git add gradle/wrapper/gradle-wrapper.jar"
echo "  git commit -m 'Add Gradle wrapper JAR'"
echo "  git push"
