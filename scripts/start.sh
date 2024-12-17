#!/bin/bash

ROOT_PATH="/home/ubuntu/Spring boot/LostArkNoticeBoard"
JAR="$ROOT_PATH/build/libs/LostArkNoticeBoard-0.0.1-SNAPSHOT.jar"  

APP_LOG="$ROOT_PATH/application.log"
ERROR_LOG="$ROOT_PATH/error.log"
START_LOG="$ROOT_PATH/start.log"

NOW=$(date +%c)

echo "[$NOW] $JAR 복사" >> $START_LOG
cp "$ROOT_PATH/build/libs/LostArkNoticeBoard-0.0.1-SNAPSHOT.jar" $JAR 

SERVICE_PID=$(pgrep -f $JAR)
if [ -n "$SERVICE_PID" ]; then
  echo "[$NOW] 기존 서비스 종료 (PID: $SERVICE_PID)" >> $START_LOG
  kill -9 $SERVICE_PID
fi

echo "[$NOW] > $JAR 실행" >> $START_LOG
nohup java -jar $JAR --server.port=8080 --server.address=0.0.0.0 > $APP_LOG 2> $ERROR_LOG &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG
