#!/bin/bash


echo "starting with config file:"
cat /root/config.yml

echo "with java opts:"
echo $JAVA_OPTS

config=""
if [ -n "$CASSANDRA_ADDR" ]; then
        config="$config -Ddw.cassandraServer=$CASSANDRA_ADDR"
fi

if [ -n "$ES_ADDR" ]; then
        config="$config -Ddw.elasticsearchServer=$ES_ADDR"
fi

echo "with envs:"
echo $config

java $config $JAVA_OPTS -jar /root/app.jar server /root/config.yml
