#!/usr/bin/env bash
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic users --from-beginning
