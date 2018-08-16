#!/bin/bash
set -e

docker run --name tenantsdb -ti -d -p 3306:3306 testdb:1.0