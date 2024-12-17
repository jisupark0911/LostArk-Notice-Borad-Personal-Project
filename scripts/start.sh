#!/bin/bash

ROOT_PATH="/home/ubuntu/spring-github-action"
JAR="$ROOT_PATH/build/libs/LostArkNoticeBoard-0.0.1-SNAPSHOT.jar"  

APP_LOG="$ROOT_PATH/application.log"
ERROR_LOG="$ROOT_PATH/error.log"
START_LOG="$ROOT_PATH/start.log"

NOW=$(date +%c)

echo "[$NOW] Copying $JAR" >> $START_LOG
cp $ROOT_PATH/build/libs/LostArkNoticeBoard-0.0.1-SNAPSHOT.jar $JAR 

echo "[$NOW] Running $JAR" >> $START_LOG
nohup java -jar $JAR > $APP_LOG 2> $ERROR_LOG & 

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] Service PID: $SERVICE_PID" >> $START_LOG
