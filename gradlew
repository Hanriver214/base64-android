#!/bin/sh

APP_BASE_NAME=${0##*/}
APP_HOME=$( cd "${0%/*}" > /dev/null && pwd -P )

JAVACMD=${JAVA_HOME}/bin/java
if [ ! -x "$JAVACMD" ]; then
    JAVACMD=java
fi

exec "$JAVACMD" \
    -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
    org.gradle.wrapper.GradleWrapperMain \
    "$@"