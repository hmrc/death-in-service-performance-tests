#!/bin/bash

sbt -Dperftest.runSmokeTest=true -DrunLocal=true Gatling/test