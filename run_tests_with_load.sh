#!/bin/bash

sbt -Dperftest.runSmokeTest=false -DrunLocal=true -Dperftest.loadPercentage=20 -Dperftest.labels=journeyFlows gatling:test

