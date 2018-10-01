SHELL := /usr/bin/env bash
.DEFAULT_GOAL := help
.PHONY: rm run

CURRENT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
DB_IMAGE_NAME?=mongo
DB_CONTAINER_NAME?=lesson16_db
APPLICATION_DATABASE?=cqrs

help:
	@echo ''
	@echo 'Makefile for '
	@echo '     make help			show this information'
	@echo '     make run			run $(DB_IMAGE_NAME) image, creates container $(DB_CONTAINER_NAME)'
	@echo '     make sh			run a shell on the container $(DB_CONTAINER_NAME)'
	@echo '     make rm			remove container $(DB_CONTAINER_NAME)'
	@echo '     make start			start the container $(DB_CONTAINER_NAME)'
	@echo '     make stop			stop the container $(DB_CONTAINER_NAME)'

rm:
	docker rm -f $(DB_CONTAINER_NAME)

#-v $(CURRENT_DIR)/../data/db:/data/db
#--logpath /var/log/mongodb/mongo.log
run:
	docker run -d \
		--name $(DB_CONTAINER_NAME) \
		-p 27017:27017 \
		$(DB_IMAGE_NAME)

sh:
	docker exec -it $(DB_CONTAINER_NAME) bash

start:
	docker start $(DB_CONTAINER_NAME)

stop:
	docker stop $(DB_CONTAINER_NAME)
