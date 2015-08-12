#!/bin/bash
mvn clean
mvn -Dmaven.test.skip=true install
rm -rf output
mkdir -p output/xiaozhuang
cp target/xiaozhuang.war output/xiaozhuang
cd output/xiaozhuang
unzip xiaozhuang.war
cd ..
mv xiaozhuang/xiaozhuang.war .


